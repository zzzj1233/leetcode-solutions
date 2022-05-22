package com.zzzj.leet;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-05-21 10:35
 */
public class Leet498 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findDiagonalOrder(LeetUtils.convertInts("[[1,2,3],[4,5,6],[7,8,9]]"))));
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        int tc = 0;
        int tr = 0;
        int bc = 0;
        int br = 0;

        int M = mat.length;
        int N = mat[0].length;

        // br到M-1后,bc开始走,直到N-1

        // tc到N-1后,tr开始走,直到M-1

        int[] ans = new int[M * N];
        int index = 0;

        boolean ttb = true;

        while (true) {

            // 斜着走
            int bc2 = bc;
            int tc2 = tc;
            int br2 = br;
            int tr2 = tr;

            if (tr2 == tc2) {
                ans[index++] = mat[tr2][tc2];
            } else {
                if (ttb) {
                    ans[index++] = mat[tr2][tc2];
                    ans[index++] = mat[br2][bc2];
                } else {
                    ans[index++] = mat[br2][bc2];
                    ans[index++] = mat[tr2][tc2];
                }


                tr2++;
                tc2--;
                br2--;
                bc2++;
                while (tr2 >= br2 && tc2 >= bc2) {
                    if (tr2 == tc2) {
                        ans[index++] = mat[tr2][tc2];
                    } else {
                        if (ttb) {
                            ans[index++] = mat[tr2][tc2];
                            ans[index++] = mat[br2][bc2];
                        } else {
                            ans[index++] = mat[br2][bc2];
                            ans[index++] = mat[tr2][tc2];
                        }
                    }

                    tr2++;
                    tc2--;
                    br2--;
                    bc2++;
                }
            }


            if (br == M - 1 && bc == N - 1 && tc == N - 1 && tr == M - 1) {
                break;
            }

            ttb = !ttb;

            if (br < M - 1) {
                br++;
            } else if (bc < N - 1) {
                bc++;
            }

            if (tc < N - 1) {
                tc++;
            } else if (tr < M - 1) {
                tr++;
            }

        }


        return ans;
    }

}
