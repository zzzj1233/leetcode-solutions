package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-18 18:54
 */
public class Leet439 {

    public static void main(String[] args) {
        System.out.println(parseTernary("T?T?F:5:3"));
    }

    public static String parseTernary(String expression) {
        return dfs(expression.toCharArray(), 0);
    }

    public static String dfs(char[] chars, int index) {
        // F return ans2
        if (chars[index] == 'F') {
            // find next :
            while (chars[index] != ':') {
                index++;
            }
            index++;
            return dfs(chars, index);
            // T return ans1
        } else if (chars[index] == 'T') {
            // skip ?
            return dfs(chars, index + 2);
        } else {
            StringBuilder ans = new StringBuilder(chars.length - index);
            while (index < chars.length && chars[index] != ':') {
                ans.append(chars[index++]);
            }
            return ans.toString();
        }
    }

}
