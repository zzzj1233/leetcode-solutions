package com.zzzj.arr;

import java.util.Arrays;

public class Leet1996 {

    public static int numberOfWeakCharacters(int[][] properties) {

        Arrays.sort(properties, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        int N = properties.length;

        int ans = 0;

        int maxDefensive = -1;

        for (int i = 0; i < N; i++) {
            if (properties[i][1] < maxDefensive) {
                ans++;
            } else {
                maxDefensive = properties[i][1];
            }
        }

        return ans;
    }

}
