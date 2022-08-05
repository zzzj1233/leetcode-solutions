package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-01 15:27
 */
public class Leet842 {

    public static void main(String[] args) {
        Solution solution = new Solution();

//        System.out.println(splitIntoFibonacci("000"));
//
//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            String num = LeetUtils.randomNumberString(LeetUtils.random.nextInt(200) + 1);
            List<Integer> rightAns = solution.splitIntoFibonacci(num);
            List<Integer> myAns = splitIntoFibonacci(num);
            boolean error = false;
            if (rightAns.isEmpty() && !myAns.isEmpty()) {
                error = true;
            } else if (myAns.isEmpty() && !rightAns.isEmpty()) {
                error = true;
            }

            if (error) {
                System.out.println("Error");
                System.out.println(num);
                System.out.println(myAns);
                System.out.println(rightAns);
                return;
            }
        }
        System.out.println("Ok");
    }

    public static List<Integer> splitIntoFibonacci(String num) {
        if (num.length() < 3) {
            return Collections.emptyList();
        }

        LinkedList<Integer> path = new LinkedList<>();

        // 穷举前两个参数
        int num1 = 0;

        // i的最大长度
        int N = num.length();

        // num1 4位数
        // num2 至少1位数
        // num3 至少4位数

        for (int i = 0; i < N - (i + 2); i++) {

            num1 = num1 * 10 + Character.digit(num.charAt(i), 10);

            int num2 = 0;

            for (int j = i + 1; j < N - Math.max(i + 1, j - i); j++) {

                num2 = num2 * 10 + Character.digit(num.charAt(j), 10);

                path.add(num1);
                path.add(num2);

                if (valid(num, j + 1, num1, num2, path)) {
                    return path;
                }

                path.removeLast();
                path.removeLast();

            }

        }

        return Collections.emptyList();
    }


    public static int equals(String str1, String str2, int str1Start) {
        int N = str2.length();

        if (str1.length() - str1Start < N) {
            return -1;
        }

        for (int i = 0; i < N; i++) {
            if (str2.charAt(i) != str1.charAt(i + str1Start)) {
                return -1;
            }
        }

        return str1Start + N;
    }

    public static boolean valid(String num, int index, int num1, int num2, LinkedList<Integer> path) {
        if (index >= num.length()) {
            return true;
        }

        int sum = num1 + num2;

        String nextNum = String.valueOf(sum);

        int nextIndex = equals(num, nextNum, index);

        if (nextIndex != -1) {
            path.add(sum);
            if (valid(num, nextIndex, num2, sum, path)) {
                return true;
            }
            path.removeLast();
        }

        return false;
    }

    private static class Solution {
        public List<Integer> splitIntoFibonacci(String num) {
            List<Integer> list = new ArrayList<Integer>();
            backtrack(list, num, num.length(), 0, 0, 0);
            return list;
        }

        public boolean backtrack(List<Integer> list, String num, int length, int index, int sum, int prev) {
            if (index == length) {
                return list.size() >= 3;
            }
            long currLong = 0;
            for (int i = index; i < length; i++) {
                if (i > index && num.charAt(index) == '0') {
                    break;
                }
                currLong = currLong * 10 + num.charAt(i) - '0';
                if (currLong > Integer.MAX_VALUE) {
                    break;
                }
                int curr = (int) currLong;
                if (list.size() >= 2) {
                    if (curr < sum) {
                        continue;
                    } else if (curr > sum) {
                        break;
                    }
                }
                list.add(curr);
                if (backtrack(list, num, length, i + 1, prev + curr, curr)) {
                    return true;
                } else {
                    list.remove(list.size() - 1);
                }
            }
            return false;
        }
    }


}
