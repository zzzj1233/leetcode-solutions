package com.zzzj.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-09-13 17:16
 */
public class Leet763 {

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }

    public static List<Integer> partitionLabels(String s) {
        int[] maxPosition = new int[26];

        int N = s.length();

        for (int i = 0; i < N; i++) {
            int charIndex = s.charAt(i) - 'a';
            maxPosition[charIndex] = i;
        }

        int index = 0;

        int max = -1;

        List<Integer> ans = new ArrayList<>();

        while (index < N) {
            char c = s.charAt(index);

            max = Math.max(max, maxPosition[c - 'a']);

            int start = index;

            index++;

            while (index <= max) {
                c = s.charAt(index);
                max = Math.max(max, maxPosition[c - 'a']);
                index++;
            }

            ans.add(index - start);
        }

        return ans;
    }

}
