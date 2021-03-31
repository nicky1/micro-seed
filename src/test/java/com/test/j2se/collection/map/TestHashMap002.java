package com.test.j2se.collection.map;

/**
 * 自定义实现Map功能.底层实现：数组+链表
 * 1.提高查询get速度
 *
 * @author Nick
 */
public class TestHashMap002 {


    SxtEntry[] arr = new SxtEntry[100];

    int size;

    public void put(Object key, Object value) {
        SxtEntry entry = new SxtEntry(key, value);
        for (int i = 0; i < size; i++) {
            if (arr[i].key.equals(key)) {
                arr[i].value = value;
                return;
            }
        }
        arr[size++] = entry;
    }


    public static void main(String[] args) {

        TestHashMap002 map = new TestHashMap002();
        map.put("11", "aa");
        map.put("11", "bb");

        System.out.println(map.size);
    }

    static class SxtEntry {
        private Object key;
        private Object value;

        public SxtEntry(Object key, Object value) {
            super();
            this.key = key;
            this.value = value;
        }

    }

}
