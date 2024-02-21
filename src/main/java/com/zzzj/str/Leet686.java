package com.zzzj.str;


import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-06-08 12:20
 */
public class Leet686 {

    public static void main(String[] args) {

        System.out.println(repeatedStringMatch("abcd", "cdabcdab"));

        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            String a = LeetUtils.randomString(LeetUtils.random.nextInt(100) + 1);

            String b = LeetUtils.randomString(LeetUtils.random.nextInt(100) + 1);

            if (repeatedStringMatch(a, b) != right(a, b)) {
                System.out.println("Error");
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int repeatedStringMatch(String a, String b) {

        if (b.isEmpty())
            return -1;

        StringBuilder builder = new StringBuilder();

        int cnt = 0;

        while (builder.length() < b.length()) {
            builder.append(a);
            cnt++;
        }

        int[] next = next(b);

        if (match(builder.toString(), b, next))
            return cnt;

        builder.append(a);

        if (match(builder.toString(), b, next))
            return cnt + 1;

        return -1;
    }

    public static boolean match(String source, String search, int[] next) {

        if (search.isEmpty())
            return false;

        int x = 0;

        int y = 0;

        int N = source.length();

        int M = search.length();

        while (x < N && y < M) {
            if (source.charAt(x) == search.charAt(y)) {
                x++;
                y++;
            } else if (next[y] >= 0) {
                y = next[y];
            } else {
                x++;
            }
        }

        return y == M;
    }

    public static int[] next(String search) {

        int N = search.length();

        if (N == 1)
            return new int[]{-1};

        int[] next = new int[N];

        next[0] = -1;

        int index = 2;

        int cc = 0;

        while (index < N)
            if (search.charAt(index - 1) == search.charAt(cc))
                next[index++] = ++cc;
            else if (next[cc] >= 0)
                cc = next[cc];
            else
                index++;

        return next;
    }

    public static int right(String a, String b) {
        int first = 0;
        int second = 0;
        char[] firstChars = a.toCharArray();
        char[] secondChars = b.toCharArray();
        // 找到第一个匹配的
        for (int i = 0; i < firstChars.length; i++) {
            if (firstChars[i] == secondChars[0]) {
                first = i;
                while (firstChars[first % firstChars.length] ==
                        secondChars[second % secondChars.length]
                        && second < secondChars.length) {
                    first++;
                    second++;
                }
                if (second == secondChars.length) {
                    return (first + firstChars.length - 1) / firstChars.length;
                }
                second = 0;
            }
        }
        return -1;
    }

}
