package com.zzzj.tree;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author zzzj
 * @create 2022-08-09 18:19
 */
public class Leet666 {

    public static void main(String[] args) {
//        System.out.println(pathSum(new int[]{113, 215, 221}));
//        System.out.println(pathSum(new int[]{113, 221}));
//        System.out.println(pathSum(new int[]{113, 229, 349, 470, 485}));
//        System.out.println(pathSum(new int[]{111, 217, 221, 315, 415}));
        System.out.println(pathSum(new int[]{112, 213, 226, 311, 344, 476}));
    }

    private static class NodeInfo {
        int value;
        int childCount;

        public NodeInfo(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + " - " + childCount;
        }
    }

    public static int pathSum(int[] nums) {
        int maxDepth = Character.digit(String.valueOf(nums[nums.length - 1]).charAt(0), 10);

        NodeInfo[][] levels = new NodeInfo[maxDepth][];

        int N = levels.length;

        for (int i = 0; i < N; i++) {
            levels[i] = new NodeInfo[((int) Math.pow(2, i))];
        }

        for (int num : nums) {
            String s = String.valueOf(num);
            int depth = Character.digit(s.charAt(0), 10) - 1;
            int location = Character.digit(s.charAt(1), 10) - 1;
            int value = Character.digit(s.charAt(2), 10);

            // 每层最多多少个元素?
            levels[depth][location] = new NodeInfo(value);
        }

        for (int i = 0; i < levels[N - 1].length; i++) {
            if (levels[N - 1][i] == null) {
                continue;
            }
            int parentDepth = N - 2;
            int parentLocation = i / 2;
            while (parentDepth >= 0) {
                levels[parentDepth][parentLocation].childCount++;
                parentDepth--;
                parentLocation /= 2;
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < levels[i].length; j++) {
                NodeInfo node = levels[i][j];

                if (node == null) {
                    continue;
                }

                if (levels[i + 1][j << 1] == null && levels[i + 1][(j << 1) + 1] == null) {
                    int parentDepth = i - 1;
                    int parentLocation = j / 2;
                    while (parentDepth >= 0) {
                        levels[parentDepth][parentLocation].childCount++;
                        parentDepth--;
                        parentLocation /= 2;
                    }
                }

            }
        }

        int ans = Arrays.stream(levels[N - 1])
                .filter(Objects::nonNull)
                .mapToInt(nodeInfo -> nodeInfo.value)
                .sum();

        for (int i = N - 2; i >= 0; i--) {
            NodeInfo[] nodes = levels[i];
            for (NodeInfo node : nodes) {
                if (node == null) {
                    continue;
                }
                ans += node.value * Math.max(node.childCount, 1);
            }
        }

        return ans;
    }


}
