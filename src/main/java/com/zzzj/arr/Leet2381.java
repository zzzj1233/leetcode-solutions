package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-09-18 19:37
 */
public class Leet2381 {

    public static void main(String[] args) {

//        System.out.println(shiftingLetters("abc", LeetUtils.convertInts("[[0,1,0],[1,2,1],[0,2,1]]")));
//
//        System.out.println(shiftingLetters("dztz", LeetUtils.convertInts("[[0,0,0],[1,1,1]]")));

        System.out.println(shiftingLetters("xuwdbdqik", LeetUtils.convertInts("[[4,8,0],[4,4,0],[2,4,0],[2,4,0],[6,7,1],[2,2,1],[0,2,1],[8,8,0],[1,3,1]]")));

    }

    public static String shiftingLetters(String s, int[][] shifts) {

        // 差分
        int N = s.length();

        int[] diff = new int[N + 1];

        for (int[] shift : shifts) {
            if (shift[2] == 1) {
                diff[shift[0]]++;
                diff[shift[1] + 1]--;
            } else {
                diff[shift[0]]--;
                diff[shift[1] + 1]++;
            }
        }

        StringBuilder builder = new StringBuilder(N);

        int curr = 0;

        for (int i = 0; i < N; i++) {
            curr += diff[i];
            curr %= 26;
            int c = s.charAt(i) - 'a';
            builder.append(((char) (((((c + curr) % 26) + 26) % 26) + 'a')));
        }

        return builder.toString();
    }

}
