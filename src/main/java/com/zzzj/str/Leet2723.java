package com.zzzj.str;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-04-23 14:39
 */
public class Leet2723 {

    public static final String SEPARATOR = "->";

    public static int adventureCamp(String[] expeditions) {
        int N = expeditions.length;

        if (N == 0) {
            return -1;
        }

        // 已知的
        Set<String> known = Arrays.stream(expeditions[0].split(SEPARATOR)).collect(Collectors.toSet());

        int ans = -1;

        int preExplore = 0;

        for (int i = 1; i < N; i++) {

            if (expeditions[i].trim().isEmpty()) {
                continue;
            }

            String[] camps = expeditions[i].split(SEPARATOR);

            int exploreCount = 0;

            for (String camp : camps) {

                if (known.add(camp)) {
                    exploreCount++;
                }

            }

            if (exploreCount > preExplore) {
                preExplore = exploreCount;
                ans = i;
            }
        }

        return ans;
    }

}
