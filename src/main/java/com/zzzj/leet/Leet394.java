package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-17 16:02
 */
public class Leet394 {

    public static void main(String[] args) {
        System.out.println(decodeString("2[abc]3[cd]ef"));
        System.out.println(decodeString("3[a2[c]]"));
    }

    public static String decodeString(String s) {
        return dfs(s.toCharArray(), 0).builder.toString();
    }

    static class Result {
        StringBuilder builder;
        int index;

        public Result(StringBuilder builder, int index) {
            this.builder = builder;
            this.index = index;
        }

    }

    public static Result dfs(char[] chars, int index) {
        // 数字后面一定接[
        StringBuilder builder = new StringBuilder();

        while (index < chars.length && chars[index] != ']') {
            char c = chars[index];
            if (Character.isDigit(c)) {
                int repeat = 0;
                while (Character.isDigit(chars[index])) {
                    c = chars[index];
                    repeat = repeat * 10 + Character.digit(c, 10);
                    index++;
                }
                // skip [
                Result childRes = dfs(chars, index + 1);

                for (int i = 0; i < repeat; i++) {
                    builder.append(childRes.builder);
                }
                index = childRes.index;
            } else {
                builder.append(c);
                index++;
            }
        }
        return new Result(builder, index + 1);
    }

}
