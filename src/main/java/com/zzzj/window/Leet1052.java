package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-16 12:08
 */
public class Leet1052 {

    public static void test() {
        final int[] arr = ArrayUtil.generateArray(10, 0, 100);
        final int[] arr2 = ArrayUtil.generateArray(10, 0, 2);
        final int k = LeetUtils.random.nextInt(arr.length / 2);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
        System.out.println(k);

        System.out.println(maxSatisfied(arr, arr2, k));
    }

    public static void main(String[] args) {
        // [4,2,5,7]
        //[0,1,1,1]
        //2
        System.out.println(maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
        System.out.println(maxSatisfied(new int[]{2, 4, 1, 4, 1}, new int[]{1, 0, 1, 0, 1}, 2));
        System.out.println(maxSatisfied(new int[]{4, 2, 5, 7}, new int[]{0, 1, 1, 1}, 2));
    }

    /**
     * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
     * 输出：16
     * 解释：
     * 书店老板在最后 3 分钟保持冷静。
     * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int ans = 0;
        int l = -1;
        int r = 0;

        int window = 0;
        int add = 0;

        while (r < grumpy.length) {
            if (grumpy[r] == 0) {
                ans += customers[r];
            } else {
                if (l == -1) {
                    l = r;
                    // 缩短窗口
                } else {
                    while (r - l + 1 > minutes) {
                        if (grumpy[l] == 1) {
                            window -= customers[l];
                        }
                        l++;
                    }
                }
                window += customers[r];
            }
            add = Math.max(add, window);
            r++;
        }

        return ans + add;
    }


}
