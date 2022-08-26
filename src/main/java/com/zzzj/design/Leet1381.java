package com.zzzj.design;

/**
 * @author zzzj
 * @create 2022-08-24 19:36
 */
public class Leet1381 {


    private static class CustomStack {

        int[] arr;

        int index;

        public CustomStack(int maxSize) {
            arr = new int[maxSize];
        }

        public void push(int x) {
            if (index == arr.length) {
                return;
            }
            arr[index] = x;
            index++;
        }

        public int pop() {
            if (index == 0) {
                return -1;
            }
            int value = arr[index - 1];
            index--;
            return value;
        }

        public void increment(int k, int val) {
            int end = Math.min(k, index);
            for (int i = 0; i < end; i++) {
                arr[i] += val;
            }
        }

    }

}
