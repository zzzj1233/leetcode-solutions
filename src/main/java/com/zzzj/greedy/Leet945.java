package com.zzzj.greedy;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2022-09-15 16:46
 */
public class Leet945 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            final int[] arr = ArrayUtil.generateArray(100, 0, 1000);
            if (minIncrementForUnique(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int minIncrementForUnique(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        int ans = 0;

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();

        Map.Entry<Integer, Integer> entry = iterator.next();

        while (entry != null) {
            Integer num = entry.getKey();
            int count = entry.getValue() - 1;

            Map.Entry<Integer, Integer> next = iterator.hasNext() ? iterator.next() : null;

            if (next == null) {
                ans += (1 + count) * (count) / 2;
                break;
            }

            if (count > 0) {
                // 没有更大
                int sub = next.getKey() - num - 1;

                int mul = Math.min(count, sub);

                ans += (1 + mul) * (mul) / 2;

                if (count > sub) {
                    int addition = count - sub;
                    ans += addition * (sub + 1);
                    map.put(next.getKey(), next.getValue() + addition);
                }
            }

            entry = next;
        }

        return ans;
    }

    public static int right(int[] A) {
        // 先排序
        Arrays.sort(A);
        int move = 0;
        // 遍历数组，若当前元素小于等于它的前一个元素，则将其变为前一个数+1
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                int pre = A[i];
                A[i] = A[i - 1] + 1;
                move += A[i] - pre;
            }
        }
        return move;
    }


}
