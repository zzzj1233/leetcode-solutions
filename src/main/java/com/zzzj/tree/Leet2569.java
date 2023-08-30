package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-08-24 11:46
 */
public class Leet2569 {

    public static void main(String[] args) {

        checkTree();

        for (int i = 0; i < 1000; i++) {

            int N = 50;

            int[] nums1 = ArrayUtil.generateArray(N, 0, 1);
            int[] nums2 = ArrayUtil.generateArray(N, 0, 10);

            int[] nums3 = Arrays.copyOfRange(nums1, 0, nums1.length);
            int[] nums4 = Arrays.copyOfRange(nums2, 0, nums2.length);

//            int M = 100;
            int M = 500;

            int[][] queries = new int[M][3];

            for (int j = 0; j < queries.length; j++) {

                int opType = LeetUtils.random.nextInt(3) + 1;

                queries[j][0] = opType;

                if (opType == 1) {
                    int L = LeetUtils.random.nextInt(nums1.length);
                    int R = Math.min(nums1.length - 1, LeetUtils.random.nextInt(nums1.length) + LeetUtils.random.nextInt(nums1.length));

                    queries[j][1] = Math.min(L, R);
                    queries[j][2] = Math.max(L, R);
                } else if (opType == 2) {
                    int K = LeetUtils.random.nextInt(10);
                    queries[j][1] = K;
                }

            }

            long[] r1 = handleQuery(nums1, nums2, queries);

            long[] r2 = right(nums3, nums4, queries);

            if (!Arrays.equals(r1, r2)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(nums1));
                System.out.println(Arrays.toString(nums2));
                System.out.println(Arrays.deepToString(queries));
                System.out.println("r1 = " + Arrays.toString(r1));
                System.out.println("r2 = " + Arrays.toString(r2));
                return;
            }

        }
    }

    public static boolean checkTree() {

        for (int i = 0; i < 1000; i++) {
            int N = 40;

            SegmentTree tree = new SegmentTree(new int[N]);

            TreeChecker checker = new TreeChecker(new int[N]);

            int M = 1000;

            List<int[]> save = new ArrayList<>();

            for (int j = 0; j < M; j++) {

                int opType = LeetUtils.random.nextInt(2);

                int L = LeetUtils.random.nextInt(N);
                int R = Math.min(N - 1, LeetUtils.random.nextInt(N) + LeetUtils.random.nextInt(N));

                int left = Math.min(L, R);
                int right = Math.max(L, R);

                save.add(new int[]{opType, left + 1, right + 1});

                if (opType == 0) {
                    tree.update(left + 1, right + 1);
                    checker.update(left, right);
                } else {

                    int r1 = tree.query(left + 1, right + 1);

                    int r2 = checker.sum(left, right);

                    if (r1 != r2) {
                        System.out.println("Error");
                        System.out.println(
                                "[" +
                                        save.stream()
                                                .map(Arrays::toString)
                                                .collect(Collectors.joining(","))
                                        + "]"
                        );
                        System.out.println("r1 = " + r1);
                        System.out.println("r2 = " + r2);
                        return false;
                    }
                }

            }
        }

        return true;
    }

    public static long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {

        SegmentTree tree = new SegmentTree(nums1);

        long sum = Arrays.stream(nums2).asLongStream().sum();

        int N = nums1.length;

        List<Long> list = new ArrayList<>();

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            if (query[0] == 1) {
                int L = query[1];
                int R = query[2];
                tree.update(L + 1, R + 1);
            } else if (query[0] == 2) {
                int K = query[1];
                sum += (long) K * tree.query(1, N);
            } else {
                list.add(sum);
            }

        }

        long[] ans = new long[list.size()];

        for (int i = 0; i < list.size(); i++) ans[i] = list.get(i);

        return ans;
    }

    private static class SegmentTree {

        private final int[] tree;

        private final boolean[] changed;

        private final int N;

        public SegmentTree(int[] nums) {
            this.N = nums.length;

            this.tree = new int[N << 2];
            this.changed = new boolean[N << 2];

            build(nums, 1, N, 1);
        }

        private void build(int[] nums, int L, int R, int rt) {
            if (L == R) {
                tree[rt] = nums[L - 1];
                return;
            }

            int mid = L + ((R - L) >> 1);

            build(nums, L, mid, leftChild(rt));
            build(nums, mid + 1, R, rightChild(rt));

            tree[rt] += tree[leftChild(rt)] + tree[rightChild(rt)];
        }

        public int query(int L, int R) {
            return query(L, R, 1, N, 1);
        }

        private int query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return tree[rt];
            }

            int mid = l + ((r - l) >> 1);

            int sum = 0;

            int LC = leftChild(rt);
            int RC = rightChild(rt);

            pushDown(l, r, rt, mid, LC, RC);

            if (L <= mid)
                sum += query(L, R, l, mid, leftChild(rt));
            if (R > mid)
                sum += query(L, R, mid + 1, r, rightChild(rt));

            return sum;
        }

        public void update(int L, int R) {
            update(L, R, 1, N, 1);
        }

        /**
         * @param L  TASK_L
         * @param R  TASK_R
         * @param l  rt_l
         * @param r  rt_r
         * @param rt rt ( treeIndex )
         */
        private void update(int L, int R, int l, int r, int rt) {
            if (l == r) {
                tree[rt] ^= 1;
                return;
            }

            if (L <= l && R >= r) {
                changed[rt] = !changed[rt];
                tree[rt] = (r - l + 1) - tree[rt];
                return;
            }

            int mid = l + ((r - l) >> 1);

            int LC = leftChild(rt);
            int RC = rightChild(rt);

            pushDown(l, r, rt, mid, LC, RC);

            if (L <= mid)
                update(L, R, l, mid, LC);
            if (R > mid)
                update(L, R, mid + 1, r, RC);

            tree[rt] = tree[LC] + tree[RC];
        }

        private void pushDown(int l, int r, int rt, int mid, int LC, int RC) {
            if (changed[rt]) {
                changed[rt] = false;
                changed[LC] = !changed[LC];
                changed[RC] = !changed[RC];

                int leftChildCnt = mid - l + 1;
                int rightChildCnt = r - mid;

                tree[LC] = leftChildCnt - tree[LC];
                tree[RC] = rightChildCnt - tree[RC];
            }
        }

        private int leftChild(int rt) {
            return rt << 1;
        }

        private int rightChild(int rt) {
            return rt << 1 | 1;
        }

    }

    public static long[] right(int[] nums1, int[] nums2, int[][] queries) {
        int N = nums1.length;

        List<Long> list = new ArrayList<>();

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            if (query[0] == 1) {
                int L = query[1];
                int R = query[2];
                for (int j = L; j <= R; j++) {
                    nums1[j] ^= 1;
                }
            } else if (query[0] == 2) {
                int K = query[1];
                for (int j = 0; j < N; j++) {
                    nums2[j] += nums1[j] * K;
                }
            } else {
                list.add(Arrays.stream(nums2).asLongStream().sum());
            }

        }

        long[] ans = new long[list.size()];

        for (int i = 0; i < list.size(); i++)
            ans[i] = list.get(i);

        return ans;
    }

    private static class TreeChecker {

        private final int[] nums;

        public TreeChecker(int[] nums) {
            this.nums = nums;
        }

        public void update(int L, int R) {
            for (int i = L; i <= R; i++) {
                nums[i] ^= 1;
            }
        }

        public int sum(int L, int R) {
            int s = 0;
            for (int i = L; i <= R; i++) {
                s += nums[i];
            }
            return s;
        }
    }

}

