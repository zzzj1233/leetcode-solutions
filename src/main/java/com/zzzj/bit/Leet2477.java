package com.zzzj.bit;


/**
 * @author zzzj
 * @create 2022-08-01 18:17
 */
public class Leet2477 {

    public static void main(String[] args) {
        int n = 5;

        int highBit = -1;

        for (int i = 31; i >= 0; i--) {
            if (((n >> i) & 1) == 1) {
                highBit = i;
                break;
            }
        }

        for (int i = 1; i <= highBit; i++) {
            if (((n >> i) & 1) == ((n >> (i - 1)) & 1)) {
                System.out.println("Error");
            }
        }

    }

    public static int lowbit(int num) {
        for (int i = 0; i < 31; i++) {
            if (((num >> i) & 1) == 1) {
                return i;
            }
        }

        return -1;
    }

    public static int missingNumber(int[] nums) {
        int xor = 0;
        int N = nums.length;

        for (int i = 0; i <= N; i++) {
            xor ^= i;
        }

        for (int i = 0; i < N; i++) {
            xor ^= nums[i];
        }

        return xor;
    }

}
