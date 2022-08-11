package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-08-07 22:42
 */
public class Leet1525 {

    public static void main(String[] args) {
        // 0001
        System.out.println(Integer.toBinaryString(-1));
    }

    public static int numSplits(String s) {

        int[] right = new int[26];
        int[] left = new int[26];

        int rightCount = 0;
        int leftCount = 0;

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            if (right[s.charAt(i) - 'a']++ == 0) {
                rightCount++;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (left[index]++ == 0) {
                leftCount++;
            }
            if (--right[index] == 0) {
                rightCount--;
            }
            if (leftCount == rightCount){
                ans++;
            }
        }

        return ans;
    }

}
