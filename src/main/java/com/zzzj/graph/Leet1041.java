package com.zzzj.graph;

/**
 * @author zzzj
 * @create 2023-06-09 16:39
 */
public class Leet1041 {

    public static void main(String[] args) {

        System.out.println(isRobotBounded("GGLLGG"));

        System.out.println(isRobotBounded("GG"));

        System.out.println(isRobotBounded("GL"));

    }

    public static boolean isRobotBounded(String instructions) {

        int direction = 0;

        int x = 0;

        int y = 0;

        int N = instructions.length();

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < N; j++) {

                char c = instructions.charAt(j);

                switch (c) {
                    case 'G':
                        switch (direction) {
                            // 北
                            case 0:
                                x--;
                                break;
                            // 东
                            case 1:
                                y++;
                                break;
                            // 南
                            case 2:
                                x++;
                                break;
                            // 西
                            case 3:
                                y--;
                                break;
                        }
                        break;
                    case 'L':
                        direction -= 1;
                        if (direction < 0) direction = 3;
                        break;
                    case 'R':
                    default:
                        direction += 1;
                        if (direction > 3) direction = 0;
                        break;
                }


            }

        }

        return x == 0 && y == 0;
    }

}
