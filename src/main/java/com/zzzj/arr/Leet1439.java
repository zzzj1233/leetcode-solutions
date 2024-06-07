package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2024-06-04 16:32
 */
public class Leet1439 {

    public static void main(String[] args) {

        System.out.println(kthSmallest(LeetUtils.convertInts("[[1,3,11],[2,4,6]]"), 5));

//        System.out.println(kthSmallest_pq(LeetUtils.convertInts("[[1,3,11],[2,4,6]]"), 9));

    }

    public static int kthSmallest(int[][] mat, int k) {

        int M = mat.length;

        int N = mat[0].length;

        int[] help = new int[M + 1];

        int left = 0;

        int right = 0;

        int s = 0;

        for (int i = 0; i < M; i++) {
            help[i] = s;
            right += mat[i][N - 1];
            s += mat[i][0];
        }

        while (left + 1 < right) {

            int mid = left + ((right - left) >> 1);

            cnt = 0;

            if (
                    check(
                            mat,
                            k,
                            mid,
                            M - 1,
                            help,
                            0
                    )
            )
                right = mid;
            else
                left = mid;

        }

        return right;
    }

    static int cnt;

    public static boolean check(
            int[][] mat,
            int k,
            int m,
            int index,
            int[] help,
            int s
    ) {

        if (index < 0) {
            return ++cnt >= k;
        }

        for (int i = 0; i < mat[index].length; i++) {

            if (s + mat[index][i] + help[index] > m)
                return false;

            if (check(mat, k, m, index - 1, help, s + mat[index][i]))
                return true;

        }

        return false;
    }

    public static int kthSmallest_arr(int[][] mat, int k) {

        int M = mat.length;

        int N = mat[0].length;

        int[] arr = Arrays.copyOf(mat[0], N);

        for (int i = 1; i < M; i++) {

            int[] b = new int[arr.length * N];

            int index = 0;

            for (int x = 0; x < arr.length; x++)
                for (int y = 0; y < N; y++)
                    b[index++] = arr[x] + mat[i][y];

            if (b.length > k) {
                Arrays.sort(b);
                arr = Arrays.copyOfRange(b, 0, k);
            } else
                arr = b;
        }

        return arr[k - 1];
    }

    public static int kthSmallest_pq(int[][] mat, int k) {

        int M = mat.length;

        int N = mat[0].length;

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);

        for (int i = 0; i < Math.min(N, k); i++)
            queue.add(mat[0][i]);

        for (int i = 1; i < M; i++) {

            PriorityQueue<Integer> b = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);

            for (int j = 0; j < N; j++) {

                for (Integer q : queue) {
                    int s = q + mat[i][j];
                    if (b.size() < k)
                        b.add(s);
                    else if (s < b.peek()) {
                        b.remove();
                        b.add(s);
                    }
                }

            }

            queue = b;

        }

        return queue.peek();
    }

}
