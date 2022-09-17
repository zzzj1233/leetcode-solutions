package com.zzzj.greedy;

public class Leet1529 {

    public static void main(String[] args) {
        // 10111
        // 1: 11111
        // 2: 10000
        // 3: 10111

        System.out.println(minFlips("10111"));
    }

    public static int minFlips(String target) {

        int ans = 0;

        int index = 0;

        int N = target.length();

        while (index < N && target.charAt(index) == '0') {
            index++;
        }

        boolean flip = false;

        for (int i = index; i < N; i++) {
            // 这个位置需要反转
            if (target.charAt(i) == '1' && !flip) {
                ans++;
                flip = true;
            } else if (flip && target.charAt(i) == '0') { // == 0 , 变成了1
                ans++;
                flip = false;
            }
        }

        return ans;
    }

}
