package com.zzzj.backtracking;

/**
 * @author zzzj
 * @create 2022-03-04 14:44
 */
public class Leet1774 {

    public static void main(String[] args) {
        System.out.println(closestCost(new int[]{1, 7}, new int[]{3, 4}, 10));
        System.out.println(closestCost(new int[]{2, 3}, new int[]{4, 5, 100}, 18));
        System.out.println(closestCost(new int[]{3, 10}, new int[]{2, 5}, 9));
        System.out.println(closestCost(new int[]{10}, new int[]{1}, 1));
        // [716,4707]
        // [399,7161,1043,8560,527,8067,117,1176,334,400]
        // 7371
        System.out.println(closestCost(new int[]{716, 4707}, new int[]{399, 7161, 1043, 8560, 527, 8067, 117, 1176, 334, 400}, 7371));
    }

    public static int ans;

    public static int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        ans = Integer.MAX_VALUE;
        // 最新接近target的答案

        for (int i = 0; i < baseCosts.length; i++) {
            dfs(baseCosts[i], toppingCosts, target, 0);
        }

        return ans;
    }

    public static void dfs(int cur, int[] toppingCosts, int target, int index) {
        if (ans == target) {
            return;
        }
        int abs1 = Math.abs(target - cur);
        int abs2 = Math.abs(target - ans);
        if (abs1 < abs2) {
            ans = cur;
        } else if (abs1 == abs2) {
            ans = Math.min(ans, cur);
        }

        if (cur >= target) {
            return;
        }

        for (int i = index; i < toppingCosts.length && ans != target; i++) {
            dfs(cur + toppingCosts[i], toppingCosts, target, i + 1);
            dfs(cur + (toppingCosts[i] << 1), toppingCosts, target, i + 1);
            dfs(cur, toppingCosts, target, i + 1);
        }

    }

}
