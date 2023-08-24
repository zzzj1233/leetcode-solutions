package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * 线段树
 *
 * @author zzzj
 * @create 2021-04-25 11:10
 */
public class SegmentTree {

    private final int N;

    private final int[] tree;

    private final int[] lazy;

    private final boolean[] changed;

    private final int[] update;

    public SegmentTree(int[] arr) {
        this.N = arr.length;
        this.tree = new int[N << 2];
        this.lazy = new int[N << 2];
        this.changed = new boolean[N << 2];
        this.update = new int[N << 2];
        buildTree(arr, 1, N, 1);
    }

    public void update(int L, int R, int C) {
        update(L + 1, R + 1, C, 1, N, 1);
    }

    private void update(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && R >= r) {
            changed[rt] = true;
            update[rt] = C;
            lazy[rt] = 0;
            tree[rt] = (r - l + 1) * C;
            return;
        }

        pushDown(l, r, rt);

        int mid = l + ((r - l) >> 1);

        if (L <= mid)
            update(L, R, C, l, mid, leftChild(rt));
        if (R > mid)
            update(L, R, C, mid + 1, r, rightChild(rt));

        pushUp(rt);
    }

    public void add(int L, int R, int C) {
        add(L + 1, R + 1, C, 1, N, 1);
    }

    private void add(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && R >= r) {
            lazy[rt] += C;
            tree[rt] += (r - l + 1) * C;
            return;
        }

        pushDown(l, r, rt);

        int mid = l + ((r - l) >> 1);

        if (L <= mid)
            add(L, R, C, l, mid, leftChild(rt));
        if (R > mid)
            add(L, R, C, mid + 1, r, rightChild(rt));

        pushUp(rt);
    }

    private void pushDown(int l, int r, int rt) {

        int LC = leftChild(rt);
        int RC = rightChild(rt);

        if (changed[rt]) {
            changed[rt] = false;
            changed[LC] = true;
            changed[RC] = true;

            lazy[LC] = 0;
            lazy[RC] = 0;

            int mid = l + ((r - l) >> 1);
            int leftChildCnt = mid - l + 1;
            int rightChildCnt = r - mid;

            update[LC] = update[rt];
            update[RC] = update[rt];
            tree[LC] = leftChildCnt * update[rt];
            tree[RC] = rightChildCnt * update[rt];

            update[rt] = 0;
        }

        if (lazy[rt] != 0) {

            int mid = l + ((r - l) >> 1);
            int leftChildCnt = mid - l + 1;
            int rightChildCnt = r - mid;

            lazy[LC] += lazy[rt];
            lazy[RC] += lazy[rt];
            tree[LC] += leftChildCnt * lazy[rt];
            tree[RC] += rightChildCnt * lazy[rt];
            lazy[rt] = 0;
        }

    }

    private void pushUp(int rt) {
        tree[rt] = tree[leftChild(rt)] + tree[rightChild(rt)];
    }

    public int query(int L, int R) {
        return query(L + 1, R + 1, 1, N, 1);
    }

    private int query(int L, int R, int l, int r, int rt) {
        if (L <= l && R >= r) {
            return tree[rt];
        }

        pushDown(l, r, rt);

        int sum = 0;

        int mid = l + ((r - l) >> 1);

        if (L <= mid)
            sum += query(L, R, l, mid, leftChild(rt));
        if (R > mid)
            sum += query(L, R, mid + 1, r, rightChild(rt));

        return sum;
    }

    private void buildTree(int[] arr, int l, int r, int rt) {
        if (l == r) {
            tree[rt] = arr[l - 1];
            return;
        }

        int mid = l + ((r - l) >> 1);

        buildTree(arr, l, mid, leftChild(rt));
        buildTree(arr, mid + 1, r, rightChild(rt));

        pushUp(rt);
    }

    private int leftChild(int rt) {
        return rt << 1;
    }

    private int rightChild(int rt) {
        return rt << 1 | 1;
    }

    private static class TreeChecker {

        private final int[] arr;

        public TreeChecker(int[] arr) {
            this.arr = arr;
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public int query(int L, int R) {
            int sum = 0;
            for (int i = L; i <= R; i++) {
                sum += arr[i];
            }
            return sum;
        }

        public static void main(String[] args) {

            int LOOP_TIMES = 10000;

            for (int i = 0; i < LOOP_TIMES; i++) {

                int OP_TIMES = 1000;

                int N = 1000;

                int L = 1;

                int R = 100;

                int[] arr1 = ArrayUtil.generateArray(N, L, R);

                int[] arr2 = Arrays.copyOfRange(arr1, 0, arr1.length);

                SegmentTree segmentTree = new SegmentTree(arr1);

                TreeChecker checker = new TreeChecker(arr2);

                for (int j = 0; j < OP_TIMES; j++) {
                    int op = LeetUtils.random.nextInt(3);

                    int bounds1 = LeetUtils.random.nextInt(arr1.length);
                    int bounds2 = LeetUtils.random.nextInt(arr1.length);

                    int left = Math.min(bounds1, bounds2);
                    int right = Math.max(bounds1, bounds2);

                    int C = LeetUtils.random.nextInt(R) + L;

                    switch (op) {
                        case 0:
                            segmentTree.add(left, right, C);
                            checker.add(left, right, C);
                            break;
                        case 1:
                            segmentTree.update(left, right, C);
                            checker.update(left, right, C);
                            break;
                        case 2:
                        default:
                            int r1 = segmentTree.query(left, right);

                            int r2 = checker.query(left, right);

                            if (r1 != r2) {
                                System.out.println("Error");
                                return;
                            }
                            break;
                    }
                }

            }

            System.out.println("Ok~");
        }
    }
}
