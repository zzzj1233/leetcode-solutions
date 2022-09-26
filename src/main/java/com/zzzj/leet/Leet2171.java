package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-22 10:57
 */
public class Leet2171 {

    public static void main(String[] args) {
//        System.out.println(minimumRemoval(new int[]{2, 3, 3, 5, 8}));
//        System.out.println(right(new int[]{2, 3, 3, 5, 8}));
//
//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            final int[] arr = ArrayUtil.generateArray(50, 0, 100);
            if (minimumRemoval(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    // [63,43,12,94]
    public static long minimumRemoval(int[] beans) {
        // [4,1,6,5] -> [4,0,4,4] = 4

        // [2,10,3,2] -> [0,10,0,0] = 7

        // 1 4 5 6

        int N = beans.length;

        Arrays.sort(beans);

        long ans = Long.MAX_VALUE;

        long preSum = 0;

        long[] nextSum = new long[N + 1];

        for (int i = N - 2; i >= 0; i--) {
            nextSum[i] = nextSum[i + 1] + beans[i + 1];
        }

        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, preSum + (nextSum[i] - ((long) beans[i] * (N - i - 1))));
            preSum += beans[i];
        }

        return ans;
    }

    public static long right(int[] beans) {
        /*
        1 <= beans.length <= 1e5
        选最后要使得数量都变成num，那么结果就是小于num的全部要变为0，大于等于num的全部变为num
        选择一种变为num代价最小的拿出糖果的数目就是答案
        我们可以试着枚举每个nums[i]选出最优值，但是时间复杂度能到O(N^2)
        首先可以进行排序，例如这里排序后为[1,4,5,6]，求得其前缀和为[0,1,5,10,16]
        观察到此时可以在O(1)内求出最少拿出的糖果数:例如现在nums[i]=4，那么结果就是sum[0,i-1]+(sum[i+1,n-1]-nums[i]*(n-i-1))
        遍历过程中维护出最小值就是答案
         */
        int n = beans.length;
        if (n == 1) return 0;
        Arrays.sort(beans); // 记得先排序(排序完可以用前缀和加速)
        long[] sum = new long[n + 1];   // int上限2.147e9
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + beans[i];
        }
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long l = sum[i], r = (sum[n] - sum[i + 1]) - (long) beans[i] * (n - i - 1);
            long cur = l + r;   // 选择最后都剩余nums[i]最少要拿出的糖果数目
            if (cur < res) res = cur;
        }
        return res;
    }

}
