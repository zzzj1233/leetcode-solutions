package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-09-27 10:58
 */
public class Leet1647 {


    public static void main(String[] args) {

        System.out.println(minDeletions("ccaadp"));

//        System.exit(0);

        for (; ; ) {
            // ccaadp
            String str = LeetUtils.randomString(100, false);

            if (minDeletions(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println(minDeletions(str));
                System.out.println(right(str));
                break;
            }

        }

    }

    // "aaabbbcc"
    public static int minDeletions(String s) {
        // 不同频次,删除最小次数

        int[] bucket = new int[26];

        for (int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        Map<Integer, Integer> counter = new HashMap<>(26);

        for (int i = 0; i < 26; i++) {
            if (bucket[i] > 0) {
                counter.put(bucket[i], counter.getOrDefault(bucket[i], 0) + 1);
            }
        }

        int ans = 0;

        for (int i = 0; i < 26; i++) {
            int c = bucket[i];
            if (c > 0) {
                int size;
                while ((size = counter.getOrDefault(c, 0)) > 1) {
                    ans++;
                    counter.put(c, size - 1);
                    c--;
                    if (c == 0){
                        break;
                    }
                    counter.put(c, counter.getOrDefault(c, 0) + 1);
                }
            }
        }

        return ans;
    }

    public static int right(String s) {
        char[] cs = s.toCharArray();
        int[] dics = new int[26];
        for (int i = 0; i < cs.length; i++) {
            dics[cs[i] - 'a']++;
        }
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < dics.length; i++) {
            int cur = dics[i];
            while (cur != 0 && !set.add(cur)) {
                cur--;
                ans++;
            }
        }
        return ans;
    }


}
