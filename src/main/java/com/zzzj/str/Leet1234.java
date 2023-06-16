package com.zzzj.str;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-06-08 15:51
 */
public class Leet1234 {

    public static void main(String[] args) {

        System.out.println(balancedString("QWWEQREE"));

//        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int num = LeetUtils.random.nextInt(10);

            if (num % 4 != 0) {
                num += 4 - (num % 4);
            }

            String str = LeetUtils.randomString(num, "QWER");

            if (balancedString(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println(balancedString(str));
                System.out.println(right(str));
                return;
            }

        }

        System.out.println("Ok");

    }

    public static int balancedString(String s) {

        int N = s.length();

        int max = N / 4;

        int[] bucket = new int[88];

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            bucket[c]++;
        }

        bucket['Q'] = Math.max(0, bucket['Q'] - max);
        bucket['W'] = Math.max(0, bucket['W'] - max);
        bucket['E'] = Math.max(0, bucket['E'] - max);
        bucket['R'] = Math.max(0, bucket['R'] - max);

        int left = 0;

        int right = 0;

        int ans = Integer.MAX_VALUE;

        while (right < N) {

            while (match(bucket, max)) {
                bucket[s.charAt(left)]++;
                left++;
                ans = Math.min(ans, right - left);
            }

            char c = s.charAt(right);

            if (bucket[c] > 0) {
                bucket[c]--;

            }

            right++;
        }

        return ans;
    }

    public static boolean match(int[] bucket, int max) {
        return bucket['Q'] == 0 && bucket['W'] == 0 && bucket['E'] == 0 && bucket['R'] == 0;
    }

    public static int right(String s) {
        int[] count = new int[26];
        int len = s.length();

        for (int i = 0; i < len; i++) {
            count[s.charAt(i) - 'A']++;
        }

        int left = 0, right = 0;
        int res = len;
        int average = len / 4;

        while (right < len) {
            //滑动窗口里进来一个元素 就把count里的这个值减1
            count[s.charAt(right) - 'A']--;
            //如果四个元素都符合要求 就计算最小值
            while (left < len && count['Q' - 'A'] <= average && count['W' - 'A'] <= average && count['E' - 'A'] <= average && count['R' - 'A'] <= average) {
                res = Math.min(res, right - left + 1);
                //移动左指针 看能不能缩小范围
                count[s.charAt(left) - 'A']++;
                left++;
            }
            right++;
        }

        return res;
    }
}
