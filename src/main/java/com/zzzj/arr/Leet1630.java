package com.zzzj.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-05-15 16:44
 */
public class Leet1630 {

    public static void main(String[] args) {
        System.out.println(checkArithmeticSubarrays(
                new int[]{4, 6, 5, 9, 3, 7},
                new int[]{0, 0, 2},
                new int[]{2, 3, 5}
        ));
    }

    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> answer = new LinkedList<>();
        for (int i = 0; i < l.length; ++i) {
            answer.add(i, false);
            int[] subArr = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            if (subArr.length > 2) {
                Arrays.sort(subArr);
                for (int j = 2; j < subArr.length; ++j) {
                    if (subArr[j - 2] - subArr[j - 1] != subArr[j - 1] - subArr[j]) {
                        answer.set(i, true);
                        break;
                    }
                }
                if (answer.get(i)) answer.set(answer.size() - 1, false);
                else answer.set(i, true);
            } else answer.set(i, true);
        }
        return answer;
    }


}
