package com.zzzj.daily;

/**
 * @author zzzj
 * @create 2022-05-13 15:29
 */
public class Leet2530 {

    public static void main(String[] args) {
        System.out.println(oneEditAway("pale", "ple"));
        System.out.println(oneEditAway("pales", "pal"));
        System.out.println(oneEditAway("teacher", "taches"));
    }

    // "teacher"
    // "taches"
    public static boolean oneEditAway(String first, String second) {
        // 插入一个字符、删除一个字符或者替换一个字符
        int N1 = first.length();
        int N2 = second.length();

        if (Math.abs(N2 - N1) > 1) {
            return false;
        }

        boolean allow = true;

        int i = 0;
        int j = 0;

        for (; i < N1 && j < N2; i++, j++) {
            if (first.charAt(i) != second.charAt(j)) {
                if (allow) {
                    allow = false;
                    // 删除一个字符
                    if (N1 > N2) {
                        j--;
                        // 插入一个字符
                    } else if (N2 > N1) {
                        i--;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }

}
