package com.zzzj.str;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-23 16:48
 */
public class Leet411 {

    public static void main(String[] args) {

        System.out.println(minAbbreviation("apple", new String[]{"blade", "plain", "amber"}));

        System.out.println(minAbbreviation("apple", new String[]{"blade"}));

    }

    public static String minAbbreviation(String target, String[] dictionary) {

        int N = target.length();

        int[] dic = Arrays.stream(dictionary)
                .filter(s -> s.length() == N)
                .mapToInt(s -> compare(target, s))
                .toArray();

        int M = dic.length;

        if (M == 0)
            return String.valueOf(N);

        int limit = 1 << N;

        int minLen = N;
        int minStat = limit - 1;

        OUTER:
        for (int stat = 0; stat < limit; stat++) {

            for (int x : dic)
                if ((stat & x) == 0)
                    continue OUTER;

            int l = len(stat, N);

            if (l < minLen) {
                minLen = l;
                minStat = stat;
            }

        }

        StringBuilder builder = new StringBuilder(minLen);

        for (int i = 0; i < N; ) {

            if ((minStat & (1 << i)) != 0) {
                builder.append(target.charAt(i));
                i++;
            } else {
                int j = i;

                while (j < N && (minStat & (1 << j)) == 0) {
                    j++;
                }

                int l = j - i;

                builder.append(l);

                i = j;
            }
        }

        return builder.toString();
    }

    public static int len(int stat, int N) {

        int len = 0;

        boolean start = false;

        for (int i = 0; i < N; i++) {
            if ((stat & (1 << i)) != 0) {
                start = false;
                len++;
            } else if (!start) {
                start = true;
                len++;
            }
        }

        return len;
    }

    public static int compare(String target, String s) {

        int N = target.length();

        int x = 0;

        for (int i = 0; i < N; i++)
            if (target.charAt(i) != s.charAt(i))
                x |= 1 << i;

        return x;
    }

}
