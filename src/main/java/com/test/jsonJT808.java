package com.test;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * @description:
 * @author: GooGoo
 * @time: 2022/11/8 11:42
 */
public class jsonJT808 {
    public static void main(String[] args) {
        String jsonStr = "{\n" +
                "    \"altitude\": 0,\n" +
                "    \"attributes\": {\n" +
                "        1: 50,\n" +
                "        2: 1540,\n" +
                "        3: 0,\n" +
                "        37: 0,\n" +
                "        48: 14,\n" +
                "        49: 10\n" +
                "    },\n" +
                "    \"bodyLength\": 54,\n" +
                "    \"clientId\": \"13050985558\",\n" +
                "    \"dateTime\": \"221108113219\",\n" +
                "    \"direction\": 0,\n" +
                "    \"encryption\": 0,\n" +
                "    \"latitude\": 387198,\n" +
                "    \"longitude\": 11752679,\n" +
                "    \"messageId\": 512,\n" +
                "    \"properties\": 54,\n" +
                "    \"protocolVersion\": 0,\n" +
                "    \"reserved\": false,\n" +
                "    \"serialNo\": 1,\n" +
                "    \"speed\": 0,\n" +
                "    \"statusBit\": 786435,\n" +
                "    \"subpackage\": false,\n" +
                "    \"verified\": true,\n" +
                "    \"version\": false,\n" +
                "    \"warnBit\": 0\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        jsonObject.put("clientId","13050985558");
        jsonObject.put("longitude","117.52679".replace(".",""));
        jsonObject.put("latitude","38.7198".replace(".",""));
        jsonObject.put("dateTime", DateUtil.format(new Date(),"yyMMddHHmmss"));
        System.out.println(jsonObject);
    }
}
