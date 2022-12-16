package com.zzzj.arr;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-12-05 16:39
 */
public class Leet1124 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(1000, 0, 10);
            if (longestWPI(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int longestWPI(int[] hours) {
        Map<Integer, Integer> position = new HashMap<>();

        int N = hours.length;

        int sum = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            boolean tired = hours[i] > 8;

            if (tired) {
                sum++;
            } else {
                sum--;
            }

            if (sum > 0) {
                ans = i + 1;
            } else {
                // 如果sum = 0 , 那么找-1
                // 如果sum = -1 , 那么找-2
                if (position.containsKey(sum - 1)) {
                    ans = Math.max(ans, i - position.get(sum - 1));
                }
            }

            position.putIfAbsent(sum, i);
        }

        return ans;
    }

    public static int right(int[] hours) {
        int n = hours.length, re = 0, cur = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (hours[i] > 8) cur++;
            else cur--;
            if (!map.containsKey(cur)) map.put(cur, i);
            if (cur > 0) re = i + 1;
            else if (map.containsKey(cur - 1)) re = Math.max(re, i - map.get(cur - 1));
        }
        return re;
    }

}
