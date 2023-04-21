package com.zzzj.review;

/**
 * @author zzzj
 * @create 2023-03-07 18:25
 */
public class Leet3 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {

        if (s.isEmpty()){
            return 0;
        }

        int N = s.length();

        int right = 0;

        int left = 0;

        int[] ct = new int[128];

        int ans = 0;

        while (right < N) {

            char c = s.charAt(right);

            ct[c]++;

            while (ct[c] > 1) { // 缩小窗口
                ct[s.charAt(left)]--;
                left++;
            }

            ans = Math.max(ans, right - left + 1);

            right++;
        }

        return ans;
    }

}
