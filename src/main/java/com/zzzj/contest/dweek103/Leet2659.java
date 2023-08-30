package com.zzzj.contest.dweek103;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-08-23 11:25
 */
public class Leet2659 {


    public static void main(String[] args) {

        System.out.println(countOperationsToEmptyArray(new int[]{1, 2, 3, 4}));
////
        System.out.println(countOperationsToEmptyArray(new int[]{3, 4, -1}));
//
        System.out.println(countOperationsToEmptyArray(new int[]{3, 2, 1, 4, 5}));

        System.out.println(countOperationsToEmptyArray(new int[]{5, 3, 2, 1, 4}));

    }

    public static long countOperationsToEmptyArray(int[] nums) {

        int N = nums.length;

        TreeSet<Integer> set = new TreeSet<>(Comparator.comparingInt(o -> nums[o]));

        for (int i = N - 1; i >= 0; i--)
            set.add(i);

        BinaryIndexedTree tree = new BinaryIndexedTree(nums);

        Integer first = set.pollFirst();

        Integer prev = first;

        long ans = (long) prev + 1;

        tree.update(prev, -1);

        for (Integer index : set) {
            ans += calc(prev, index, tree, N);
            prev = index;
        }

        return ans;
    }

    public static long calc(
            int prevIndex,
            int index,
            BinaryIndexedTree tree,
            int N
    ) {

        // index - N有多少个元素
        if (index > prevIndex) {
            tree.update(index, -1);
            return tree.sum(prevIndex, index) + 1;
        }

        // 0 - index
        int d1 = tree.sum(0, index) - 1;

        int d2 = tree.sum(prevIndex, N - 1);

        tree.update(index, -1);

        return d1 + d2 + 1;
    }

    public static class BinaryIndexedTree {

        final int[] indexes;

        public BinaryIndexedTree(int[] nums) {
            indexes = new int[nums.length + 1];
            this.init(nums);
        }

        private void init(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                this.update(i, 1);
            }
        }

        public void update(int index, int newValue) {
            index++;
            while (index < indexes.length) {
                indexes[index] += newValue;
                index += lowbit(index);
            }
        }


        public int sum(int rangeX, int rangeY) {
            return getValue(rangeY) - getValue(rangeX - 1);
        }

        public int getValue(int index) {
            index++;
            int sum = 0;
            while (index > 0) {
                sum += indexes[index];
                index -= lowbit(index);
            }
            return sum;
        }

        private int lowbit(int x) {
            return x & (~x + 1);
        }

    }
}
