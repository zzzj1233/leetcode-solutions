package com.zzzj.window;


import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-07-29 14:46
 */
public class Leet480 {

    // [2147483647,2147483647]
    // 2
    public static void main(String[] args) {

        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648}, 2)));

        System.exit(0);

        Solution solution = new Solution();
        for (int i = 0; i < 1000000; i++) {
            int[] arr = ArrayUtil.generateArray(5, -10, 30);
            int k = Math.min(arr.length - 1, LeetUtils.random.nextInt(arr.length) + 2);
            if (!Arrays.equals(medianSlidingWindow(arr, k), solution.medianSlidingWindow(arr, k))) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                return;
            }
        }
        System.out.println("Ok");
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        int N = nums.length;

        // initWindow
        int[] window = initWindow(nums, k);

        double[] ans = new double[N - k + 1];

        ans[0] = middleValue(window);

        for (int i = 1; i <= N - k; i++) {
            // 删除i-1个元素
            del(window, nums[i - 1]);

            // 添加i+k-1个元素
            add(window, nums[i + k - 1]);

            // 计算中位数
            ans[i] = middleValue(window);
        }

        return ans;
    }

    public static void add(int[] window, int value) {
        int N = window.length;

        for (int i = 0; i < N; i++) {
            // 插入到 i - 1的位置
            if (window[i] >= value) {
                // i ~ N 全部右移
                int temp = window[i];
                for (int j = i + 1; j < N; j++) {
                    int temp2 = window[j];
                    window[j] = temp;
                    temp = temp2;
                }
                window[i] = value;
                return;
            }
        }

        window[N - 1] = value;
    }

    public static void del(int[] window, int value) {
        int index = Arrays.binarySearch(window, value);
        // index右的元素全部左移
        int N = window.length - 2;

        int temp = window[N + 1];

        while (N >= index) {
            int temp2 = window[N];
            window[N] = temp;
            temp = temp2;
            N--;
        }

    }

    public static double middleValue(int[] window) {
        int len = window.length / 2;
        if (window.length % 2 == 0) {
            return (double) window[len - 1] / 2 + (double) window[len] / 2;
        } else {
            return window[len];
        }
    }

    public static int[] initWindow(int[] nums, int k) {
        int[] window = new int[k];

        for (int i = 0; i < k; i++) {
            window[i] = nums[i];
        }

        Arrays.sort(window);

        return window;
    }

    private static class Solution {
        public double[] medianSlidingWindow(int[] nums, int k) {
            DualHeap dh = new DualHeap(k);
            for (int i = 0; i < k; ++i) {
                dh.insert(nums[i]);
            }
            double[] ans = new double[nums.length - k + 1];
            ans[0] = dh.getMedian();
            for (int i = k; i < nums.length; ++i) {
                dh.insert(nums[i]);
                dh.erase(nums[i - k]);
                ans[i - k + 1] = dh.getMedian();
            }
            return ans;
        }
    }

    private static class DualHeap {
        // 大根堆，维护较小的一半元素
        private PriorityQueue<Integer> small;
        // 小根堆，维护较大的一半元素
        private PriorityQueue<Integer> large;
        // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
        private Map<Integer, Integer> delayed;

        private int k;
        // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
        private int smallSize, largeSize;

        public DualHeap(int k) {
            this.small = new PriorityQueue<Integer>(new Comparator<Integer>() {
                public int compare(Integer num1, Integer num2) {
                    return num2.compareTo(num1);
                }
            });
            this.large = new PriorityQueue<Integer>(new Comparator<Integer>() {
                public int compare(Integer num1, Integer num2) {
                    return num1.compareTo(num2);
                }
            });
            this.delayed = new HashMap<Integer, Integer>();
            this.k = k;
            this.smallSize = 0;
            this.largeSize = 0;
        }

        public double getMedian() {
            return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
        }

        public void insert(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                ++smallSize;
            } else {
                large.offer(num);
                ++largeSize;
            }
            makeBalance();
        }

        public void erase(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);
            if (num <= small.peek()) {
                --smallSize;
                if (num == small.peek()) {
                    prune(small);
                }
            } else {
                --largeSize;
                if (num == large.peek()) {
                    prune(large);
                }
            }
            makeBalance();
        }

        // 不断地弹出 heap 的堆顶元素，并且更新哈希表
        private void prune(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty()) {
                int num = heap.peek();
                if (delayed.containsKey(num)) {
                    delayed.put(num, delayed.get(num) - 1);
                    if (delayed.get(num) == 0) {
                        delayed.remove(num);
                    }
                    heap.poll();
                } else {
                    break;
                }
            }
        }

        // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
        private void makeBalance() {
            if (smallSize > largeSize + 1) {
                // small 比 large 元素多 2 个
                large.offer(small.poll());
                --smallSize;
                ++largeSize;
                // small 堆顶元素被移除，需要进行 prune
                prune(small);
            } else if (smallSize < largeSize) {
                // large 比 small 元素多 1 个
                small.offer(large.poll());
                ++smallSize;
                --largeSize;
                // large 堆顶元素被移除，需要进行 prune
                prune(large);
            }
        }
    }

}
