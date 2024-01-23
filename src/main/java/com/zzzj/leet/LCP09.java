package com.zzzj.leet;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2024-01-05 11:12
 */
public class LCP09 {

    public static void main(String[] args) {

        System.out.println(minJump(new int[]{3, 2, 4, 2, 4, 1, 1, 4}));

        // System.exit(0);

        for (; ; ) {

            int N = 1000;

            int[] arr = ArrayUtil.generateArray(N, 1, N);

            ArrayCopyIterator it = ArrayCopyIterator.fromArray(arr);

            int r = minJump(it.next());

            int rr = right(it.next());

            if (r != rr) {
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("it.next() = " + Arrays.toString(it.next()));
                return;
            }

        }

        // System.out.println("Ok");

    }

    public static int minJump(int[] jump) {

        int N = jump.length;

        int[] visited = new int[N];

        Arrays.fill(visited, Integer.MAX_VALUE);

        visited[0] = 1;

        int ans = Integer.MAX_VALUE;

        Deque<Integer> queue = new LinkedList<>();

        queue.add(0);

        while (!queue.isEmpty()) {

            Integer rm = queue.removeFirst();

            int next = jump[rm] + rm;

            if (next >= N) {
                ans = Math.min(ans, visited[rm]);
                continue;
            }

            int index = rm + 1;

            if (visited[rm] + 1 < visited[next]) {
                while (index <= next) {
                    if (visited[index] == Integer.MAX_VALUE)
                        queue.addLast(index);

                    visited[index] = Math.min(
                            visited[index],
                            visited[rm] + 2 - (index == next ? 1 : 0)
                    );

                    index++;
                }
            }


        }

        return ans;
    }

    public static int right(int[] jump) {
        int[] dp = new int[jump.length];
        dp[jump.length - 1] = 1;
        for (int i = jump.length - 2; i > -1; --i) {
            dp[i] = jump[i] + i >= jump.length ? 1 : dp[jump[i] + i] + 1;
            //遍历当前位置更新后影响到的后面的位置，只需要更新到dp[j] >= dp[i]+1即可
            //如果遍历到某dp[j]<dp[i]+1就不需要向右遍历了,因为j到dp.length的值会被当前遍历到的dp[j]更新而不是dp[i]+1
            for (int j = i + 1; j < dp.length && dp[j] >= dp[i] + 1; ++j) {
                dp[j] = dp[i] + 1;
            }
        }
        return dp[0];
    }

}
