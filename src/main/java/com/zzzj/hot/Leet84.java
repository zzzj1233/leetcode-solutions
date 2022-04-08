package com.zzzj.hot;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-04-07 10:34
 */
public class Leet84 {

    public static void main(String[] args) {
//        System.out.println(largestRectangleArea(new int[]{3, 3, 3}));
        System.out.println(largestRectangleArea(new int[]{2, 4}));
    }

    public static int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();

        int ans = 0;

        // 2,1,5,6,2,3
        stack.addLast(0);

        for (int i = 1; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peekLast()]) {
                Integer lastIndex = stack.removeLast();

                int lastHeight = heights[lastIndex];

                int left = stack.isEmpty() ? -1 : stack.peekLast();

                int right = i;

                ans = Math.max(ans, (right - left - 1) * lastHeight);
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            Integer last = stack.removeLast();
            int right = heights.length;
            int left = stack.isEmpty() ? -1 : stack.peekLast();
            ans = Math.max(ans, (right - left - 1) * heights[last]);
        }

        return ans;
    }

}
