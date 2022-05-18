package com.zzzj.leet;


import java.util.List;

/**
 * @author zzzj
 * @create 2022-05-17 12:26
 */
public class Leet385 {

    public static void main(String[] args) {
        deserialize("[[[]],10,[]]");
    }

    private static class NestedInteger {


        public NestedInteger() {
        }

        public NestedInteger(int value) {
        }

        boolean isInteger() {
            return false;
        }

        List<NestedInteger> getList() {
            return null;
        }

        Integer getInteger() {
            return null;
        }

        void setInteger(int value) {

        }

        void add(NestedInteger ni) {

        }

    }


    public static NestedInteger deserialize(String s) {
        Result dfs = dfs(s.toCharArray(), 0, false);
        return dfs == null ? null : dfs.nestedInteger;
    }

    private static class Result {
        NestedInteger nestedInteger;
        int nextIdx;

        public Result(NestedInteger nestedInteger, int nextIdx) {
            this.nestedInteger = nestedInteger;
            this.nextIdx = nextIdx;
        }

    }

    public static Result dfs(char[] chars, int index, boolean nested) {

        NestedInteger nestedInteger = new NestedInteger();

        boolean negative = false;

        while (index < chars.length && chars[index] != ']') {
            if (chars[index] == '[') {
                Result result = dfs(chars, index + 1, true);
                index = result.nextIdx;
                if (nested) {
                    nestedInteger.add(result.nestedInteger);
                } else {
                    nestedInteger = result.nestedInteger;
                }
                continue;
            } else if (chars[index] == ',') {
                // nothing to_do
            } else if (chars[index] == '-') {
                negative = true;
            } else {
                int num = 0;
                while (index < chars.length && Character.isDigit(chars[index])) {
                    num = num * 10 + Character.digit(chars[index], 10);
                    index++;
                }
                if (negative) {
                    num = -num;
                    negative = false;
                }
                if (nested) {
                    nestedInteger.add(new NestedInteger(num));
                } else {
                    nestedInteger.setInteger(num);
                }
                continue;
            }
            index++;
        }

        return new Result(nestedInteger, index + 1);
    }


}
