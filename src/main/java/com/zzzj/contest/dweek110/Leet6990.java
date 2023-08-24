package com.zzzj.contest.dweek110;

public class Leet6990 {

    public static void main(String[] args) {

        System.out.println(accountBalanceAfterPurchase(9));

        System.out.println(accountBalanceAfterPurchase(15));

        System.out.println(accountBalanceAfterPurchase(20));

        System.out.println(accountBalanceAfterPurchase(11));

    }

    public static int accountBalanceAfterPurchase(int purchaseAmount) {

        int case1 = (purchaseAmount / 10) * 10;

        int case2 = ((purchaseAmount / 10) + 1) * 10;

        if (Math.abs(case1 - purchaseAmount) < Math.abs(case2 - purchaseAmount)) {
            return 100 - case1;
        }

        return 100 - case2;
    }

}
