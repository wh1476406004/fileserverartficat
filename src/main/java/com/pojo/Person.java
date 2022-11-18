package com.pojo;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/4/25 10:57
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JSONType
public class Person {
    private String name;
    private int age;
    private int hight;
}
