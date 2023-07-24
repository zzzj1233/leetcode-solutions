package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-07-05 15:52
 */
public class Leet1125 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(smallestSufficientTeam(new String[]{"java", "nodejs", "reactjs"}, LeetUtils.convertListStrings("[[\"java\"],[\"nodejs\"],[\"nodejs\",\"reactjs\"]]"))));

        System.out.println(Arrays.toString(smallestSufficientTeam(new String[]{"algorithms", "math", "java", "reactjs", "csharp", "aws"}, LeetUtils.convertListStrings("[[\"algorithms\",\"math\",\"java\"],[\"algorithms\",\"math\",\"reactjs\"],[\"java\",\"csharp\",\"aws\"],[\"reactjs\",\"csharp\"],[\"csharp\",\"math\"],[\"aws\",\"java\"]]"))));
////
        System.out.println(Arrays.toString(smallestSufficientTeam(new String[]{"mwobudvo", "goczubcwnfze", "yspbsez", "pf", "ey", "hkq"}, LeetUtils.convertListStrings("[[],[\"mwobudvo\"],[\"hkq\"],[\"pf\"],[\"pf\"],[\"mwobudvo\",\"pf\"],[],[\"yspbsez\"],[],[\"hkq\"],[],[],[\"goczubcwnfze\",\"pf\",\"hkq\"],[\"goczubcwnfze\"],[\"hkq\"],[\"mwobudvo\"],[],[\"mwobudvo\",\"pf\"],[\"pf\",\"ey\"],[\"mwobudvo\"],[\"hkq\"],[],[\"pf\"],[\"mwobudvo\",\"yspbsez\"],[\"mwobudvo\",\"goczubcwnfze\"],[\"goczubcwnfze\",\"pf\"],[\"goczubcwnfze\"],[\"goczubcwnfze\"],[\"mwobudvo\"],[\"mwobudvo\",\"goczubcwnfze\"],[],[\"goczubcwnfze\"],[],[\"goczubcwnfze\"],[\"mwobudvo\"],[],[\"hkq\"],[\"yspbsez\"],[\"mwobudvo\"],[\"goczubcwnfze\",\"ey\"]]"))));

    }

    public static int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

        int N = req_skills.length;

        int M = people.size();

        int[] ps = new int[M];

        for (int i = 0; i < M; i++)
            for (String skill : people.get(i))
                for (int j = 0; j < N; j++)
                    if (skill.equals(req_skills[j])) ps[i] |= 1 << j;

        List[] dp = new List[1 << N];

        dp[0] = Collections.emptyList();

        for (int i = 0; i < M; i++) {

            for (int stat = 0; stat < dp.length; stat++) {

                if (dp[stat] == null) continue;

                int s = stat | ps[i];

                if (dp[s] == null || dp[stat].size() + 1 < dp[s].size()) {
                    dp[s] = new ArrayList(dp[stat]);
                    dp[s].add(i);
                }

            }

        }
        return dp[dp.length - 1]
                .stream().mapToInt(value -> (int) value)
                .toArray();
    }


}
