package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-07-29 17:22
 */
public class Leet683 {

    public static void main(String[] args) {
        System.out.println(kEmptySlots(new int[]{2, 3, 1, 5, 4}, 3));
        System.out.println(right(new int[]{2, 3, 1, 5, 4}, 3));
        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(10, 1, 5);
            int[] distinct = ArrayUtil.distinct(arr);
            int k = LeetUtils.random.nextInt(distinct.length);
            try {
                if (kEmptySlots(distinct, k) != right(distinct, k)) {
                    System.out.println("Error");
                    System.out.println(Arrays.toString(distinct));
                    System.out.println(k);
                    System.out.println(kEmptySlots(distinct, k));
                    System.out.println(right(distinct, k));
                    return;
                }
            } catch (Exception e) {
                continue;
            }
        }
        System.out.println("Ok");
    }

    public static int kEmptySlots(int[] bulbs, int k) {
        // 窗口控制在

        // i ~ i + k + 1

        // i和i + k + 1必须是亮着的

        // 中间必须是不亮的

        int N = bulbs.length;

        if (N < 2) {
            return -1;
        }

        Set<Integer> lighting = new HashSet<>();

        int left = 1;

        int preLight = bulbs[0] - 1;

        lighting.add(preLight);

        while (left < N) {
            int nextLight = bulbs[left] - 1;

            int distance = nextLight - preLight;

            if (distance > k && lighting.contains(nextLight - k - 1)) {
                return left + 1;
            }

            lighting.add(nextLight);
            left++;
            preLight = nextLight;
        }

        return -1;
    }

    public static int right(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            days[flowers[i] - 1] = i + 1;
        }

        int ans = Integer.MAX_VALUE;
        int left = 0, right = k + 1;

        search:
        while (right < days.length) {
            for (int i = left + 1; i < right; ++i) {
                if (days[i] < days[left] || days[i] < days[right]) {
                    left = i;
                    right = i + k + 1;
                    continue search;
                }
            }
            ans = Math.min(ans, Math.max(days[left], days[right]));
            left = right;
            right = left + k + 1;
        }

        return ans < Integer.MAX_VALUE ? ans : -1;
    }

}
