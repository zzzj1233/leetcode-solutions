package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-09 14:33
 */
public class Leet1064 {


    // [-10,-5,-2,0,4,5,6,7,8,9,10]
    public static int fixedPoint(int[] arr) {

        int l = 0;
        int r = arr.length - 1;

        int ans = Integer.MAX_VALUE;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);

            if (arr[mid] == mid) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else if (arr[mid] < mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
