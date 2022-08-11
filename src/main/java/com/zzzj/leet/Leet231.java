package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-08-06 15:52
 */
public class Leet231 {


    public static void main(String[] args) {
//        System.out.println(isPowerOfTwo(2));
        System.out.println(isPowerOfTwo(4));
//        System.out.println(isPowerOfTwo(3));
        System.out.println(isPowerOfTwo(6));
    }

    public static boolean isPowerOfTwo(int n) {
        if (n <= 1) {
            return false;
        }
        return (n & -n) == n;
    }

}
