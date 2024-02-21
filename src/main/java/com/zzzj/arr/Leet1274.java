package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2024-01-26 12:21
 */
public class Leet1274 {

    interface Sea {
        boolean hasShips(int[] topRight, int[] bottomLeft);
    }

    public static int countShips(Sea sea, int[] topRight, int[] bottomLeft) {

        if (!sea.hasShips(topRight, bottomLeft))
            return 0;

        if (topRight[0] == topRight[1] && bottomLeft[0] == bottomLeft[1])
            return 1;

        int top = topRight[0];
        int bottom = bottomLeft[0];

        int right = topRight[1];
        int left = bottomLeft[1];

        // vertical middle
        int vm = top + ((bottom - top) >> 1);

        // horizontal middle
        int hm = left + ((right - left) >> 1);

        int v = 0;

        // 左上角
        v += countShips(sea, new int[]{top, hm}, new int[]{vm, left});

        // 右上角
        v += countShips(sea, new int[]{top, right}, new int[]{vm, hm + 1});

        // 左下角
        v += countShips(sea, new int[]{vm + 1, hm}, new int[]{bottom, left});

        // 右下角
        v += countShips(sea, new int[]{vm + 1, right}, new int[]{bottom, hm + 1});

        return v;
    }

}
