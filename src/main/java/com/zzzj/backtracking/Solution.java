package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-02-15 16:37
 */
class Solution {
    public List<List<Integer>> getFactors(int n) {
        return dfs(2, n);
    }

    List<List<Integer>> dfs(int start, int num) {
        if (num == 1) {
            return new ArrayList<>();
        }

        int qNum = (int) Math.sqrt(num);
        List<List<Integer>> result = new ArrayList<>();
        for (int mulNum = start; mulNum <= qNum; mulNum++) {
            if (num % mulNum == 0) {
                List<Integer> simpleList = new ArrayList<>();
                simpleList.add(mulNum);
                simpleList.add(num / mulNum);
                result.add(simpleList);
                // 检查mulNum能怎么拆
                List<List<Integer>> nextLists = dfs(mulNum, num / mulNum);
                for (List<Integer> list : nextLists) {
                    list.add(mulNum);
                    result.add(list);
                }
            }
        }
        return result;
    }
}
