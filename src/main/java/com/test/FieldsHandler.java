package com.test;

import java.util.Arrays;

/**
 * @description:
 * @author: GooGoo
 * @time: 2022/8/12 17:38
 */
public class FieldsHandler {
    public static void main(String[] args) {

        String fields = "WORK_WAY\n" +
                "WORK_TYPE\n" +
                "WORK_REQ\n" +
                "WORK_PLAC\n" +
                "WMS_REC_FEE_UNIT_ID\n" +
                "WMS_REC_COMPUTER_WAY\n" +
                "WMS_PAY_FEE_UNIT_ID\n" +
                "WMS_PAY_COMPUTER_WAY\n" +
                "WMS_FEE_WGT\n" +
                "UPDATED_ON\n" +
                "UPDATED_BY\n" +
                "THREAD\n" +
                "TENANCY_ID\n" +
                "STORE_MONTH\n" +
                "STORE_FLAG\n" +
                "STATUS\n" +
                "SPEC_REQ\n" +
                "SPEC_DATE\n" +
                "REQ_DATE\n" +
                "PACK_SUPPLY\n" +
                "PACK_NUM\n" +
                "ORDER_ID\n" +
                "NOTES\n" +
                "MAN_FLAG\n" +
                "MACH_FLAG\n" +
                "ID\n" +
                "HD_RFEE_WGT\n" +
                "HD_REC_FEE_UNIT_ID\n" +
                "HD_REC_COMPUTER_WAY\n" +
                "FLOW_ID\n" +
                "FLOW_DET_ID\n" +
                "FINISHED_TIME\n" +
                "CREATED_ON\n" +
                "CREATED_BY\n" +
                "CARGO_WGT\n" +
                "CARGO_PIECES\n" +
                "CARGO_ID\n" +
                "BILL_NO\n" +
                "BAG_REQ\n" +
                "BAG_NO\n" +
                "BAG_KIND";
        String[] fieldsList = fields.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String s : fieldsList) {
            sb.append(s).append("=").append("{?").append(s).append("?},\n");
        }
        System.out.println(sb.substring(0,sb.length()-1));

    }

}
