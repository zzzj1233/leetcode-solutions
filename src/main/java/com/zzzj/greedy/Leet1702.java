package com.zzzj.greedy;

import com.zzzj.util.Unresolved;

import java.util.Arrays;

@Unresolved
public class Leet1702 {

    public static void main(String[] args) {
        // 111011
        System.out.println(maximumBinaryString("000110"));
    }

    public static String maximumBinaryString(String binary) {

        int n = binary.length();

        int begin = (int) 1e6, count = 0;

        for (int i = 0; i < n; i++) {

            if (binary.charAt(i) == '0') {

                count++;

                begin = Math.min(begin, i);

            }

        }

        if (count < 2) return binary;

        char[] res = new char[n];

        Arrays.fill(res, '1');

        res[begin + count - 1] = '0';

        return new String(res);
    }


}
