package com.zzzj.backtracking;


/**
 * @author zzzj
 * @create 2022-04-07 11:32
 */
public class Leet679 {

    public static void main(String[] args) {
        System.out.println(judgePoint24(new int[]{1, 3, 4, 6}));
    }

    public static boolean judgePoint24(int[] cards) {
        boolean[] used = new boolean[cards.length];

        int[] path = new int[cards.length + 3];

        for (int i = 0; i < cards.length; i++) {
            used[i] = true;
            path[i] = cards[i];
            if (dfs(cards, used, path, 1)) {
                return true;
            }
            used[i] = false;
        }
        return false;
    }

    public static boolean dfs(int[] cards, boolean[] used, int[] path, int len) {
        if (len == cards.length) {
            // 针对path计算结果
            for (int i = 0; i < path.length; i++) {

            }
            return false;
        }

        for (int i = 0; i < cards.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;

            boolean valid = false;

            if (valid) {
                return true;
            }

            used[i] = false;
        }

        return false;
    }

}
