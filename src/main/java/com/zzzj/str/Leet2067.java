package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-01-23 22:40
 */
public class Leet2067 {

    public static void main(String[] args) {
        System.out.println(equalCountSubstrings("abcd", 2));
    }

    public static int equalCountSubstrings(String s, int count) {

        int N = s.length();

        int[][] preSum = new int[N + 1][26];

        for (int i = 1; i <= N; i++) {

            for (int j = 0; j < 26; j++) {
                preSum[i][j] = preSum[i - 1][j];
            }

            preSum[i][s.charAt(i - 1) - 'a']++;

        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            // 枚举每一个i作为子串的开始字符

            OUTER:
            for (int j = 1; j <= 26 && j * count + i <= N; j++) {

                int left = i;
                int right = j * count + i;

                for (int k = 0; k < 26; k++) {
                    int c = preSum[right][k] - preSum[left][k];

                    if (c > 0 && c != count) {
                        continue OUTER;
                    }
                }

                ans++;
            }

        }

        return ans;
    }

}
