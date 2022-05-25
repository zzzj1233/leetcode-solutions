package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-25 12:03
 */
public class Leet434 {

    public static void main(String[] args) {
        System.out.println(countSegments(", , , , a, eaefa"));
        System.out.println(countSegments("Hello, my name is John"));
    }

    public static int countSegments(String s) {
        char[] chars = s.toCharArray();

        int ans = 0;

        int j = -1;

        for (int i = 0; i < chars.length; i++) {
            // 遇到空格
            if (chars[i] == ' ') {
                continue;
            } else {
                if (i > j){
                    ans++;
                }
                j = i + 1;
            }
        }

        return ans;
    }

}
