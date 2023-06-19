package com.zzzj.str;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-06-16 17:51
 */
public class Leet1177 {

    public static void main(String[] args) {
        System.out.println(canMakePaliQueries("abcda", LeetUtils.convertInts("[[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]")));
    }

    public static List<Boolean> canMakePaliQueries(String s, int[][] queries) {

        int N = s.length();

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) preSum[i] = preSum[i - 1] ^ (1 << (s.charAt(i - 1) - 'a'));

        int M = queries.length;

        List<Boolean> ans = new ArrayList<>(M);

        OUTER:
        for (int i = 0; i < M; i++) {

            int[] query = queries[i];

            int start = query[0];

            int end = query[1];

            int k = query[2] << 1;

            if (start == end) {
                ans.add(true);
                continue;
            }

            int len = end - start + 1;

            int sum = preSum[end + 1] ^ preSum[start];

            // 是偶数
            if (len % 2 == 0) {

                // 最多有k个奇数
                for (int j = 0; j < 31; j++) {
                    if (((sum >> j) & 1) == 1) k--;
                    if (k < 0) {
                        ans.add(false);
                        continue OUTER;
                    }
                }

                // 是奇数
            } else {

                // 最多有k + 1个奇数
                k++;

                for (int j = 0; j < 31; j++) {
                    if (((sum >> j) & 1) == 1) k--;
                    if (k < 0) {
                        ans.add(false);
                        continue OUTER;
                    }
                }

            }

            ans.add(true);
        }

        return ans;
    }

}
