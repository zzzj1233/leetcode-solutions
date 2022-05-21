package com.zzzj.leet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-05-12 16:43
 */
public class Leet364 {

    private interface NestedInteger {
        boolean isInteger();

        List<NestedInteger> getList();

        Integer getInteger();

        void setInteger(int value);

        void add(NestedInteger ni);
    }


    public int depthSumInverse(List<NestedInteger> nestedList) {
        return bfs(nestedList, maxDepth(nestedList));
    }

    public int maxDepth(List<NestedInteger> nestedList) {
        int max = 0;

        for (NestedInteger item : nestedList) {
            if (!item.isInteger()) {
                max = Math.max(max, maxDepth(item.getList()));
            }
        }

        return max + 1;
    }

    public int bfs(List<NestedInteger> nestedList, int maxDep) {
        LinkedList<NestedInteger> queue = new LinkedList<>(nestedList);

        int depth = 0;

        int ans = 0;

        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger first = queue.removeFirst();
                if (first.isInteger()) {
                    ans += first.getInteger() * (maxDep - depth);
                } else {
                    queue.addAll(first.getList());
                }
            }
        }

        return ans;
    }

}
