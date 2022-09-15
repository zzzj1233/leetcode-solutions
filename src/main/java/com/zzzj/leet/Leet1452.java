package com.zzzj.leet;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-09-08 11:48
 */
public class Leet1452 {

    public static List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        int N = favoriteCompanies.size();

        List<Integer> ans = new ArrayList<>();

        Set[] sets = new Set[N];

        for (int i = 0; i < N; i++) {
            List<String> strings = favoriteCompanies.get(i);

            sets[i] = new HashSet(strings);
        }

        OUTER:
        for (int i = 0; i < N; i++) {
            Set set = sets[i];

            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                if (sets[j].containsAll(set)) {
                    continue OUTER;
                }
            }
            ans.add(i);
        }

        return ans;
    }

}
