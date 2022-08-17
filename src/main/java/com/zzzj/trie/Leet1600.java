package com.zzzj.trie;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-08-16 11:25
 */
public class Leet1600 {

    private static class ThroneInheritance {

        Map<String, List<String>> map = new HashMap<>();

        Set<String> death = new HashSet<>();

        String king;

        public ThroneInheritance(String kingName) {
            map.put(kingName, new ArrayList<>());
            king = kingName;
        }

        public void birth(String parentName, String childName) {
            map.computeIfAbsent(parentName, ignore -> new ArrayList<>())
                    .add(childName);
        }

        public void death(String name) {
            death.add(name);
        }


        public void dfs(List<String> ans, String s) {
            if (!death.contains(s)) {
                ans.add(s);
            }

            List<String> children = map.get(s);

            if (children == null) {
                return;
            }

            for (String child : children) {
                dfs(ans, child);
            }
        }

        public List<String> getInheritanceOrder() {
            if (king == null) {
                return Collections.emptyList();
            }
            List<String> ans = new ArrayList<>();

            dfs(ans, king);

            return ans;
        }

    }

}
