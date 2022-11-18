package com.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: GooGoo
 * @time: 2022/10/18 13:54
 */
public class jsontest {
    public static void main(String[] args) {
        String timestamp = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        int nonce = RandomUtil.randomInt(999999);
        //appID=appID&method=method&nonce=123456&timestamp=20201219141142&org=CKSNS&appSecrect
        //拼接加密
        String signString = "appID=" + "lGwAAAMXD6i/DAQOA" + "&method=" + "updateCarTeam" + "&nonce=" + nonce + "&timestamp=" + timestamp + "&org=" + "TRAILER" + "&" + "TRAILER_TEST";
        String md5 = getMD5(signString);
        JSONObject param = new JSONObject();
        param.put("documentNo","1");
        param.put("documentDate",DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        param.put("billNo","HDD");
        param.put("orgCode","ZX");
        param.put("type","2");
        param.put("transCarTeam","TEST_CODE");

        com.alibaba.fastjson.JSONObject body = new com.alibaba.fastjson.JSONObject();
        body.put("appID", "lGwAAAMXD6i/DAQOA"); //appID
        body.put("reqbody", param.toJSONString()); //数据体
        body.put("timestamp", timestamp); //时间戳
        body.put("nonce", nonce + ""); //签名随机数字
        body.put("sign", md5); //加密后MD5
        body.put("method", "updateCarTeam"); //方法
        body.put("org", "TRAILER"); //对应组织
        System.out.println( body.toJSONString());
        //请求
        try (HttpResponse execute = HttpRequest.post("https://183.6.134.198:29082/api/secint/tms")
                .contentType("application/json;charset=utf-8")
                .body(body.toJSONString())
                .execute())
        {

            System.out.println( execute.body());
            execute.close();
        }
    }

    //生成MD5 32位小写密文
    public static String getMD5(String plainText) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(plainText.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            plainText = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return plainText;
    }

    public static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s.append(b[n % 16]);
            n = n / 16;
        }
        a = s.reverse().toString();
        return a;
    }
}
