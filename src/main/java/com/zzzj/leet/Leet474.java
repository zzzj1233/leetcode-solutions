package com.zzzj.leet;

import javax.naming.NameNotFoundException;

/**
 * @author zzzj
 * @create 2022-05-20 20:26
 */
public class Leet474 {

    public static void main(String[] args) {
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 3, 2));
//        System.out.println(findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
    }

    // ["10","0001","111001","1","0"]
    // 3
    // 2
    private static class Node {
        int zero;
        int one;
        int count;

        public Node(int zero, int one, int count) {
            this.zero = zero;
            this.one = one;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "zero=" + zero +
                    ", one=" + one +
                    ", count=" + count +
                    '}';
        }
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int N = strs.length;
        Node[] dp = new Node[N + 1];
        int[] ints = zeroCount(strs[0]);
        int zero = ints[0];
        int one = ints[1];

        dp[0] = new Node(0, 0, 0);

        if (zero > m || one > n) {
            dp[1] = new Node(0, 0, 0);
        } else {
            dp[1] = new Node(zero, one, 1);
        }

        for (int i = 2; i <= N; i++) {
            ints = zeroCount(strs[i - 1]);
            zero = ints[0];
            one = ints[1];
            if (dp[i - 1].zero + zero <= m && dp[i - 1].one + one <= n) {
                dp[i] = process(dp[i - 1], zero, one);
            } else if (dp[i - 2].zero + zero <= m && dp[i - 2].one + one <= n) {
                dp[i] = process(dp[i - 2], zero, one);
            } else {
                dp[i] = new Node(dp[i - 1].one, dp[i - 1].zero, dp[i - 1].count);
            }
        }
        return dp[N].count;
    }

    public static Node process(Node node, int zero, int one) {
        return new Node(node.zero + zero, node.one + one, node.count + 1);
    }

    public static int[] zeroCount(String str) {
        int[] ans = new int[2];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '0') {
                ans[0]++;
            } else {
                ans[1]++;
            }
        }
        return ans;
    }

}
