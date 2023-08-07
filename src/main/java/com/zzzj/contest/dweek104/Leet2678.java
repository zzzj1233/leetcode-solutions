package com.zzzj.contest.dweek104;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-01 16:30
 */
public class Leet2678 {

    public static void main(String[] args) {
        System.out.println(countSeniors(
                new String[]{"7868190130M7522", "5303914400F9211", "9273338290F4010"}
        ));

        System.out.println(countSeniors(
                new String[]{"1313579440F2036", "2921522980M5644"}
        ));
    }

    public static int countSeniors(String[] details) {

        return (int) Arrays.stream(details)
                .map(s -> s.substring(s.length() - 4, s.length() - 2))
                .map(Integer::parseInt)
                .filter(age -> age > 60)
                .count();
    }


}
