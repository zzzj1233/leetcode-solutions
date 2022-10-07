package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyIterator;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-10-07 14:59
 */
public class Leet1648 {

    public static void main(String[] args) {

        System.out.println(maxProfit(new int[]{76}, 22));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(1000, 0, 1000);
            int k = LeetUtils.random.nextInt(10000) + 100;
            CopyIterator it = CopyIterator.fromArray(arr);

            if (maxProfit(it.next(), k) != new Solution().maxProfit(it.next(), k)) {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("OK~");
    }

    public static int maxProfit(int[] inventory, int orders) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int it : inventory) {
            queue.add(it);
        }

        long ans = 0;

        int mod = 1000000007;

        while (queue.size() >= 2 && orders > 0) {
            Integer first = queue.remove();
            Integer second = queue.peek();
            //
            int items = Math.min(orders, first - second + 1);

            orders -= items;

            if (items == 1) {
                ans += first;
            } else {
                ans += (long) (second + first) * items / 2;
            }

            ans %= mod;

            first = second - 1;
            queue.add(first);
        }

        if (!queue.isEmpty() && orders > 0) {
            Integer last = queue.remove();
            int min = Math.max(1, last - orders + 1);
            int items = last - min + 1;
            if (items == 1) {
                ans += last;
            } else {
                ans += (long) items * (min + last) / 2;
            }
            ans %= mod;
        }

        return (int) ans;
    }

    private static class Solution {
        int mod = (int) 1e9 + 7;

        public int maxProfit(int[] inventory, int orders) {
            int max = 0;
            for (int i = 0; i < inventory.length; i++) {
                max = Math.max(max, inventory[i]);
            }
            long l = 0, r = max;
            long cut = 0;
            while (l <= r) {
                long mid = l + ((r - l) >> 1);
                long sum = 0;
                for (long num : inventory) {
                    if (num > mid) {
                        long size = num - mid;
                        sum += size;
                    }
                }
                if (sum >= orders) {
                    cut = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            long sum = 0;
            cut += 1;
            long ans = 0;
            for (long num : inventory) {
                if (num > cut) {
                    long size = num - cut;
                    sum += size;
                    ans += ((num + cut + 1) * size / 2) % mod;
                    ans %= mod;
                }
            }
            ans += ((orders - sum) * cut) % mod;
            ans %= mod;
            return (int) ans;
        }
    }

}
