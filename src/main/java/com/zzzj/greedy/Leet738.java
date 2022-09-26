package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-09-19 14:19
 */
public class Leet738 {

    // 332
    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(0));
    }

    public static int monotoneIncreasingDigits(int n) {
        int num = n;

        StringBuilder builder = new StringBuilder();

        int prev = 10;

        while (num != 0) {
            int bitNum = num % 10;

            if (bitNum > prev) {
                // 当前位-1,后面的全部变成9
                for (int i = 0; i < builder.length(); i++) {
                    builder.setCharAt(i, '9');
                }
                builder.append(bitNum - 1);
                prev = bitNum - 1;
            } else {
                builder.append(bitNum);
                prev = bitNum;
            }

            num /= 10;
        }

        if (builder.length() == 0){
            return 0;
        }

        return Integer.parseInt(builder.reverse().toString(), 10);
    }


}
