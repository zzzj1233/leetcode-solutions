package com.zzzj.interview;

/**
 * @author zzzj
 * @create 2022-06-02 12:18
 */
public class LRProblem {

    // 给定一个字符串,字符串只含有'L'和'R',要么全是L，要么全是R，要么L全在左边,R全在右边，
    // 求最小的操作次数(可以使任意一个字符变成L或者R)

    public static int solution(String str) {

        int N = str.length();

        int[] lPrefix = new int[N];
        int[] rPrefix = new int[N];

        lPrefix[0] = str.charAt(0) == 'L' ? 1 : 0;

        rPrefix[N - 1] = str.charAt(N - 1) == 'R' ? 1 : 0;

        for (int i = 1; i < N; i++) {
            lPrefix[i] = lPrefix[i - 1] + (str.charAt(i) == 'L' ? 1 : 0);
        }

        for (int i = N - 2; i >= 0; i--) {
            rPrefix[i] = rPrefix[i + 1] + (str.charAt(i) == 'R' ? 1 : 0);
        }

        // 假设右边全是R
        int ans = N - rPrefix[0];

        for (int i = 0; i < N - 1; i++) {
            // 假设 0 ~ i 是L, i + 1 ~ N 全是R
            int leftPaddingL = i - lPrefix[i] + 1;
            int rightPaddingR = (N - i + 1) - rPrefix[i + 1];
            ans = Math.min(ans, leftPaddingL + rightPaddingR);
        }

        // 假设左边全是L
        ans = Math.min(ans, N - lPrefix[N - 1]);

        return ans;
    }

}
