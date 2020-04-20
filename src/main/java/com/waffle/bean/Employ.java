package com.waffle.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public Employ() {
        System.out.println("employ");
    }

    public Employ(String company, String name, String identity) {
        super(name, identity);
        this.company = company;
    }

    public static void main(String[] args) {
//        Employ e1 = new Employ("c1", "1", "111112");
//        Employ e2 = new Employ("c1", "12", "11111");
//        System.out.println(e1.equals(e2));

        Employ e3 = new Employ();
        Employ e4 = new Employ();

        int a[] = {1, 2, 4};

        List list = Arrays.asList(a);

        System.out.println("e");

    }

    public static long arraysTest(int count) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            List<String> arrays = new ArrayList<String>();
            arrays.addAll(Arrays.asList("hello", "java", "world"));
        }
        return System.currentTimeMillis() - startTime;
    }

    public static long collectionsTest(int count) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            List<String> arrays = new ArrayList<String>();
            Collections.addAll(arrays, "hello", "java", "world");
        }
        return System.currentTimeMillis() - startTime;
    }
}
