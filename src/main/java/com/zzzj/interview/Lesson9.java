package com.zzzj.interview;

/**
 * @author Zzzj
 * @create 2022-07-09 21:56
 */
public class Lesson9 {

    /**
     * 是否可以将arr分为4个部分,每个部分的和都相同
     * attention:
     * [1,2,3]
     * 如果在 i == 1 这刀进行划分, i == 1这个数据将会被丢掉
     *
     * @see com.zzzj.interview.solutions.lesson9.Question2Solution
     */
    public static boolean question2(int[] arr) {
        return false;
    }

    /***
     *
     * aim 是否可以由str1和str2交错组成,题目保证 len(aim) = len(str1) + len(str2)
     * @see com.zzzj.interview.solutions.lesson9.Question3Solution
     */
    public static boolean question3(String str1, String str2, String aim) {
        return false;
    }


    /**
     * @param arr 正数数组
     * @return 最小子集 到最大子集的最小不可组成元素
     * <p>
     * 例如：
     * arr = [2,4,1]
     * min = 1
     * max = 2 + 4 + 1 = 7
     * 1 : 可达
     * 2 : 可达
     * 3 (1 + 2) : 可达
     * 4 : 可达
     * 5 : 可达
     * 6 : 可达
     * 7 : 可达
     * ans = 8
     * <p>
     * arr = [2,3,4] , ans = 8
     * @see com.zzzj.interview.solutions.lesson9.Question4Solution
     */
    public static int question4(int[] arr) {
        return -1;
    }

}
