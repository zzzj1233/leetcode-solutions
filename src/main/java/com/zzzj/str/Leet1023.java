package com.zzzj.str;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-05-19 10:54
 */
public class Leet1023 {

    public static void main(String[] args) {
        Leet1023 solution = new Leet1023();

        // ["aksvbjLiknuTzqon","ksvjLimflkpnTzqn","mmkasvjLiknTxzqn","ksvjLiurknTzzqbn","ksvsjLctikgnTzqn","knzsvzjLiknTszqn"]
        //"ksvjLiknTzqn"

        // [false,true,false,true,true,true]
        // [true,true,true,true,true,true]

        System.out.println(solution.camelMatch(new String[]{"aksvbjLiknuTzqon", "ksvjLimflkpnTzqn", "mmkasvjLiknTxzqn", "ksvjLiurknTzzqbn", "ksvsjLctikgnTzqn", "knzsvzjLiknTszqn"}, "ksvjLiknTzqn"));
    }

    private String pattern;

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        this.pattern = pattern;

        return Arrays.stream(queries).map(this::match).collect(Collectors.toList());
    }

    public boolean match(String query) {

        int N = query.length();

        int M = pattern.length();

        int j = 0;

        for (int i = 0; i < N; i++) {

            char c = query.charAt(i);

            if (j >= pattern.length() && Character.isUpperCase(c)) {
                return false;
            }

            if (j < pattern.length() && c == pattern.charAt(j)) {
                j++;
            } else if (Character.isUpperCase(c)) {
                return false;
            }

        }

        return j == M;
    }

}
