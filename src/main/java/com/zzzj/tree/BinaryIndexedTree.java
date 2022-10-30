package com.zzzj.tree;

public class BinaryIndexedTree {

    final int[] indexes;

    final int[] nums;

    public BinaryIndexedTree(int length) {
        indexes = new int[length + 1];
        nums = new int[length];
    }

    public BinaryIndexedTree(int[] nums) {
        indexes = new int[nums.length + 1];
        this.nums = nums;
        this.init(nums);
    }

    private void init(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            this.update(i, nums[i]);
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
