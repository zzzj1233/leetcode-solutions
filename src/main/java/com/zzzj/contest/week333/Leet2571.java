package com.zzzj.contest.week333;

/**
 * @author zzzj
 * @create 2023-08-04 17:03
 */
public class Leet2571 {

    public static void main(String[] args) {

        System.out.println(minOperations(39));

        System.out.println(minOperations(54));

        System.out.println(minOperations(79));

    }

    // 01010100
    public static int minOperations(int n) {

        int right = 31;

        for (; right >= 0; right--) if ((n & (1 << right)) != 0) break;

        int cnt = 0;

        int ans = 0;

        for (int i = 0; i <= right + 1; i++) {

            if ((n & (1 << i)) == 0) {

                if (cnt > 1 && i + 1 <= right && (n & (1 << (i + 1))) != 0) {
                    ans++;
                    cnt = 1;
                } else {
                    ans += Math.min(cnt, 2);
                    cnt = 0;
                }
            } else {
                cnt++;
            }

        }

        return ans;
    }

}
