package com.zzzj.contest.week349;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2533分的题 cool~
 *
 * @author zzzj
 * @create 2023-08-31 10:46
 */
public class Leet2736 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(maximumSumQueries(new int[]{4, 3, 1, 2}, new int[]{2, 4, 9, 5}, LeetUtils.convertInts("[[4,1],[1,3],[2,5]]"))));

        System.out.println(Arrays.toString(maximumSumQueries(new int[]{3, 2, 5}, new int[]{2, 3, 4}, LeetUtils.convertInts("[[4,4],[3,2],[1,1]]"))));

        System.out.println(Arrays.toString(maximumSumQueries(new int[]{2, 1}, new int[]{2, 3}, LeetUtils.convertInts("[[3,3]]"))));
    }

    private static final int START = 0;

    private static final int END = 1;

    public static int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {

        int N = nums1.length;

        int[][] sortByEnd = new int[N][2];

        for (int i = 0; i < N; i++) {
            sortByEnd[i][START] = nums1[i];
            sortByEnd[i][END] = nums2[i];
        }

        Arrays.sort(sortByEnd, Comparator.comparingInt(o -> o[END]));

        SegmentTree tree = new SegmentTree(sortByEnd);

        int[][] sortByStart = new int[N][2];

        for (int i = 0; i < N; i++) {
            sortByStart[i][START] = sortByEnd[i][START];
            sortByStart[i][1] = i;
        }

        Arrays.sort(sortByStart, Comparator.comparingInt(o -> o[START]));

        int M = queries.length;

        int[] ans = new int[M];

        Arrays.fill(ans, -1);

        int[][] combinedQuery = new int[M][3];

        for (int i = 0; i < M; i++) {
            combinedQuery[i][START] = queries[i][START];
            combinedQuery[i][END] = queries[i][END];
            combinedQuery[i][2] = i;
        }

        Arrays.sort(combinedQuery, Comparator.comparingInt(o -> o[START]));

        int sortByStartIndex = 0;

        for (int i = 0; i < M; i++) {

            int[] query = combinedQuery[i];

            int s = query[START];

            while (sortByStartIndex < N && sortByStart[sortByStartIndex][0] < s) {

                int sortByEndIndex = sortByStart[sortByStartIndex][1];

                tree.clear(sortByEndIndex);

                sortByStartIndex++;
            }

            if (sortByStartIndex >= N)
                break;

            // 根据end二分
            int e = query[END];

            int left = 0;
            int right = N - 1;
            int index = -1;

            while (left <= right) {

                int mid = left + ((right - left) >> 1);

                if (sortByEnd[mid][END] >= e) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (index == -1)
                continue;

            ans[query[2]] = tree.query(index, N - 1);
        }

        return ans;
    }

    private static class SegmentTree {

        private final int N;

        private final int[] tree;

        public SegmentTree(int[][] nums) {

            this.N = nums.length;

            this.tree = new int[N << 2];

            buildTree(1, N, 1, nums);
        }

        private void buildTree(int l, int r, int rt, int[][] nums) {

            if (l == r) {
                tree[rt] = nums[l - 1][0] + nums[l - 1][1];
                return;
            }

            int mid = l + ((r - l) >> 1);

            buildTree(l, mid, rt << 1, nums);
            buildTree(mid + 1, r, rt << 1 | 1, nums);

            tree[rt] = Math.max(tree[rt << 1], tree[rt << 1 | 1]);
        }

        public int query(int L, int R) {
            return query(L + 1, R + 1, 1, N, 1);
        }

        private int query(int L, int R, int l, int r, int rt) {

            if (L <= l && R >= r)
                return tree[rt];

            int mid = l + ((r - l) >> 1);

            int left = -1;
            int right = -1;

            if (L <= mid)
                left = query(L, R, l, mid, rt << 1);
            if (R > mid)
                right = query(L, R, mid + 1, r, rt << 1 | 1);

            return Math.max(left, right);
        }

        public void clear(int index) {
            clear(index + 1, 1, N, 1);
        }

        private void clear(int index, int l, int r, int rt) {
            if (l == r && l == index) {
                tree[rt] = -1;
                return;
            }

            int mid = l + ((r - l) >> 1);

            if (index <= mid)
                clear(index, l, mid, rt << 1);
            else
                clear(index, mid + 1, r, rt << 1 | 1);

            tree[rt] = Math.max(tree[rt << 1], tree[rt << 1 | 1]);
        }
    }

}
