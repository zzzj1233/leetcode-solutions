package com.zzzj.leet;

import java.util.TreeSet;

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

        private TreeSet<Integer> startSet = new TreeSet<>();
        private TreeSet<Integer> endSet = new TreeSet<>();

        public MyCalendar() {
        }

        public boolean book(int start, int end) {
            return true;
        }


    }

}
