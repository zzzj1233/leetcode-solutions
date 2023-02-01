package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-01-29 14:53
 */
public class Leet949 {

    public static String largestTimeFromDigits(int[] arr) {

        // 无效时间返回空字符串
        int[] bucket = new int[10];

        for (int i = 0; i < 4; i++) {
            bucket[arr[i]]++;
        }

        // 1. 不能超过23:59, 如果是2开头,那么不能超过3, 能作为小时的只有012
        if (bucket[0] == 0 && bucket[1] == 0 && bucket[2] == 0) {
            return "";
        }

        char[] chars = new char[4];

        boolean[][] hourAllow = new boolean[3][10];

        for (int i = 0; i < 3; i++) {
            Arrays.fill(hourAllow[i], true);
        }

        for (int i = 4; i < 10; i++) {
            hourAllow[2][i] = false;
        }

        for (int i = 3; i >= 0; i--) {
            if (bucket[i] > 0) {

                bucket[i]--;

                boolean[] allow = hourAllow[i];

                boolean find = false;

                for (int j = allow.length - 1; j >= 0; j--) {
                    if (allow[j] && bucket[j] > 0) {
                        bucket[j]--;
                        find = true;
                        break;
                    }
                }

                // 还原
                if (!find) {
                    bucket[i]++;
                } else {
                    // 计算分钟

                }

            }
        }


        return String.valueOf(chars);
    }

}
