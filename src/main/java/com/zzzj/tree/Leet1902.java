package com.zzzj.tree;

import com.zzzj.util.Unresolved;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-28 15:09
 */
@Unresolved
public class Leet1902 {

    public static int maxDepthBST(int[] order) {

        int root = order[0];

        Arrays.sort(order);


        int index = Arrays.binarySearch(order, root);

        int left = index + 1;
        int right = order.length - index;

        return Math.max(left, right);
    }

}
