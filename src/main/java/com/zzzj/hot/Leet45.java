package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-04 11:42
 */
public class Leet45 {

    public static void main(String[] args) {
        int step = jump(new int[]{2, 3, 1, 4, 4});

        System.out.println(step);
    }

    public static int jump(int[] nums) {

        // 2 3 1 4 4

        // step = 步数
        int step = 0;

        // cur = 当前可达到的最大下标
        int cur = 0;

        // 再走一步可达到的最大下标
        int next = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (i > cur) {
                step++;
                cur = next;
            }
            next = Math.max(next, nums[i] + i);
        }

        return step;
    }

}
