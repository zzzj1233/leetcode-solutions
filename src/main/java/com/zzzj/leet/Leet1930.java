package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-23 19:15
 */
public class Leet1930 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString(100, false);
            if (countPalindromicSubsequence(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int countPalindromicSubsequence(String s) {
        char[] chars = s.toCharArray();

        int[][] prefix = new int[s.length()][26];

        int[] indexes = new int[26];

        int[] visited = new int[26];

        Arrays.fill(indexes, -1);

        prefix[0] = new int[26];

        prefix[0][s.charAt(0) - 'a']++;

        indexes[s.charAt(0) - 'a'] = 0;

        int ans = 0;

        for (int i = 1; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';

            prefix[i] = Arrays.copyOfRange(prefix[i - 1], 0, prefix[i].length);

            prefix[i][index]++;

            int preIndex = indexes[index];

            if (preIndex >= 0 && i - 1 > preIndex) {
                // 当前index和preIndex中间有哪些char?
                int[] curChars = prefix[i - 1];
                int[] preChars = prefix[preIndex];

                for (int j = 0; j < 26; j++) {
                    if (curChars[j] > preChars[j] && !isVisited(visited[index], j)) {
                        ans++;
                        visited[index] |= (1 << j);
                    }
                }
            }

            if (indexes[index] == -1) {
                indexes[index] = i;
            }

        }

        return ans;
    }

    public static boolean isVisited(int stat, int index) {
        return (stat & (1 << index)) != 0;
    }

    public static int right(String s) {
        int num = 0;
        for (int i = 0; i < 26; i++) {
            int[] arr = new int[26];
            char index = (char) (i + 97);
            int left = s.indexOf(index);
            int right = s.lastIndexOf(index);
            for (int j = left + 1; j < right; j++) {
                arr[s.charAt(j) - 97]++;
            }
            for (int j = 0; j < 26; j++) {
                if (arr[j] != 0) {
                    num++;
                }
            }
        }
        return num;

    }

}
