package com.zzzj.leet;

public class Leet393 {

    public static void main(String[] args) {
        System.out.println(validUtf8(new int[]{145}));
    }

    public static boolean validUtf8(int[] data) {

        // 一个字节以0开头

        int N = data.length;

        int count = 0;

        for (int i = 0; i < N; i++) {
            int datum = data[i];
            if ((datum & 0b10000000) == 0) {
                // 一字节
                if (count > 0) {
                    return false;
                }
            } else if (count == 0) {
                if ((datum & 0b11000000) != 0b11000000) {
                    return false;
                }
                count = count(datum);
                if (count > 4) {
                    return false;
                }
                if (count == 0) {
                    return false;
                }
                count--;
            } else {
                if ((datum & 0b10000000) != 0b10000000) {
                    return false;
                }
                count--;
            }
        }

        return count == 0;
    }

    public static int count(int datum) {
        int c = 0;
        for (int i = 7; i >= 0; i--) {
            if (((datum >> i) & 1) == 0) {
                break;
            }
            c++;
        }
        return c;
    }

}
