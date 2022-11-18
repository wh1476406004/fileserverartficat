package com.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/10/30 17:04
 */
public class testFunction {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("123", "1234", "12345");
        Function<String, Integer> lengthFunction = String::length;

        Map<String, Integer> stringIntegerMap = listToMap(list, lengthFunction);
        System.out.println(stringIntegerMap);


    }

    public static <T,R>Map<T,R> listToMap(List<T> list,Function<T,R> function){
        HashMap<T,R> hashMap = new HashMap();
        for (T t : list) {
            hashMap.put(t,function.apply(t));
        }
        return  hashMap;
    }
}
