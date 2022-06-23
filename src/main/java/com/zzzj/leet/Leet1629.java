package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-22 11:36
 */
public class Leet1629 {


    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        int max = releaseTimes[0];
        int[] bucket = new int[26];
        bucket[keysPressed.charAt(0) - 'a'] = max;

        for (int i = 1; i < keysPressed.length(); i++) {
            char c = keysPressed.charAt(i);
            int time = releaseTimes[i] - releaseTimes[i - 1];
            max = Math.max(max, time);
            bucket[c - 'a'] = Math.max(bucket[c - 'a'], time);
        }

        for (int i = 25; i >= 0; i--) {
            if (bucket[i] == max) {
                return ((char) (i + 'a'));
            }
        }
        return ' ';
    }

}
