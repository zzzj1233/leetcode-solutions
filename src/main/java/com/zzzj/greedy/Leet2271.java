package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-06-29 14:54
 */
public class Leet2271 {

    public static void main(String[] args) {
//        System.out.println(maximumWhiteTiles(LeetUtils.convertInts("[[1,5],[10,11],[12,18],[20,35],[40,42]]"), 10));

        // [[1,5],[10,11],[12,18],[20,25],[30,32]]
        // 10
        System.out.println(maximumWhiteTiles(LeetUtils.convertInts("[[1,5],[10,11],[12,18],[20,25],[30,32]]"), 10));
    }

    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int n = tiles.length;

        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));

        int[] sum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + tiles[i - 1][1] - tiles[i - 1][0] + 1;
        }


        int ans = 0;

        int j = 0;

        for (int i = 0; i < n; i++) {
            int end = Math.min(tiles[tiles.length - 1][1], tiles[i][0] + carpetLen - 1);

            while (j + 1 < n && tiles[j + 1][0] <= end) {
                j++;
            }

            int fullCover = sum[j] - sum[i];

            int partCover = Math.min(end, tiles[j][1]) - tiles[j][0] + 1;

            ans = Math.max(ans, fullCover + partCover);
        }

        return ans;
    }

}
