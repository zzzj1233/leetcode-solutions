package com.zzzj.stack;

/**
 * @author zzzj
 * @create 2022-09-15 16:09
 */
public class Leet1190 {

    public static void main(String[] args) {
        // ( u ( l o v e ) i )
        // 0 1 2 3 4 5 6 7
        // ulove 0 ~ 4
        //
        System.out.println(reverseParentheses("(u(love)i)"));
        System.out.println(reverseParentheses("(ed(et(oc))el)"));
        System.out.println(reverseParentheses("a(bcdefghijkl(mno)p)q"));
        System.out.println(reverseParentheses("(abcd)"));
    }

    public static String reverseParentheses(String s) {
        // 括号内的字符串需要反转
        StringBuilder builder = new StringBuilder(s.length());

        dfs(builder, s, 0);

        return builder.toString().replaceAll("[()]", "");
    }

    public static void reverse(StringBuilder builder, int index, int end) {
        while (index < end) {
            char c1 = builder.charAt(index);
            char c2 = builder.charAt(end);
            builder.setCharAt(end, c1);
            builder.setCharAt(index, c2);
            index++;
            end--;
        }
    }

    public static int dfs(StringBuilder builder, String s, int index) {
        while (index < s.length()) {
            char c = s.charAt(index);
            builder.append(c);
            if (c == '(') {
                int nextIndex = dfs(builder, s, index + 1);
                reverse(builder, index, nextIndex);
                index = nextIndex;
            } else if (c == ')') {
                return index;
            }
            index++;
        }
        return index;
    }

}
