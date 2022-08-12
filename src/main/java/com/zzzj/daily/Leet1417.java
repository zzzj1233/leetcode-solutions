package com.zzzj.daily;

/**
 * @author zzzj
 * @create 2022-08-12 12:21
 */
public class Leet1417 {

    public static void main(String[] args) {
        System.out.println(reformat("619mama"));
    }

    public static String reformat(String s) {
        if (s.isEmpty()) {
            return "";
        }

        int alphaCount = 0;
        int digitCount = 0;

        int N = s.length();

        int alphaIndex = -1;
        int digitIndex = -1;

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                if (alphaIndex == -1) {
                    alphaIndex = i;
                }
                alphaCount++;
            } else {
                if (digitIndex == -1) {
                    digitIndex = i;
                }
                digitCount++;
            }
        }

        if (Math.abs(alphaCount - digitCount) > 1) {
            return "";
        }

        StringBuilder builder = new StringBuilder(N);

        boolean alphaFirst = alphaCount >= digitCount;

        while (builder.length() < N) {
            if (alphaFirst) {
                builder.append(s.charAt(alphaIndex));
                alphaIndex = findNextIndex(s, alphaIndex, false);
                alphaFirst = false;
            } else {
                builder.append(s.charAt(digitIndex));
                digitIndex = findNextIndex(s, digitIndex, true);
                alphaFirst = true;
            }
        }

        return builder.toString();
    }

    public static int findNextIndex(String str, int start, boolean digit) {
        for (int i = start + 1; i < str.length(); i++) {
            if (digit) {
                if (str.charAt(i) < 'a') {
                    return i;
                }
            } else {
                if (str.charAt(i) >= 'a') {
                    return i;
                }
            }

        }
        return str.length() + 1;
    }

}
