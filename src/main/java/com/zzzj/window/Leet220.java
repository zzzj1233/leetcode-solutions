package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2022-01-05 12:11
 */
public class Leet220 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(50, -50, 150);
            int k = LeetUtils.random.nextInt(150);
            int t = LeetUtils.random.nextInt(150);

            if (containsNearbyAlmostDuplicate(arr, k, t) != right(arr, k, t)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                System.out.println(t);
                System.out.println(containsNearbyAlmostDuplicate(arr, k, t));
                return;
            }
        }

        System.out.println(containsNearbyAlmostDuplicate(new int[]{2147483640, 2147483641}, 1, 100));
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // k大小的窗口

        TreeSet<Long> set = new TreeSet<>();

        int l = 0;
        int r = Math.min(nums.length - 1, k);

        for (int i = 0; i <= r; i++) {
            long num = nums[i];
            Long floor = set.floor(num + t);
            if (floor != null && Math.abs(floor - num) <= t) {
                return true;
            }
            set.add(num);
        }

        r++;

        while (r < nums.length) {
            set.remove((long) nums[l]);

            long numR = nums[r];

            Long floor = set.floor(numR + t);

            if (floor != null && Math.abs(floor - numR) <= t) {
                return true;
            }

            set.add(numR);

            l++;
            r++;
        }

        return false;
    }

    public static boolean right(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }


}
