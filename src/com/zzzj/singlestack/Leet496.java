package com.zzzj.singlestack;

import java.lang.reflect.AnnotatedParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2021-12-12 09:58
 */
public class Leet496 {

    public static void main(String[] args) {
        nextGreaterElement(new int[]{}, new int[]{1, 3, 4, 2});
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] answer = new int[nums1.length];

        ArrayList<Integer> stack = new ArrayList<>();

        stack.add(0);

        Map<Integer, Integer> recorder = new HashMap<>();

        for (int i = 1; i < nums2.length; i++) {
            int num = nums2[i];
            // last的最大一个元素是当前节点
            while (!stack.isEmpty() && num > nums2[stack.get(stack.size() - 1)]) {
                Integer remove = stack.remove(stack.size() - 1);
                recorder.put(nums2[remove], num);
            }
            stack.add(i);
        }

        for (int i = 0; i < nums1.length; i++) {
            answer[i] = recorder.getOrDefault(nums1[i], -1);
        }

        return answer;
    }

}
