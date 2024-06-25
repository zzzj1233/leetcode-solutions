package com.zzzj.stack;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2024-06-18 15:23
 */
public class Leet1776 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(getCollisionTimes(LeetUtils.convertInts("[[1,3],[4,1],[7,3],[10,5],[11,2],[13,5],[17,4],[20,1]]"))));

        //        System.out.println(Arrays.toString(getCollisionTimes(LeetUtils.convertInts("[[2,8],[4,6],[20,1]]"))));

//        System.out.println(Arrays.toString(getCollisionTimes(LeetUtils.convertInts("[[2,6],[12,4],[15,1]]"))));

    }

    public static double[] getCollisionTimes(int[][] cars) {

        int N = cars.length;

        double[] ans = new double[N];

        Arrays.fill(ans, -1);

        Double unreachable = -1D;

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = N - 1; i >= 0; i--) {

            while (!stack.isEmpty()) {

                // 1. 永远都追不上的车可以从栈中移除
                if (cars[stack.peekLast()][1] >= cars[i][1]) {
                    stack.removeLast();
                } else if (!unreachable.equals(ans[stack.peekLast()]) && cost(cars, i, stack.peekLast()) > ans[stack.peekLast()]) {
                    stack.removeLast();
                } else {
                    break;
                }

            }

            if (!stack.isEmpty())
                ans[i] = cost(cars, i, stack.peekLast());

            stack.add(i);
        }

        return ans;
    }

    public static double cost(int[][] cars, int x, int y) {
        return ((double) (cars[y][0] - cars[x][0])) / (cars[x][1] - cars[y][1]);
    }

}
