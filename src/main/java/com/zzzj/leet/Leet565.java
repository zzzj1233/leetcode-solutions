package com.zzzj.leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-09-19 18:02
 */
public class Leet565 {

    public static void main(String[] args) {
        System.out.println(arrayNesting(new int[]{5, 4, 0, 3, 1, 6, 2}));
    }

    // 输入: A = [5,4,0,3,1,6,2]
    // 输出: 4
    // 解释:
    // A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
    //
    // 其中一种最长的 S[K]:
    // S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
    public static int arrayNesting(int[] nums) {
        Map<Integer, Integer> cache = new HashMap<>(nums.length);

        HashSet<Integer> visited = new HashSet<>(nums.length);

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if (!cache.containsKey(nums[i])) {
                cache.put(i, dfs(cache, i, visited, nums));
            }
            ans = Math.max(ans, cache.get(nums[i]));
        }

        return ans;
    }

    private static int dfs(Map<Integer, Integer> cache, int index, Set<Integer> visited, int[] nums) {
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        if (visited.contains(index)) {
            return 0;
        }
        int result = 1;
        visited.add(index);
        result += dfs(cache, nums[index], visited, nums);
        visited.remove(index);
        cache.put(index, result);
        return result;
    }


}
