package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-10-08 21:28
 */
public class Leet1864 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString(5, "01");
            if (minSwaps(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println(minSwaps(str));
                System.out.println(right(str));
                return;
            }
        }
        System.out.println("OK~");
    }

    public static int minSwaps(String s) {

        // 0 = 偶数1
        // 1 = 奇数1
        // 2 = 偶数0
        // 3 = 奇数0
        int[] count = new int[4];

        int N = s.length();

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '1') {
                if (i % 2 == 0) {
                    count[0]++;
                } else {
                    count[1]++;
                }
            } else {
                if (i % 2 == 0) {
                    count[2]++;
                } else {
                    count[3]++;
                }
            }
        }

        // 1. 假设s最终为101010 : 1在奇数上,0在偶数上 , 那么交换的条目为在偶数上的1和在奇数上的0
        int ways1 = count[0] == count[3] ? count[0] : Integer.MAX_VALUE;

        // 2. 假设s最终为010101 : 1在偶数上,0在奇数上 , 那么交换的条目为在奇数上的1和在偶数上的0
        int ways2 = count[1] == count[2] ? count[1] : Integer.MAX_VALUE;

        int ans = Math.min(ways1, ways2);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int right(String s) {
        int n = s.length();
        int count = 0;
        int oneCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                count++;
                if (i % 2 == 0) {
                    oneCount++;
                }
            }
        }
        if (n % 2 == 0) {
            if (count != n / 2) {
                return -1;
            }
            return Math.min(n / 2 - oneCount, oneCount);
        }
        if (count < n / 2 || count > n / 2 + 1) {
            return -1;
        }
        if (count == n / 2) {
            return oneCount;
        } else {
            return n / 2 + 1 - oneCount;
        }
    }

}
