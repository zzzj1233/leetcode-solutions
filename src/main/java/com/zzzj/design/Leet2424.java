package com.zzzj.design;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-10-11 12:19
 */
public class Leet2424 {

    private static class LUPrefix {
        Set<Integer> set;

        int max;

        public LUPrefix(int n) {
            set = new HashSet<>();
        }

        public void upload(int video) {
            if (video - 1 == max) {
                while (set.contains(video + 1)) {
                    set.remove(video + 1);
                    video++;
                }
                max = video;
            } else {
                set.add(video);
            }
        }

        public int longest() {
            return max;
        }

    }


}
