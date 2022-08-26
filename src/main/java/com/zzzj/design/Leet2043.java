package com.zzzj.design;

/**
 * @author zzzj
 * @create 2022-08-24 19:52
 */
public class Leet2043 {


    private static class Bank {

        private final long[] balance;

        public Bank(long[] balance) {
            this.balance = balance;
        }

        public boolean transfer(int account1, int account2, long money) {
            if (account1 > balance.length || account2 > balance.length) {
                return false;
            }
            return false;
        }

        public boolean deposit(int account, long money) {
            if (account > balance.length) {
                return false;
            }
            return false;
        }

        public boolean withdraw(int account, long money) {
            if (account > balance.length) {
                return false;
            }
            return false;
        }
    }

}
