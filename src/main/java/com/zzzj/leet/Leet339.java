package com.zzzj.leet;

import java.util.List;

public class Leet339 {

    private interface NestedInteger {
        boolean isInteger();

        List<NestedInteger> getList();

        Integer getInteger();

        void setInteger(int value);

        void add(NestedInteger ni);
    }

    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            sum += dfs(nestedInteger, 1);
        }

        return sum;
    }

    public int dfs(NestedInteger nestedInteger, int depth) {
        if (nestedInteger.isInteger()) {
            return nestedInteger.getInteger() * depth;
        }
        int sum = 0;
        for (NestedInteger item : nestedInteger.getList()) {
            sum += dfs(item, depth + 1);
        }
        return sum;
    }

}
