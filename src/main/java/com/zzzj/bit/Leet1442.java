package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2022-08-05 15:50
 */
public class Leet1442 {

    public static void main(String[] args) {
        System.out.println(countTriplets(new int[]{2, 3, 1, 6, 7}));
        System.out.println(countTriplets(new int[]{1, 1, 1, 1, 1}));
        System.out.println(countTriplets(new int[]{2, 3}));
        System.out.println(countTriplets(new int[]{1, 3, 5, 7, 9}));
    }

    public static int countTriplets(int[] arr) {
        return O2(arr);
    }

    public static int O2(int[] arr) {
        int N = arr.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            int xor = 0;

            for (int j = i; j < N; j++) {

                xor ^= arr[j];

                if (xor == 0) {
                    ans += j - i;
                }
            }

        }

        return ans;
    }

    public static int O3(int[] arr) {
        int N = arr.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            int a = arr[i];

            for (int j = i + 1; j < N; j++) {
                if (j - 1 > i) {
                    a ^= arr[j - 1];
                }

                int b = arr[j];

                for (int k = j; k < N; k++) {

                    if (k > j) {
                        b ^= arr[k];
                    }

                    if (a == b) {
                        ans++;
                    }
                }
            }

        }

        return ans;
    }

}
