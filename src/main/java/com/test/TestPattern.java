package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
    /**
     * @param args
     */
    public static void main(String[] args) {
//        removeDigital("b1a07f37558b36ee879eed2dfcbf6c");
        String str = "j士大夫ava121士大&*^夫搜索";

        str = str.replaceAll("[^\u4e00-\u9fa5a-zA-Z]", "");
        System.out.println(str);
//        removeLetter("b1a07f37558b36ee879eed2dfcbf6c");
//        replaceLetter("b1a07f37558b36ee879eed2dfcbf6c", "-");
    }

    /**
     * 剔除数字 * @param value
     */
    public static void removeDigital(String value) {
        Pattern p = Pattern.compile("[\\d]");
        Matcher matcher = p.matcher(value);
        String result = matcher.replaceAll("");
        System.out.println(result);
    }

    /**
     * 剔除字母 * @param value
     */
    public static void removeLetter(String value) {
        Pattern p = Pattern.compile("[a-zA-z]");
        Matcher matcher = p.matcher(value);
        String result = matcher.replaceAll("");
        System.out.println(result);
    }

    /**
     * 替换 * @param value * @param replacement
     */
    public static void replaceLetter(String value, String replaceParam) {
        Pattern p = Pattern.compile("[a-zA-z]");
        Matcher matcher = p.matcher(value);
        String result = matcher.replaceAll(replaceParam);
        System.out.println(result);
    }



}
