package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-15 17:45
 */
public class Leet1894 {

    public static int chalkReplacer(int[] chalk, int k) {
        int mod = k % Arrays.stream(chalk).sum();
        int N = chalk.length;

        for (int i = 0; i < N; i++) {
            mod -= chalk[i];
            if (mod < 0) {
                return i;
            }
        }

        return -1;
    }


}
