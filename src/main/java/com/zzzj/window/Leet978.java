package com.zzzj.window;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-31 11:53
 */
public class Leet978 {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(100, 0, 20);

        System.out.println(Arrays.toString(arr));

        System.out.println(maxTurbulenceSize(arr));
    }

    public static int maxTurbulenceSize(int[] arr) {
        if (arr.length == 1) {
            return 1;
        }


        int l = 0;
        int r = 1;

        int ans = 1;

        Boolean up = null;

        while (r < arr.length) {
            if (arr[r] == arr[r - 1]) {
                l = r;
                r++;
                continue;
            }

            if (up == null) {
                up = arr[r] <= arr[r - 1];
            } else {
                boolean reduce = (arr[r] > arr[r - 1] && !up) || (arr[r] < arr[r - 1] && up) || arr[r] == arr[r - 1];

                if (reduce) {
                    l = r - 1;
                    up = null;
                    continue;
                } else {
                    up = !up;
                }
            }

            ans = Math.max(ans, r - l + 1);

            r++;
        }

        return ans;
    }

}
