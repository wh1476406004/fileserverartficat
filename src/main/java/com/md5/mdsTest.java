package com.md5;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Person;

import java.math.BigInteger;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/6/23 14:32
 */
public class mdsTest {
    public static void main(String[] args) {

//        String fys1109 ;
//        for (int i = 0; i <100 ; i++) {
//            fys1109= SecureUtil.md5( i%2==0?"WH123":"wh123");
//            System.out.println(fys1109.length());
//
//        }
//        String sss= "a11079c9ffa923f845480cda68195ae";
//        System.out.println(sss.length());
        //
        
        String sss = "select " +
                "--   f_get_wms_order_fist_bill(W.ORDER_ID) BILL_NO, " +
                "--   f_get_wms_order_sec_bill(W.ORDER_ID) SEC_BILL_NO, " +
                "--   f_get_wms_order_seal_cont(W.ORDER_ID) SEAL_CONTRACT_NO, " +
                "  '' BILL_NO, " +
                "  '' SEC_BILL_NO, " +
                "  '' SEAL_CONTRACT_NO, " +
                "  if(NVL(F.CREATED_TYPE,'0')='1','手动','自动') CREATED_TYPE, " +
                "  if(package='无',null,package) package, " +
                "  if(cargo_model='无',null,cargo_model)cargo_model, " +
                "  if(BATCH_NO= " +
                "  '无', " +
                "  null, " +
                "  BATCH_NO) BATCH_NO, " +
                "  if(ORIGIN_PLACE= " +
                "  '无', " +
                "  null, " +
                "  ORIGIN_PLACE) ORIGIN_PLACE, " +
                "  if(MODEL= " +
                "  '无', " +
                "  null, " +
                "  MODEL)MODEL, " +
                "  if(MATERIAL= " +
                "  '无', " +
                "  null, " +
                "  MATERIAL) MATERIAL, " +
                "  if(cargo_FROM= " +
                "  '无', " +
                "  null, " +
                "  cargo_FROM) cargo_FROM, " +
                "  w.WMS_IN_ORDER_NO, " +
                "  w.NET_WGT, " +
                "  w.NOTES, " +
                "  w.CONFIRM_FLAG, " +
                "  w.REC_COMPUTER_WAY, " +
                "  w.PAY_MONEY, " +
                "  w.PAY_RATE, " +
                "  w.STORE_TYPE, " +
                "  w.WORK_WGT, " +
                "  w.OPER_NAME, " +
                "  w.STORE_DAYS, " +
                "  w.TOOL_NO, " +
                "  w.ORDER_ID, " +
                "  w.ORDER_NO, " +
                "  w.WAREHOUSE, " +
                "  w.STORE_MONTH, " +
                "  w.STORE_ID, " +
                "  w.pkg_id, " +
                "  F.ID, " +
                "  F.ID FEE_DET_ID, " +
                "  F.FEE_ITEM_ID, " +
                "  f.fee_unit_id FEE_UNIT_ID_NO, " +
                "  F.AGREEMENT_ID, " +
                "  F.NODE_ID, " +
                "  F.COIN_KIND_ID, " +
                "  F.FEE_ITEM_UNIT, " +
                "  F.FEE_TYPE_ID, " +
                "--   F.ST_RATE, " +
                "  F.ACTUAL_RATE, " +
                "  F.QUANTITY, " +
                "  F.ST_MONEY, " +
                "  F.ACTUAL_MONEY, " +
                "--   F.ABSTRACT, " +
                "  F.FEE_ON, " +
                "  F.CHECK_MAN, " +
                "  F.ADD_FLAG, " +
                "  F.TAX_MONEY, " +
                "  F.TAX_RATE, " +
                "  F.FEE_KIND, " +
                "  F.FEE_KIND_ID, " +
                "  F.WORK_ID, " +
                "  F.SUM_FEE_ITEM, " +
                "  F.INV_TITLE, " +
                "  F.FIN_FLAG, " +
                "  F.ASCRIPTION, " +
                "  FEE_WAY, " +
                "  FEE_BILL_DATE, " +
                "  FEE_BILL_NO, " +
                "  APPLY_NO, " +
                "  INV_NO, " +
                "  f.ID, " +
                "  w.END_PIECES, " +
                "  w.END_NET_WGT, " +
                "  w.END_VOL, " +
                "  w.END_WGT, " +
                "  w.O_CARGOOWNER_DAYS, " +
                "  w.I_CARGOOWNER_DAYS, " +
                "  w.CARGO_OUT_TIME, " +
                "  w.CARGO_NAME, " +
                "  w.WORK_VOL, " +
                "  w.WMS_OUT_ORDER_NO, " +
                "  ( " +
                "  select " +
                "    F_GET_FEEDET_INVAMOUNT (F.ID) " +
                "  from " +
                "    dual) INVAMOUNT, " +
                "--   ( " +
                "--   select " +
                "--     f_get_feedet_lastfact (F.ID) " +
                "--   from " +
                "--     dual) LASTFACTAMOUNT, " +
                "--   ( " +
                "--   select " +
                "--     f_get_feedet_lastinv (F.ID) " +
                "--   from " +
                "--     dual) LASTINVAMOUNT, " +
                "  ( " +
                "  select " +
                "    F_GET_FEEDET_FACAMOUNT (F.ID) " +
                "  from " +
                "    dual) FACAMOUNT, " +
                "  ( " +
                "  select " +
                "    F_GET_FEEDET_BILLAMOUNT (F.ID) " +
                "  from " +
                "    dual) BILLAMOUNT, " +
                "  ( " +
                "  select " +
                "    f_FEE_ISALLOW_EDIT (F.ID) editable " +
                "  from " +
                "    dual) editable, " +
                "  ( " +
                "  select " +
                "    c_cargo_name " +
                "  from " +
                "    c_cargo " +
                "  where " +
                "    id = w.CARGO_ID) CARGO_ID, " +
                "  ( " +
                "  select " +
                "    CLIENT_NAME " +
                "  from " +
                "    C_CLIENT " +
                "  where " +
                "    ID = w.CLIENT_ID) CLIENT_ID, " +
                "  ( " +
                "  select " +
                "    account_name " +
                "  from " +
                "    TENANCY_ACCOUNT " +
                "  where " +
                "    account_no = F.ACCOUNT_NO) ACCOUNT_NO, " +
                "  ( " +
                "  select " +
                "    display_name " +
                "  from " +
                "    wb_user " +
                "  where " +
                "    user_name = w.CREATED_BY) CREATED_BY, " +
                "  ( " +
                "  select " +
                "    display_name " +
                "  from " +
                "    wb_user " +
                "  where " +
                "    user_name = F.FEE_MAN) FEE_MAN, " +
                "  ( " +
                "  select " +
                "    CLIENT_NAME " +
                "  from " +
                "    C_CLIENT " +
                "  where " +
                "    ID = nvl(f.fee_unit_id, " +
                "    w.FEE_UNIT_ID)) FEE_UNIT_ID, " +
                "    '' FEE_FACT_MAN, " +
                "   '' INV_MAN, " +
                "--   to_char(f_get_feedet_factdate(f.id), " +
                "--   'yyyy-MM-dd HH24:mi') FEE_FACT_DATE, " +
                "--   to_char(f_get_feedet_inv_date(f.id), " +
                "--   'yyyy-MM-dd HH24:mi') INV_DATE, " +
                "  to_char(w.CREATED_ON, " +
                "  'yyyy-MM-dd HH24:mi') CREATED_ON, " +
                "  to_char(F.CHECK_ON, " +
                "  'yyyy-MM-dd HH24:mi') CHECK_ON, " +
                "  to_char(w.CARGO_IN_TIME, " +
                "  'yyyy-MM-dd') CARGO_IN_TIME " +
                "from " +
                "  WMS_STORE_RECORD W " +
                "left join v_FEE_DET F on " +
                "  F.WORK_ID = W.STORE_ID " +
                "left join order_head h on " +
                "   w.order_id = h.id " +
                "where";
        System.out.println(sss);
    }


}
