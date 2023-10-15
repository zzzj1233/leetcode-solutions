package com.zzzj.arr;

import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-09-28 11:23
 */
public class Leet315 {

    public static void main(String[] args) {

//        System.out.println(countSmaller(new int[]{5, 2, 6, 1}));
//
//        System.out.println(countSmaller(new int[]{8, 3, 0, 2, 5}));
//
//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            final int[] arr = ArrayUtil.generateArray(500, 0, 1000);
            if (!countSmaller(arr).equals(new Solution().countSmaller(arr))) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(countSmaller(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static final Item[] EMPTY = new Item[0];

    public static List<Integer> countSmaller(int[] nums) {

        int[] sorted = Arrays.stream(nums)
                .distinct()
                .sorted()
                .toArray();

        BinaryIndexedTree tree = new BinaryIndexedTree(sorted.length);

        int N = nums.length;

        List<Integer> ans = new ArrayList<>(N);

        for (int i = N - 1; i >= 0; i--) {

            int index = Arrays.binarySearch(sorted, nums[i]);

            ans.add((int) tree.query(index - 1));

            tree.update(index);
        }

        Collections.reverse(ans);

        return ans;
    }

    private static class BinaryIndexedTree {
        private final long[] data;

        public BinaryIndexedTree(int N) {
            this.data = new long[N + 1];
        }

        public long query(int value) {
            int index = value + 1;
            int cnt = 0;
            while (index > 0) {
                cnt += data[index];
                index -= lowbit(index);
            }
            return cnt;
        }

        public void update(int value) {
            int index = value + 1;
            while (index < data.length) {
                data[index]++;
                index += lowbit(index);
            }
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }

    public static List<Integer> countSmaller_old(int[] nums) {
        int N = nums.length;

        Item[] items = new Item[N];

        for (int i = 0; i < N; i++) {
            items[i] = new Item(nums[i], 0, i);
        }

        // merge sort
        items = merge(items, 0, N - 1);

        List<Integer> ans = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            ans.add(0);
        }

        for (Item item : items) {
            ans.set(item.index, item.smaller);
        }

        return ans;
    }

    public static Item[] merge(Item[] items, int left, int right) {
        if (left > right) {
            return EMPTY;
        }

        if (left == right) {
            return new Item[]{items[left]};
        }

        int mid = left + ((right - left) >> 1);

        Item[] L = merge(items, left, mid);
        Item[] R = merge(items, mid + 1, right);

        // sort l 和 r
        return mergeSort(L, R);
    }

    // 5 2 6 1
    public static Item[] mergeSort(Item[] L, Item[] R) {
        int leftIndex = 0;
        int rightIndex = 0;

        Item[] result = new Item[L.length + R.length];

        int resIndex = 0;

        while (leftIndex < L.length || rightIndex < R.length) {
            // 左边到底了
            if (leftIndex >= L.length) {                    // 1 2 | 3 4
                result[resIndex] = R[rightIndex];
                rightIndex++;
            } else if (rightIndex >= R.length) { // 右边到底了   3 4 | 1 2
                result[resIndex] = L[leftIndex];
                leftIndex++;
                if (leftIndex < L.length) {
                    L[leftIndex].smaller += rightIndex;
                }
            } else if (L[leftIndex].num <= R[rightIndex].num) { // 1 x | 2 5
                result[resIndex] = L[leftIndex];
                leftIndex++;
                if (leftIndex < L.length) {
                    L[leftIndex].smaller += rightIndex;
                }
            } else {                                            // 3 5 | 2 4
                result[resIndex] = R[rightIndex];               // 2 || 3 5 | 4
                rightIndex++;                                   // 2 3 || 5 | 4
                L[leftIndex].smaller++;         // 2 3 4 || 5
            }
            resIndex++;
        }

        return result;
    }


    private static class Item {
        int num;
        int smaller;
        int index;

        public Item(int num, int smaller, int index) {
            this.num = num;
            this.smaller = smaller;
            this.index = index;
        }

        @Override
        public String toString() {
            return num + " ------------ " + smaller;
        }
    }

    private static class Solution {
        private int[] c;
        private int[] a;

        public List<Integer> countSmaller(int[] nums) {
            List<Integer> resultList = new ArrayList<Integer>();
            discretization(nums);
            init(nums.length + 5);
            for (int i = nums.length - 1; i >= 0; --i) {
                int id = getId(nums[i]);
                resultList.add(query(id - 1));
                update(id);
            }
            Collections.reverse(resultList);
            return resultList;
        }

        private void init(int length) {
            c = new int[length];
            Arrays.fill(c, 0);
        }

        private int lowBit(int x) {
            return x & (-x);
        }

        private void update(int pos) {
            while (pos < c.length) {
                c[pos] += 1;
                pos += lowBit(pos);
            }
        }

        private int query(int pos) {
            int ret = 0;
            while (pos > 0) {
                ret += c[pos];
                pos -= lowBit(pos);
            }

            return ret;
        }

        private void discretization(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(num);
            }
            int size = set.size();
            a = new int[size];
            int index = 0;
            for (int num : set) {
                a[index++] = num;
            }
            Arrays.sort(a);
        }

        private int getId(int x) {
            return Arrays.binarySearch(a, x) + 1;
        }
    }

}
