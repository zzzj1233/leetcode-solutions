package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2022-08-01 17:47
 */
public class Leet2618 {


    public static boolean isUnique(String astr) {
        int mask = 0;

        for (int i = 0; i < astr.length(); i++) {
            int index = astr.charAt(i) - 'a';
            if (((mask >> index) & 1) == 1) {
                return false;
            }
            mask |= 1 << index;
        }

        return true;
    }

}
