package com.zzzj.hot;

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

    // [5,1,2,3,4]
    // [4,4,1,5,1]
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int N = gas.length;

        for (int i = 0; i < N; i++) {
            if (gas[i] >= cost[i] && allow(i, gas, cost)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean allow(int start, int[] gas, int[] cost) {
        int N = gas.length;

        int oil = gas[start];

        int nextCost = cost[start];

        for (int i = start + 1; i < N; i++) {
            if (oil < nextCost) {
                return false;
            }
            oil -= nextCost;
            oil += gas[i];
            nextCost = cost[i];
        }

        for (int i = 0; i <= start; i++) {
            if (oil < nextCost) {
                return false;
            }
            oil -= nextCost;
            oil += gas[i];
            nextCost = cost[i];
        }

        return true;
    }

}
