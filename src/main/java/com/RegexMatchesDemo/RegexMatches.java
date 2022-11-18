package com.RegexMatchesDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/9/2 8:58
 */
public class RegexMatches {
    public static void main(String[] args) {
        String line = "google runoob taobao";
        String regex = "[aeiou]";
        boolean matches = regex.matches(line);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        String s = matcher.replaceAll(line);
        System.out.println(s);

    }
}
