package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-12-17 17:20
 */
public class Leet42 {

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    // 2,1,0,1,3
    // 2
    public static int trap(int[] walls) {
        if (walls == null || walls.length <= 2) {
            return 0;
        }

        int ans = 0;

        LinkedList<Integer> stack = new LinkedList<>();

        stack.add(0);

        for (int i = 1; i < walls.length; i++) {
            int height = walls[i];

            while (!stack.isEmpty() && walls[stack.peekLast()] < height) {
                Integer last = stack.removeLast();
                // 只有凹陷没有左墙
                if (stack.isEmpty()) {
                    break;
                }

                // calc answer
            }

        }

        return ans;
    }

}
