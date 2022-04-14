package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-03 14:06
 */
public class Leet72 {

    /**
     * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
     * <p>
     * 你可以对一个单词进行如下三种操作：
     * <p>
     * 插入一个字符
     * <p>
     * 删除一个字符
     * <p>
     * 替换一个字符
     * <p>
     * 示例1：
     * <p>
     * 输入：word1 = "horse", word2 = "ros"
     * <p>
     * 输出：3
     * <p>
     * 解释：
     * <p>
     * horse -> rorse (将 'h' 替换为 'r')
     * <p>
     * rorse -> rose (删除 'r')
     * <p>
     * rose -> ros (删除 'e')
     *
     * <p>
     * 示例2：
     * <p>
     * 输入：word1 = "intention", word2 = "execution"
     * <p>
     * 输出：5
     * <p>
     * 解释：
     * <p>
     * intention -> inention (删除 't')
     * <p>
     * inention -> enention (将 'i' 替换为 'e')
     * <p>
     * enention -> exention (将 'n' 替换为 'x')
     * <p>
     * exention -> exection (将 'n' 替换为 'c')
     * <p>
     * exection -> execution (插入 'u')
     */
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        // System.out.println(dynamicPlanning("horse", "ros"));
    }

    public static int minDistance(String word1, String word2) {
        return -1;
    }

    public static int dfs(char[] word1, char[] word2) {
        return -1;
    }

}
