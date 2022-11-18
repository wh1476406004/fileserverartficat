package com.guavaTest;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: GooGoo
 * @time: 2022/2/10 8:57
 */
public class GuavaTest {
    public static void main(String[] args) {
//        checkNotNull("wocao");
//        GuavaList();
        ListTest();
    }

    public static void  checkNotNull(String param){
        String s = Preconditions.checkNotNull(param);
        System.out.println(s);
    }

    /**
     * 创建一个不能删除、不能修改、不能增加元素的集合
     */
    public  static void GuavaList(){
        // 创建方式1：of
//        ImmutableSet<String> immutableSet = ImmutableSet.of("1", "2", "3", "4", "5");
//        immutableSet.forEach(System.out::println);
        // 创建方式2：builder
        ImmutableSet.<String>builder().add("hello").add("1").build().forEach(System.out::println);


    }

    /**
     * 集合交集并集差集
     */
    public static void ListTest(){
        Set<String> newHashSet1 = Sets.newHashSet("a", "a", "b", "c");
        Set<String> newHashSet2 = Sets.newHashSet("b", "b", "c", "d");
        // 交集
        Sets.SetView<String> intersectionSet = Sets.intersection(newHashSet1, newHashSet2);
        System.out.println(intersectionSet);
        // 并集
        Sets.SetView<String> unionSet = Sets.union(newHashSet1, newHashSet2);
        System.out.println(unionSet); // [a, b, c, d]

        // newHashSet1 中存在，newHashSet2 中不存在
        Sets.SetView<String> setView = Sets.difference(newHashSet1, newHashSet2);
        System.out.println(setView); // [a]
        HashSet<String> strings = Sets.newHashSet(Lists.newArrayList("1", "2"));
        System.out.println(strings);
    }



}
