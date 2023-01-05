package com.zzzj.leet;

public class Leet1538 {

    private interface ArrayReader {

        int query(int a, int b, int c, int d);

        int length();

    }

    static final int RESULT_EQUAL = 4;

    static final int RESULT_THREE_ONE = 2;

    static final int RESULT_HALF = 0;

    public static int guessMajority(ArrayReader reader) {
        // 还原整个数组

        int N = reader.length();

        int[] arr = new int[N];

        // 前两个数
        reader.query(0,1,2,3);

        // 后两个数

        for (int i = 0; i < N; i++) {

        }

        return -1;
    }


}
