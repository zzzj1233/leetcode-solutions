package com.zzzj.leet;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Zzzj
 * @create 2022-07-31 19:55
 */
public class Leet729 {


    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(LeetUtils.executeExpression("[\"MyCalendar\",\"book\",\"book\",\"book\",\"book\",\"book\",\"book\",\"book\",\"book\",\"book\",\"book\"]", "[[],[47,50],[33,41],[32,99],[33,42],[25,32],[26,35],[19,25],[3,8],[8,13],[18,27]]", myCalendar));
    }

    private static class MyCalendar {

        TreeMap<Integer, Integer> map;

        public MyCalendar() {
            map = new TreeMap<>();
            map.put(-1, -1);
            map.put((int) 1e9 + 1, (int) 1e9 + 1);
        }

        public boolean book(int start, int end) {
            Integer a = map.ceilingKey(start);//右边
            Integer b = map.floorKey(start);//左边
            if (a < end || map.get(b) > start) {
                return false;
            }
            map.put(start, end);
            return true;
        }


    }

}
