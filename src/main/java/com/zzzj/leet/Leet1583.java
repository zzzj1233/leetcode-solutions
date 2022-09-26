package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-09-16 15:36
 */
public class Leet1583 {

    // 4
    // [[2,1,3],[0,3,2],[1,0,3],[2,0,1]]
    // [[0,1],[2,3]]

    public static void main(String[] args) {
        int[][] preferences = LeetUtils.convertInts("[[2,1,3],[0,3,2],[1,0,3],[2,0,1]]");

        int[][] pairs = LeetUtils.convertInts("[[0,1],[2,3]]");

        System.out.println(unhappyFriends(4, preferences, pairs));
    }

    public static int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map[] maps = new Map[n];

        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> map = new HashMap<>(n);

            int M = preferences[i].length;

            for (int j = 0; j < M; j++) {
                map.put(preferences[i][j], j);
            }

            maps[i] = map;
        }


        int ans = 0;

        Map<Integer, Integer> combination = new HashMap<>(n);

        for (int[] pair : pairs) {
            combination.put(pair[0], pair[1]);
            combination.put(pair[1], pair[0]);
        }

        for (int[] pair : pairs) {
            int x = pair[0];
            int y = pair[1];
            // x 与 u 的亲近程度胜过 x 与 y
            // &&
            // u 与 x 的亲近程度胜过 u 与 v

            Map<Integer, Integer> map1 = maps[x];
            Map<Integer, Integer> map2 = maps[y];

            // x和y的亲密度
            final Integer xCloseY = map1.get(y);

            if (xCloseY > 0) {
                int[] preference = preferences[x];

                for (int i = 0; i < xCloseY; i++) {
                    // 更亲密的朋友

                    // x和y是一队
                    // u和v是一队
                    int u = preference[i];
                    Integer v = combination.get(u);

                    // x和u和亲密度为i , i > x 与 y的亲密度

                    // u和v的亲密度
                    Integer uCloseX = (Integer) maps[u].get(x);
                    Integer uCloseV = (Integer) maps[u].get(v);

                    if (uCloseX < uCloseV) {
                        ans++;
                        break;
                    }
                }

            }

            // y 与 u 的亲近程度胜过 y 与 x
            // &&
            // u 与 y 的亲近程度胜过 u 与 v

            // y和x的亲密度
            final Integer yCloseX = map2.get(x);

            if (yCloseX > 0) {
                int[] preference = preferences[y];

                for (int i = 0; i < yCloseX; i++) {
                    int u = preference[i];

                    Integer v = combination.get(u);

                    Integer uCloseY = (Integer) maps[u].get(y);
                    Integer uCloseV = (Integer) maps[u].get(v);

                    // u和y的亲密度为i
                    // u和v的亲密度为uCloseV
                    if (uCloseY < uCloseV) {
                        ans++;
                        break;
                    }
                }

            }

        }

        return ans;
    }

}
