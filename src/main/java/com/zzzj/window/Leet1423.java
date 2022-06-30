package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2021-12-19 23:00
 */
public class Leet1423 {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(10, 0, 200);
        int n = LeetUtils.random.nextInt(arr.length);

//        System.out.println(Arrays.toString(arr));
//
//        System.out.println(n);
//
//        System.out.println(maxScore(arr, n));

        System.out.println(maxScore(new int[]{166, 191, 195, 184, 192, 179, 3, 114, 163, 14}, 1));
    }

    // 每次只能拿最左或者最右边的牌,最多可以拿K次
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int[] sum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + cardPoints[i - 1];
        }


        int windowSize = n - k;

        int l = 0;

        int r = windowSize - 1;

        int total = sum[r + 1] - sum[l];

        int ans = 0;

        r++;

        ans = Math.max(ans, sum[n] - total);

        while (r < n) {
            total -= cardPoints[l];
            total += cardPoints[r];
            ans = Math.max(ans, sum[n] - total);
            l++;
            r++;
        }


        return ans;
    }

}
