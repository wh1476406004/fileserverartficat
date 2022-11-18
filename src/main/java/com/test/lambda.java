package com.test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/4/25 11:35
 */
public class lambda {
    public static void main(String[] args) {

//        ArrayList<Integer> list = new ArrayList<>();
//
//        Collections.addAll(list, 1,2,3,4,5);
//        System.out.println(list);
//        list.removeIf(a -> a.equals(1));
//        System.out.println(list);
        
        String str ="ID, TENANCY_ID, ORDER_ID, ORDER_NO, FEE_UNIT_ID, COIN_KIND_ID, " +
                "                                            MONEY, APPLY_TIME, BANK, BANK_ACCOUNT, ACCOUNT_NO, " +
                "                                            CLIENT_BANK_ACCOUNT, CLIENT_BANK,PAY_ACCOUNT_NAME, ACO_WAY, " +
                "                                            CREATED_BY, CREATED_ON, " +
                "                                            DEL_FLAG, CANCEL_DATE, CANCEL_FLAG, " +
                "                                            CANCEL_MAN, CHECK_MAN, CHECK_TIME, STATUS, " +
                "                                            APPLY_NO, FEE_TYPE, VOUCHER_TYPE, APPLY_TYPE, DEPOSIT_KIND, DEPOSIT_TYPE, " +
                "                                            RECORD_FLAG, " +
                "                                            FEE_KIND, NOTES, BEG_DATE, END_DATE, ASCRIPTION, REQ_TIME, PERIOD_ID, APPLY_MAN, " +
                "                                            BORROW_NUM, TURN_FEE_UNIT_ID, ADPAY_FLAG, WAREHOUSE, PAY_KIND_NAME,APPLY_TIME";
        ArrayList<String> arrayList = Lists.newArrayList(str.split(","));
        HashMultiset<String> multiset = HashMultiset.create(arrayList);
        multiset.elementSet().forEach(s -> System.out.println(s + ":" + multiset.count(s)));

    }
    
    

}
