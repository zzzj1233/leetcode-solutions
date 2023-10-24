package com.zzzj.stack;

/**
 * @author zzzj
 * @create 2023-10-13 12:23
 */
public class Leet1106 {

    public static void main(String[] args) {
        // &(|(f))
        // |(f,f,f,t)
        // !(&(f,t))

        // "|(f,&(t,t))"

        System.out.println(parseBoolExpr("|(f,&(t,t))"));

//        System.out.println(parseBoolExpr("&(!(t),&(f),|(f)),&(!(&(f)),&(t),|(f,f,t))"));

        System.exit(0);

        System.out.println(parseBoolExpr("!(&(&(f),&(!(t),&(f),|(f)),&(!(&(f)),&(t),|(f,f,t))))"));

        System.out.println(parseBoolExpr("|(&(t,f,t),!(t))"));

        System.out.println(parseBoolExpr("&(|(f))"));

        System.out.println(parseBoolExpr("|(f,f,f,t)"));

        System.out.println(parseBoolExpr("!(&(f,t))"));

    }

    public static boolean parseBoolExpr(String expression) {
        return parse(expression, 0, 0)[0] == 1;
    }

    static final int TYPE_NONE = 0;

    static final int TYPE_NOT = 1;

    static final int TYPE_AND = 2;

    static final int TYPE_OR = 3;

    // "&(t,t)|(f,f)"
    // "&(t,f)|(f,t)"
    public static int[] parse(String expression, int index, int type) {

        int N = expression.length();

        Boolean ans = null;

        int end = N;

        for (int i = index; i < N; ) {

            char c = expression.charAt(i);

            int[] result = null;

            if (c == '&') {
                result = parse(expression, i + 1, TYPE_AND);
                if (ans == null) ans = result[0] == 1;
                else ans &= result[0] == 1;
            } else if (c == '|') {
                result = parse(expression, i + 1, TYPE_OR);
                if (ans == null) ans = result[0] == 1;
                else ans |= result[0] == 1;
            } else if (c == '!') {
                result = parse(expression, i + 1, 0);
                ans = result[0] == 0;
            } else if (c == '(') {
                return parse(expression, i + 1, type);
            } else if (c == ')') {
                return new int[]{ans ? 1 : 0, i + 1};
            } else if (c == 't') {
                if (ans == null || type == TYPE_OR || type == TYPE_NONE)
                    ans = true;
                i++;
            } else if (c == 'f') {
                if (ans == null || type == TYPE_AND || type == TYPE_NONE)
                    ans = false;
                i++;
            } else if (c == ',') {
                i++;
            } else {
                throw new IllegalArgumentException("Illegal char : " + c + " , index = " + i);
            }

            if (result != null) {
                i = result[1];
            }

        }

        return new int[]{ans ? 1 : 0, -1};
    }

}
