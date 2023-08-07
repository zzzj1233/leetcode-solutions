package com.zzzj.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-08-04 18:33
 */
public class Leet2564 {

    public static int[][] substringXorQueries(String s, int[][] queries) {

        Map<Integer, Integer> index = new HashMap<>();

        int xor = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                xor |= (1 << i);
            }
            index.put(xor, i);
        }

        int N = queries.length;

        int[][] ans = new int[N][2];

        for (int i = 0; i < N; i++) {

            int[] query = queries[i];

            int expect = query[1] ^ query[0];


        }

        return null;
    }

}
