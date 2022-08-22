package com.zzzj.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-19 18:37
 */
public class Leet331 {

    public static void main(String[] args) {
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    public static boolean isValidSerialization(String preorder) {
        String[] tokens = preorder.split(",");

        List<String> stack = new ArrayList<>();

        for (String token : tokens) {
            stack.add(token);
            while (stack.size() >= 3 && stack.get(stack.size() - 1).equals("#") && stack.get(stack.size() - 2).equals("#") && !stack.get(stack.size() - 3).equals("#")) {
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);
                stack.add("#");
            }
        }

        return stack.isEmpty() || (stack.size() == 1 && stack.get(0).equals("#"));
    }

}
