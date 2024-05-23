package com.zzzj.contest.week391;

import com.zzzj.leet.LeetUtils;

import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2024-05-23 15:43
 */
public class Leet3102 {

    public static void main(String[] args) {

        System.out.println(minimumDistance(LeetUtils.convertInts("[[3,10],[5,15],[10,2],[4,4]]")));

    }

    // 最大曼哈顿距离固定公式
    // max( max(xi + yi) - min(xi + yi) , max(xi - yi) - min(xi - yi) )
    public static int minimumDistance(int[][] points) {

        int N = points.length;

        TreeMap<Integer, Integer> add = new TreeMap<>();

        TreeMap<Integer, Integer> sub = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            int a = points[i][0] + points[i][1];
            int s = points[i][0] - points[i][1];
            add.put(a, add.getOrDefault(a, 0) + 1);
            sub.put(s, sub.getOrDefault(s, 0) + 1);
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int a = points[i][0] + points[i][1];
            int s = points[i][0] - points[i][1];
            if (add.get(a) == 1) add.remove(a);
            if (sub.get(s) == 1) sub.remove(s);
            ans = Math.min(ans, Math.max(
                    add.lastKey() - add.firstKey(),
                    sub.lastKey() - sub.firstKey()
            ));
            add.putIfAbsent(a, 1);
            sub.putIfAbsent(s, 1);
        }

        return ans;
    }

}
