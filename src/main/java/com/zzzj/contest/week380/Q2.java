package com.zzzj.contest.week380;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-01-15 15:19
 */
public class Q2 {

    public static void main(String[] args) {

        System.out.println(beautifulIndices(
                "owaapzdk",
                "apzdk",
                "wa",
                1
        ));

        System.out.println(beautifulIndices(
                "abcd",
                "a",
                "a",
                4
        ));

        System.out.println(beautifulIndices(
                "isawsquirrelnearmysquirrelhouseohmy",
                "my",
                "squirrel",
                5
        ));

    }

    public static List<Integer> beautifulIndices(String s, String a, String b, int k) {

        int N = s.length();

        boolean[] aa = new boolean[N];

        boolean[] ba = new boolean[N];

        for (int i = 0; i <= N - a.length(); i++)
            if (s.charAt(i) == a.charAt(0) && check(s, a, i))
                aa[i] = true;

        for (int i = 0; i <= N - b.length(); i++)
            if (s.charAt(i) == b.charAt(0) && check(s, b, i))
                ba[i] = true;

        LinkedList<Integer> queue = new LinkedList<>();

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= k && i < N; i++)
            if (ba[i])
                queue.add(i);

        for (int i = 0; i < N; i++) {
            if (i + k < N && ba[i + k])
                queue.add(i + k);
            while (!queue.isEmpty() && queue.peekFirst() + k < i)
                queue.removeFirst();
            if (aa[i] && !queue.isEmpty())
                ans.add(i);
        }

        return ans;
    }

    public static boolean check(String str, String match, int index) {
        for (int i = 0; i < match.length(); i++)
            if (str.charAt(i + index) != match.charAt(i))
                return false;
        return true;
    }


}
