package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-05-29 17:59
 */
public class Leet2241 {


    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.deposit(new int[]{0, 0, 1, 2, 1});
        System.out.println(Arrays.toString(atm.withdraw(600)));
        atm.deposit(new int[]{0, 1, 0, 1, 1});
        System.out.println(Arrays.toString(atm.withdraw(600)));
        System.out.println(Arrays.toString(atm.withdraw(550)));
    }


    private static class ATM {
        private long[] noteCount = new long[5];

        private final int[] AMOUNT = {20, 50, 100, 200, 500};

        public ATM() {

        }

        public void deposit(int[] banknotesCount) {
            if (banknotesCount == null) {
                return;
            }
            for (int i = 0; i < banknotesCount.length; i++) {
                noteCount[i] += banknotesCount[i];
            }
        }

        public int[] withdraw(int amount) {
            // 优先取最大的
            int[] ans = new int[5];

            for (int i = noteCount.length - 1; i >= 0; i--) {
                if (noteCount[i] == 0 || amount < this.AMOUNT[i]) {
                    continue;
                }
                int num = amount / this.AMOUNT[i];
                ans[i] = (int) Math.min(num, noteCount[i]);
                amount -= this.AMOUNT[i] * ans[i];
            }

            if (amount == 0) {
                for (int i = 0; i < ans.length; i++) {
                    this.noteCount[i] -= ans[i];
                }
            }


            return amount == 0 ? ans : new int[]{-1};
        }
    }

}
