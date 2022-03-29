package com.zzzj.backtracking;

/**
 * @author zzzj
 * @create 2022-03-25 15:23
 */
public class Leet1415 {

    public static void main(String[] args) {
        System.out.println(getHappyString(3, 9));
        System.out.println(getHappyString(10, 100));
        System.out.println(getHappyString(1, 3));
        System.out.println(getHappyString(2, 7));
        System.out.println(getHappyString(1, 4));
    }

    public static final char[] HAPPY = {'a', 'b', 'c'};

    public static int cur = 0;

    public static String ans;


    public static String getHappyString(int n, int k) {
        cur = 0;
        ans = "";

        char[] path = new char[n];

        path[0] = HAPPY[0];
        dfs(n, k, path, 0, 1);

        path[0] = HAPPY[1];
        dfs(n, k, path, 1, 1);

        path[0] = HAPPY[2];
        dfs(n, k, path, 2, 1);

        return ans;
    }

    public static void dfs(int n, int k, char[] path, int pre, int index) {
        if (cur > k) {
            return;
        }

        if (index == path.length) {
            cur++;
            if (cur == k) {
                ans = String.valueOf(path);
            }
            return;
        }

        for (int i = 0; i < HAPPY.length; i++) {
            if (i != pre) {
                path[index] = HAPPY[i];
                dfs(n, k, path, i, index + 1);
            }
        }
    }

}
