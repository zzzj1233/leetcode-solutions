package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-06-16 10:07
 */
public class Leet532 {

    public static void main(String[] args) {
//        System.out.println(findPairs(new int[]{1, 8, 9, 0, 1, 3, 9, 9, 6, 8}, 9));
//
//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            final int[] arr = ArrayUtil.generateArray(10, 0, 10);
            final int k = LeetUtils.random.nextInt(arr.length);
            if (findPairs(arr, k) != right(arr, k)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);

        int ans = 0;

        Collection<Integer> visited = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            Integer pre = map.get(nums[i]);
            if (pre != null && k == 0 && !visited.contains(nums[i])) {
                ans++;
                visited.add(nums[i]);
            }
            map.put(nums[i], i);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer num = entry.getKey();
            Integer i = entry.getValue();
            if (k > 0 && map.containsKey(num - k) && map.get(num - k) > i) {
                ans++;
            }
            if (k > 0 && map.containsKey(num + k) && map.get(num + k) > i) {
                ans++;
            }
        }

        return ans;
    }

    public static int right(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        if (k < 0) return count;
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], 0);
            map.put(nums[i], map.get(nums[i]) + 1);
        }
        for (int i : map.keySet()) {
            if (k == 0) {
                if (map.get(i) > 1)
                    count++;
            } else if (map.containsKey(i + k)) {
                count++;
            }
        }
        return count;
    }

}
