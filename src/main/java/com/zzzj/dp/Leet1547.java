package com.zzzj.dp;

public class Leet1547 {

    public static void main(String[] args) {

        System.out.println(minCost(7, new int[]{1, 3, 4, 5}));

//        System.out.println(minCost(9, new int[]{5, 6, 1, 4, 2}));

    }

    public static int minCost(int n, int[] cuts) {

        int N = cuts.length;

        Info[][] dp = new Info[N][N];

        for (int i = 0; i < N; i++) {
            dp[0][i] = new Info(cuts[i], cuts[i], n);
        }

        // 第[i]刀的情况
        // 依赖dp[i - 1]
        for (int i = 1; i < N; i++) {

            // 第[j]个元素
            for (int j = 0; j < N; j++) {

                dp[i][j] = new Info();
                dp[i][j].cost = Integer.MAX_VALUE;

                int cut = cuts[j];

                // 其他元素[k]
                for (int k = 0; k < N; k++) {

                    if (k == j) continue;

                    Info prev = dp[i - 1][k];

                    int curCost;
                    // right做左边
                    if (cut > prev.right) {
                        curCost = n - prev.right;
                    } else if (cut < prev.left) {
                        curCost = prev.left;
                    } else {
                        curCost = prev.right - prev.left + 1;
                    }

                    if (curCost + prev.cost < dp[i][j].cost) {
                        dp[i][j].left = Math.min(prev.left, cut);
                        dp[i][j].right = Math.max(prev.right, cut);
                        dp[i][j].cost = curCost + prev.cost;
                    }

                }

            }

        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, dp[N - 1][i].cost);
        }

        return ans;
    }

    private static class Info {
        int left;
        int right;
        int cost;

        public Info(int left, int right, int cost) {
            this.left = left;
            this.right = right;
            this.cost = cost;
        }

        public Info() {
        }
    }

}
