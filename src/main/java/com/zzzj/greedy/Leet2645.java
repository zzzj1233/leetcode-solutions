package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-04-26 17:32
 */
public class Leet2645 {

    public static void main(String[] args) {
        int N = 10000;

        for (int i = 0; i < N; i++) {

            int M = 160;

            String str = LeetUtils.randomString(M, "abc");

            if (addMinimum(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println(addMinimum(str));
                System.out.println(right(str));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int addMinimum(String word) {
        int N = word.length();

        int ans = 0;

        for (int i = 0; i < N; i++) {
            char c = word.charAt(i);
            char next = i + 1 == N ? ' ' : word.charAt(i + 1);
            char nextNext = i + 2 >= N ? ' ' : word.charAt(i + 2);

            switch (c) {
                case 'a':
                    // 跳过next
                    if (next == 'b' && nextNext == 'c') {
                        i += 2;
                        continue;
                    }
                    if (next == 'c' || next == 'b') {
                        ans += 1;
                        i++;
                    } else {   // a
                        ans += 2;
                    }
                    break;
                case 'b':
                    ans += 1;
                    if (next == 'c') {
                        i++;
                    } else {
                        ans += 1;
                    }
                    break;
                case 'c':
                    ans += 2;
                    break;
            }

        }

        return ans;
    }

    public static int right(String word) {
        int cnt = 1;
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i + 1) <= word.charAt(i)) cnt++;
        }
        return 3 * cnt - word.length();
    }

}
