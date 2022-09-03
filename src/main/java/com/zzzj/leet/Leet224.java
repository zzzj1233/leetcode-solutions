package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-09-01 15:04
 */
public class Leet224 {

    public static void main(String[] args) {
        System.out.println(calculate("1 + 1"));
        System.out.println(calculate("2-1 + 2"));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    public static int calculate(String s) {
        return dfs(s, 0)[0];
    }


    /**
     * @param s
     * @param index
     * @return int[0] = 计算后的值 , int[1] = 下一个索引
     */
    public static int[] dfs(String s, int index) {
        int N = s.length();

        Integer lastNum = null;

        char lastOp = '?';

        while (index < N && s.charAt(index) != ')') {
            char c = s.charAt(index);
            // 1. 忽略空格
            if (c == ' ') {
                index++;
                continue;
            }
            // 2. 遇到左括号进递归
            if (c == '(') {
                int[] ints = dfs(s, index + 1);
                // 2.1 对递归返回的值做计算
                lastNum = cal(lastNum, ints[0], lastOp);
                index = ints[1];
            } else if (c >= '0' && c <= '9') {
                // 3. 数字
                int num = 0;
                while (index < N && Character.isDigit(s.charAt(index))) {
                    num = num * 10 + Character.digit(s.charAt(index), 10);
                    index++;
                }
                // 3.1 遇到数字就计算
                lastNum = cal(lastNum, num, lastOp);
            } else {
                // 4. 运算符
                lastOp = c;
                index++;
            }
        }

        return new int[]{lastNum == null ? 0 : lastNum, index + 1};
    }

    /**
     * @param lastNum 最后一个记录的值
     * @param num     当前值
     * @param lastOp  最后遇到的一个操作符
     * @return
     */
    public static int cal(Integer lastNum, int num, char lastOp) {
        if (lastNum == null) {
            return lastOp == '-' ? -num : num;
        } else {
            if (lastOp == '+') {
                return lastNum + num;
            } else {
                return lastNum - num;
            }
        }
    }

}
