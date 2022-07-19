package com.zzzj.dp;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2022-07-11 11:39
 */
public class Leet975 {

    public static void main(String[] args) {
//        System.out.println(oddEvenJumps(new int[]{10, 13, 12, 14, 15}));
//        System.out.println(oddEvenJumps(new int[]{2, 3, 1, 1, 4}));
//        System.out.println(oddEvenJumps(new int[]{5, 1, 3, 4, 2}));

//        System.out.println(oddEvenJumps(new int[]{2, 2, 6, 8, 4}));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            final int[] arr = ArrayUtil.generateArray(100, 0, 1000);
            if (oddEvenJumps(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(oddEvenJumps(arr));
                System.out.println(right(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int oddEvenJumps(int[] arr) {

        int N = arr.length;


        TreeMap<Integer, Integer> map = new TreeMap<>();

        boolean[][] dp = new boolean[N][2];

        // 0 = 偶数
        dp[N - 1][0] = true;
        // 1 = 奇数
        dp[N - 1][1] = true;

        int ans = 1;

        map.put(arr[N - 1], N - 1);

        for (int i = N - 2; i >= 0; i--) {
            int num = arr[i];

            Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(arr[i]);

            Map.Entry<Integer, Integer> floor = map.floorEntry(arr[i]);

            Integer nextIndex = ceiling != null ? ceiling.getValue() : null;

            // 奇数次跳跃找 >= num的最小值
            if (nextIndex != null) {
                dp[i][1] = dp[nextIndex][0];
                if (dp[i][1]) {
                    ans++;
                }
            }

            // 偶数次跳跃找 <= num的最大值
            Integer nextIndex2 = floor != null ? floor.getValue() : null;

            if (nextIndex2 != null) {
                dp[i][0] = dp[nextIndex2][1];
            }

            map.put(arr[i], i);
        }

        return ans;
    }

    public static int right(int[] A) {
        int N = A.length;
        if (N <= 1) return N;
        boolean[] odd = new boolean[N];
        boolean[] even = new boolean[N];
        odd[N - 1] = even[N - 1] = true;

        TreeMap<Integer, Integer> vals = new TreeMap();
        vals.put(A[N - 1], N - 1);
        for (int i = N - 2; i >= 0; --i) {
            int v = A[i];
            if (vals.containsKey(v)) {
                odd[i] = even[vals.get(v)];
                even[i] = odd[vals.get(v)];
            } else {
                Integer lower = vals.lowerKey(v);
                Integer higher = vals.higherKey(v);

                if (lower != null)
                    even[i] = odd[vals.get(lower)];
                if (higher != null) {
                    odd[i] = even[vals.get(higher)];
                }
            }
            vals.put(v, i);
        }

        int ans = 0;
        for (boolean b : odd)
            if (b) ans++;
        return ans;
    }

}
