package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-25 12:10
 */
public class Leet1533 {

    public static void main(String[] args) {

        System.out.println(getIndex(new ArrayReaderImpl(new int[]{1, 1, 1, 1, 1, 1, 11})));

//        System.exit(0);

        for (int i = 0; i < 100000; i++) {
            int N = LeetUtils.random.nextInt(1000) + 2;

            int[] arr = ArrayUtil.generateArray(N);

            for (int j = 0; j < N; j++) {
                arr[j] = 1;
            }

            int idx = LeetUtils.random.nextInt(N);

            arr[idx] = 11;

            ArrayReaderImpl reader = new ArrayReaderImpl(arr);

            int ret = getIndex(reader);

            if (ret != idx) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(ret);
                return;
            }
        }

        System.out.println("Ok");
    }

    private interface ArrayReader {
        int compareSub(int l, int r, int x, int y);

        int length();
    }

    private static class ArrayReaderImpl implements ArrayReader {

        private final int[] nums;

        private ArrayReaderImpl(int[] nums) {
            this.nums = nums;
        }

        @Override
        public int compareSub(int l, int r, int x, int y) {
            Integer leftSum = 0;
            Integer rightSum = 0;

            for (int i = l; i <= r; i++) {
                leftSum += nums[i];
            }


            for (int i = x; i <= y; i++) {
                rightSum += nums[i];
            }

            return leftSum.compareTo(rightSum);
        }

        @Override
        public int length() {
            return nums.length;
        }
    }

    public static int getIndex(ArrayReader reader) {
        int N = reader.length();

        int left = 0;
        int right = N - 1;

        while (left < right) {
            int mid = left + ((right - left) >> 1);

            // sub > 0 = 左边大
            // sub < 0 = 右边大
            // sub = 0 = 一样大
            if ((right - left + 1) % 2 == 0) {
                int sub = reader.compareSub(left, mid, mid + 1, right);
                // 在左边
                if (sub > 0) {
                    right = mid;
                } else {    // 在右边
                    left = mid + 1;
                }
            } else {
                int sub = reader.compareSub(left, mid - 1, mid + 1, right);

                if (sub == 0) {
                    return mid;
                } else if (sub > 0) { // 在左边
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if (left == right) {
                return left;
            }
        }

        return -1;
    }


}
