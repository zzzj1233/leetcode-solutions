package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-05-19 16:40
 */
public class Leet1335 {

    public static void main(String[] args) {
        System.out.println(minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2));

        System.out.println(minDifficulty(new int[]{7, 1, 7, 1, 7, 1}, 3));

        System.out.println(minDifficulty(new int[]{11, 111, 22, 222, 33, 333, 44, 444}, 6));
    }

    public static int minDifficulty(int[] jobDifficulty, int d) {
        return d > jobDifficulty.length ? -1 : dfs(jobDifficulty, 0, 0, d);
    }

    public static int dfs(int[] jobDifficulty, int jobIndex, int index, int days) {

        int N = jobDifficulty.length;

        // 1. 时间不足
        if (days == 1) {
            if (jobIndex >= N) {
                return 0;
            }
            int max = jobDifficulty[jobIndex];

            for (int i = index; i < N; i++) {
                max = Math.max(max, jobDifficulty[i]);
            }

            return max;
        }

        // 2. 任务不足
        if (N - index + 1 == days) {

            int res = jobDifficulty[jobIndex];

            for (int i = index; i < N; i++) {
                res += jobDifficulty[i];
            }

            return res;
        }

        // 3. 能力足够
        if (jobDifficulty[jobIndex] >= jobDifficulty[index]) {

            return dfs(jobDifficulty, jobIndex, index + 1, days);
        }

        // 4. 能力不够

        int x = dfs(jobDifficulty, index, index, days);

        int y = jobDifficulty[jobIndex] + dfs(jobDifficulty, index, index, days - 1);

        return Math.min(x, y);
    }

}
