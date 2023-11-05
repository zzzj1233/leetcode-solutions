package com.zzzj.leet;

import com.zzzj.util.StringCopyIterator;

/**
 * @author zzzj
 * @create 2023-09-18 16:43
 */
public class Leet2516 {

    public static void main(String[] args) {

        System.out.println(takeCharacters("cbbab", 1));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int M = 10000;

            int k = LeetUtils.random.nextInt(1000) + 1;
//            int k = 1;

            String str = LeetUtils.randomString(M, "abc");

            StringCopyIterator it = new StringCopyIterator(str);

            if (takeCharacters(it.next(), k) != right(it.next(), k)) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringToLeetCode(it.next()));
                System.out.println(k);
                System.out.println("MyAns = " + takeCharacters(it.next(), k));
                System.out.println("RightAns = " + right(it.next(), k));
                return;
            }

        }

        System.out.println("OK~");


    }

    public static int takeCharacters(String s, int k) {

        int N = s.length();

        int[] windowSum = new int[3];

        int left = 0;

        for (; left < N && !match(windowSum, k); left++)
            windowSum[s.charAt(left) - 'a']++;

        if (!match(windowSum, k))
            return -1;

        int ans = left;

        int right = N - 1;

        left--;

        while (left >= 0) {
            windowSum[s.charAt(left) - 'a']--;

            while (!match(windowSum, k) && right > left) {
                windowSum[s.charAt(right) - 'a']++;
                right--;
            }

            if (match(windowSum, k))
                ans = Math.min(ans, left + (N - right - 1));
            left--;
        }

        return ans;
    }

    public static boolean match(int[] windowSum, int k) {
        return windowSum[0] >= k && windowSum[1] >= k && windowSum[2] >= k;
    }

    public static int right(String s, int k) {
        //滑动窗口
        if (k == 0) {
            return 0;
        }
        int n = s.length();
        int[] counts = new int[3];
        for (int i = 0; i < n; ++i) {
            ++counts[s.charAt(i) - 'a'];
        }
        if (counts[0] < k || counts[1] < k || counts[2] < k) {
            return -1;
        }
        //构建滑窗 ——> [l, r]
        int maxlen = 0, l = 0;
        for (int r = 0; r < n; ++r) {
            //r不断右移扩张窗口
            --counts[s.charAt(r) - 'a'];
            //维护窗口大小，保证窗口外的abc至少都有k个，否则左边界l右移缩小窗口
            while (l < r && counts[0] < k || counts[1] < k || counts[2] < k) {
                ++counts[s.charAt(l) - 'a'];
                ++l;
            }
            //记录最长的窗口长度
            if (counts[0] >= k && counts[1] >= k && counts[2] >= k) {
                maxlen = Math.max(maxlen, r - l + 1);
            }
        }
        return n - maxlen;
    }

}
