package com.test;

import cn.hutool.core.io.FileUtil;
import com.pojo.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/4/14 21:42
 */
public class test2 {
    public static void main(String[] args) {

//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < 15; i++) {
//            list.add("hello"+i);
//        }
//        list.add("hello1");
//        //筛选出前十个元素并转换为list
//        List<String> collect = list.stream().limit(10).collect(Collectors.toList());
//
//        //筛选出 == hello3的元素并转换为list
//        List<String> hello3 = list.stream().filter(String -> String.equals("hello3")).collect(Collectors.toList());


        /*泛型为pojo的筛选
        去重
         */

//        List<Person> personList = new ArrayList<>();
//        personList.add(new Person("wanghai",11));
//        personList.add(new Person("wanghai",13));
//        personList.add(new Person("wanghai",11));
//        List<Person> collect1 = personList.stream().filter(distinctByKey(b -> b.getAge())).collect(Collectors.toList());
//
//        System.out.println(collect1);

//        List<Person> wanghai = personList.stream().filter(Person -> Person.getName().equals("wanghai")).collect(Collectors.toList());
//        System.out.println(wanghai);
//        List<String> collect1 = list.stream().distinct().collect(Collectors.toList());
//        System.out.println(collect1);

//        System.out.println(hello3);


//        Stream.of("one", "two", "three", "four")
//                .filter(e -> e.length() > 3)
//                .peek(e -> System.out.println("Filtered value: " + e))
//                .map(String::toUpperCase)
//                .peek(e -> System.out.println("Mapped value: " + e))
//                .collect(Collectors.toList());

        List<String> list1 = new ArrayList<String>();
        list1.add("aaaa");
        list1.add("bbbb");
        list1.add("aaaa");
        list1.add("bbbb");

        Map<String, Long> collect = list1.stream().distinct().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

//        System.out.println(collect);

        String name = FileUtil.getName("GXIC21090028F01+COSU6305969290\\GXIC21090028F01+COSU6305969290+提单+报关单+海关放行通知书.pdf");
        System.out.println(name);


    }


    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
