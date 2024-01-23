package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2024-01-05 17:31
 */
public class Leet2311 {

    public static void main(String[] args) {

        System.out.println(longestSubsequence("0", 5));

        System.out.println(longestSubsequence("00101001", 1));

    }

    public static int longestSubsequence(String s, int k) {

        int N = s.length();

        int[] leftCnt = new int[N];

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '1')
                leftCnt[i] = cnt;
            else
                cnt++;
        }

        int ans = cnt;

        for (int i = 0; i < N; i++) {

            if (s.charAt(i) == '1') {

                int value = 1;

                int j = i + 1;

                for (; j < N; j++) {
                    if (s.charAt(j) == '1')
                        value = value << 1 | 1;
                    else
                        value <<= 1;
                    if (value > k)
                        break;
                }

                ans = Math.max(ans, leftCnt[i] + j - i);
            }

        }

        return ans;
    }


}
