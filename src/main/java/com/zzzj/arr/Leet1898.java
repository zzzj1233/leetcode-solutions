package com.zzzj.arr;

public class Leet1898 {

    public static void main(String[] args) {
        System.out.println(maximumRemovals("qlevcvgzfpryiqlwy", "qlecfqlw", new int[]{12, 5}));
    }

    public static int maximumRemovals(String s, String p, int[] removable) {
        int N = removable.length;

        int low = 0;

        int high = N - 1;

        int result = -1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            if (check(new StringBuilder(s), p, removable, mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static boolean check(StringBuilder builder, String p, int[] removable, int k) {
        for (int i = 0; i <= k; i++) {
            builder.setCharAt(removable[i], '0');
        }

        int index = 0;

        for (int i = 0; i < builder.length() && index < p.length(); i++) {
            if (builder.charAt(i) == p.charAt(index)) {
                index++;
            }
        }

        return index == p.length();
    }

}
