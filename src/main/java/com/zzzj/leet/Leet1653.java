package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-08-25 11:55
 */
public class Leet1653 {

    public static void main(String[] args) {
        System.out.println(minimumDeletions("bb"));
    }

    public static int minimumDeletions(String s) {

        // 左边全是a
        // 右边全是b
        int N = s.length();

        if (N < 2) {
            return 0;
        }

        int ans = N;

        // 每个位置前面有多少个B,后面有多少个A
        int[] left = new int[N + 1];
        int[] right = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            left[i] = left[i - 1] + (s.charAt(i - 1) == 'b' ? 1 : 0);
        }

        for (int i = N - 1; i >= 0; i--) {
            right[i] = right[i + 1] + (s.charAt(i) == 'a' ? 1 : 0);
        }

        for (int i = 1; i < N; i++) {
            if (s.charAt(i) == 'a') {
                ans = Math.min(ans, left[i] + right[i + 1]);
            } else {
                ans = Math.min(ans, left[i - 1] + right[i]);
            }
        }

        return ans;
    }

}
