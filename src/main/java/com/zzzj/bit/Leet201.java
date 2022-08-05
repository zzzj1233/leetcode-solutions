package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2022-08-02 17:00
 */
public class Leet201 {


    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(52, 63));
        System.out.println(rangeBitwiseAnd(5, 7));
        System.out.println(rangeBitwiseAnd(0, 0));
        System.out.println(rangeBitwiseAnd(1, 2147483647));
    }

    public static int rangeBitwiseAnd(int left, int right) {
        int ans = 0;

        for (int i = 31; i >= 0; i--) {
            if ((((right >> i) & 1) == 1)) {
                if ((((left >> i) & 1) == 0)) {
                    return ans;
                }
                ans |= 1 << i;
            }
        }

        return ans;
    }


}
