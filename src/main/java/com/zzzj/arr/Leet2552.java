package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-08-17 16:44
 */
public class Leet2552 {

    public static void main(String[] args) {
        SegmentTree tree = new SegmentTree(new int[]{1, 3, 2, 4, 5});

        System.out.println(tree.query(2, 4, 3));
    }

    public static long countQuadruplets(int[] nums) {

        SegmentTree tree = new SegmentTree(nums);

        return -1;
    }

    private static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n]; // Assuming size won't exceed 4 * n
            build(arr, 1, 0, n - 1);
        }

        // Build the segment tree
        private void build(int[] arr, int v, int tl, int tr) {
            if (tl == tr) {
                tree[v] = 0;
            } else {
                int tm = (tl + tr) / 2;
                build(arr, v * 2, tl, tm);
                build(arr, v * 2 + 1, tm + 1, tr);
                tree[v] = tree[v * 2] + tree[v * 2 + 1];
            }
        }

        // Query the count of numbers greater than arr[L] in the range [L + 1, R]
        private int query(int v, int tl, int tr, int L, int R, int x) {
            if (L > R)
                return 0;
            if (tl == L && tr == R) {
                return tree[v];
            }
            int tm = (tl + tr) / 2;
            return query(v * 2, tl, tm, L, Math.min(R, tm), x) +
                    query(v * 2 + 1, tm + 1, tr, Math.max(L, tm + 1), R, x);
        }

        // Public query method
        public int query(int L, int R, int x) {
            return query(1, 0, n - 1, L, R, x);
        }
    }

}
