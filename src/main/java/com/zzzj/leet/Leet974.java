package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

public class Leet974 {

    public static void main(String[] args) {

        System.out.println(subarraysDivByK(new int[]{7, 4, -10}, 5));

//        System.exit(0);

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

        int mod = 0;

        Map<Integer, Integer> rec = new HashMap<>();

        rec.put(0, 1);

        int ans = 0;

        for (int i = 0; i < N; i++) {

            mod = (((mod + nums[i]) % k) + k) % k;

            ans += rec.getOrDefault(mod, 0);

            rec.put(mod, rec.getOrDefault(mod, 0) + 1);
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
