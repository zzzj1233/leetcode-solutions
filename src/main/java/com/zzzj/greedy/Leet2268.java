package com.zzzj.greedy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-09-22 15:41
 */
public class Leet2268 {

    public static void main(String[] args) {
        // System.out.println(minimumKeypresses("aacbbc"));
        System.out.println(minimumKeypresses("akj"));
    }

    public static int minimumKeypresses(String s) {
        int N = s.length();

        int[] count = new int[26];

        for (int i = 0; i < N; i++) {
            count[s.charAt(i) - 'a']++;
        }

        List<Integer> list = Arrays.stream(count).boxed()
                .filter(it -> it > 0)
                .sorted((o1, o2) -> o2 - o1)
                .collect(Collectors.toList());

        if (list.size() <= 9) {
            return list.stream().mapToInt(value -> value).sum();
        }

        if (list.size() <= 18) {
            int ans = 0;
            for (int i = 0; i < 9; i++) {
                ans += list.get(i);
            }
            for (int i = 9; i < list.size(); i++) {
                ans += 2 * list.get(i);
            }
            return ans;
        }

        int ans = 0;

        for (int i = 0; i < 9; i++) {
            ans += list.get(i);
        }

        for (int i = 9; i < 18; i++) {
            ans += 2 * list.get(i);
        }

        for (int i = 18; i < list.size(); i++) {
            ans += 3 * list.get(i);
        }

        return ans;
    }
}
