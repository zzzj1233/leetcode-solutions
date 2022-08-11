package com.zzzj.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-08 19:33
 */
public class Leet1104 {


    public static void main(String[] args) {
        System.out.println(pathInZigZagTree(14));
    }

    public static List<Integer> pathInZigZagTree(int label) {
        if (label == 1) {
            return Collections.singletonList(1);
        }

        int level = 0;

        int count = 1;

        while (count < label) {
            level++;
            count += StrictMath.pow(2, level);
        }

        // 当前在level层


        return null;
    }


}
