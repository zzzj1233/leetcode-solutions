package com.zzzj.dp;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-05-26 11:03
 */
public class Leet1567 {

    public static void main(String[] args) {

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int K = 5;

            ArrayCopyIterator it = ArrayCopyIterator.fromArray(ArrayUtil.generateArray(K, -5, 10));

            if (getMaxLen(it.next()) != right(it.next())) {
                System.out.println("Error");
                System.out.println("myAns = " + getMaxLen(it.next()));
                System.out.println("rightAns = " + right(it.next()));
                System.out.println(Arrays.toString(it.next()));
                return;
            }

        }

        System.out.println("Ok");

    }

    public static int getMaxLen(int[] nums) {
        int cnt = 0;
        int start = 0;
        int firstNegative = -1;

        int N = nums.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            if (num == 0) {
                cnt = 0;
                start = i + 1;
                firstNegative = -1;
            } else if (num > 0) {

            } else {
                if (firstNegative == -1) {
                    firstNegative = i;
                }
                cnt++;
            }

            if (cnt % 2 == 0) {
                ans = Math.max(ans, i - start + 1);
            } else if (cnt > 0) {
                ans = Math.max(ans, i - firstNegative);
            }

        }

        return ans;
    }

    public static int right(int[] nums) {
        int res = 0;
        int z = 0, f = 0;
        for (int n : nums) {
            if (n == 0) {
                z = 0;  // 正数长度
                f = 0;  // 负数长度
            } else if (n > 0) {
                z++;
                if (f > 0) f++;
                res = Math.max(res, z);
            } else {
                int temp = z;
                z = f;
                f = temp;
                f++;
                if (z > 0) z++;
                res = Math.max(res, z);
            }
        }
        return res;
    }

}
