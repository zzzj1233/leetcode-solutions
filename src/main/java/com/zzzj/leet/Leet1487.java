package com.zzzj.leet;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-12-30 15:16
 */
public class Leet1487 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getFolderNames(new String[]{"kaido", "kaido(1)", "kaido", "kaido(1)"})));
    }

    public static final String[] SEQ_CACHE = new String[100];

    public static String[] getFolderNames(String[] names) {
        int N = names.length;

        String[] ans = new String[N];

        Map<String, Integer> map = new HashMap<>();

        Set<String> exists = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String name = names[i];

            int seq = seq(name);

            Integer count = map.get(name);

            if (count != null) { // 有相同名称
                name = names[i] + wrapSeq(count + 1);

                while (exists.contains(name)) {
                    count++;
                    name = names[i] + wrapSeq(count + 1);
                }

                map.put(names[i], count + 1);
            }

            map.put(name, 0);
            exists.add(name);

            ans[i] = name;
        }

        return ans;
    }

    public static String wrapSeq(int seq) {
        if (seq >= 100) {
            return "(" + seq + ")";
        }

        if (SEQ_CACHE[seq] == null) {
            SEQ_CACHE[seq] = "(" + seq + ")";
        }

        return SEQ_CACHE[seq];
    }


    public static int seq(String name) {
        // find : (\n)
        if (!name.endsWith(")")) {
            return -1;
        }

        int N = name.length();

        StringBuilder seq = new StringBuilder();

        for (int i = N - 2; i >= 0; i--) {
            char c = name.charAt(i);
            if (c == '(') {
                return seq.length() == 0 ? -1 : Integer.parseInt(seq.reverse().toString());
            } else if (!Character.isDigit(c)) {
                return -1;
            } else {
                seq.append(c);
            }
        }

        return -1;
    }

}
