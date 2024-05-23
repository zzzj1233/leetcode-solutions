package com.zzzj.contest.week391;

/**
 * @author zzzj
 * @create 2024-05-23 15:34
 */
public class Leet3100 {

    public static void main(String[] args) {

        System.out.println(maxBottlesDrunk(13, 6));

        System.out.println(maxBottlesDrunk(10, 3));

    }

    public static int maxBottlesDrunk(int numBottles, int numExchange) {

        int ans = numBottles;

        int empty = numBottles;

        while (empty >= numExchange) {
            ans++;
            empty -= numExchange;
            numExchange++;
            empty += 1;
        }

        return ans;
    }

}
