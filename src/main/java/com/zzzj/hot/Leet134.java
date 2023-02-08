package com.zzzj.hot;

import com.zzzj.graph.leetcode.FarmerAndSheep;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-04-19 17:51
 */
public class Leet134 {


    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{5, 1, 2, 3, 4}, new int[]{4, 4, 1, 5, 1}));
        System.out.println(canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int N = gas.length;

        int gs = Arrays.stream(gas).sum();
        int cs = Arrays.stream(cost).sum();

        if (cs > gs) {
            return -1;
        }

        int curGas = 0;

        for (int i = 0; i < N; ) {
            for (int j = i; j < N; j++) {
                curGas = curGas - cost[j] + gas[j];
                if (curGas < 0) {
                    curGas = 0;
                    i = j + 1;
                }
            }
            return i;
        }

        return -1;
    }


}
