package com.zzzj.window;


import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-01-14 12:00
 */
public class Leet992 {

    public static void main(String[] args) {

        System.out.println(subarraysWithKDistinct(new int[]{1, 4, 4, 1}, 2));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int M = 4000;

            int[] arr = ArrayUtil.generateArray(M, 1, M);

            int k = LeetUtils.random.nextInt(arr.length) + 1;

            int r = subarraysWithKDistinct(arr, k);

            int rr = right(arr, k);

            if (r != rr) {
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("k = " + k);
                return;
            }
        }

        System.out.println("Ok");
    }

    public static int subarraysWithKDistinct(int[] nums, int k) {

        int N = nums.length;

        Map<Integer, Integer> window = new HashMap<>(N);

        Map<Integer, Integer> inner = new HashMap<>(N);

        int innerEnd = 0;

        int left = 0;

        int ans = 0;

        for (int right = 0; right < N; right++) {

            int num = nums[right];

            incr(window, num);

            if (inner.size() < k) {
                incr(inner, num);
                innerEnd++;
            }

            while (window.size() > k) {

                ans += right - innerEnd + 1;

                decr(window, nums[left]);
                decr(inner, nums[left]);

                while (inner.size() < k) {
                    incr(inner, nums[innerEnd]);
                    innerEnd++;
                }

                left++;
            }

        }

        while (left < N && window.size() >= k) {

            ans += N - innerEnd + 1;

            decr(inner, nums[left]);
            decr(window, nums[left]);

            while (innerEnd < N && inner.size() < k) {
                incr(inner, nums[innerEnd]);
                innerEnd++;
            }

            left++;
        }


        return ans;
    }

    private static void incr(Map<Integer, Integer> map, int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    private static boolean decr(Map<Integer, Integer> map, int num) {
        Integer old = map.get(num);

        if (old == 1) {
            map.remove(num);
            return true;
        }

        map.put(num, old - 1);

        return false;
    }

    public static int right(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    /**
     * @param A
     * @param K
     * @return 最多包含 K 个不同整数的子区间的个数
     */
    private static int atMostKDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];

        int left = 0;
        int right = 0;
        // [left, right) 里不同整数的个数
        int count = 0;
        int res = 0;
        // [left, right) 包含不同整数的个数小于等于 K
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            // [left, right) 区间的长度就是对结果的贡献
            res += right - left;
        }
        return res;
    }


}
