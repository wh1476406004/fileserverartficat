package com.test;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Person;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/4/27 15:40
 */
public class test3 {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public static void main(String[] args) {
        String appID= "lGwAAAMXD6i/DAQOA";
        String method = "queryPonderation";
        String org = "TRAILER";
        String appSecret = "TRAILER_TEST";

        cn.hutool.json.JSONObject params = JSONUtil.createObj()
                .putOpt("documentNo", IdUtil.fastSimpleUUID())
                .putOpt("documentDate", DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN));
        params.putOpt("billNo","CSVQXYKA0002");
        params.putOpt("ctrNo","CCLU2524734");
        params.putOpt("orgCode","ZX");


        String timestamp = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        int nonce = RandomUtil.randomInt(999999);
        //appID=appID&method=method&nonce=123456&timestamp=20201219141142&org=CKSNS&appSecrect
        //拼接加密
        String signString = "appID=" + appID + "&method=" + method + "&nonce=" + nonce + "&timestamp=" + timestamp + "&org=" + org + "&" + appSecret;
        String md5 = getMD5(signString);
        com.alibaba.fastjson.JSONObject body = new com.alibaba.fastjson.JSONObject();
        body.put("appID", appID); //appID
        body.put("reqbody", params.toString()); //数据体
        body.put("timestamp", timestamp); //时间戳
        body.put("nonce", nonce + ""); //签名随机数字
        body.put("sign", md5); //加密后MD5
        body.put("method", method); //方法
        body.put("org", org); //对应组织
        System.out.println(body.toJSONString());

        //请求
        String body1 = HttpRequest.post("https://183.6.134.198:29082/api/secint/tms")
                .contentType("application/json;charset=utf-8")
                .body(body.toJSONString())
                .timeout(5000)
                .execute().body();
        System.out.println(body1);
    }
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
}
