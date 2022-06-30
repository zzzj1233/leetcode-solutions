package com.zzzj.leet;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-06-27 14:58
 */
public class Leet2231 {

    public static void main(String[] args) {
        System.out.println(largestInteger(1234));
    }

    public static int largestInteger(int num) {
        PriorityQueue<Integer> queue1 = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> queue2 = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int temp = num;

        while (num != 0) {
            int i = num % 10;
            if (i % 2 == 0) {
                queue1.add(i);
            } else {
                queue2.add(i);
            }
            num /= 10;
        }

        String numStr = String.valueOf(temp);

        StringBuilder builder = new StringBuilder(numStr.length());

        for (int i = 0; i < numStr.length(); i++) {
            if (Character.digit(numStr.charAt(i), 10) % 2 == 0) {
                builder.append(queue1.remove());
            } else {
                builder.append(queue2.remove());
            }
        }

        return Integer.parseInt(builder.toString());
    }

}
