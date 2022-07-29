package com.zzzj.trie;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-07-27 14:15
 */
public class Leet386 {


    public static void main(String[] args) {
        System.out.println(lexicalOrder(103));
    }

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new LinkedList<>();
        dfs(n, 1, ans);
        return ans;
    }

    public static void dfs(int n, int cur, List<Integer> ans) {
        if (cur > n) {
            return;
        }
        ans.add(cur);

        dfs(n, cur * 10, ans);

        // 获取最高位
        if (cur % 10 != 9) {
            dfs(n, cur + 1, ans);
        }
    }

}
