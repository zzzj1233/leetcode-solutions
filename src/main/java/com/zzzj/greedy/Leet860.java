package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-07-29 11:26
 */
public class Leet860 {

    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5, 5, 5, 10, 20}));
    }

    public static boolean lemonadeChange(int[] bills) {

        int five = 0;
        int ten = 0;

        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                ten++;
                five--;
            } else {
                // 找15
                if (ten == 0) {
                    if (five < 3) {
                        return false;
                    }
                    five -= 3;
                } else {
                    if (five < 1) {
                        return false;
                    }
                    ten -= 1;
                    five -= 1;
                }
            }
        }

        return true;
    }

}
