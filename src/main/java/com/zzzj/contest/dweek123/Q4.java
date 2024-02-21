package com.zzzj.contest.dweek123;

import cn.hutool.core.lang.Assert;
import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-02-04 17:18
 */
public class Q4 {

    public static void main(String[] args) {

        System.out.println(numberOfPairs(LeetUtils.convertInts("[[4, 2], [5, 2], [5, 5]]")));

        System.out.println(right(LeetUtils.convertInts("[[4, 2], [5, 2], [5, 5]]")));

//        System.exit(0);

        Assert.isTrue(check("[[1, 4], [3, 2], [3, 4]]"));
        Assert.isTrue(check("[[4, 3], [5, 3], [5, 1]]"));
        Assert.isTrue(check("[[2, 5], [3, 4], [5, 4]]"));
        Assert.isTrue(check("[[6,2],[4,4],[2,6]]"));
        Assert.isTrue(check("[[3,1],[1,3],[1,1]]"));

        int N = 3;

        int M = 5;

        for (int i = 0; i < 1000; i++) {

            int[][] points = new int[N][2];

            Map<Integer, Set<Integer>> used = new HashMap<>();

            for (int j = 0; j < N; j++) {
                points[j][0] = LeetUtils.random.nextInt(M) + 1;
                points[j][1] = LeetUtils.random.nextInt(M) + 1;
                while (used.getOrDefault(points[j][0], Collections.emptySet()).contains(points[j][1])) {
                    points[j][0] = LeetUtils.random.nextInt(M) + 1;
                    points[j][1] = LeetUtils.random.nextInt(M) + 1;
                }
                used.computeIfAbsent(points[j][0], integer -> new HashSet<>()).add(points[j][1]);
            }

            CopyableIterator<int[][]> it = new CopyableIterator<>(points, ArrayUtil::copy);

            int r = numberOfPairs(it.next());

            int rr = right(it.next());

            if (r != rr) {
                Arrays.sort(points, COMPARATOR);
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("it.next() = " + Arrays.deepToString(points));
                return;
            }

        }

        System.out.println("Ok");
    }

    private static final int INDEX_COL = 0;

    private static final int INDEX_ROW = 1;

    private static final Comparator<int[]> COMPARATOR = (o1, o2) -> {
        int diff = o1[INDEX_COL] - o2[INDEX_COL];
        return diff == 0 ? o2[INDEX_ROW] - o1[INDEX_ROW] : diff;
    };

    public static int numberOfPairs(int[][] points) {

        int N = points.length;

        int ans = 0;

        Arrays.sort(points, COMPARATOR);

        for (int left = 0; left < N; left++) {

            int[] lp = points[left];

            int leftCol = lp[INDEX_COL];

            int leftRow = lp[INDEX_ROW];

            int maxRow = Integer.MIN_VALUE;

            for (int right = left + 1; right < N; right++) {

                int[] rp = points[right];

                int rightCol = rp[INDEX_COL];

                int rightRow = rp[INDEX_ROW];

                if (rightRow <= leftRow) {
                    if (maxRow < rightRow) {
                        ans++;
                        maxRow = rightRow;
                    }
                }

            }

        }

        return ans;
    }


    public static int right(int[][] points) {

        int N = points.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            int[] p1 = points[i];

            OUTER:
            for (int j = i + 1; j < N; j++) {

                int[] p2 = points[j];

                if (!check(p1, p2) && !check(p2, p1))
                    continue;

                int colMin = Math.min(p1[0], p2[0]);

                int colMax = Math.max(p1[0], p2[0]);

                int rowMin = Math.min(p1[1], p2[1]);

                int rowMax = Math.max(p1[1], p2[1]);

                // 检查中间是否有元素
                for (int k = 0; k < N; k++) {

                    if (k == i || k == j)
                        continue;

                    int[] p3 = points[k];

                    int col = p3[0];

                    int row = p3[1];

                    if (col >= colMin && col <= colMax && row >= rowMin && row <= rowMax) {
                        continue OUTER;
                    }

                }

                ans++;
            }

        }

        return ans;
    }

    public static boolean check(int[] p1, int[] p2) {
        return p1[0] <= p2[0] && p1[1] >= p2[1];
    }

    public static boolean check(String source) {
        int[][] points = LeetUtils.convertInts(source);
        return numberOfPairs(points) == right(points);
    }
}
