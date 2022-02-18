package com.zzzj.backtracking;

import com.zzzj.util.Unresolved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-02-14 10:57
 */
@Unresolved
public class Leet1239 {

    public static void main(String[] args) {
        System.out.println(maxLength(Arrays.asList("aa", "bb")));
    }

    public static int ans;

    public static int maxLength(List<String> arr) {
        ans = 0;

        List<Integer> masks = new ArrayList<>(arr.size());

        OUTER:
        for (String s : arr) {

            int mask = 0;

            for (int i = 0; i < s.length(); i++) {
                int index = (int) s.charAt(i) - 'a';
                if (((mask >> index) & 1) != 0) {
                    continue OUTER;
                }
                mask |= 1 << index;
            }

            if (mask != 0){
                masks.add(mask);
            }
        }

        process(masks, 0, 0);

        return ans;
    }

    public static void process(List<Integer> masks, int idx, int mask) {
        if (idx == masks.size()) {
            // mask有多少个1,就有多少个单词
            ans = Math.max(ans, Integer.bitCount(mask));
            return;
        }

        if ((mask & masks.get(idx)) == 0) {
            process(masks, idx + 1, mask | masks.get(idx));
        }

        process(masks, idx + 1, mask);
    }

}
