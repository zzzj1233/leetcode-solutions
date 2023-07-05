package com.zzzj.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-06-29 12:02
 */
public class Leet1253 {

    public static void main(String[] args) {

        System.out.println(reconstructMatrix(5, 5, new int[]{2, 1, 2, 0, 1, 0, 1, 2, 0, 1}));

    }

    public static List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {

        final int col = colsum.length;

        if (Arrays.stream(colsum).sum() != upper + lower) return Collections.emptyList();

        // 第一行的和 == upper
        // 第二行的和 == lower

        List<List<Integer>> ans = new ArrayList<>(2);

        ans.add(new ArrayList<>(col));
        ans.add(new ArrayList<>(col));

        for (int i = 0; i < col; i++) {

            List<Integer> row1 = ans.get(0);

            List<Integer> row2 = ans.get(1);

            int value = colsum[i];

            if (value == 0) {
                row1.add(0);
                row2.add(0);
            } else if (value == 1) {
                if (upper >= lower) {
                    row1.add(1);
                    row2.add(0);
                    upper--;
                } else {
                    row1.add(0);
                    row2.add(1);
                    lower--;
                }
            } else {
                if (lower == 0 || upper == 0) return Collections.emptyList();
                row1.add(1);
                row2.add(1);
                upper--;
                lower--;
            }
        }

        return ans;
    }

}
