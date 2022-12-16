package com.zzzj.str;

/**
 * @author zzzj
 * @create 2022-12-05 14:48
 */
public class Leet43 {

    public static void main(String[] args) {
        System.out.println(multiply("0", "0"));
    }

    public static String multiply(String num1, String num2) {
        if (num2.equals("0") || num1.equals("0")) {
            return "0";
        }

        StringBuilder ans = new StringBuilder(num1);

        StringBuilder n2 = new StringBuilder(num2);

        if (hasNext(n2)) {
            next(n2);
        }

        while (hasNext(n2)) {
            add(ans, num1);
            next(n2);
        }

        return ans.toString();
    }

    public static void add(StringBuilder value1, CharSequence value2) {
        boolean addition = false;

        int sub = value1.length() - value2.length();

        for (int i = value2.length() - 1; i >= 0; i--) {
            char c2 = value2.charAt(i);

            char c1 = value1.charAt(i + sub);

            int sum = Character.digit(c1, 10) + Character.digit(c2, 10) + (addition ? 1 : 0);

            value1.setCharAt(i + sub, Character.forDigit(sum % 10, 10));

            addition = sum >= 10;
        }

        int start = sub - 1;

        for (int i = start; i >= 0 && addition; i--) {
            char c1 = value1.charAt(i);

            int sum = Character.digit(c1, 10) + 1;

            value1.setCharAt(i, Character.forDigit(sum % 10, 10));

            addition = sum >= 10;
        }

        if (addition) {
            value1.append(' ');

            for (int i = value1.length() - 1; i > 0; i--) {
                value1.setCharAt(i, value1.charAt(i - 1));
            }

            value1.setCharAt(0, '1');
        }

    }

    public static boolean hasNext(CharSequence value) {
        return value.length() > 0 && (value.length() > 1 || Character.digit(value.charAt(0), 10) > 0);
    }

    public static void next(StringBuilder value) {
        sub1(value, value.length() - 1);
    }

    private static void sub1(StringBuilder value, int index) {
        char c = value.charAt(index);

        if (c == '0') {
            value.setCharAt(index, '9');
            sub1(value, index - 1);
        } else {
            if (index == 0 && c == '1') {
                for (int i = 0; i < value.length() - 1; i++) {
                    value.setCharAt(i, value.charAt(i + 1));
                }
                value.setLength(value.length() - 1);
            } else {
                value.setCharAt(index, Character.forDigit(Character.digit(c, 10) - 1, 10));
            }
        }
    }

}
