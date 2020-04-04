package com.waffle.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yixiaoshuang
 * @date 2020/3/28 18:31
 */
@Data
@EqualsAndHashCode(exclude = "name")
public class Person {

    private String name;

    private String identity;

    public Person(String name, String identity) {
        this.name = name;
        this.identity = identity;
    }
}
