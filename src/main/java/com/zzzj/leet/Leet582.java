package com.zzzj.leet;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-06-24 15:46
 */
public class Leet582 {

    public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Set<Integer>> ppidMap = new HashMap<>(ppid.size());

        for (int i = 0; i < ppid.size(); i++) {
            ppidMap.computeIfAbsent(ppid.get(i), ignore -> new HashSet<>()).add(pid.get(i));
        }

        List<Integer> result = new LinkedList<>();
        result.add(kill);

        HashSet<Integer> set = new HashSet<>();
        set.add(kill);

        dfs(result, ppidMap, set);

        return result;
    }

    public static void dfs(List<Integer> result,
                           Map<Integer, Set<Integer>> ppidMap,
                           Set<Integer> kill) {

        for (Integer kil : kill) {
            Set<Integer> set = ppidMap.get(kil);

            if (set != null && !set.isEmpty()) {
                result.addAll(set);
                dfs(result, ppidMap, set);
            }

        }

    }

}
