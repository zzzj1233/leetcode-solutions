package com.zzzj.stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-01-21 20:02
 */
public class Leet1944 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(canSeePersonsCount(new int[]{10, 6, 8, 5, 11, 9})));
    }

    public static int[] canSeePersonsCount(int[] heights) {

        int N = heights.length;

        LinkedList<Integer> stack = new LinkedList<>();

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            int height = heights[i];

            while (!stack.isEmpty() && height > heights[stack.peekLast()]) {
                Integer last = stack.removeLast();
                ans[last]++;
                if (!stack.isEmpty()) {
                    ans[stack.peekLast()]++;
                }
            }

            stack.add(i);
        }

        if (!stack.isEmpty()){
            stack.removeLast();
        }

        while (!stack.isEmpty()){
            ans[stack.removeLast()]++;
        }

        return ans;
    }

}
