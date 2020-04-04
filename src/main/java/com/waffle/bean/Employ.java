package com.waffle.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * EqualsAndHashCode默认没有使用父类的属性,要设置callSuper=true,使用父类和子类的属性
 *
 * @author yixiaoshuang
 * @date 2020/3/28 19:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Employ extends Person {

    private String company;

    public Employ(String company, String name, String identity) {
        super(name, identity);
        this.company = company;
    }

    public static void main(String[] args) {
        Employ e1 = new Employ("c1", "1", "111112");
        Employ e2 = new Employ("c1", "12", "11111");
        System.out.println(e1.equals(e2));

    }
}
