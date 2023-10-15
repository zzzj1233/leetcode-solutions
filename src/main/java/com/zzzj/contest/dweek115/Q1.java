package com.zzzj.contest.dweek115;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(lastVisitedIntegers(
                Arrays.asList(
                        "1", "prev", "2", "prev", "prev"
                )
        ));

        System.out.println(lastVisitedIntegers(
                Arrays.asList(
                        "1", "2", "prev", "prev", "prev"
                )
        ));

    }

    public static List<Integer> lastVisitedIntegers(List<String> words) {

        int N = words.size();


        List<Integer> nums = new ArrayList<>(N);

        List<Integer> ans = new ArrayList<>(N);

        // "1","prev","2","prev","prev"
        // 0, 0, 1, 1,
        // 1, 1
        for (int i = 0; i < N; i++) {

            String word = words.get(i);

            if (word.equals("prev")) {

                int index = nums.size();

                for (int k = i; k >= 0 && index >= 0; k--) {
                    if (words.get(k).equals("prev"))
                        index--;
                    else
                        break;
                }

                if (index < 0)
                    ans.add(-1);
                else
                    ans.add(nums.get(index));

            } else {
                nums.add(Integer.parseInt(word));
            }

        }

        return ans;
    }


}
