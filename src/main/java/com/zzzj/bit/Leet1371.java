package com.zzzj.bit;


import com.zzzj.leet.LeetUtils;
import com.zzzj.util.StringCopyIterator;

import java.util.HashMap;
import java.util.Map;

public class Leet1371 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            int N = LeetUtils.random.nextInt(100) + 1;

            String str = LeetUtils.randomString(N, false);

            StringCopyIterator iterator = new StringCopyIterator(str);

            if (findTheLongestSubstring(str) != right(str)){
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Ok~");
    }

    public static int findTheLongestSubstring(String s) {
        int a = 0;
        int e = 0;
        int i = 0;
        int o = 0;
        int u = 0;

        int N = s.length();

        Map<Integer, Integer> position = new HashMap<>();

        int ans = 0;

        for (int j = 0; j < N; j++) {
            char c = s.charAt(j);
            if (c == 'a') {
                a++;
            } else if (c == 'e') {
                e++;
            } else if (c == 'i') {
                i++;
            } else if (c == 'o') {
                o++;
            } else if (c == 'u') {
                u++;
            }

            int bits = compress(a, e, i, o, u);

            if (isEven(bits)) {
                ans = j + 1;
            } else if (position.containsKey(bits)) {
                // 3 2 2 2 2
                ans = Math.max(ans, j - position.get(bits));
            } else {
                position.put(bits, j);
            }


        }

        return ans;
    }

    public static int compress(int a, int e, int i, int o, int u) {
        return mod2(a) << 4 | mod2(e) << 3 | mod2(i) << 2 | mod2(o) << 1 | mod2(u);
    }

    public static boolean isEven(int bits) {
        return bits == 0;
    }

    public static int mod2(int num) {
        return num % 2;
    }

    private static final String VOWELS = "aeiou";

    public static int right(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        int size = s.length();
        int state = 0; // (00000)
        int maxSize = 0;
        map.putIfAbsent(0, -1);
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < VOWELS.length(); k++) {
                if (s.charAt(i) == VOWELS.charAt(k)) {
                    state ^= (1 << (VOWELS.length() - k - 1));
                    break;
                }
            }
            if (map.containsKey(state)) {
                maxSize = Math.max(maxSize, i - map.get(state));
            }
            map.putIfAbsent(state, i);
        }

        return maxSize;
    }

}
