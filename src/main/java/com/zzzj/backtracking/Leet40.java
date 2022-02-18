package com.zzzj.backtracking;


import cn.hutool.core.collection.ListUtil;
import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-01-25 17:58
 */
public class Leet40 {

    public static void main(String[] args) {

        Leet40Comparer comparer = new Leet40Comparer();

        for (int i = 0; i < 1000; i++) {
            int[] array = ArrayUtil.generateArray(10, 1, 50);
            final int target = LeetUtils.random.nextInt(30) + 1;
            List<List<Integer>> ans = comparer.combinationSum2(array, target);
            List<List<Integer>> perhaps = combinationSum2(array, target);

            if (perhaps.size() != ans.size()) {
                System.out.println(Arrays.toString(array));
                System.out.println(target);
                System.out.println("Error");
                System.out.println(ans);
                System.out.println(perhaps);
                return;
            }
        }

        System.out.println(combinationSum2(new int[]{2, 11, 14, 15, 16, 17, 32, 37, 41, 42},
                11));
    }


    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(candidates);

        process(ans, candidates, target, 0, new LinkedList<>(), 0, new LinkedList<>());

        return ans;
    }

    public static boolean process(List<List<Integer>> ans,
                                  int[] candidates,
                                  int target,
                                  int index,
                                  LinkedList<Integer> path,
                                  int cur, LinkedList<Integer> path2) {
        if (cur > target) {
            return false;
        }

        if (cur == target) {
            ans.add(new ArrayList<>(path));
            if (path.equals(ListUtil.toList(1, 2, 2))) {
                //   System.out.println(path2);
            }
            return true;
        }

        boolean successful = false;

        for (int i = index; i < candidates.length; i++) {

            if (cur == 0 && i > 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (successful && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.add(candidates[i]);
            path2.add(i);

            if (process(ans, candidates, target, i + 1, path, cur + candidates[i], path2)) {
                successful = true;
            }

            path.removeLast();
            path2.removeLast();
        }

        return successful;
    }

}
