package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-05-24 19:41
 */
public class Leet128 {

    public static void main(String[] args) {

//        System.out.println(longestConsecutive(new int[]{1, 5, 3, 2, 3, 7, 1, 4, 2, 2}));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(10, 0, 10);
            if (longestConsecutive(arr) != ufSolution(arr)) {
                System.out.println(Arrays.toString(arr));
                System.out.println("Error");
                return;
            }
        }
    }


    private static class UF {
        Map<Integer, Integer> parent = new HashMap<>();

        void union(int p, int q) {
            if (!parent.containsKey(p)) {
                parent.put(p, p);
            }
            if (!parent.containsKey(q)) {
                parent.put(q, q);
            }

            Integer pParent = find(p);
            Integer qParent = find(q);

            if (pParent.equals(qParent)) {
                return;
            }

            parent.put(pParent, qParent);
        }

        Integer find(int p) {
            while (parent.get(p) != p) {
                p = parent.get(p);
            }
            return p;
        }
    }

    // 使用并查集
    public static int ufSolution(int[] nums) {

        UF uf = new UF();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            uf.union(num, num + 1);
        }

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, uf.find(nums[i]) - nums[i]);
        }

        return ans;
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

//        final int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
//        Arrays.sort(copy);
//        System.out.println(Arrays.toString(copy));

        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int num : nums) {
            treeSet.add(num);
        }

        Integer num = treeSet.first();

        int max = 1;
        int ans = max;

        Integer bigger = treeSet.higher(num);

        while (bigger != null) {
            if (bigger == num + 1) {
                max++;
            } else {
                max = 1;
            }
            ans = Math.max(ans, max);
            num = bigger;
            bigger = treeSet.higher(num);
        }

        return ans;
    }

    public static int right(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

}
