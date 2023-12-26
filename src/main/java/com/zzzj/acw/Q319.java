package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-13 12:29
 */
public class Q319 {

    public static void main(String[] args) {

        // ABABABXYZXYZXYZXYZNASANASAABABABXYZXYZXYZXYZNASANASA
        // 2(ABABABXYZXYZXYZXYZNASANASA)
        // 2(3(AB)4(XYZ)2(NASA))
        Scanner scanner = new Scanner("ABABABXYZXYZXYZXYZNASANASAABABABXYZXYZXYZXYZNASANASA");

        String str = scanner.nextLine();

        int N = str.length();

        int[][] f = new int[N][N];

        int[][] p = new int[N][N];

        for (int i = 0; i < N; i++) {
            f[i][i] = 1;
            p[i][i] = 0;
        }

        for (int len = 1; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                f[left][right] = Integer.MAX_VALUE;

                for (int k = left; k < right; k++) {

                    int s = f[left][k] + f[k + 1][right];

                    if (f[left][right] > s) {
                        f[left][right] = s;
                        p[left][right] = k;
                    }

                }

                for (int cnt = 1; cnt < len; cnt++) {

                    if ((len + 1) % cnt != 0 || !check(str, left, right, cnt))
                        continue;

                    int c = (len + 1) / cnt;

                    int folded = cnt + 2 + String.valueOf(c).length();

                    if (f[left][right] > folded) {
                        f[left][right] = folded;
                        p[left][right] = -c;
                    }

                }

            }

        }

        System.out.println(f[0][N - 1]);

        printPath(str, p, 0, N - 1);

    }

    private static void printPath(
            String str,
            int[][] p,
            int left,
            int right
    ) {

        if (left == right) {
            System.out.print(str.charAt(left));
            return;
        }

        int v = p[left][right];

        if (v >= 0) {
            printPath(str, p, left, v);
            printPath(str, p, v + 1, right);
        } else {
            int cnt = -v;
            int c = (right - left + 1) / cnt;
            System.out.printf("%s(%s)", cnt, str.substring(left, left + c));
        }
    }

    private static boolean check(
            String source,
            int left,
            int right,
            int cnt
    ) {

        int index = left + cnt;

        while (index <= right) {

            for (int i = 0; i < cnt; i++) {
                if (source.charAt(left + i) != source.charAt(index + i))
                    return false;
            }

            index += cnt;
        }

        return true;
    }

}
