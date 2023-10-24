package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2023-04-12 17:16
 */
public class Leet1678 {

    public static void main(String[] args) {
        System.out.println(interpret("G()(al)"));
        System.out.println(interpret("G()()()()(al)G"));
    }

    public static String interpret(String command) {

        StringBuilder ans = new StringBuilder();

        int N = command.length();

        for (int i = 0; i < N; i++) {
            char c = command.charAt(i);
            if (c == 'G') {
                ans.append(c);
            } else {
                if (command.charAt(i + 1) == ')') {
                    ans.append('o');
                    i++;
                } else {
                    ans.append("al");
                    i += 3;
                }
            }
        }

        return ans.toString();
    }

}
