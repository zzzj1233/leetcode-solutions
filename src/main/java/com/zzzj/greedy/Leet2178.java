package com.zzzj.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leet2178 {

    public static void main(String[] args) {
        System.out.println(maximumEvenSplit(28));
    }

    public static List<Long> maximumEvenSplit(long finalSum) {

        if (finalSum % 2 != 0) {
            return Collections.emptyList();
        }

        List<Long> ans = new ArrayList<>();

        long next = 0;

        long curSum = 0;


        while (finalSum - curSum > next) {
            next += 2;
            curSum += next;
            ans.add(next);
        }

        ans.remove(ans.size() - 1);
        ans.add(finalSum - (curSum - next));

        return ans;
    }

}
