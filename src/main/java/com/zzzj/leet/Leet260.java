package com.zzzj.leet;

import com.zzzj.util.Unresolved;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-11 11:52
 */
@Unresolved
public class Leet260 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
    }

    public static int[] singleNumber(int[] nums) {

        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        // 第一次出现1的位数
        int firstOneBit = -1;

        for (int i = 0; i < 31; i++) {
            if (((xor >> i) & 1) == 1) {
                firstOneBit = i;
                break;
            }
        }

        // 如果这个位是1, 那么说明这个位1 一定出现了[奇数次]

        int group1 = 0;

        for (int num : nums) {
            // 视该位上全部为1的为一组

            // 已知这个位上的1出现了奇数次,其余所有数都为偶数次,那么这一定是那个只出现了一次的数
            if (((num >> firstOneBit) & 1) == 1) {
                group1 ^= num;
            }
        }


        return new int[]{group1, xor ^ group1};
    }

}
