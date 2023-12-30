package com.zzzj.acw;

import java.util.*;

public class Q487 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 预算
        int V = scanner.nextInt();

        int N = scanner.nextInt();

        Map<Integer, int[]> master = new HashMap<>();

        Map<Integer, List<int[]>> servant = new HashMap<>();

        for (int i = 0; i < N; i++) {
            // 价格
            int price = scanner.nextInt();
            // 重要度
            int weight = scanner.nextInt();
            // q=0, 表示该物品为主件, 如果q>0, 表示该物品为附件, q是所属主件的编号
            int type = scanner.nextInt();

            if (type == 0)
                master.put(i, new int[]{price, price * weight});
            else
                servant.computeIfAbsent(type - 1, integer -> new ArrayList<>()).add(new int[]{price, price * weight});
        }

        int[] dp = new int[V + 1];

        // 买附件前需要买主件
        for (int i = 0; i < N; i++) {

            int[] item = master.get(i);

            if (item == null)
                continue;

            int price = item[0];

            int value = item[1];

            for (int j = V; j >= price; j--) {

                dp[j] = Math.max(
                        dp[j],
                        dp[j - price] + value
                );

                List<int[]> s = servant.get(i);

                if (s == null)
                    continue;

                int size = s.size();

                int limit = 1 << size;

                for (int stat = 0; stat < limit; stat++) {

                    int p = price;

                    int v = value;

                    for (int k = 0; k < size; k++) {

                        if ((stat & (1 << k)) != 0) {
                            p += s.get(k)[0];
                            v += s.get(k)[1];

                            if (j - p >= 0)
                                dp[j] = Math.max(
                                        dp[j],
                                        dp[j - p] + v
                                );
                        }

                    }

                }

            }

        }

        System.out.println(dp[V]);
    }

}
