package com.zzzj.greedy;


/**
 * @author zzzj
 * @create 2022-12-16 12:19
 */
public class Leet681 {

    public static void main(String[] args) {
        System.out.println(nextClosestTime("19:34"));
        System.out.println(nextClosestTime("23:59"));
    }

    public static String nextClosestTime(String time) {
        int minSubSecond = Integer.MAX_VALUE;

        char[] chars = {
                time.charAt(0),
                time.charAt(1),
                time.charAt(3),
                time.charAt(4)
        };


        StringBuilder builder = new StringBuilder(4);

        builder.append("    ");

        String ans = null;

        String time2 = new String(chars);

        for (int i = 0; i < 4; i++) {

            builder.setCharAt(0, chars[i]);

            int num1 = Character.digit(chars[i], 10);

            if (num1 > 2) {
                continue;
            }

            for (int j = 0; j < 4; j++) {

                builder.setCharAt(1, chars[j]);

                if (num1 == 2 && Character.digit(chars[j], 10) > 3) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {

                    builder.setCharAt(2, chars[k]);

                    if (Character.digit(chars[k], 10) > 5) {
                        continue;
                    }

                    for (int l = 0; l < 4; l++) {

                        builder.setCharAt(3, chars[l]);

                        int subSecond = sub(builder, time2);

                        if (subSecond == 0) {
                            continue;
                        }

                        if (minSubSecond > subSecond) {
                            ans = builder.toString();
                            minSubSecond = subSecond;
                        }

                    }

                }

            }

        }

        if (ans == null) {
            return time;
        }

        return new StringBuilder(ans).insert(2, ':').toString();
    }

    public static int SECOND_OF_DAY = 86400;


    private static final int[] MUL = {
            10 * 60,
            60,
            10,
            1
    };

    public static int sub(CharSequence time1, CharSequence time2) {
        int index = 0;

        int c = 0;

        while (index < 4 && (c = compare(time1, time2, index)) == 0) {
            index++;
        }

        if (index == 4) {
            return 0;
        }


        // >
        int subSecond;
        if (c > 0) {
            subSecond = subSecond(time1, time2);
        } else { // <
            subSecond = SECOND_OF_DAY - subSecond(time2, time1);
        }

        return subSecond;
    }

    public static int compare(CharSequence time1, CharSequence time2, int index) {
        return Character.digit(time1.charAt(index), 10) - Character.digit(time2.charAt(index), 10);
    }

    public static int subSecond(CharSequence time1, CharSequence time2) {
        int result = 0;

        for (int i = 0; i < 4; i++) {
            int value1 = Character.digit(time1.charAt(i), 10);
            int value2 = Character.digit(time2.charAt(i), 10);
            result += (value1 - value2) * MUL[i];
        }

        return result;
    }

}
