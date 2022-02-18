package com.zzzj.stack;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-12-06 17:01
 */
public class Leet503 {

    public static void main(String[] args) {

        final int[] arr = ArrayUtil.generateArray(10, 0, 100);

        System.out.println(Arrays.toString(arr));

        final int[] answer2 = nextGreaterElements(arr);

        System.out.println(Arrays.toString(answer2));
    }


    private static int[] violent(int[] nums) {
        int n = nums.length;

        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n + i; j++) {

                if (nums[j % n] > nums[i]) {
                    answer[i] = nums[j % n];
                }

            }

            if (answer[i] == 0) {
                answer[i] = -1;
            }

        }

        return answer;
    }

    public static int[] nextGreaterElements(int[] nums) {
        int[] answer = new int[nums.length];

        LinkedList<Integer> singleStack = new LinkedList<>();

        singleStack.add(0);

        for (int i = 1; i < nums.length; i++) {
            while (!singleStack.isEmpty() && nums[i] > nums[singleStack.peekLast()]) {
                Integer lastIdx = singleStack.removeLast();
                answer[lastIdx] = nums[i];
            }
            singleStack.add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            while (!singleStack.isEmpty() && nums[i] > nums[singleStack.peekLast()]) {
                Integer lastIdx = singleStack.removeLast();
                if (lastIdx < answer.length) {
                    answer[lastIdx] = nums[i];
                }
            }
        }

        while (!singleStack.isEmpty()) {
            Integer lastIdx = singleStack.removeLast();
            answer[lastIdx] = -1;
        }


        return answer;
    }

}
