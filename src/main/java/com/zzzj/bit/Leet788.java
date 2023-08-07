package com.zzzj.bit;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-28 14:28
 */
public class Leet788 {


    public static void main(String[] args) {

        System.out.println(rotatedDigits(10));

        System.out.println(rotatedDigits(857));

    }

    public static final int[] GOOD_NUMS = new int[10];

    public static int rotatedDigits(int n) {
        GOOD_NUMS[3] = -1;
        GOOD_NUMS[4] = -1;
        GOOD_NUMS[7] = -1;

        GOOD_NUMS[0] = 0;
        GOOD_NUMS[1] = 0;
        GOOD_NUMS[8] = 0;

        GOOD_NUMS[2] = 1;
        GOOD_NUMS[5] = 1;
        GOOD_NUMS[6] = 1;
        GOOD_NUMS[9] = 1;

        String value = String.valueOf(n);

        int N = value.length();

        int[][] memo = new int[N][10];

        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(value, 0, 0, memo, true);
    }

    public static int dfs(String value, int index, int cnt, int[][] memo, boolean isLimit) {

        if (index >= value.length()) return cnt > 0 ? 1 : 0;

        if (!isLimit && memo[index][cnt] != -1) return memo[index][cnt];

        int result = 0;

        if (isLimit) {

            int limit = Character.digit(value.charAt(index), 10);

            for (int i = 0; i < limit; i++) {
                if (GOOD_NUMS[i] == -1)
                    continue;
                result += dfs(value, index + 1, cnt + GOOD_NUMS[i], memo, false);
            }

            if (GOOD_NUMS[limit] != -1)
                result += dfs(value, index + 1, cnt + GOOD_NUMS[limit], memo, true);

        } else {

            for (int i = 0; i < 10; i++) {
                if (GOOD_NUMS[i] == -1)
                    continue;
                result += dfs(value, index + 1, cnt + GOOD_NUMS[i], memo, false);
            }

            memo[index][cnt] = result;
        }

        return result;
    }

}
