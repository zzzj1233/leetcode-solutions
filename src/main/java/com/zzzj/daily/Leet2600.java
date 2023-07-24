package com.zzzj.daily;

/**
 * @author zzzj
 * @create 2023-07-05 15:30
 */
public class Leet2600 {

    public static int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {

        int ans = 0;

        while (k > 0) {
            if (numOnes > 0) {
                numOnes--;
                ans += 1;
            } else if (numZeros > 0) numZeros--;
            else {
                numNegOnes--;
                ans -= 1;
            }
            k--;
        }

        return ans;
    }


}
