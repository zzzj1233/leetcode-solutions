package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-22 15:55
 */
public class Leet2288 {

    public static void main(String[] args) {
        System.out.println(discountPrices("there are $1 $2 and 5$ candies in the shop", 50));
    }

    public static String discountPrices(String sentence, int discount) {

        double dis = (100 - (double) discount) / 100;

        int N = sentence.length();

        StringBuilder builder = new StringBuilder(N);

        boolean wordStart = true;

        for (int i = 0; i < N; ) {
            if (sentence.charAt(i) == '$' && wordStart && (i + 1 < N) && Character.isDigit(sentence.charAt(i + 1))) {
                i++;
                int num = 0;
                while (i < N && Character.isDigit(sentence.charAt(i))) {
                    num = num * 10 + Character.digit(sentence.charAt(i), 10);
                    i++;
                }
                builder.append("$");
                if (i < N && sentence.charAt(i) == ' ') {
                    builder.append(String.format("%.2f", num * dis));
                } else {
                    builder.append(num);
                }
            } else {
                wordStart = sentence.charAt(i) == ' ';
                builder.append(sentence.charAt(i));
                i++;
            }
        }

        return builder.toString();
    }


}
