package com.zzzj.interview;


import java.util.List;

/**
 * @author Zzzj
 * @create 2022-06-03 14:11
 */
public class Lesson4 {


    /**
     * @param job     job[i][0] = profit, job[i][1] = hard
     * @param ability 能力, ability[i] >= job[?][1] , 这个人就可以选择?这份job,每一个job可以被选择无限次
     *                贪心
     * @return 每个人按照标准选工作后所能获得的最高报酬
     */
    public static int[] question1(int[][] job, int[] ability) {
        return null;
    }


    /**
     * @param baggage 背包容量
     * @param snacks  零食, [i] = 零食的重量  >0
     *                动态规划
     * @return 不超过背包容量的情况下, 一共有多少种零食放法 (总体积为0也算一种放法)
     */
    public static int question2(int baggage, int[] snacks) {
        return -1;
    }

    /**
     * @param matrix 每个数都是正数
     *               动态规划
     * @return 要求从左上到右下, 每一步只能向右或者向下, 沿途经过的数字要累加起来, 求最小的路径和
     */
    public static int question3(int[][] matrix) {
        return -1;
    }

    /**
     * 最长公共子序列
     * 动态规划
     * 1143
     * <p>
     * 延伸题目:
     * tag : 最长公共子串
     * 1042
     * 1062
     * 5
     * 14
     * 409
     *
     * @return
     */
    public static int question4(String str1, String str2) {
        return -1;
    }

    /**
     * 最长公共子串
     * 动态规划 : dp[i][j] = 以str1[i]和str2[j]位置结尾,最长公共字串是多长
     *
     * @return
     */
    public static int question5(String str1, String str2) {
        return -1;
    }

    /**
     * 给定一个字符串数组,一个正整数K
     * <p>
     * 小根堆
     *
     * @return 词频(出现次数)最大的前K个字符串, 假设结果是唯一的
     */
    public static List<String> question6(String[] strs, int k) {
        return null;
    }


    /**
     * 实现如下结构
     * <pre>
     * {@code
     *      class TopRecord {
     *           public TopRecord(int k);
     *
     *           public void add(String str); // 添加一个字符串,可重复
     *
     *           public List<String> top(); // 返回词频最大的K个字符串
     *       }
     * }
     *
     * add O(log K)
     * top O(K)
     * 堆结构改写 : 小根堆
     * </pre>
     */
    public static void question7() {

    }

}
