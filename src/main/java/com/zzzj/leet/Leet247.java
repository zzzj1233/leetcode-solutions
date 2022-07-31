package com.zzzj.leet;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-07-30 15:24
 */
public class Leet247 {

    private static final Integer[] mapping = new Integer[10];

    static {
        mapping[0] = 0;
        mapping[1] = 1;
        mapping[8] = 8;
        mapping[6] = 9;
        mapping[9] = 6;
    }

    public static final List<String> BASE_ANS = Arrays.asList("0", "1", "8");

    public static void main(String[] args) {
        System.out.println(findStrobogrammatic(4));
    }

    public static List<String> findStrobogrammatic(int n) {
        return dfs(1, n, 1);
    }

    public static List<String> dfs(int i, int j, int start) {
        if (i == j) {
            return BASE_ANS;
        }

        if (i > j) {
            return Collections.emptyList();
        }

        List<String> ret = new LinkedList<>();

        StringBuilder builder = new StringBuilder(j - i + 1);

        for (int k = start; k <= 9; k++) {
            Integer candidate = mapping[k];

            if (candidate != null) {
                builder.append(k);

                List<String> list = dfs(i + 1, j - 1, 0);

                int len = builder.length();

                if (!list.isEmpty()) {
                    for (String s : list) {
                        builder.append(s);
                        builder.append(candidate);
                        ret.add(builder.toString());
                        builder.setLength(len);
                    }
                } else {
                    builder.append(candidate);
                    ret.add(builder.toString());
                    builder.setLength(len);
                }
            }

            builder.setLength(0);
        }


        return ret;
    }


}
