package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-01-23 16:29
 */
public class Leet1881 {

    public static void main(String[] args) {
        System.out.println(maxValue("99", 9));
        System.out.println(maxValue("-13", 2));

        System.out.println(maxValue("73", 6));

        System.out.println(maxValue("-55", 2));
    }

    public static String maxValue(String n, int x) {

        boolean negative = n.charAt(0) == '-';

        StringBuilder builder = new StringBuilder(n);

        int N = n.length();

        if (negative) {
            for (int i = 0; i < N; i++) {
                // 插入到前面
                if (Character.digit(builder.charAt(i), 10) > x) {
                    builder.insert(i, x);
                    return builder.toString();
                }
            }
        } else { // positive
            for (int i = 0; i < N; i++) {
                // 插入到前面
                if (Character.digit(builder.charAt(i), 10) < x) {
                    builder.insert(i, x);
                    return builder.toString();
                }
            }
        }

        builder.append(x);

        return builder.toString();
    }

}
