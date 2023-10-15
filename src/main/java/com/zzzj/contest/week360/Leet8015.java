package com.zzzj.contest.week360;

public class Leet8015 {

    public static void main(String[] args) {

        System.out.println(furthestDistanceFromOrigin("L_RL__R"));

        System.out.println(furthestDistanceFromOrigin("_R__LL_"));

        System.out.println(furthestDistanceFromOrigin("_______"));

    }

    public static int furthestDistanceFromOrigin(String moves) {

        int L = 0;
        int R = 0;

        int length = moves.length();

        int location = 0;

        for (int i = 0; i < length; i++) {
            if (moves.charAt(i) == 'L') {
                location--;
                L++;
            } else if (moves.charAt(i) == 'R') {
                location++;
                R++;
            }
        }

        int X = length - L - R;

        return location <= 0 ? Math.abs(location - X) : location + X;
    }

}
