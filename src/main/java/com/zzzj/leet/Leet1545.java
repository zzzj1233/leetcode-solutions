package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-09-08 14:16
 */
public class Leet1545 {

    public static char findKthBit(int n, int k) {
        return dfs(n).charAt(k);
    }

    public static StringBuilder dfs(int n) {
        if (n == 1) {
            return new StringBuilder("0");
        }
        StringBuilder dfs = dfs(n - 1);
        return new StringBuilder(dfs).append("1").append(invert(dfs).reverse());
    }

    public static StringBuilder invert(StringBuilder builder) {
        for (int i = 0; i < builder.length(); i++) {
            char c = builder.charAt(i);
            if (c == '0') {
                builder.setCharAt(i, '1');
            } else {
                builder.setCharAt(i, '0');
            }
        }
        return builder;
    }

}
