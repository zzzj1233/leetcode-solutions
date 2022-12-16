package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-11-23 14:17
 */
public class Leet2115 {

    public static void main(String[] args) {
        // ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
        System.out.println(findAllRecipes(
                LeetUtils.convertString1("[\"bread\",\"sandwich\",\"burger\"]"),
                LeetUtils.convertListStrings("[[\"yeast\",\"flour\"],[\"bread\",\"meat\"],[\"sandwich\",\"meat\",\"bread\"]]"),
                LeetUtils.convertString1("[\"yeast\",\"flour\",\"meat\"]")
        ));
    }

    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        int N = recipes.length;

        int[] inDegree = new int[N];

        Map<String, Integer> indexes = new HashMap<>(N);

        Set<String> suppliesSet = Arrays.stream(supplies).collect(Collectors.toSet());

        Map<Integer, List<Integer>> graph = new HashMap<>(N);

        for (int i = 0; i < N; i++) {
            indexes.put(recipes[i], i);
        }

        for (int i = 0; i < N; i++) {
            List<String> ingredient = ingredients.get(i);

            for (String it : ingredient) {

                Integer index = indexes.get(it);

                if (index != null) {
                    inDegree[i]++;
                    graph.computeIfAbsent(index, integer -> new ArrayList<>())
                            .add(i);
                }
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<String> ans = new ArrayList<>(N);

        OUTER:
        while (!queue.isEmpty()) {
            Integer index = queue.removeFirst();

            String recipe = recipes[index];

            // 这道菜可以做出来
            for (String s : ingredients.get(index)) {
                if (indexes.containsKey(s)) {
                    continue;
                }
                if (!suppliesSet.contains(s)) {
                    continue OUTER;
                }
            }

            List<Integer> neigh = graph.get(index);

            if (neigh != null) {

                for (Integer neighIndex : neigh) {
                    inDegree[neighIndex]--;

                    if (inDegree[neighIndex] == 0) {
                        queue.add(neighIndex);
                    }

                }

            }

            ans.add(recipe);
        }

        return ans;
    }

}
