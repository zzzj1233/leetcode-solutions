package com.zzzj.greedy;

import com.zzzj.util.Unresolved;

@Unresolved
public class Leet1702 {

    public static void main(String[] args) {
        // 111011
        System.out.println(maximumBinaryString("000110"));
    }

    public static String maximumBinaryString(String binary) {

        int N = binary.length();

        StringBuilder ans = new StringBuilder(binary);

        for (int i = 0; i < N; ) {
            if (is00(ans, i)) {
                ans.setCharAt(i, '1');
                i++;
            } else if (is010(ans, i)) {
                ans.setCharAt(i, '1');
                ans.setCharAt(i + 1, '0');
                ans.setCharAt(i + 2, '1');
                i += 3;
            } else {
                i++;
            }
        }

        return ans.toString();
    }

    public static boolean is00(CharSequence binary, int index) {
        return binary.charAt(index) == '0' && index + 1 < binary.length() && binary.charAt(index + 1) == '0';
    }

    public static boolean is010(CharSequence binary, int index) {
        return binary.charAt(index) == '0' && index + 2 < binary.length() && binary.charAt(index + 1) == '1' && binary.charAt(index + 2) == '0';
    }

}
