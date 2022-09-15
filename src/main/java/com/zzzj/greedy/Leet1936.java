package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-09 17:24
 */
public class Leet1936 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            final int[] arr = ArrayUtil.stepUpArray(100, 1, 10);

            int dist = LeetUtils.random.nextInt(20) + 1;

            if (addRungs(arr, dist) != right(arr, dist)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    // [4,8,12,16]
    // 3

    // 4
    public static int addRungs(int[] rungs, int dist) {
        int ans = 0;

        int start = 0;

        for (int i = 0; i < rungs.length; i++) {
            int sub = rungs[i] - start;
            if (sub <= dist) {
                start = rungs[i];
                continue;
            }
            int ceil = (int) Math.ceil((sub - dist) / (double) dist);
            ans += ceil;
            start = Math.max((ceil * dist + start), rungs[i]);
        }

        return ans;
    }


    public static int right(int[] rungs, int dist) {
        int n = rungs.length;
        int prev = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (rungs[i] - prev > dist) {
                res += (rungs[i] - prev) % dist == 0 ? (rungs[i] - prev) / dist - 1 : (rungs[i] - prev) / dist;
            }
            prev = rungs[i];
        }
        return res;
    }

}