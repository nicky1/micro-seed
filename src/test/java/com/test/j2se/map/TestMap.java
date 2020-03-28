package com.test.j2se.map;

import com.google.common.collect.Maps;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yixiaoshuang on 2018/1/8.
 */
@Slf4j
public class TestMap extends TestCase {

    public void test1() {
        Map<String, String> map = Maps.newHashMap();
        Map map2 = new HashMap();
        System.out.println(1 << 30);

        String val = map.compute("a", (k, v) -> "b");
        log.info(map.get("a") + ";" + val);

        String val2 = map.compute("a", (k, v) -> null);
        log.info(map.get("a") + ";" + val2);

        String v2 = map.putIfAbsent("b", "b22");
        System.out.println(v2);

        String v3 = map.putIfAbsent("b", "b3");
        System.out.println(v3);
        System.out.println(map.get("b"));

        
    }
}
