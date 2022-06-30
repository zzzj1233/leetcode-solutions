package com.zzzj.daily;

/**
 * @author zzzj
 * @create 2022-06-27 11:11
 */
public class Leet522 {

    public static void main(String[] args) {
        System.out.println(findLUSlength(new String[]{"aba", "cdc", "eae"}));
    }

    // 给出一个字符串数组，在里面找出字符串满足当前字符串不是字符串数组中其他字符串的子序列,返回满足条件的字符串中,最长的字符串的长度
    public static int findLUSlength(String[] strs) {

        int ans = -1;

        OUTER:
        for (int i = 0; i < strs.length; i++) {

            for (int j = 0; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isSubSeq(strs[i], strs[j])) {
                    continue OUTER;
                }
            }

            ans = Math.max(ans, strs[i].length());
        }

        return ans;
    }

    /**
     * @return str1是否是str2的子序列
     */
    public static boolean isSubSeq(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return false;
        }

        int str1Index = 0;
        int str2Index = 0;

        while (str2Index < str2.length() && str1Index < str1.length()) {
            char char1 = str1.charAt(str1Index);
            char char2 = str2.charAt(str2Index);
            if (char1 == char2) {
                str1Index++;
            }
            str2Index++;
        }

        return str1Index == str1.length();
    }

}
