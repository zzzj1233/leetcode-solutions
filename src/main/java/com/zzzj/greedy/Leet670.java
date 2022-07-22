package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-07-22 12:04
 */
public class Leet670 {

    public static void main(String[] args) {
        System.out.println(maximumSwap(1993));
    }

    public static int maximumSwap(int num) {
        int[] max = new int[9];

        int maxValue = -1;
        int maxIndex = -1;

        char[] chars = String.valueOf(num).toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            int value = Character.digit(chars[i], 10);
            if (value >= maxValue) {
                maxValue = value;
                maxIndex = i;
            }
            max[i] = maxIndex;
        }

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (i == max[i] || c == chars[max[i]]) {
                continue;
            }

            char tempSwap = chars[max[i]];
            chars[max[i]] = chars[i];
            chars[i] = tempSwap;
            break;
        }

        return Integer.parseInt(String.valueOf(chars));
    }

}
