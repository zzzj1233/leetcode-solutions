package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leet2488 {

    public static void main(String[] args) {

        System.out.println(countSubarrays(new int[]{2, 1, 5, 3, 4}, 2));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int N = 500;

            int[] arr = ArrayUtil.distinctArray(N);

            ArrayCopyIterator it = ArrayCopyIterator.fromArray(arr);

            int k = arr[LeetUtils.random.nextInt(arr.length)];

            int r = countSubarrays(it.next(), k);

            int rr = right(it.next(), k);

            if (r != rr) {
                System.out.println("Error");
                System.out.println("it.next() = " + Arrays.toString(it.next()));
                System.out.println("k = " + k);
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                return;
            }
        }

        System.out.println("Ok");
    }

    public static int countSubarrays(int[] nums, int k) {

        int kIndex = -1;

        int N = nums.length;

        for (int i = 0; i < N; i++) {
            if (nums[i] == k) {
                kIndex = i;
                break;
            }
        }

        if (kIndex == -1)
            return 0;

        Map<Integer, Integer> right = new HashMap<>(N);

        right.put(k, 1);

        int index = k;

        for (int i = kIndex + 1; i < N; i++) {
            if (nums[i] > k)
                index++;
            else
                index--;
            right.put(index, right.getOrDefault(index, 0) + 1);
        }

        index = k;

        int ans = right.getOrDefault(k, 0) + right.getOrDefault(k + 1, 0);

        for (int i = kIndex - 1; i >= 0; i--) {
            if (nums[i] > k)
                index++;
            else
                index--;

            if (index <= k) {
                ans += right.getOrDefault(k + (k - index), 0);
                ans += right.getOrDefault(k + (k - index) + 1, 0);
            } else {
                ans += right.getOrDefault(k - (index - k), 0);
                ans += right.getOrDefault(k - (index - k) + 1, 0);
            }
        }

        return ans;
    }

    public static int right(int[] nums, int k) {
        int n = nums.length;
        int kIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                kIndex = i;
                break;
            }
        }
        int ans = 0;
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        counts.put(0, 1);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += sign(nums[i] - k);
            if (i < kIndex) {
                counts.put(sum, counts.getOrDefault(sum, 0) + 1);
            } else {
                int prev0 = counts.getOrDefault(sum, 0);
                int prev1 = counts.getOrDefault(sum - 1, 0);
                ans += prev0 + prev1;
            }
        }
        return ans;
    }

    public static int sign(int num) {
        if (num == 0) {
            return 0;
        }
        return num > 0 ? 1 : -1;
    }

}
