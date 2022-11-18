package com.test;

import com.alibaba.fastjson.JSONObject;
import com.pojo.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/6/4 11:57
 */
public class pojoTest {
    public static void main(String[] args) {
        String str =" {   // 分配信息，可以为空，为空时只生成订单，不生成作业单\n" +
                "        \"supplierName\":\"供应商A\", //供应商名称\n" +
                "        \"plateNumber\":\"粤A22222\",\n" +
                "          \"chargeList\":[{\n" +
                "                \"chargeType\":\"ZL21011806\",\n" +
                "                \"chargeAmount\":456.00\n" +
                "            }]\n" +
                "        \n" +
                "    }";
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
    }
}
