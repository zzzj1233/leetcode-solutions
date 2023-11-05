package com.zzzj.leet;


import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2022-06-27 15:48
 */
public class Leet2444 {

    public static void main(String[] args) {

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int M = 500;

            int num1 = LeetUtils.random.nextInt(M) + 1;
            int num2 = LeetUtils.random.nextInt(M) + 1;

            int min = Math.min(num1, num2);

            int max = Math.max(num1, num2);

            int[] arr = ArrayUtil.generateArray(M, 1, M);

            ArrayCopyIterator it = new ArrayCopyIterator(arr);

            long r1 = countSubarrays(it.next(), min, max);

            long r2 = right(it.next(), min, max);

            if (r1 != r2) {
                System.out.println("Error");
                System.out.println("it.next() = " + Arrays.toString(it.next()));
                System.out.println("min = " + min);
                System.out.println("max = " + max);
                System.out.println("r1 = " + r1);
                System.out.println("r2 = " + r2);
                return;
            }

        }

        System.out.println("Ok");

        System.exit(0);

        System.out.println(countSubarrays(new int[]{1, 3, 5, 2, 7, 5}, 1, 5));

        System.out.println(countSubarrays(new int[]{1, 1, 1, 1}, 1, 1));

        System.out.println(countSubarrays(new int[]{35054, 398719, 945315, 945315, 820417, 945315, 35054, 945315, 171832, 945315, 35054, 109750, 790964, 441974, 552913}, 35054, 945315));

        // 118
        System.out.println(countSubarrays(new int[]{934372, 927845, 479424, 49441, 17167, 17167, 65553, 927845, 17167, 927845, 17167, 425106, 17167, 927845, 17167, 927845, 251338, 17167}, 17167, 927845));

    }

    public static long countSubarrays(int[] nums, int minK, int maxK) {

        // 最小值 = minK
        // 最大值 = maxK

        if (minK > maxK)
            return 0;

        TreeSet<Integer> minIndexes = new TreeSet<>();

        TreeSet<Integer> maxIndexes = new TreeSet<>();

        TreeSet<Integer> illegalIndexes = new TreeSet<>();

        int N = nums.length;

        for (int i = 0; i < N; i++) {
            if (nums[i] == minK)
                minIndexes.add(i);
            else if (nums[i] == maxK)
                maxIndexes.add(i);
            else if (nums[i] < minK || nums[i] > maxK)
                illegalIndexes.add(i);
        }

        if (minK == maxK)
            maxIndexes = minIndexes;

        if (minIndexes.isEmpty() || maxIndexes.isEmpty())
            return 0;

        long ans = 0;

        Integer minIndex = minIndexes.first();
        Integer maxIndex = maxIndexes.first();

        Integer floor = illegalIndexes.floor(Math.min(minIndex, maxIndex));

        int prevEnd;

        if (floor == null)
            prevEnd = 0;
        else
            prevEnd = floor + 1;

        while (minIndex != null && maxIndex != null) {

            int max = Math.max(minIndex, maxIndex);

            int min = Math.min(minIndex, maxIndex);

            Integer centerIllegal;

            // 确保 minIndex - maxIndex 中间没有 illegalIndex
            while ((centerIllegal = illegalIndexes.ceiling(min)) != null && centerIllegal < max) {
                minIndex = minIndexes.ceiling(centerIllegal);
                maxIndex = maxIndexes.ceiling(centerIllegal);
                if (minIndex == null || maxIndex == null)
                    return ans;
                max = Math.max(minIndex, maxIndex);
                min = Math.min(minIndex, maxIndex);
                prevEnd = illegalIndexes.floor(Math.min(minIndex, maxIndex)) + 1;
            }

            if (minK == maxK) {
                prevEnd = min;
            }

            Integer nextIllegal = illegalIndexes.ceiling(max);

            // 直到END
            if (nextIllegal == null) {
                ans += (long) (N - max) * (min - prevEnd + 1);
            } else {
                ans += (long) (nextIllegal - max) * (min - prevEnd + 1);
            }

            if (minK == maxK) {
                minIndex = minIndexes.ceiling(minIndex + 1);
                maxIndex = maxIndexes.ceiling(maxIndex + 1);
            } else {
                prevEnd = min + 1;
                if (minIndex > maxIndex)
                    maxIndex = maxIndexes.ceiling(maxIndex + 1);
                else
                    minIndex = minIndexes.ceiling(minIndex + 1);
            }

        }


        return ans;
    }

    public static long right(int[] nums, int minK, int maxK) {
        int n = nums.length;
        long ret = 0;
        int left = 0, right = 0, lastMinKIndex = -1, lastMaxKIndex = -1;
        for (; right < n; right++) {

            int num = nums[right];
            if (num > maxK || num < minK) { //值不在区间[minK,maxK]内，重置
                left = right + 1;
                lastMinKIndex = -1;
                lastMaxKIndex = -1;
                continue;
            }
            if (num == minK) lastMinKIndex = right;
            if (num == maxK) lastMaxKIndex = right;
            if (lastMinKIndex != -1 && lastMaxKIndex != -1) {//[left, right]是一个定界子数组
                ret += Math.min(lastMaxKIndex, lastMinKIndex) - left + 1;// 累加以right为右边界的定界子数组的数量
            }
        }
        return ret;
    }

}
