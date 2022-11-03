package com.zzzj.greedy;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-01 11:05
 */
public class Leet611 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int N = 4;

            int[] arr = ArrayUtil.generateArray(N, 1, 10);

            ArrayCopyIterator iterator = new ArrayCopyIterator(arr);

            if (triangleNumber(iterator.next()) != right(iterator.next())) {
                System.out.println("Error");
                System.out.println(Arrays.toString(iterator.next()));
                System.out.println("MyAns :" + triangleNumber(iterator.next()));
                System.out.println("Right :" + right(iterator.next()));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int triangleNumber(int[] nums) {
        // a<=b<=c,当且仅当a+b>c成立可以组成三角形

        // 2 2 3 4

        int N = nums.length;

        Arrays.sort(nums);

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int a = nums[i];

            for (int j = i + 1; j < N; j++) {
                int b = nums[j];

                int c = findC(nums, a + b, j + 1);

                if (c < 0) {
                    continue;
                }

                if (c >= N) {
                    continue;
                }

                ans += c - j;
            }

        }

        return ans;
    }

    public static int findC(int[] nums, int sum, int low) {
        int high = nums.length - 1;
        int ret = -1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < sum) {
                ret = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ret;
    }

    public static int right(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int k = 0;
            int j = i - 1;
            while (k < j) {
                //满足该条件，说明从num[k]到num[j]的数都满足要求，结果直接加上j - k
                if (nums[k] + nums[j] > nums[i]) {
                    result += j - k;
                    j--;
                } else {
                    //否则k自增，重新判断
                    k++;
                }
            }
        }
        return result;
    }

}
