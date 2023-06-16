package com.zzzj.str;


import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-06-08 12:20
 */
public class Leet686 {

    public static void main(String[] args) {

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

        StringBuilder builder = new StringBuilder();

        int cnt = 0;

        while (builder.length() < b.length()) {
            builder.append(a);
            cnt++;
        }

        if (builder.toString().contains(b)) {
            return cnt;
        }

        builder.append(a);

        if (builder.toString().contains(b)) {
            return cnt + 1;
        }

        return -1;
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
