package com.zzzj.contest.dweek143;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-11-09 22:34
 */
public class Q3 {

    public static void main(String[] args) {

        System.out.println(maxFrequency(
                new int[]{5, 64},
                42,
                2
        ));

        System.out.println(maxFrequency(
                new int[]{1, 4, 5},
                1,
                2
        ));

        System.out.println(maxFrequency(
                new int[]{5, 11, 20, 20},
                5,
                1
        ));

    }

    public static int maxFrequency(int[] nums, int k, int numOperations) {

        // 必须操作 numOperations 次
        // 每个下标只能操作一次
        // nums[i] += [ -k - k ]
        // 返回频率最高的元素次数

        // 是否可以2分?
        // 思考: 哪个数可以出现的次数最多?
        // 1. 不用改变的次数过多
        // 2. 其他数可以变为该数

        // 3. 遍历某个数, 如何快速求出最大次数?
        // 3.1 +=相同的数
        // 3.2 +=处于 min( numOperations , [ nums[i] - k - nums[i] + k] )

        // 4. 使用什么数据结构?
        // 线段树是否满足需求?
        // x - y : 求次数
        // 离散化nums没有问题, 问题在于 + k 如何被离散化?
        //
        int N = nums.length;

        int last = discretization(nums, k);

        int[] arr = new int[last + 1];

        for (int num : nums) {
            arr[index.get(num - k)]++;
            arr[index.get(num + k) + 1]--;
        }

        int ans = 0;
        int cur = 0;

        for (int i = 0; i < arr.length; i++) {

            Integer num = indexNum.get(i);

            Integer c = numsCnt.get(num);

            cur += arr[i];

            if (c != null) {
                ans = Math.max(ans, c + Math.min(numOperations, cur - c));
            } else {
                ans = Math.max(
                        ans,
                        Math.min(numOperations, cur)
                );
            }

            int debug = -1;
        }

        return ans;

    }

    public static HashMap<Integer, Integer> index;

    public static HashMap<Integer, Integer> indexNum;

    public static HashMap<Integer, Integer> numsCnt;

    public static int discretization(int[] arr, int k) {

        TreeSet<Integer> set = new TreeSet<>();

        numsCnt = new HashMap<>();
        indexNum = new HashMap<>();

        for (int num : arr) {
            set.add(num);
            set.add(num - k);
            set.add(num + k);
            numsCnt.put(num, numsCnt.getOrDefault(num, 0) + 1);
        }

        index = new HashMap<>();

        int last = 0;

        for (Integer num : set) {
            index.put(num, last);
            indexNum.put(last, num);
            last++;
        }

        return last;
    }

    private static class SegmentTree {

        private final int[] sum;

        private final int N;

        public SegmentTree(int[] arr) {
            this.N = arr.length + 1;
            this.sum = new int[N << 2];
            this.buildTree(arr, 1, N - 1, 1);
        }

        private void buildTree(int[] arr, int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l - 1];
                return;
            }

            int mid = l + ((r - l) >> 1);

            buildTree(arr, l, mid, rt << 1);

            buildTree(arr, mid + 1, r, rt << 1 | 1);

            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private int query(int L, int R) {
            return query(L + 1, R + 1, 1, N - 1, 1);
        }

        private int query(int L, int R, int l, int r, int rt) {

            if (L <= l && R >= r)
                return sum[rt];

            int mid = l + ((r - l) >> 1);

            int left = 0;
            int right = 0;

            if (L <= mid)
                left = query(L, R, l, mid, rt << 1);
            if (R > mid)
                right = query(L, R, mid + 1, r, rt << 1 | 1);

            return left + right;
        }

    }

}
