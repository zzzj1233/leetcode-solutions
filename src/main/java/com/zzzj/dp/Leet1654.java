package com.zzzj.dp;

import com.zzzj.util.Unresolved;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Zzzj
 * @create 2022-07-18 21:42
 */
@Unresolved
public class Leet1654 {

    public static void main(String[] args) {
        System.out.println(minimumJumps(new int[]{14, 4, 18, 1, 15}, 3, 15, 9));
        System.out.println(minimumJumps(new int[]{8, 3, 16, 6, 12, 20}, 15, 13, 11));
        System.out.println(minimumJumps(new int[]{1, 6, 2, 14, 5, 17, 4}, 16, 9, 7));
    }

    public static int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> set = new HashSet<>(forbidden.length);

        for (int item : forbidden) {
            set.add(item);
        }

        int result = dfs(set, a, b, x, 0, 0, new Integer[x + 1][3]);

        return result == Integer.MAX_VALUE ? -1 : result;
    }


    public static int dfs(Set<Integer> forbidden, int a, int b, int x, int cur, int back, Integer[][] memo) {
        if (forbidden.contains(cur)) {
            return Integer.MAX_VALUE;
        }

        if (cur == x) {
            return 0;
        }

        // 只能往回跳
        if (cur > x) {
            if (back == 2 || cur - b < 0) {
                return Integer.MAX_VALUE;
            }
            int result = dfs(forbidden, a, b, x, cur - b, back + 1, memo);
            if (result == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return result + 1;
        } else {
            if (memo[cur][back] != null) {
                return memo[cur][back];
            }
            // 可以往回跳也可以往前跳
            if (back == 2 || cur - b < 0) {
                int result = dfs(forbidden, a, b, x, cur + a, 0, memo);
                if (result == Integer.MAX_VALUE) {
                    memo[cur][back] = Integer.MAX_VALUE;
                } else {
                    memo[cur][back] = result + 1;
                }
            } else {
                int ways1 = dfs(forbidden, a, b, x, cur + a, 0, memo);
                int ways2 = dfs(forbidden, a, b, x, cur - b, back + 1, memo);

                if (ways1 == Integer.MAX_VALUE && ways2 == Integer.MAX_VALUE) {
                    memo[cur][back] = Integer.MAX_VALUE;
                } else if (ways1 != Integer.MAX_VALUE && ways2 != Integer.MAX_VALUE) {
                    memo[cur][back] = Math.min(ways1, ways2) + 1;
                } else if (ways1 == Integer.MAX_VALUE) {
                    memo[cur][back] = ways2 + 1;
                } else {
                    memo[cur][back] = ways1 + 1;
                }
            }
        }

        return memo[cur][back];
    }

}
