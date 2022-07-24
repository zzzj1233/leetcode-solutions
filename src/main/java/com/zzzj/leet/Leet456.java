package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Zzzj
 * @create 2022-07-23 15:03
 */
public class Leet456 {


    public static void main(String[] args) {
        System.out.println(find132pattern(new int[]{12, 8, 14, 2, 9}));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(5, -5, 20);
            if (find132pattern(arr) != right(arr)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(find132pattern(arr));
                System.out.println("Error");
                return;
            }
        }
        System.out.println("Ok");
    }

    public static boolean find132pattern(int[] nums) {
        int N = nums.length;

        if (N < 3) {
            return false;
        }

        // 1. 当前数后面有两个数大于自己,并且第二个数小于第一个数

        // 2. 当前数左边有一个数小于自己,右边也有一个数小于自己,且左边小于自己的数 < 右边小于自己的数

        // 3. 当前数左边有一个数大于自己,且大于自己的数的左边有一个数小于自己

        int[] min = new int[N];

        min[0] = nums[0];

        for (int i = 1; i < N; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = N - 1; i >= 0; i--) {
            // 最大栈
            int num = nums[i];

            while (!stack.isEmpty() && stack.peekLast() <= num) {
                if (stack.peekLast() == num) {
                    stack.removeLast();
                } else if (min[i] < stack.removeLast()) {
                    return true;
                }
            }

            stack.addLast(num);
        }

        return false;
    }


    public static boolean right(int[] nums) {
        int n = nums.length;
        int last = Integer.MIN_VALUE; // 132中的2
        Stack<Integer> sta = new Stack<>();// 用来存储132中的3
        if (nums.length < 3)
            return false;
        for (int i = n - 1; i >= 0; i--) {

            if (nums[i] < last) // 若出现132中的1则返回正确值
                return true;

            // 若当前值大于或等于2则更新2（2为栈中小于当前值的最大元素）
            while (!sta.isEmpty() && sta.peek() < nums[i]) {
                last = sta.pop();
            }

            // 将当前值压入栈中
            sta.push(nums[i]);
        }
        return false;
    }

}
