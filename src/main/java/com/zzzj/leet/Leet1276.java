package com.zzzj.leet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-07-22 15:18
 */
public class Leet1276 {

    // 4,1
    // 2,1
    public static List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {

        // x * (4) + y * (2) ==  tomatoSlices
        // &&
        // x * (1) + y * (1) ==  cheeseSlices

        if (tomatoSlices > 4 * cheeseSlices || tomatoSlices < 2 * cheeseSlices || (tomatoSlices % 2) != 0) {
            return Collections.emptyList();
        }

        int big = 0;
        int small = cheeseSlices;

        while (big <= cheeseSlices) {
            if ((big << 2) + (small << 1) == tomatoSlices) {
                return Arrays.asList(big, small);
            }
            big++;
            small--;
        }

        return Collections.emptyList();
    }


}
