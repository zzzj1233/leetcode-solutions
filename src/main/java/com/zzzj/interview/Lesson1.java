package com.zzzj.interview;

import com.zzzj.interview.solutions.lesson1.Question7Solution;
import com.zzzj.leet.TreeNode;

/**
 * @author Zzzj
 * @create 2022-06-06 22:59
 */
public class Lesson1 {


    /**
     * @param N 数组长度
     * @return N长的数组, 要求任意的i, j, k三个位置, 如果 i < j < k , 那么 arr[i] + arr[k] != 2 * arr[j]
     * <p>
     * Temporarily ignore
     */
    public static int[] question6(int N) {
        return null;
    }


    /**
     * @return 路径必须从头节点出发, 到叶子节点为止, 返回最大的路径和
     * 延伸题: 129,112,687
     */
    public static int question7(TreeNode treeNode) {
        return Question7Solution.solute(treeNode);
    }

    /**
     * @return 路径可以从任何节点出发, 但必须往下达到任一节点, 返回最大路径和
     */
    public static int question8(TreeNode treeNode) {
        return -1;
    }


    /**
     * @return 路径可以从任何节点出发, 到任意节点, 返回最大的路径和
     * <p>
     * Leet124
     */
    public static int question9(TreeNode treeNode) {
        return -1;
    }


}
