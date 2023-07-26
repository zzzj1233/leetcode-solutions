package com.zzzj.contest.week350;

import com.zzzj.util.ArrayUtil;

import javax.sql.rowset.FilteredRowSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Leet2741 {

    public static void main(String[] args) {

        System.out.println(specialPerm(new int[]{2, 3, 6}));

        System.out.println(specialPerm(new int[]{1, 4, 3}));

        System.out.println(specialPerm(new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192}));
//        for (int i = 0; i < 10000; i++) {
//            int[] arr = ArrayUtil.generateArray(14, 1, 20);
//            int result = specialPerm(arr);
//            if (result > 0)
//                System.out.println("specialPerm(arr) = " + result);
//        }
    }

    public static int specialPerm(int[] nums) {
        int N = nums.length;

        OUT:
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                if (i == j) continue;

                if (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0) continue OUT;

            }

            return 0;
        }

        int[] stat = new int[nums.length];

        int[][][] memo = new int[N][N + 1][(1 << 14) + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return dfs(nums, 0, N, 0, memo);
    }


    static final int MOD = 1000000007;

    public static int dfs(int[] nums, int index, int prevChooseIndex, int used, int[][][] memo) {

        if (index >= nums.length) return 1;

        if (memo[index][prevChooseIndex][used] != -1) return memo[index][prevChooseIndex][used];

        int result = 0;

        int N = nums.length;

        for (int i = 0; i < N; i++) {

            if ((used & (1 << i)) != 0) continue;

            // 可选的值必须可以 % prev
            if (index > 0) {
                int prev = nums[prevChooseIndex];

                if (prev % nums[i] != 0 && nums[i] % prev != 0)
                    continue;
            }

            result += dfs(nums, index + 1, i, used | (1 << i), memo) % MOD;
            result %= MOD;
        }

        memo[index][prevChooseIndex][used] = result;

        return result;
    }

}
