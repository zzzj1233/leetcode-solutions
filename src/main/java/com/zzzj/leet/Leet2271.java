package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-02 17:50
 */
public class Leet2271 {

    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        int[] arr = new int[tiles.length << 1];

        for (int i = 0; i < tiles.length; i++) {
            arr[i] = tiles[i][0];
            arr[i + 1] = tiles[i][1];
        }


        return -1;
    }

}
