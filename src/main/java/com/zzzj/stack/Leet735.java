package com.zzzj.stack;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-09-13 17:26
 */
public class Leet735 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(asteroidCollision(new int[]{5, 10, -5})));
//        System.out.println(Arrays.toString(asteroidCollision(new int[]{8, -8})));
//        System.out.println(Arrays.toString(asteroidCollision(new int[]{10, 2, -5})));
//
//        System.out.println(Arrays.toString(asteroidCollision(new int[]{-2, -1, 1, 2})));
//
//        System.out.println(Arrays.toString(asteroidCollision(new int[]{5, -10})));
//        System.out.println(Arrays.toString(asteroidCollision(new int[]{-5, 10})));

        System.out.println(Arrays.toString(asteroidCollision(new int[]{-2, 1, -2, -2})));
    }

    // [-2,1,-2,-2]
    public static int[] asteroidCollision(int[] asteroids) {

        int N = asteroids.length;

        int L = 0;

        int R = N - 1;

        List<Integer> list = new ArrayList<>();

        while (L < N && asteroids[L] < 0) {
            list.add(asteroids[L]);
            L++;
        }

        while (R > L && asteroids[R] >= 0) {
            R--;
        }

        LinkedList<Integer> stack = new LinkedList<>();

        while (L <= R) {
            if (asteroids[L] > 0) {
                stack.add(asteroids[L]);
            } else {
                stack.addLast(asteroids[L]);
                while (stack.size() >= 2) {
                    Integer first = stack.removeLast();
                    Integer second = stack.removeLast();
                    if (second < 0) {
                        stack.addLast(second);
                        stack.addLast(first);
                        break;
                    }
                    if (Math.abs(first) == Math.abs(second)) {
                        break;
                    } else if (Math.abs(first) > Math.abs(second)) {
                        stack.addLast(first);
                    } else {
                        stack.addLast(second);
                        break;
                    }
                }
            }
            L++;
        }

        list.addAll(stack);

        R++;
        while (R < N) {
            list.add(asteroids[R]);
            R++;
        }

        return listToArray(list);
    }

    public static int[] listToArray(List<Integer> list) {
        int N = list.size();

        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

}
