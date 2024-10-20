package com.zzzj.contest.week141;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-10-13 13:50
 */
public class Leet3315 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(minBitwiseArray(Arrays.asList(31))));

    }

    public static int[] minBitwiseArray(List<Integer> nums) {

        int N = nums.size();

        // 11 = 1011
        // 9  = 1001
        // 10 = 1010

        // 31 = 1 1111
        // 15 = 0 1111
        // 16 = 1 0000

        // 7 = 0111
        // 3 = 0011
        // 4 = 0100

        // 13 = 1101
        // 12 = 1100
        // 11 = 1011
        // OR = 1111

        // 13 = 1101
        // 12 = 1100
        // OR = 1101

        //

        return nums.stream().mapToInt(value -> {
            if (value == 2)
                return -1;
            int cnt = 0;
            for (int i = 0; i <= 31; i++) {
                if ((value & (1 << i)) == 0)
                    break;
                cnt++;
            }
            return (int) (value - Math.pow(2, cnt - 1));
        }).toArray();
    }

}
