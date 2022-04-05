package com.zzzj.hot;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Zzzj
 * @create 2022-04-19 22:36
 */
public class Leet179 {

    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    public static String largestNumber(int[] nums) {
        if (Arrays.stream(nums).allMatch(value -> value == 0)) {
            return "0";
        }

        return Arrays.stream(nums).boxed().sorted((o1, o2) -> {
            int o1len = String.valueOf(o1).length();
            int o2len = String.valueOf(o2).length();
            return (o2 * ((int) Math.pow(10, o1len)) + o1) - (o1 * ((int) Math.pow(10, o2len)) + o2);
        }).map(Object::toString).collect(Collectors.joining());
    }

}
