package com.zzzj.interview.solutions.lesson9;

/**
 * @author Zzzj
 * @create 2022-07-09 22:30
 */
public class Question2Solution {


    public static void main(String[] args) {
        System.out.println(question2(new int[]{3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2}));
    }


    // 是否可以将arr分为4个部分,每个部分的和都相同

    // attention:
    // [1,2,3]
    // 如果在 i == 1 这刀进行划分, i == 1这个数据将会被丢掉
    public static boolean question2(int[] arr) {
        int K = 4;


        int N = arr.length;

        // 0 这个地方无法划分

        // N - 5 = (left) (N - 5) [N - 4] (N - 3) [N - 2] (N - 1) : [] = 划分点

        final int end = N - 5;

        int sum1 = 0;

        OUTER:
        for (int i = 1; i < N - 5; i++) {
            // 是否有能划第二刀的地方

            sum1 += arr[i - 1];

            int sum2 = 0;

            // N - 3 = (left1) (left2) (N - 3) [N - 2] (N - 1)
            for (int j = i + 1; j < N - 3; j++) {
                sum2 += arr[j];

                // 此处可以划第二刀
                if (sum2 == sum1) {

                    int sum3 = 0;
                    // N - 1 = (left1) (left2) (left3) [N - 2] (N - 1)
                    for (int k = j + 2; k < N - 1; k++) {
                        sum3 += arr[k];
                        if (sum3 == sum2) {

                            // 看看剩余的部分是否和sum3相同
                            int sum4 = 0;

                            for (int l = k + 2; l < N; l++) {
                                sum4 += arr[l];
                                if (sum4 == sum3) {
                                    return true;
                                } else if (sum4 > sum3) {
                                    break;
                                }
                            }

                        } else if (sum3 > sum2) {
                            break;
                        }
                    }

                } else if (sum2 > sum1) {
                    break;
                }
            }

        }

        return false;
    }

}
