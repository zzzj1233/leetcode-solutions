package com.zzzj.arr;

public class Let2810 {

    public static void main(String[] args) {
        System.out.println(findClosest(new String[]{"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"}, "a", "student"));
    }

    public static int findClosest(String[] words, String word1, String word2) {
        int N = words.length;

        int l = -1;
        int r = -1;

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if (words[i] == word1) {
                l = i;
                if (r != -1) {
                    ans = Math.min(ans, l - r);
                }
            } else if (words[i] == word2) {
                r = i;
                if (l != -1) {
                    ans = Math.min(ans, r - l);
                }
            }
        }

        return ans;
    }

}
