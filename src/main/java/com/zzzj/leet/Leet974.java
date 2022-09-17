package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

public class Leet974 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(100, 10, 200);
            int K = LeetUtils.random.nextInt(200) + 2;
            if (subarraysDivByK(arr, K) != right(arr, K)) {
                System.out.println("ERROR");
                System.out.println(subarraysDivByK(arr, K));
                System.out.println(right(arr, K));
                return;
            }
        }
        System.out.println("OK");
    }

    public static int subarraysDivByK(int[] nums, int k) {
        int N = nums.length;

        int[] counter = new int[5];

        counter[0] = 1;

        int sum = 0;

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            sum += num;

            ans += counter[sum % 5];

            counter[sum % 5]++;
        }

        return ans;
    }

    public static int right(int[] A, int K) {
        int[] map = new int[K];
        ++map[0];
        int prefix = 0, res = 0;
        for (int a : A) {
            prefix = (a + prefix) % K;
            if (prefix < 0) prefix += K;
            res += map[prefix]++;
        }
        return res;
    }

}
