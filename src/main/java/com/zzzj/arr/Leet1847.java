package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import sun.security.x509.OCSPNoCheckExtension;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2023-10-16 12:02
 */
public class Leet1847 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(closestRoom(
                LeetUtils.convertInts("[[2,2],[1,2],[3,2]]"),
                LeetUtils.convertInts("[[3,1],[3,3],[5,2]]")
        )));

        System.out.println(Arrays.toString(closestRoom(
                LeetUtils.convertInts("[[1,4],[2,3],[3,5],[4,1],[5,2]]"),
                LeetUtils.convertInts("[[2,3],[2,4],[2,5]]")
        )));

    }

    public static final int INDEX_ID = 0;

    public static final int INDEX_SIZE = 1;

    public static int[] closestRoom(int[][] rooms, int[][] queries) {

        int N = rooms.length;

        int M = queries.length;

        int[][] combined = new int[M][3];

        for (int i = 0; i < M; i++) {
            combined[i][INDEX_ID] = queries[i][INDEX_ID];
            combined[i][INDEX_SIZE] = queries[i][INDEX_SIZE];
            combined[i][2] = i;
        }

        Arrays.sort(rooms, Comparator.comparingInt(o -> o[INDEX_SIZE]));

        Arrays.sort(combined, (o1, o2) -> o2[INDEX_SIZE] - o1[INDEX_SIZE]);

        TreeSet<Integer> available = new TreeSet<>();

        int[] ans = new int[M];

        Arrays.fill(ans, -1);

        int right = N - 1;

        for (int i = 0; i < M && right >= 0; i++) {

            int L = 0;

            int R = right;

            int preRight = right;

            int size = combined[i][INDEX_SIZE];

            int id = combined[i][INDEX_ID];

            int ansIndex = combined[i][2];

            int index = -1;

            boolean find = false;

            while (L <= R) {

                int mid = L + ((R - L) >> 1);

                if (rooms[mid][INDEX_SIZE] >= size) {
                    find = true;
                    right = mid;
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }

            }

            if (!find)
                continue;

            for (int j = right; j <= preRight; j++)
                available.add(rooms[j][INDEX_ID]);

            Integer ceiling = available.ceiling(id);
            if (ceiling != null)
                ans[ansIndex] = ceiling;

            Integer floor = available.floor(id);

            if (floor != null && (ans[ansIndex] == -1 || Math.abs(floor - id) <= Math.abs(ans[ansIndex] - id)))
                ans[ansIndex] = floor;
        }


        return ans;
    }


}
