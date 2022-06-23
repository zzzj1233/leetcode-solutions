package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-06-23 17:28
 */
public class Leet1762 {

    public static int[] findBuildings(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] >= heights[stack.peekLast()]) {
                stack.removeLast();
            }
            stack.add(i);
        }

        final int size = stack.size();

        int[] ans = new int[size];

        for (int i = 0; i < size; i++) {
            ans[i] = heights[stack.removeFirst()];
        }

        return ans;
    }

}
