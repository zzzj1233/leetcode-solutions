package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-08-07 22:36
 */
public class Leet2671 {


    public static int multiply(int A, int B) {
        if (B == 0){
            return 0;
        }
        if (B == 1) {
            return A;
        }
        return A + multiply(A, B - 1);
    }


}
