package com.zzzj.window;

/**
 * @author zzzj
 * @create 2021-12-22 16:28
 */
public class Leet424 {

    public static void main(String[] args) {
        System.out.println(characterReplacement("ACBBBCCC", 2));
    }

    public static int characterReplacement(String s, int k) {

        int l = 0;
        int r = 0;

        int[] table = new int[26];

        int ans = 0;

        int maxIdx = 0;

        while (r < s.length()) {
            int index = s.charAt(r) - 65;

            table[index]++;

            maxIdx = table[maxIdx] > table[index] ? maxIdx : index;

            while (table[maxIdx] + k < r - l + 1) {
                int idx2 = s.charAt(l) - 65;
                table[idx2]--;

                if (idx2 == maxIdx) {
                    // 重新计算maxIdx
                    maxIdx = getMaxIdx(table);
                }

                l++;
            }

            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

    private static int getMaxIdx(int[] table) {
        int maxIdx = 0;

        for (int i = 1; i < table.length; i++) {
            if (table[i] > table[maxIdx]) {
                maxIdx = i;
            }
        }

        return maxIdx;
    }

}
