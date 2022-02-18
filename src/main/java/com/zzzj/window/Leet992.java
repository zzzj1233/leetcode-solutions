package com.zzzj.window;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-01-14 12:00
 */
public class Leet992 {


    public static void main(String[] args) {
//        int n = 5;
//        int[] ints = ArrayUtil.generateArray(n, 1, 5);
//        int k = LeetUtils.random.nextInt(3) + 1;
//
//        System.out.println(Arrays.toString(ints));
//        System.out.println(k);
//        System.out.println(subarraysWithKDistinct(ints, k));

        // 25
        // 225
        // 255
        // 2255
        // 2555
        // 22555
        System.out.println(subarraysWithKDistinct(new int[]{2, 2, 5, 5, 5}, 2));
    }


    // 12,
    // 21 121
    // 212,1212,12
    // 23
    //
    // 输入：A = [1,2,1,2,3], K = 2
    // 输出：7
    // 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
    public static int subarraysWithKDistinct(int[] nums, int k) {

        int ans = 0;

        int l = 0;
        int r = 0;

        Map<Integer, Integer> record = new HashMap<>();

        while (r < nums.length) {
            record.put(nums[r], record.getOrDefault(nums[r], 0) + 1);
            r++;

            while (record.size() > k && l < r) {
                Integer count = record.get(nums[l]);
                if (count == 1) {
                    record.remove(nums[l]);
                } else {
                    record.put(nums[l], count - 1);
                }
                l++;
            }

            if (record.size() == k) {
                System.out.println(Arrays.toString(Arrays.copyOfRange(nums, l, r)));
                ans += r - l + 1 - k;
            }

        }

        return ans;
    }

}
