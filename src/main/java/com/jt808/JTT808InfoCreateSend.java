package com.jt808;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JTT808InfoCreateSend {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyMMddHHmmss");

    public static double pi = 3.1415926535897932384626;
    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;

    private static final String REGX = "[0-9]{12}";
    private static final String REGXD = "[0-9]{1,12}";
    private static final String REGIP = "[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}";

    private static int SERNUM = 0;

    private static Socket SOCKET = null;


    public static void getSocket(String ip,Integer port){
        try {
            SOCKET = new Socket(ip,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * SOCKET发送数据
     * @param data 数据
     * @param isHex 是否是16进制数据
     */
    public static void sendInfo(String data, boolean isHex){
        try {
            OutputStream out = SOCKET.getOutputStream();
            if (isHex){
                byte[] bytes = hexStrToByteArray(data);
                out.write(bytes);
                out.flush();
            }else {
                out.write(data.getBytes());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /** SOCKET接收数据 */
    public static String getSocketInfo(){
        String res = null;
        try {
            InputStream in = SOCKET.getInputStream();
            byte[] b = new byte[8096];
            int len = in.read(b);
            //res =  new String(b);
            res =  byte2Hex(b,len).toUpperCase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    /** 判断连接是否断开 */
    public static Boolean isServerClose(){
        try{
            SOCKET.sendUrgentData(0xFF);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        }catch(Exception se){
            return true;
        }
    }


    /** 将byte转为16进制 */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


    /** 将byte转为16进制 */
    private static String byte2Hex(byte[] bytes,int len) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp = null;
        for (int i = 0; i < len; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }



    /** 判空 */
    public static boolean isEmpty(String str){
        if (str == null || "".equals(str.trim())) return true;
        return false;
    }

    /** 还原7D7E */
    public static String reduction7D7E(String data){
        StringBuilder buffer = new StringBuilder(data);
        for (int i = 0; i < ((buffer.length() - 4) / 2) - 1; i++) {
            String substring = buffer.substring(i * 2 + 2, i * 2 + 6);
            if (substring.equals("7D01")) {
                buffer.replace(i * 2 + 2, i * 2 + 6, "xx01");
            } else if (substring.equals("7D02")) {
                buffer.replace(i * 2 + 2, i * 2 + 6, "xx02");
            }
        }
        return buffer.toString().replace("xx01", "7D").replace("xx02", "7e");
    }

    /** 转换7D7E */
    public static String convert7D7E(String str){
        StringBuilder res = new StringBuilder("7E");
        int length = str.length();
        String data = str.substring(2, length -2);
        for (int i = 0; i < length-4; i+=2) {
            String s = data.substring(i, i + 2);
            if ("7E".equals(s)){
                res.append("7D02");
            }else if ("7D".equals(s)){
                res.append("7D01");
            }else {
                res.append(s);
            }
        }

        res.append("7E");

        return res.toString();
    }

    public static String convert7D7E2(String data) {
        StringBuilder buffer = new StringBuilder(data);
        for (int i = 0; i < ((buffer.length() - 2) / 2) - 1; i++) {
            String substring = buffer.substring(i * 2 + 2, i * 2 + 4);
            if ("7D".equals(substring) || "7d".equals(substring)) {
                buffer.replace(i * 2 + 2, i * 2 + 4, "x1");
            } else if ("7E".equals(substring) || "7e".equals(substring)) {
                buffer.replace(i * 2 + 2, i * 2 + 4, "x2");
            }
        }
        return buffer.toString().replace("x1", "7D01").replace("x2", "7D02");
    }

    /**
     * 计算返回给GPS的校验码
     */
    public static String checkCode(String data) {
        String str = data.substring(0, 2);

        for (int i = 0; i < ((data.length() - 2) / 2); i++) {
            str = hexXOR(str, data.substring(i * 2 + 2, i * 2 + 4));
        }

        String res = checkLengthTo2(str);

        return res;
    }

    private static String hexXOR(String hex1, String hex2) {
        BigInteger res = new BigInteger(hex1, 16).xor(new BigInteger(hex2, 16));
        return res.toString(16).toUpperCase();
    }


    /** 十六进制转字节 */
    public static byte[] hexStrToByteArray(String hex) {
        int len = hex.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character
                    .digit(hex.charAt(i + 1), 16));
        }
        return b;
    }


    /**
     * * 火星坐标系 (GCJ-02) to 84 * * @param lon * @param lat * @return
     * */
    public static double[] gcj02_To_Gps84(double lat, double lon) {
        double[] gps = transform(lat, lon);
        double lontitude = lon * 2 - gps[1];
        double latitude = lat * 2 - gps[0];
        return new double[] { latitude, lontitude };
    }

    /**
     * 火星坐标系(GCJ-02)转GPS84字符串(lng,lat)
     * */
    public static String[] gcj02To84Str(String Gcj02Lng,String Gcj02Lat) {
        double lon = Double.parseDouble(Gcj02Lng);
        double lat = Double.parseDouble(Gcj02Lat);
        double[] gps = transform(lat, lon);
        double lng0 = lon * 2 - gps[1];
        double lat0 = lat * 2 - gps[0];
        return new String[] {""+lng0,""+lat0};
    }

    public static double[] transform(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new double[] { lat, lon };
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new double[] { mgLat, mgLon };
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }

    /** 是否在国外 */
    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }
    /** 是否在国外(重载) */
    public static boolean outOfChina(String latStr, String lngStr) {
        double lat = Double.parseDouble(latStr);
        double lon = Double.parseDouble(lngStr);

        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }


    /** 十进制数转十六进制数 */
    public static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n = n / 16;
        }
        a = s.reverse().toString();
        return a;
    }

    private static String checkLengthTo2(String s) {
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s.toUpperCase();
    }
    private static String checkLengthTo4(String s) {
        if (s.length() == 1) {
            s = "000" + s;
        } else if (s.length() == 2) {
            s = "00" + s;
        } else if (s.length() == 3) {
            s = "0" + s;
        }
        return s;
    }
    /** 将字符串长度补足8位 */
    private static String checkLengthTo8(String s) {
        while (s.length() < 8) {
            s = "0" + s;
        }
        return s;
    }

    /** 将字符串长度补足12位 */
    private static String checkLengthTo12(String s) {
        while (s.length() < 12) {
            s = "0" + s;
        }
        return s;
    }

    public static String makeWGS84Info(String deviceId0, double lat84, double lng84, int speed0, String time0,
                                           boolean sosCheck,boolean engine,boolean pin1C,boolean pin2C){

            String deviceId = checkLengthTo12(deviceId0);
            String lat = checkLengthTo8(intToHex((int)(lat84 * 1000000)));
            String lng = checkLengthTo8(intToHex((int)(lng84 * 1000000)));
            String speed = checkLengthTo4(intToHex((speed0 * 10)));
            SERNUM++;
            String seNum = checkLengthTo4(intToHex((SERNUM)));//流水号
            String sosStr = "00000000";//SOS
            if (sosCheck){
                sosStr = "00000001";
            }
            String engStr = "000C0002";//发动机开关
            if (engine){
                engStr = "000C0003";
            }
            String pin = "00000000";//高电平
            if (pin1C && pin2C){
                pin ="00000030";
            }
            if (pin1C && !pin2C){//倒车
                pin ="00000020";
            }
            if (!pin1C && pin2C){//制动
                pin ="00000010";
            }

            String body = sosStr+engStr+lat+lng+"0000"+speed+"0000"+time0+"0104000000320202060403020000"+"2504"+pin+"30010E31010A";
            String length = checkLengthTo4(intToHex(body.length()/2));

            return "0200"+length+deviceId+seNum+body;
        }

    public static String makeGCJ02ToWGS84Info(String deviceId0, double latGcj02, double lngGcj02, int speed0, String time0,
                                              boolean sosCheck,boolean engine,boolean pin1C,boolean pin2C){

        double[] coos = gcj02_To_Gps84(latGcj02, lngGcj02);

        double lat0 = coos[0];
        double lng0 = coos[1];

        String deviceId = checkLengthTo12(deviceId0);
        String lat = checkLengthTo8(intToHex((int)(lat0 * 1000000)));
        String lng = checkLengthTo8(intToHex((int)(lng0 * 1000000)));
        String speed = checkLengthTo4(intToHex((speed0 * 10)));
        SERNUM++;
        String seNum = checkLengthTo4(intToHex((SERNUM)));//流水号
        String sosStr = "00000000";//SOS
        if (sosCheck){
            sosStr = "00000001";
        }
        String engStr = "000C0002";//发动机开关
        if (engine){
            engStr = "000C0003";
        }
        String pin = "00000000";//高电平
        if (pin1C && pin2C){
            pin ="00000030";
        }
        if (pin1C && !pin2C){//倒车
            pin ="00000020";
        }
        if (!pin1C && pin2C){//制动
            pin ="00000010";
        }

        String body = sosStr+engStr+lat+lng+"0000"+speed+"0000"+time0+"0104000000320202060403020000"+"2504"+pin+"30010E31010A";
        String length = checkLengthTo4(intToHex(body.length()/2));
        return "0200"+length+deviceId+seNum+body;
    }



    /** 面板内容 */
    private static void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 设备ID标题
        JLabel deviceLab = new JLabel("设备ID:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        deviceLab.setBounds(10,20,200,28);
        deviceLab.setFont(new Font("微软雅黑", Font.PLAIN,18));
        panel.add(deviceLab);

        // 输入设备ID
        final JTextField deviceStr = new JTextField(20);
        deviceStr.setBounds(100,20,300,28);
        deviceStr.setText("123456789111");
        deviceStr.setFont(new Font("微软雅黑", Font.PLAIN,16));
        panel.add(deviceStr);

        // 发动机开关
        JLabel engIneC = new JLabel("发动机开关");
        engIneC.setBounds(440,20,140,28);
        engIneC.setFont(new Font("微软雅黑", Font.BOLD,16));
        engIneC.setForeground(Color.red);
        panel.add(engIneC);

        final JCheckBox engineBox = new JCheckBox();
        engineBox.setBounds(560,20,30,30);
        engineBox.setSize(30,30);
        engineBox.setSelected(true);
        panel.add(engineBox);

        // GPS时间标题
        JLabel gpsLab = new JLabel("GPS时间:");
        gpsLab.setBounds(10,60,200,28);
        gpsLab.setFont(new Font("微软雅黑", Font.PLAIN,18));
        panel.add(gpsLab);

        // 输入GPS时间
        final JTextField gpsStr = new JTextField(20);
        gpsStr.setBounds(100,60,230,28);
        gpsStr.setText(SDF.format(new Date()));
        gpsStr.setFont(new Font("微软雅黑", Font.PLAIN,16));
        panel.add(gpsStr);


        // 刷新
        JButton fluButton = new JButton("f");
        fluButton.setBounds(360, 60, 40, 28);
        fluButton.setFont(new Font("微软雅黑", Font.BOLD,16));
        fluButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gpsStr.setText(SDF.format(new Date()));
            }
        });
        panel.add(fluButton);


        // SOS求救
        JLabel sosC = new JLabel("SOS求救报警");
        sosC.setBounds(440,60,140,28);
        sosC.setFont(new Font("微软雅黑", Font.BOLD,16));
        sosC.setForeground(Color.red);
        panel.add(sosC);

        final JCheckBox sosBox = new JCheckBox();
        sosBox.setBounds(560,60,30,30);
        sosBox.setSize(30,30);
        panel.add(sosBox);


        // 经度标题
        JLabel lngLab = new JLabel("经度:");
        lngLab.setBounds(10,100,200,28);
        lngLab.setFont(new Font("微软雅黑", Font.PLAIN,18));
        panel.add(lngLab);

        // 输入经度
        final JTextField lngStr = new JTextField(20);
        lngStr.setBounds(100,100,300,28);
        lngStr.setText("113.334991");
        lngStr.setFont(new Font("微软雅黑", Font.PLAIN,16));
        panel.add(lngStr);


        // 电平1
        JLabel pin1C = new JLabel("电平1(倒车信号)");
        pin1C.setBounds(440,100,140,28);
        pin1C.setFont(new Font("微软雅黑", Font.BOLD,16));
        pin1C.setForeground(Color.red);
        panel.add(pin1C);

        final JCheckBox pin1Box = new JCheckBox();
        pin1Box.setBounds(560,100,30,30);
        pin1Box.setSize(30,30);
        panel.add(pin1Box);



        // 纬度标题
        JLabel latLab = new JLabel("纬度:");
        latLab.setBounds(10,140,200,28);
        latLab.setFont(new Font("微软雅黑", Font.PLAIN,18));
        panel.add(latLab);

        // 输入纬度
        final JTextField latStr = new JTextField(20);
        latStr.setBounds(100,140,300,28);
        latStr.setText("23.132905");
        latStr.setFont(new Font("微软雅黑", Font.PLAIN,16));
        panel.add(latStr);


        // 电平2
        JLabel pin2C = new JLabel("电平2(制动信号)");
        pin2C.setBounds(440,140,140,28);
        pin2C.setFont(new Font("微软雅黑", Font.BOLD,16));
        pin2C.setForeground(Color.red);
        panel.add(pin2C);

        final JCheckBox pin2Box = new JCheckBox();
        pin2Box.setBounds(560,140,30,30);
        pin2Box.setSize(30,30);
        panel.add(pin2Box);


        // 速度标题
        JLabel speedLab = new JLabel("速度:");
        speedLab.setBounds(10,180,200,28);
        speedLab.setFont(new Font("微软雅黑", Font.PLAIN,18));
        panel.add(speedLab);

        // 输入速度
        final JTextField speedStr = new JTextField(20);
        speedStr.setBounds(100,180,300,28);
        speedStr.setText("80");
        speedStr.setFont(new Font("微软雅黑", Font.PLAIN,16));
        panel.add(speedStr);


        // 是否是GCJ02坐标
        JLabel coorCheck = new JLabel("GCJ02-WGS84");
        coorCheck.setBounds(440,180,140,28);
        coorCheck.setFont(new Font("微软雅黑", Font.BOLD,16));
        coorCheck.setForeground(Color.red);
        panel.add(coorCheck);

        final JCheckBox coorbox = new JCheckBox();
        coorbox.setBounds(560,180,30,30);
        coorbox.setSize(30,30);
        panel.add(coorbox);


        /* 输出结果 */
        final JTextArea  resStr = new JTextArea();
        resStr.setBounds(10,280,585,100);
        resStr.setFont(new Font("微软雅黑", Font.PLAIN,16));
        resStr.setLineWrap(true);//激活自动换行功能
        resStr.setWrapStyleWord(true);//激活断行不断字功能
        panel.add(resStr);


        // GCJ02转换84坐标
        final JTextField coo84 = new JTextField(20);
        coo84.setBounds(10,230,300,30);
        coo84.setText("GCJ02转换84坐标");
        coo84.setFont(new Font("微软雅黑", Font.PLAIN,14));
        coo84.setForeground(Color.GRAY);
        panel.add(coo84);


        // 转换坐标
        JButton covButton = new JButton("转换坐标");
        covButton.setBounds(330, 230, 115, 30);
        covButton.setFont(new Font("微软雅黑", Font.BOLD,20));
        covButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resStr.setText("");

                String lng = lngStr.getText();
                if (isEmpty(lng)) {
                    JOptionPane.showMessageDialog(null, "经度不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String lat = latStr.getText();
                if (isEmpty(lat)) {
                    JOptionPane.showMessageDialog(null, "纬度不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] arr = gcj02To84Str(lng, lat);
                coo84.setText(arr[0]+","+arr[1]);
                coo84.setForeground(Color.BLUE);
            }
        });
        panel.add(covButton);


        // 生成按钮
        JButton createButton = new JButton("生成数据");
        createButton.setBounds(465, 230, 115, 30);
        createButton.setFont(new Font("微软雅黑", Font.BOLD,20));
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                coo84.setText("GCJ02转换84坐标");
                coo84.setForeground(Color.GRAY);

                boolean gcj = coorbox.isSelected();
                boolean engine = engineBox.isSelected();
                boolean sosC = sosBox.isSelected();
                boolean pin1C = pin1Box.isSelected();
                boolean pin2C = pin2Box.isSelected();

                String deviceId = deviceStr.getText();
                if (isEmpty(deviceId)) {
                    JOptionPane.showMessageDialog(null, "设备ID不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!deviceId.matches(REGXD)) {
                    JOptionPane.showMessageDialog(null, "设备ID不能超过12位", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String gpsTime = gpsStr.getText();
                if (isEmpty(gpsTime)) {
                    JOptionPane.showMessageDialog(null, "GPS时间不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!gpsTime.matches(REGX)) {
                    JOptionPane.showMessageDialog(null, "GPS时间格式错误", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String lng = lngStr.getText();
                if (isEmpty(lng)) {
                    JOptionPane.showMessageDialog(null, "经度不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String lat = latStr.getText();
                if (isEmpty(lat)) {
                    JOptionPane.showMessageDialog(null, "纬度不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String speed = speedStr.getText();
                if (isEmpty(speed)) {
                    JOptionPane.showMessageDialog(null, "速度不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String info = "";
                if (gcj){
                    info = makeGCJ02ToWGS84Info(deviceId,Double.parseDouble(lat),Double.parseDouble(lng),Integer.parseInt(speed),
                            gpsTime,sosC,engine,pin1C,pin2C);
                }else {
                    info = makeWGS84Info(deviceId,Double.parseDouble(lat),Double.parseDouble(lng),Integer.parseInt(speed),
                            gpsTime,sosC,engine,pin1C,pin2C);
                }

                String code = checkCode(info);

                //String res = convert7D7E("7E" + info + code + "7E");
                String res = convert7D7E2("7E" + info + code + "7E");

                resStr.setText(res);


                //确认对话框
                //JOptionPane.showConfirmDialog(null, "111", "222", JOptionPane.YES_NO_OPTION);
            }
        });
        panel.add(createButton);




        // IP标题
        JLabel ip = new JLabel("IP:");
        ip.setBounds(10,400,30,28);
        ip.setFont(new Font("微软雅黑", Font.PLAIN,18));
        panel.add(ip);

        // 输入IP
        final JTextField ipInfo = new JTextField(20);
        ipInfo.setBounds(40,400,150,28);
        ipInfo.setText("127.0.0.1");
        ipInfo.setFont(new Font("微软雅黑", Font.PLAIN,16));
        panel.add(ipInfo);

        // 端口标题
        JLabel port = new JLabel("端口:");
        port.setBounds(200,400,40,28);
        port.setFont(new Font("微软雅黑", Font.PLAIN,18));
        panel.add(port);

        // 输入端口
        final JTextField portInfo = new JTextField(20);
        portInfo.setBounds(250,400,60,28);
        portInfo.setText("8888");
        portInfo.setFont(new Font("微软雅黑", Font.PLAIN,16));
        panel.add(portInfo);

        // 连接状态
        final JLabel conStatus = new JLabel("未连接");
        conStatus.setBounds(330,400,60,28);
        conStatus.setFont(new Font("微软雅黑", Font.PLAIN,16));
        conStatus.setForeground(Color.red);
        panel.add(conStatus);


        // 连接按钮
        JButton connButton = new JButton("连接");
        connButton.setBounds(400, 400, 80, 30);
        connButton.setFont(new Font("微软雅黑", Font.BOLD,20));
        connButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ipStr = ipInfo.getText();
                if (isEmpty(ipStr)) {
                    JOptionPane.showMessageDialog(null, "IP不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!ipStr.matches(REGIP)) {
                    JOptionPane.showMessageDialog(null, "IP格式错误", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String portStr = portInfo.getText();
                if (isEmpty(portStr)) {
                    JOptionPane.showMessageDialog(null, "端口不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                getSocket(ipStr,Integer.parseInt(portStr));

                if (SOCKET != null){
                    conStatus.setText("已连接");
                    conStatus.setForeground(Color.GREEN);
                    System.out.println(SOCKET);
                }

            }
        });
        panel.add(connButton);

        // 断开按钮
        JButton desButton = new JButton("断开");
        desButton.setBounds(500, 400, 80, 30);
        desButton.setFont(new Font("微软雅黑", Font.BOLD,20));
        desButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    SOCKET.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                SOCKET = null;
                conStatus.setText("未连接");
                conStatus.setForeground(Color.RED);

            }
        });
        panel.add(desButton);


        /* 发送结果 */
        final JTextArea  sendStr = new JTextArea();
        sendStr.setBounds(10,440,470,80);
        sendStr.setLineWrap(true);//激活自动换行功能
        sendStr.setWrapStyleWord(true);//激活断行不断字功能
        panel.add(sendStr);


        // 发送按钮
        JButton senButton = new JButton("发送");
        senButton.setBounds(500, 440, 80, 80);
        senButton.setFont(new Font("微软雅黑", Font.BOLD,20));
        senButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (SOCKET == null || isServerClose()) {
                    SOCKET = null;
                    conStatus.setText("未连接");
                    conStatus.setForeground(Color.RED);
                    return;
                }

                String msg = resStr.getText();
                if (SOCKET != null && !isEmpty(msg)){
                    sendInfo(msg,true);
                    sendStr.setText("数据发送成功!"+"\n");
                    //sendStr.append(msg+"\n");

                    String res = getSocketInfo();
                    if (!isEmpty(res)){
                        sendStr.append("服务器响应:");
                        sendStr.append(res+"\n");
                    }
                }

            }
        });
        panel.add(senButton);


    }

    /** 创建 JFrame 实例 */
    public static void createJFrame(){
        // 创建 JFrame 实例
        JFrame frame = new JFrame("JTT808数据生成器  -  by:MichLee");
        // Setting the width and height of frame
        frame.setSize(620, 580);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);//在屏幕中居中显示

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        createJFrame();
    }



}

