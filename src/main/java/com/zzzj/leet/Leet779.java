package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-08-06 23:21
 */
public class Leet779 {

    public static void main(String[] args) {
        System.out.println(1 ^ 1);
        System.out.println(0 ^ 1);
    }

    public static int kthGrammar(int n, int k) {
        return dfs(n, k);
    }

    public static int dfs(int n, int k) {
        // 0
        if (n == 1) {
            return 0;
        }
        // 01
        if (n == 2) {
            if (k == 1) {
                return 0;
            } else {
                return 1;
            }
        }

        //         0
        //        0  1
        //       01   10
        //      0110 1001
        // 01101001 | 10010110

        int middle = ((int) Math.pow(2, n)) / 2;

        if (k <= middle) {
            return dfs(n - 1, k);
        } else {
            int middleMiddle = middle / 2;
            if (k <= middleMiddle) {
                return dfs(n - 1, middleMiddle + k - middle);
            } else {
                return dfs(n - 1, k - middle - middleMiddle);
            }
        }
    }

}
