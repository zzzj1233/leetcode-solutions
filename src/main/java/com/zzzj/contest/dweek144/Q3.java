package com.zzzj.contest.dweek144;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * @author zzzj
 * @create 2024-11-23 22:37
 */
public enum Q3 {
    ;

    public static void main(String[] args) {

        System.out.println(maxRemoval(new int[]{0, 0, 3}, LeetUtils.convertInts("[[0,2],[1,1],[0,0],[0,0]]")));

        System.out.println(maxRemoval(new int[]{2, 0, 2}, LeetUtils.convertInts("[[0,2],[0,2],[1,1]]")));

        System.out.println(maxRemoval(new int[]{1, 1, 1, 1}, LeetUtils.convertInts("[[1,3],[0,2],[1,3],[1,2]]")));

        System.out.println(maxRemoval(new int[]{1, 2, 3, 4}, LeetUtils.convertInts("[[0,3]]")));

    }

    public static int maxRemoval(int[] nums, int[][] queries) {

        int N = nums.length;

        int M = queries.length;

        // 贪心 最大堆 差分数组

        // 1. while queue.peek[1] < i
        //      queue.remove()

        // 2. 按照right排序, 如果相同, 再按照长度排序
        PriorityQueue<int[]> queue = new PriorityQueue<>(M, (o1, o2) -> {
            int diff = o2[1] - o1[1];
            if (diff == 0)
                return (o2[1] - o2[0]) - (o1[1] - o1[0]);
            return diff;
        });

        // 3. 什么时候enQueue?
        // qi < M && queries[qi][0] <= i

        Arrays.sort(queries, Comparator.comparingInt(o -> o[0]));

        int cur = 0;

        int[] diff = new int[N + 1];

        int cnt = 0;

        int qi = 0;

        for (int i = 0; i < N; i++) {

            while (qi < M && queries[qi][0] <= i) {
                queue.add(queries[qi]);
                qi++;
            }

            cur += diff[i];

            // 必须从堆里拿出了
            while (cur < nums[i] && !queue.isEmpty()) {
                int[] node = queue.remove();
                if (node[1] < i)
                    break;
                cur++;
                diff[node[1] + 1]--;
                cnt++;
            }

            if (cur < nums[i])
                return -1;
        }

        return M - cnt;
    }


}
