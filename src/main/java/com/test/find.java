package com.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FindFile {
    static int fileCount = 0;//文件总数
    static int wrong = 0;//含有中文字符的文件数
    static String regEx = "[\u4e00-\u9fa5]";  //匹配中文正则
    static Pattern pat = Pattern.compile(regEx);
    static FileOutputStream fos = null;
    static OutputStreamWriter osw = null;

    public static void main(String[] args) {
        Scanner sc = null;
        //long a = System.currentTimeMillis();

        try {
            //每次重新执行的时候删除上次写入的文件
            File file = new File("D:\\xxxx.txt");
            file.delete();


            System.out.println("输入文件路径: ");
            //读取输入的路径
//            sc = new Scanner(System.in);
//            String filePath = sc.nextLine();
//            String filePath = "F:\\IdeaProject\\channel-system\\src\\main\\java\\com\\lesso";
//            E:\workspaces\channel-system\src\main\java\com\lesso ;;;E:\workspaces\channel-system\src\main\resources\mapper
            // String filePath ="E:\\workspaces\\channel-system\\src\\main\\java\\com\\lesso";
            // String filePath ="E:\\9_20";
            String filePath = "D:\\projects\\AG_YTHNTK_ILOG_1.0\\ilogWebSystem\\src\\main\\webapp\\wb\\modules\\AG_YTHNTK_ILOG_1.0";
            //打开输出流
            fos = new FileOutputStream(new File("D:\\xxxx.txt"), true);
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

            //refreshFileList("f:\\wk");
            //开始检查文件
            TreeSet setChose = new TreeSet();
            refreshFileList(filePath, setChose);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                osw.close();
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //refreshFileList("E:\\workspace\\test\\ognl\\enhance");

        //System.out.print("运行时间为 ");
        //System.out.println(System.currentTimeMillis() - a);
        //输出检查结果
        System.out.println("处理了" + fileCount + " 个文件" + wrong + "包含的字符已存储到f:\\FileCH.txt文件");
    }

    //递归查找文件
    private static void refreshFileList(String strPath, Set setChose) throws IOException {
        File dir = new File(strPath);
        File[] files = dir.listFiles();

        if (files == null)
            return;
        for (int i = 0; i < files.length; i++) {
            System.out.println("第" + i + "个文件");
            int flag = 0;
            if (files[i].isDirectory()) {
                refreshFileList(files[i].getAbsolutePath(), setChose);
            } else {
                fileCount++;
                String strFileName = files[i].getAbsolutePath().toLowerCase();
                //System.out.println(getFileEncode(files[i].getAbsolutePath())+" ----" +files[i].getName());
                //截取文件格式
                String fileName = strFileName.substring(strFileName.lastIndexOf(".") + 1, strFileName.length());

//                //排除不需要扫描的文件
                if (fileName.equals("rar") || fileName.equals("jpg") || fileName.equals("png") || fileName.equals("jar") || fileName.equals("doc") || fileName.equals("xls") || fileName.equals("gif") || fileName.equals("wmz") || fileName.equals("sql")) {
                    continue;
                }
//                if (!fileName.equals("js")) {
//                    continue;
//                }

                //不知为何  两种方法判断的时候都会吧class文件和jar文件当做是含有中文字符的文件
                //所以此处排除掉这class文件和jar文件不参与判断
                if (!"class".equals(fileName.toLowerCase())) {
                    //开始输入文件流，检查文件
//                    String enCode = getFileEncode(files[i].getAbsolutePath());
//                    if ("void".equals(enCode)) {
//                        enCode = "UTF-8";
//                    }
//                    if ("windows-1252".equals(enCode)) {
//                        enCode = "GBK";
//                    }
                    String enCode = "UTF-8";
                    FileInputStream fis = new FileInputStream(files[i].getAbsolutePath());
                    InputStreamReader in = new InputStreamReader(fis, enCode);
                    BufferedReader br = new BufferedReader(in);
                    //用于记录行数  确定文件哪一行有中文
                    int lineCount = 0;
                    String line = null;
                    //逐行检查文件
                    while ((line = br.readLine()) != null) {
                        //使用正则表达式进行判断
                        lineCount++;

                        //line.trim().indexOf("*")==0 || line.trim().indexOf("/")==0 ||
                        if (line.trim().indexOf("@ApiModelProperty") == 0
                                || line.trim().indexOf("@ApiOperation") == 0 || line.trim().indexOf("@Api") == 0 || line.trim().indexOf("logger.info") == 0
                                || line.trim().indexOf("*") != -1 || line.trim().indexOf("//") != -1 || line.trim().indexOf("@ApiParam") != -1
                                ) {
                            continue;
                        }

//                        if(
//                                line.trim().indexOf("//")!=-1 || !line.trim().contains("content:")
//                        ){
//                            continue;
//                        }

//                        if(
//                                line.trim().indexOf("//")!=-1 || !line.trim().contains("toastSvc.show")   //!line.trim().contains("headerName:")
//                        ){
//                            continue;
//                        }

                        String s = "^[\\u4e00-\\u9fa5]";
                        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
                        Matcher m = p.matcher(line);
                        boolean b = m.find();
                        if (!m.find()) {
                            continue;
                        }

                        String substring = line.trim();

//                        int i1 = line.length() - line.replaceAll("\"", "").length();
//                        if(i1<=2){
//                            continue;
//                        }
//                        if(line.contains("println") || line.contains("log.info") || line.contains("log.error")){
//                            continue;
//                        }
//                        if(!line.contains("+") && !line.contains("getLangName")){
//                            continue;
//                        }
                        // substring = line.trim();
//                        int i1 = line.indexOf("headerName:");
//                        int ii = line.indexOf(",",i1+1);
//                        //int ii = -1;
//                        if(ii==-1){
//                            ii = line.indexOf("\'",i1+1);
//                            if(ii==-1){
//                                ii = line.indexOf("\"",i1+1);
//                                ii = line.indexOf("\"",ii+1);
//                            }else{
//                                ii = line.indexOf("\'",ii+1);
//                            }
//                            substring = line.substring(i1, ii+1);
//                        }else {
//                            substring = line.substring(i1, ii);
//                        }
//
//                        int i2 = substring.indexOf(":");
//                        String substring1 = substring.substring(i2+1);
//                        substring1 =  substring1.replaceAll("\'","").replaceAll("\"","").replaceAll("}","").trim();
//                        substring = substring1;

//                        int i1 = line.indexOf("\'");
//                        int ii = line.indexOf("\"");
//
//                        if(i1==-1){
//                            if(ii ==-1){
//                                continue;
//                            }else {
//                                int ii2 = line.lastIndexOf("\"");
//                                substring = line.substring(ii + 1, ii2);
//                            }
//
//                        }else {
//                            int i2 = line.lastIndexOf("\'");
//                            substring = line.substring(i1 + 1, i2);
//                        }

//                        if(ii == -1){
//                            continue;
//                        }
//                        int ii2 = line.lastIndexOf("\"");
//                        substring = line.substring(ii + 1, ii2);

//                        if(i1 == -1){
//                            continue;
//                        }
//                        int i2 = line.lastIndexOf(")");
//                        substring = line.substring(i1 + 1, i2);
//
//                       String ss = substring.trim().substring(1,substring.length());

                        if (!setChose.add(substring)) continue;
                        int i3 = strFileName.lastIndexOf("\\");
                        osw.write(substring + "===============文件名:" + strFileName.substring(i3 + 1) + "==========================" + lineCount + "\r\n");
                        //osw.write(substring+"\r\n");
                        osw.flush();
                        flag++;

//                        char[] charArray = line.toCharArray();
//                        for (int k = 0; k < charArray.length; k++) {
//                            if ((charArray[k] >= 0x4e00) && (charArray[k] <= 0x9fbb)) {
//                                //每行进行扫描，用正则判断含有中文的行
//                                Pattern p = Pattern.compile("([\u4e00-\u9fa5]+)");
//                                String mv = "";
//                                //正则判断
//                                Matcher m = p.matcher(line);
//                                //遍历含有中文的行。并取出中文
//                                while (m.find()) {
//                                    mv += m.group(0);
//                                }
//                                if(!setChose.add(mv)){
//                                    continue;
//                                }
//                                //将含有中文的文件名称和中文所在行数写入文件夹
//                        osw.write(files[i].getAbsolutePath() + " ------- 第" + lineCount + "行<===>" + mv + "\r\n");
//                                osw.write(mv + "\r\n");
//                                osw.flush();
//                                flag++;
//                                //wrong++;
//                                if (flag != 0) k = charArray.length;
//                            }
//
//                        }
                    }
                    //flag!=0 说明该文件中含有中文
                    if (flag != 0) wrong++;
                    br.close();
                    in.close();
                    fis.close();
                }
            }
        }
        System.out.println(45646);
    }
}