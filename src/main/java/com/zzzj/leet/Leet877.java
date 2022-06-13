package com.zzzj.leet;


import com.zzzj.util.ArrayUtil;
import com.zzzj.util.Unresolved;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-06 10:34
 */
@Unresolved
public class Leet877 {

    /**
     * 动态规划思想是希望连续的，也就是说上一个状态和下一个状态(自变量)之间有关系而且连续。
     * <p>
     * 如果涉及到最多，最少的，一般使用动态规划。
     * <p>
     * dp[i][j]dp[i][j]：表示先手玩家（亚历克斯）与后手玩家（李）在区间 [i, j][i,j] 之间互相拿，先手玩家比后手玩家多的最大石子个数。这是个差值，而且是个最大差值。
     * <p>
     * 对于先手玩家，有两种拿法：
     * <p>
     * 拿开头的 piles[i]piles[i]：先手玩家手里有了 piles[i]piles[i]，因为在区间 [i + 1, j][i+1,j] 中只能由后手玩家来选择，则 dp[i + 1][j]dp[i+1][j] 表示的是后手玩家在这个区间内，比先手玩家多的最大石子个数，反过来 -dp[i + 1][j]−dp[i+1][j] 表示在这个区间内，先手玩家比后手玩家多的最大石子个数；
     * 状态转移方程：dp[i][j] = piles[i] + (-dp[i + 1][j])
     * <p>
     * 拿结尾的 piles[j]piles[j]：先手玩家手里有了 piles[j]piles[j]，因为在区间 [i, j - 1][i,j−1] 中只能由后手玩家来选择，则 dp[i][j - 1]dp[i][j−1] 表示的是后手玩家在这个区间内，比先手玩家多的最大石子个数，反过来 -dp[i][j - 1]−dp[i][j−1] 表示在这个区间内，先手玩家比后手玩家多的最大石子个数；
     * 状态转移方程：dp[i][j] = piles[j] + (-dp[i][j - 1])
     * <p>
     * 在这两种情况中，选择先手玩家和后手玩家选择石子堆后，石子个数差更大的一种情况：dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])dp[i][j]=Math.max(piles[i]−dp[i+1][j],piles[j]−dp[i][j−1])
     * <p>
     * 初始化：当只有一个数时 dp[i][i]dp[i][i]，此时先手玩家拿了必赢，所以 dp[i][i] = piles[i]dp[i][i]=piles[i]。
     * <p>
     * 对于区间 dpdp 来说，将 ii 从 n - 1n−1 往前遍历到 00，而 jj 从 ii 位置往后遍历到 n - 1n−1，这样能够方便 i < ji<j，将大区间划分成小区间。从小区间开始判断，不断的扩大我们的判断范围看会不会赢
     * <p>
     * <p>
     * 实例：piles = [1, 5, 233, 7]，dp[0][0] = 1、dp[1][1] = 5、dp[2][2] = 233、dp[3][3] = 7
     * i = 3，j = 4 直接跳过；
     * <p>
     * i = 2，j = 3，区间 [2, 3] dp[2][3] = max(233 - 7, 7 - 233) = 226；
     * <p>
     * i = 1，j = 2，区间 [1, 2] dp[1][2] = max(5 - 233, 233 - 5) = 228；
     * j = 3，区间 [1, 3] dp[1][3] = max(5 - 226, 7 - 228) = -221；
     * <p>
     * i = 0，j = 1，区间 [0, 1] dp[0][1] = max(1 - 5, 5 - 1) = 4；
     * j = 2，区间 [0, 2] dp[0][2] = max(1 - 228, 233 - 4) = 229；
     * j = 3，区间 [0, 3] dp[0][3] = max(1 + 221, 7 - 229) = 222；
     * <p>
     * 区间 [0, 3] 即 dp[0][3] = 222 > 0，则先手玩家（亚历克斯）会赢。
     */

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(20, 1, 100);

            if (Arrays.stream(arr).sum() % 2 == 0) {
                arr[0] += 1;
            }

            if (stoneGame(arr) != right(arr)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(stoneGame(arr));
                return;
            }

        }
    }


    // N 是偶数
    // 总数是奇数
    // 只能从开始或者结束拿石头
    // 结束时谁的石头更多谁赢
    public static boolean stoneGame(int[] piles) {
        int sum = Arrays.stream(piles).sum();
        int take = dfs(piles, 0, piles.length - 1);
        return (sum - take) < take;
    }

    public static int dfs(int[] piles, int i, int j) {
        if (i >= j) {
            return 0;
        }
        // 只能拿i个或者j个
        int take1 = piles[i];
        int take2 = piles[j];

        int dfs = dfs(piles, i + 1, j - 1);
        // 拿了左边的
        take1 += Math.max(dfs, dfs(piles, i + 2, j));

        // 拿了右边的
        take2 += Math.max(dfs, dfs(piles, i, j - 2));

        return Math.max(take1, take2);
    }

    public static int dp(int[] piles) {
        int N = piles.length;

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {

        }

        return -1;
    }

    public static boolean right(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }

}
