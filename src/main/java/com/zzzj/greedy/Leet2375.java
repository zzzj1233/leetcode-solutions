package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.StringCopyIterator;

/**
 * @author zzzj
 * @create 2022-10-10 10:47
 */
public class Leet2375 {

    // 123456
    // DIDDI
    // 2143
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString(8, "DI");

            StringCopyIterator it = new StringCopyIterator(str);

            if (!smallestNumber(it.next()).equals(right(it.next()))) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringToLeetCode(it.next()));
                System.out.println("MyAns = " + smallestNumber(it.next()));
                System.out.println("RightAns = " + right(it.next()));
                return;
            }

        }
    }

    // 12345678
    // DDDIDDD
    // 43218765

    // DDDII
    // 432156
    public static String smallestNumber(String pattern) {
        int N = pattern.length();

        StringBuilder builder = new StringBuilder(N + 1);

        int[] right = new int[N];

        right[N - 1] = pattern.charAt(N - 1) == 'I' ? 0 : Integer.MAX_VALUE;

        for (int i = N - 2; i >= 0; i--) {
            if (pattern.charAt(i) == 'I') {
                right[i] = 0;
            } else if (right[i + 1] == Integer.MAX_VALUE) {
                right[i] = right[i + 1];
            } else {
                right[i] = right[i + 1] + 1;
            }
        }

        boolean[] used = new boolean[N + 2];

        for (int i = 0; i < N; i++) {
            if (right[i] == 0) {
                for (int j = 1; j <= N + 1; j++) {
                    if (!used[j]) {
                        builder.append(j);
                        used[j] = true;
                        break;
                    }
                }
            } else if (right[i] == Integer.MAX_VALUE) {
                for (int j = N + 1; j >= 0; j--) {
                    if (!used[j]) {
                        builder.append(j);
                        used[j] = true;
                        break;
                    }
                }
            } else {
                int min = -1;
                for (int j = 1; j <= N + 1; j++) {
                    if (!used[j]) {
                        min = j;
                        break;
                    }
                }
                builder.append(min + right[i]);
                used[min + right[i]] = true;
            }
        }

        for (int j = N + 1; j >= 0; j--) {
            if (!used[j]) {
                builder.append(j);
                break;
            }
        }

        return builder.toString();
    }

    public static String right(String pattern) {
        int n = pattern.length();

        int[] arr = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            arr[i] = i + 1;
        }

        for (int i = 0; i < n; i++) {
            if (pattern.charAt(i) == 'I')
                continue;
            else {
                for (int j = i; j >= 0 && pattern.charAt(j) == 'D'; j--) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++)
            sb.append(arr[i]);
        return sb.toString();
    }

}
