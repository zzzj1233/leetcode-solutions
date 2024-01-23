package com.zzzj.util;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2024-01-02 17:07
 */
public class SegmentTreeChecker {

    public static int TIMES = 1000;

    public static int CHECK_TIMES = 1000;

    public static int RANGE_L = 1;

    public static int RANGE_R = 100;

    public static int N = 1000;

    private final Supplier<ISegmentTree<Integer>> treeSupplier;

    public SegmentTreeChecker(Supplier<ISegmentTree<Integer>> treeSupplier) {
        this.treeSupplier = treeSupplier;
    }

    public boolean check() {
        for (int i = 0; i < TIMES; i++) {

            TreeChecker checker = new TreeChecker(new int[N]);

            ISegmentTree<Integer> tree = treeSupplier.get();

            List<int[]> save = new ArrayList<>();

            for (int j = 0; j < CHECK_TIMES; j++) {
                int opType = LeetUtils.random.nextInt(2);

                int L = LeetUtils.random.nextInt(N);
                int R = Math.min(N - 1, LeetUtils.random.nextInt(N) + LeetUtils.random.nextInt(N));

                int left = Math.min(L, R);
                int right = Math.max(L, R);
                int V = LeetUtils.random.nextInt(RANGE_R) + RANGE_L;

                if (opType == 0) {
                    save.add(new int[]{opType, left + 1, right + 1, V});
                    checker.update(left, right, V);
                    try {
                        tree.update(left + 1, right + 1, V);
                    } catch (Exception e) {
                        System.out.printf("Left = %d , right = %d , v = %d %n", left, right, V);
                        throw new RuntimeException(e);
                    }
                } else {

                    save.add(new int[]{opType, left + 1, right + 1});

                    int rr = tree.query(left + 1, right + 1);

                    int r = checker.sum(left, right);

                    if (rr != r) {
                        System.out.println("Error");
                        System.out.println(
                                "[" +
                                        save.stream()
                                                .map(Arrays::toString)
                                                .collect(Collectors.joining(","))
                                        + "]"
                        );
                        System.out.println("rr = " + rr);
                        System.out.println("r = " + r);
                        return false;
                    }
                }
            }

        }

        return true;
    }


    private static class TreeChecker {

        private final int[] nums;

        public TreeChecker(int[] nums) {
            this.nums = nums;
        }

        public void update(int L, int R, int V) {
            for (int i = L; i <= R; i++) {
                nums[i] += V;
            }
        }

        public int sum(int L, int R) {
            int s = 0;
            for (int i = L; i <= R; i++) {
                s += nums[i];
            }
            return s;
        }
    }

}
