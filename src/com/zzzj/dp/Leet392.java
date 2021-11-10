package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-02 17:32
 */
public class Leet392 {

    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * <p>
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * <p>
     * 示例 1： 输入：s = "abc", t = "ahbgdc" 输出：true
     * <p>
     * 示例 2： 输入：s = "axc", t = "ahbgdc" 输出：false
     * <p>
     * 提示：
     * <p>
     * 两个字符串都只由小写字符组成。
     */
    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc"));
        System.out.println(isSubsequence("axc", "ahbgdc"));

        System.out.println(dynamicPlanning("abc", "ahbgdc"));
        System.out.println(dynamicPlanning("axc", "ahbgdc"));
    }

    private static boolean dynamicPlanning(String s, String t) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        if (t == null || t.isEmpty()) {
            return false;
        }

        final char[] str1 = s.toCharArray();
        final char[] str2 = t.toCharArray();

        for (int i = 0, j = 0; i < str2.length; i++) {
            if (str1[j] == str2[i]) {
                j++;
            }
            if (j >= str1.length) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSubsequence(String s, String t) {
        return violentRecursion(s.toCharArray(), t.toCharArray(), 0, 0);
    }

    private static boolean violentRecursion(char[] str1, char[] str2, int i, int j) {
        if (i >= str1.length) {
            return true;
        }

        if (j >= str2.length) {
            return false;
        }

        if (str1[i] == str2[j]) {
            return violentRecursion(str1, str2, i + 1, j + 1);
        }

        return violentRecursion(str1, str2, i, j + 1);
    }

}
