package com.zzzj.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leet2178 {

    public static void main(String[] args) {
        System.out.println(maximumEvenSplit(28));
        System.out.println(maximumEvenSplit(12));
    }

    public static List<Long> maximumEvenSplit(long finalSum) {

        if (finalSum % 2 != 0) return Collections.emptyList();

        List<Long> ans = new ArrayList<>();

        long prev = 2;

        long sum = finalSum;

        while (sum > 0) {

            if (sum - prev <= prev) {
                ans.add(sum);
                break;
            }

            ans.add(prev);
            sum -= prev;
            prev += 2;
        }

        return ans;
    }

}
