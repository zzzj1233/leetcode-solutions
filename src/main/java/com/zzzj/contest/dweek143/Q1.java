package com.zzzj.contest.dweek143;

/**
 * @author zzzj
 * @create 2024-11-09 22:30
 */
public class Q1 {


    public static void main(String[] args) {

        System.out.println(smallestInteger(10, 2));

        System.out.println(smallestInteger(15, 3));

    }

    public static int smallestInteger(int n, int t) {
        while (true) {
            if (isDivisibleByT(n, t)) {
                return n;
            }
            n++;
        }
    }

    private static boolean isDivisibleByT(int number, int t) {
        int product = 1;
        int temp = number;

        // 计算数字的各位数之积
        while (temp > 0) {
            int digit = temp % 10;
            // 如果存在0，乘积直接为0
            if (digit == 0) {
                product = 0;
                break;
            }
            product *= digit;
            temp /= 10;
        }

        // 检查乘积是否可以被 t 整除
        return product % t == 0;
    }

}
