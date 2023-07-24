package com.zzzj.contest.week352;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-18 14:50
 */
public class Leet2763 {

    public static void main(String[] args) {

        // 3, 1
        // 3, 1, 4
        // 1, 4
        System.out.println(sumImbalanceNumbers(new int[]{2, 3, 1, 4}));

        System.out.println(sumImbalanceNumbers(new int[]{1, 3, 3, 3, 5}));

        // 1 3 = 1
        // 1 1 3 = 1
        // 1 3 = 1
        System.out.println(sumImbalanceNumbers(new int[]{1, 3, 1}));

    }

    public static int sumImbalanceNumbers(int[] nums) {

        int N = nums.length;

        int ans = 0;

        boolean[] visited = new boolean[N + 2];

        for (int i = 0; i < N; i++) {

            Arrays.fill(visited, false);

            int cnt = 0;

            visited[nums[i]] = true;

            for (int j = i + 1; j < N; j++) {

                int num = nums[j];

                if (visited[num]) {
                    ans += cnt;
                    continue;
                }

                visited[num] = true;

                if (visited[num - 1] || visited[num + 1]) {
                    if (visited[num - 1] && visited[num + 1]) cnt = Math.max(0, cnt - 1);
                    ans += cnt;
                    continue;
                }

                cnt++;
                ans += cnt;
            }

        }

        return ans;
    }

}
