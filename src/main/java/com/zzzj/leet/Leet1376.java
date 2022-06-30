package com.zzzj.leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-06-27 11:42
 */
public class Leet1376 {

    // 15
    // 0
    // [-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6]
    // [1,1,1,1,1,1,1,0,0,0,0,0,0,0,0]
    public static void main(String[] args) {
        System.out.println(numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0}));
        System.out.println(numOfMinutes(1, 0, new int[]{-1}, new int[]{0}));
        System.out.println(numOfMinutes(15, 0, new int[]{-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6}, new int[]{1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}));
    }

    // N名员工,id从 0 ~ n - 1
    // headId 总负责人
    // i名员工的负责人
    // 通知时间
    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, Set<Integer>> managerMap = new HashMap<>(manager.length);

        for (int i = 0; i < manager.length; i++) {
            managerMap.computeIfAbsent(manager[i], ignore -> new HashSet<>())
                    .add(i);
        }

        return informTime[headID] + dfs(headID, informTime, managerMap);
    }

    public static int dfs(int leaderID, int[] informTime, Map<Integer, Set<Integer>> managerMap) {
        Set<Integer> followers = managerMap.get(leaderID);


        int result = 0;

        if (followers != null) {

            for (Integer follower : followers) {
                result = Math.max(result, informTime[follower] + dfs(follower, informTime, managerMap));
            }

        }

        return result;
    }

}
