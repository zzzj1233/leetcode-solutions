package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-30 17:15
 */
public class Leet1524 {

    /**
     * 1. 如果当前和为奇数,那么 ans += 偶数和的个数
     * 2. 如果当前和为偶数,那么 ans += 奇数和的个数
     * <p>
     * <p>
     * sum = 6
     * [1,2,3] 当前和为偶数, 奇数和有两个:[1], [1, 2] , sub奇数和的和为 : [ 6 - 1 = 5 ] , [ 6 - 3  = 3 ] , ans += 2
     * <p>
     * sum = 9
     * [1,3,5] 当前和为奇数, 偶数和有[1,3] , sub奇数和的和为 : [9 - 4 = 5] , ans += 1
     * <p>
     * sum = 13
     * [1,3,4,5] 当前和为奇数, 偶数和有[1,3],[1,3,4] , sub奇数和的和为 : [13 - 4 = 9] , [ 13 - 5 ] ans += 2
     */

    public static void main(String[] args) {
        System.out.println(numOfSubarrays(new int[]{1, 3, 5}));
    }

    public static int numOfSubarrays(int[] arr) {

        final int MOD = 1000000007;

        int N = arr.length;

        int oddSum = 0;
        int evenSum = 0;
        int sum = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if ((sum & 1) == 1) {
                oddSum++;
                ans += evenSum + 1;
            } else {
                evenSum++;
                ans += oddSum;
            }
            ans %= MOD;
        }

        return ans;
    }

}
