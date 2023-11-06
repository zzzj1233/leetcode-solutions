package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2023-10-27 14:20
 */
public class Leet1224 {

    public static void main(String[] args) {

        System.out.println(maxEqualFreq(new int[]{1, 1}));

//        System.exit(0);

        for (int i = 0; i < 100000; i++) {

            int M = LeetUtils.random.nextInt(100) + 1;

            int[] arr = ArrayUtil.generateArray(M, 1, M);

            ArrayCopyIterator it = ArrayCopyIterator.fromArray(arr);

            int r = maxEqualFreq(it.next());

            int rr = right(it.next());

            if (r != rr) {
                System.out.println("Error");
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("arr = " + Arrays.toString(it.next()));
                return;
            }

        }

        System.out.println("Ok");

    }

    public static int maxEqualFreq(int[] nums) {

        int N = nums.length;

        Map<Integer, Integer> freq = new HashMap<>(N);

        TreeMap<Integer, Integer> cnt = new TreeMap<>();

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            Integer old = freq.get(num);

            if (old == null) {
                freq.put(num, 1);
            } else {
                freq.put(num, old + 1);

                Integer v = cnt.get(old);
                if (v == 1)
                    cnt.remove(old);
                else
                    cnt.put(old, v - 1);
            }

            int newCnt = old == null ? 1 : old + 1;
            cnt.put(newCnt, cnt.getOrDefault(newCnt, 0) + 1);

            if (cnt.size() == 1) {
                Map.Entry<Integer, Integer> first = cnt.firstEntry();
                if (first.getValue() == 1 || first.getKey() == 1)
                    ans = i + 1;
            } else if (cnt.size() == 2) {

                Map.Entry<Integer, Integer> first = cnt.firstEntry();

                Map.Entry<Integer, Integer> last = cnt.lastEntry();

                if (first.getKey() == 1 && first.getValue() == 1 || (last.getKey() == 2 && last.getValue() == 1)) {
                    ans = i + 1;
                } else if (last.getValue() == 1 && last.getKey() == first.getKey() + 1)
                    ans = i + 1;
            }

        }

        return ans;
    }

    public static int right(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int res = 0, maxFreq = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count.getOrDefault(nums[i], 0) > 0) {
                freq.put(count.get(nums[i]), freq.get(count.get(nums[i])) - 1);
            }
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            maxFreq = Math.max(maxFreq, count.get(nums[i]));
            freq.put(count.get(nums[i]), freq.getOrDefault(count.get(nums[i]), 0) + 1);
            boolean ok = maxFreq == 1 ||
                    freq.get(maxFreq) * maxFreq + freq.get(maxFreq - 1) * (maxFreq - 1) == i + 1 && freq.get(maxFreq) == 1 ||
                    freq.get(maxFreq) * maxFreq + 1 == i + 1 && freq.get(1) == 1;
            if (ok) {
                res = Math.max(res, i + 1);
            }
        }
        return res;
    }

}
