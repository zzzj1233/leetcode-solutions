package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-08-25 12:13
 */
public class Leet838 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString0("RL.", 20);
            if (!pushDominoes(str).equals(right(str))) {
                System.out.println("Error");
                System.out.println(str);
                return;
            }
        }
        System.out.println("Ok");
    }

    public static String pushDominoes(String dominoes) {
        // .L.R...LR..L..
        // LL.RR.LLRRLL..

        int N = dominoes.length();

        StringBuilder ans = new StringBuilder(N);

        int i = 0;

        while (i < N && dominoes.charAt(i) == 'L') {
            ans.append("L");
            i++;
        }

        while (i < N) {
            char c = dominoes.charAt(i);
            if (c == '.') {
                // 找到下一个非.
                int j = i + 1;
                while (j < N && dominoes.charAt(j) == '.') {
                    j++;
                }
                if (j == N) {
                    for (int k = i; k < j; k++) {
                        ans.append('.');
                    }
                    break;
                }
                if (dominoes.charAt(j) == 'L') {
                    while (j < N && dominoes.charAt(j) == 'L') {
                        j++;
                    }
                    for (int k = i; k < j; k++) {
                        ans.append("L");
                    }
                } else { // j == R
                    for (int k = i; k < j; k++) {
                        ans.append(".");
                    }
                }
                i = j;
            } else { // c == R
                // while == R or == . , until L
                int j = i + 1;
                while (j < N && (dominoes.charAt(j) == '.')) {
                    j++;
                }
                if (j == N || dominoes.charAt(j) == 'R') {
                    for (int k = i; k < j; k++) {
                        ans.append("R");
                    }
                    i = j;
                    continue;
                }
                // 碰到了L
                int step = j - i + 1;
                int end;

                if (step % 2 == 0) {
                    end = (j - i) / 2 + 1 + i;
                } else {
                    end = (j - i) / 2 + i;
                }

                for (int k = i; k < end; k++) {
                    ans.append("R");
                }

                if (step % 2 != 0) {
                    ans.append(".");
                    end++;
                }

                // 直到不是L
                while (j < N && dominoes.charAt(j) == 'L') {
                    j++;
                }

                for (int k = end; k < j; k++) {
                    ans.append("L");
                }

                i = j;
            }
        }

        return ans.toString();
    }

    public static String right(String dominoes) {
        int n = dominoes.length();
        char[] array = dominoes.toCharArray();
        for (int i = 0; i < n; i++) {
            if (array[i] == '.') {
                int end = i;
                while (end + 1 < n && array[end + 1] == '.') end++;
                char left = i - 1 > -1 ? array[i - 1] : 'L';
                char right = end + 1 < n ? array[end + 1] : 'R';
                if (left == right) for (int j = i; j <= end; j++) array[j] = left;
                else if (left == 'R' && right == 'L') {
                    int cnt = (end - i + 1) / 2;
                    for (int j = 0; j < cnt; j++) {
                        array[i + j] = 'R';
                        array[end - j] = 'L';
                    }
                }
                i = end;
            }
        }
        return new String(array);
    }

}
