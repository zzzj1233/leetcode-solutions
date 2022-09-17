package com.zzzj.greedy;

public class Leet984 {

    // 3
    // 3

    // "aabbba"
    public String strWithout3a3b(int a, int b) {
        StringBuilder builder = new StringBuilder(a + b);

        while (a > 0 && b > 0) {
            if (a == b) {
                builder.append("ab");
                a--;
                b--;
            } else if (a > b) {
                builder.append("aa");
                builder.append("b");
                a -= 2;
                b -= 1;
            } else {
                builder.append("bb");
                builder.append("a");
                b -= 2;
                a -= 1;
            }
        }

        while (a > 0) {
            builder.append("a");
            a--;
        }

        while (b > 0) {
            builder.append("b");
            b--;
        }

        return builder.toString();
    }

}
