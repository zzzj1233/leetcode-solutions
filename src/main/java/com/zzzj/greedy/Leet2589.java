package com.zzzj.greedy;

import cn.hutool.core.lang.Assert;
import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;

import java.util.*;

/**
 * 周一下午开始思考题目
 * <p>
 * 周三17:00才AC
 * <p>
 * 真是醉了
 *
 * @author zzzj
 * @create 2023-08-28 16:10
 */
public class Leet2589 {

    public static void main(String[] args) {

        Assert.isTrue(findMinimumTime(LeetUtils.convertInts("[[8,19,1],[3,20,1],[1,20,2],[6,13,3]]")) == 3);

        Assert.isTrue(findMinimumTime(LeetUtils.convertInts("[[1,10,7],[4,11,1],[3,19,7],[10,15,2]]")) == 8);

        Assert.isTrue(findMinimumTime(LeetUtils.convertInts("[[8,19,1],[3,20,1],[1,20,2],[6,13,3]]")) == 3);

        Assert.isTrue(findMinimumTime(LeetUtils.convertInts("[[68,129,2],[58,86,9],[112,142,10],[106,108,1],[48,48,1],[116,143,2],[28,43,5],[1,1,1],[75,83,3],[104,136,10],[11,11,1],[60,63,1],[73,111,8],[57,57,1],[117,119,3],[25,38,2],[20,21,1],[78,80,2],[17,17,1],[28,28,1],[77,117,3],[76,109,4],[61,61,1],[84,92,5],[18,41,4],[47,55,9],[74,132,6],[53,87,3],[102,131,7],[26,26,1],[66,68,3],[59,73,1],[22,30,9],[9,13,2],[31,35,2],[90,91,2],[72,72,1],[62,84,8],[105,106,2],[3,3,1],[32,32,1],[99,103,4],[45,52,4],[108,116,3],[91,123,8],[89,114,4],[94,130,7],[103,104,2],[14,17,4],[63,66,4],[98,112,7],[101,140,9],[58,58,1],[109,145,1],[8,15,8],[4,16,5],[115,141,1],[40,50,4],[118,118,1],[81,120,7]]")) == 68);

        Assert.isTrue(findMinimumTime(LeetUtils.convertInts("[[2,3,1],[4,5,1],[1,5,2]]")) == 2);

        Assert.isTrue(findMinimumTime(LeetUtils.convertInts("[[1,3,2],[2,5,3],[5,6,2]]")) == 4);

        Assert.isTrue(findMinimumTime(LeetUtils.convertInts("[[2, 5, 2], [1, 1, 1], [1, 3, 1], [4, 5, 2]]")) == 3);

//        System.out.println(right(LeetUtils.convertInts("[[1,10,7],[4,11,1],[3,19,7],[10,15,2]]")));
//
//        System.exit(0);

//        int N = 10000;

        for (; ; ) {

            int M = LeetUtils.random.nextInt(100) + 1;

            int MAX = M;

            int[][] tasks = new int[M][3];

            for (int j = 0; j < M; j++) {
                int n1 = LeetUtils.random.nextInt(MAX) + 1;
                int n2 = LeetUtils.random.nextInt(MAX) + 1;
                int n3 = LeetUtils.random.nextInt(MAX) + 1;
                tasks[j][0] = Math.min(n1, n2);
                tasks[j][1] = Math.max(n1, n2);
                tasks[j][2] = Math.min(tasks[j][1] - tasks[j][0] + 1, n3);
            }

            CopyableIterator<int[][]> it = new CopyableIterator<>(tasks, ArrayUtil::copy);

            if (findMinimumTime(it.next()) != right(it.next())) {
                System.out.println("Error");
                System.out.println(Arrays.deepToString(it.next()));
                System.out.println("findMinimumTime(it.next()) = " + findMinimumTime(it.next()));
                System.out.println("right(it.next()) = " + right(it.next()));
                return;
            }

        }

//        System.out.println("Ok");
    }

    public static int findMinimumTime(int[][] tasks) {

        int max = Arrays.stream(tasks).mapToInt(ints -> ints[1]).max().orElse(0);

        int sum = Arrays.stream(tasks).mapToInt(ints -> ints[1]).sum();

        int M = max + 1;

        int[] cnt = new int[M];

        for (int[] task : tasks) {
            for (int i = task[0]; i <= task[1]; i++) {
                cnt[i]++;
            }
        }

        Arrays.sort(tasks, (o1, o2) -> o2[0] - o1[0]);

        int N = tasks.length;

        SegmentTree tree = new SegmentTree(cnt);

        int ans = 0;

        for (int i = 0; i < tasks.length && sum > 0; i++) {

            int[] task = tasks[i];

            int start = task[0];

            int end = task[1];

            while (task[2] > 0) {

                int maxIndex = tree.queryMax(start, end);

                tree.clear(maxIndex);

                for (int[] t : tasks) {
                    if (t[2] > 0 && maxIndex >= t[0] && maxIndex <= t[1]) {
                        if (--t[2] == 0) {
                            tree.update(t[0], t[1], -1);
                        }
                        sum--;
                    }
                }

                ans++;
            }

        }


        return ans;
    }

    private static class SegmentTree {

        private final int[][] tree;

        private final int[] lazy;

        private final int N;

        public SegmentTree(int[] nums) {
            this.N = nums.length;
            this.lazy = new int[N << 2];
            this.tree = new int[N << 2][2];
            buildTree(1, N, 1, nums);
        }

        private void buildTree(int l, int r, int rt, int[] nums) {
            if (l == r) {
                tree[rt] = new int[]{nums[l - 1], l - 1};
                return;
            }

            int mid = l + ((r - l) >> 1);

            buildTree(l, mid, rt << 1, nums);

            buildTree(mid + 1, r, rt << 1 | 1, nums);

            pushUp(rt);
        }

        public void clear(int index) {
            int s = _queryMax(index, index)[0];
            update(index, index, -s);
        }

        public int[] _queryMax(int L, int R) {
            return queryMax(L + 1, R + 1, 1, N, 1);
        }

        public int queryMax(int L, int R) {
            return queryMax(L + 1, R + 1, 1, N, 1)[1];
        }

        private int[] queryMax(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return tree[rt];
            }

            pushDown(l, r, rt);

            int mid = l + ((r - l) >> 1);

            int[] left = null;
            int[] right = null;

            if (L <= mid)
                left = queryMax(L, R, l, mid, rt << 1);
            if (R > mid)
                right = queryMax(L, R, mid + 1, r, rt << 1 | 1);

            if (left == null)
                return right;
            if (right == null)
                return left;

            return max(left, right);
        }

        public void update(int L, int R, int C) {
            update(L + 1, R + 1, C, 1, N, 1);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {

            if (L <= l && R >= r) {
                lazy[rt] += C;

                // maxIndex不变
                tree[rt][0] += C;

                return;
            }

            pushDown(l, r, rt);

            int mid = l + ((r - l) >> 1);

            if (L <= mid)
                update(L, R, C, l, mid, rt << 1);
            if (R > mid)
                update(L, R, C, mid + 1, r, rt << 1 | 1);

            pushUp(rt);
        }

        private void pushUp(int rt) {
            int[] left = tree[rt << 1];
            int[] right = tree[rt << 1 | 1];

            if (left[0] == right[0])
                tree[rt] = new int[]{left[0], Math.min(left[1], right[1])};
            else
                tree[rt] = left[0] > right[0] ? new int[]{left[0], left[1]} : new int[]{right[0], right[1]};
        }

        private int[] max(int[] left, int[] right) {
            if (left[0] == right[0])
                return left[1] < right[1] ? left : right;
            return left[0] > right[0] ? left : right;
        }

        private void pushDown(int l, int r, int rt) {

            if (lazy[rt] != 0) {

                int leftChild = rt << 1;

                int rightChild = rt << 1 | 1;

                tree[leftChild][0] += lazy[rt];
                tree[rightChild][0] += lazy[rt];

                lazy[leftChild] += lazy[rt];
                lazy[rightChild] += lazy[rt];

                lazy[rt] = 0;
            }

        }

    }

    private static class Checker {

        private final int[] tree;

        public Checker(int[] tree) {
            this.tree = tree;
        }

        public int query(int L, int R) {
            int max = L;

            for (int i = L; i <= R; i++) {
                if (tree[i] > tree[max])
                    max = i;
            }

            return max;
        }

        public void clear(int index) {
            this.tree[index] = 0;
        }

        public void update(int L, int R, int C) {

            for (int i = L; i <= R; i++) {
                tree[i] += C;
            }

        }

        public static void main(String[] args) {
            int N = 10000;

            String[] OPS_STR = {
                    "Query",
                    "Update",
                    "Clear"
            };

            for (int i = 0; i < N; i++) {

                int M = 50;

                int[] arr1 = ArrayUtil.generateArray(M, 1, M);

                Checker checker = new Checker(arr1);
                SegmentTree tree = new SegmentTree(ArrayUtil.copy(arr1));

//                List<int[]> ops = new ArrayList<>();

                for (int j = 0; j < 1000; j++) {

                    int op = LeetUtils.random.nextInt(5) % 3;

                    int n1 = LeetUtils.random.nextInt(M);
                    int n2 = LeetUtils.random.nextInt(M);

                    int left = Math.min(n1, n2);

                    int right = Math.max(n1, n2);

                    int c = LeetUtils.random.nextInt(M);

//                    ops.add(new int[]{op, left, right, c});

                    if (op == 0) {
                        int m1 = checker.query(left, right);

                        int m2 = tree.queryMax(left, right);

                        if (m1 != m2) {
                            System.out.println("Error");
                            System.out.println("checker = " + m1 + " , value = " + checker.tree[m1]);
                            System.out.println("tree = " + m2 + " , value = " + checker.tree[m2]);

//                            System.out.println(ops.stream().map(ints -> String.format("Op = %s , left = %d , right = %d , C = %d %n",
//                                    OPS_STR[ints[0]], ints[1], ints[2], ints[3]
//                            )).collect(Collectors.joining()));
                            return;
                        }
                    } else if (op == 1) {
                        checker.update(left, right, c);
                        tree.update(left, right, c);
                    } else {
                        checker.clear(left);
                        tree.clear(left);
                    }
                }

            }

            System.out.println("Ok");
        }

    }

    public static int right(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int ans = 0;
        boolean[] run = new boolean[tasks[tasks.length - 1][1] + 1];
        for (int[] t : tasks) {
            int start = t[0], end = t[1], d = t[2];
            for (int i = start; i <= end; ++i)
                if (run[i]) --d;  // 去掉运行中的时间点
            for (int i = end; d > 0; --i) // 剩余的 d 填充区间后缀
                if (!run[i]) {
                    run[i] = true;
                    --d;
                    ++ans;
                }
        }
        return ans;
    }

}