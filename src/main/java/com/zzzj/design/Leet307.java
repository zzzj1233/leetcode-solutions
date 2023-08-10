package com.zzzj.design;

/**
 * @author zzzj
 * @create 2023-08-08 12:01
 */
public class Leet307 {

    public static void main(String[] args) {

        NumArray numArray = new NumArray(new int[]{1, 3, 5});

        System.out.println(numArray.sumRange(0, 2));

        numArray.update(1, 2);

        System.out.println(numArray.sumRange(1, 2));

        System.out.println(numArray.sumRange(0, 2));

    }

    private static class NumArray {

        private final BinaryIndexedTree tree;

        private final int[] nums;

        public NumArray(int[] nums) {
            this.tree = new BinaryIndexedTree(nums);
            this.nums = nums;
        }

        public void update(int index, int val) {
            int update = val - nums[index];
            nums[index] = val;
            this.tree.update(index + 1, update);
        }

        public int sumRange(int left, int right) {
            return this.tree.query(right + 1) - this.tree.query(left);
        }

    }

    private static class BinaryIndexedTree {

        private final int N;

        private final int[] tree;

        public BinaryIndexedTree(int[] nums) {
            this.N = nums.length + 1;
            this.tree = new int[N];

            for (int i = 1; i < N; i++) {
                this.update(i, nums[i - 1]);
            }

        }

        public void update(int index, int value) {

            while (index < N) {
                tree[index] += value;
                index += lowbit(index);
            }

        }

        public int query(int index) {

            int sum = 0;

            while (index > 0) {
                sum += tree[index];
                index -= lowbit(index);
            }

            return sum;
        }

    }

    public static int lowbit(int x) {
        return x & ((~x) + 1);
    }

}
