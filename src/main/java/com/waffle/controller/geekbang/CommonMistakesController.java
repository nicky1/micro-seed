package com.waffle.controller.geekbang;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yixiaoshuang
 * @date 2020/3/28 15:19
 */
@RestController
@Slf4j
public class CommonMistakesController {

    List<String> list = Lists.newArrayList();

    @GetMapping(value = "/api/gk/common/internperformance")
    public ResponseEntity internperformance(@RequestParam int size) {
        //-XX:+PrintStringTableStatistics 
        // -XX:StringTableSize=10000000
        StopWatch sw = new StopWatch();
        sw.start();
        list = IntStream.rangeClosed(1, size).mapToObj(i -> String.valueOf(i).intern()).collect(Collectors.toList());
        sw.stop();

        log.info(sw.prettyPrint());


        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/api/gk/common/string/intern")
    public ResponseEntity testIntern() {
        String s = new String("123");
        String s4 = "123";

        log.info((s == s4) + "");

        String s1 = new String("223") + new String("1");
        String s2 = "2231";
        s1.intern();
        log.info("23,{}", s1 == s2);

        // jdk7中，字符串常量池 是在heap中，
        // intern方法,如果存在堆中的对象，会直接保存对象的引用，而不是创建新对象。
        // 但如果超过了常量池1009的长度，性能急剧下降。所以在使用的时候，要考虑数据量

        // EqualsAndHashCode默认没有使用父类的属性,要设置callSuper=true,使用父类和子类的属性
        // 对象的类加载器也要保持一致。
        return ResponseEntity.ok().build();
    }

    public static void main(String[] args) {
//        String s1 = new String("12341") + new String("1");
//        s1.intern();
//        String s2 = "123411";
//        log.info("22,{}", s1 == s2);

        // 数值精度问题
        BigDecimal b1 = new BigDecimal("1.0").stripTrailingZeros();
        BigDecimal b2 = new BigDecimal("1");
        HashSet<BigDecimal> hashSet = Sets.newHashSet();
        hashSet.add(b2);
        System.out.println(hashSet.contains(b1));

    }
}
