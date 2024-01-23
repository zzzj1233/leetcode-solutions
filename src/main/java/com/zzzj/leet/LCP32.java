package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;
import com.zzzj.util.Unresolved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author zzzj
 * @create 2024-01-05 15:10
 */
@Unresolved
public class LCP32 {

    public static void main(String[] args) {

        System.out.println(processTasks(LeetUtils.convertInts("[[1, 2, 1], [2, 6, 2], [3, 6, 3], [3, 8, 3]]")));

        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int N = 4;

            int M = 3;

            int[][] tasks = new int[N][3];

            for (int j = 0; j < N; j++) {

                int period = LeetUtils.random.nextInt(M) + 1;
                tasks[j][0] = LeetUtils.random.nextInt(M) + 1;
                tasks[j][1] = tasks[j][0] + LeetUtils.random.nextInt(M) + period;
                tasks[j][2] = period;
            }

            CopyableIterator<int[][]> it = new CopyableIterator<>(tasks, ArrayUtil::copy);

            int r = processTasks(it.next());

            int rr = right(it.next());

            if (r != rr) {
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                int[][] arr = it.next();
                Arrays.sort(arr, comparator);
                System.out.println("it.next() = " + Arrays.deepToString(arr));
                return;
            }

        }

        System.out.println("Ok");
    }

    private static Comparator<int[]> comparator = Comparator.comparingInt(value -> value[1]);

    public static int processTasks(int[][] tasks) {

        Arrays.sort(tasks, comparator);

        int N = tasks.length;

        tasks[0][0] = tasks[0][1] - tasks[0][2] + 1;

        int ans = tasks[0][2];

        for (int i = 1; i < N; i++) {

            int preEnd = tasks[i - 1][1];

            int preCost = tasks[i - 1][2];

            int start = tasks[i][0];

            int end = tasks[i][1];

            int period = tasks[i][2];

            int reUsed = preEnd >= start ? Math.min(
                    preEnd - start + 1,
                    preCost
            ) : 0;

            if (reUsed >= tasks[i][2])
                tasks[i] = tasks[i - 1];
            else {
                int cost = tasks[i][2] - reUsed;
                tasks[i][0] = tasks[i][1] - cost + 1;
                tasks[i][2] = cost;
                ans += cost;
            }

        }

        return ans;
    }

    public static int right(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        List<int[]> stack = new ArrayList<>();
        for (int[] task : tasks) {
            int start = task[0];
            int end = task[1] + 1;
            int period = task[2];
            int i = binarySearch(stack, (x -> x[1] > start));
            int already = 0;
            if (i != stack.size()) {
                int[] top = stack.get(stack.size() - 1);
                already = Math.max(0, top[2] - stack.get(i)[2] + stack.get(i)[1] - Math.max(start, stack.get(i)[0]));
            }
            int extra = Math.max(0, period - already);
            int cur = end;
            while (!stack.isEmpty() && cur - stack.get(stack.size() - 1)[1] <= extra) {
                int[] top = stack.remove(stack.size() - 1);
                extra -= cur - top[1];
                cur = top[0];
            }
            stack.add(new int[]{cur - extra, end, end - cur + extra + (stack.isEmpty() ? 0 : stack.get(stack.size() - 1)[2])});
        }
        return stack.get(stack.size() - 1)[2];
    }

    private static int binarySearch(List<int[]> list, Predicate<int[]> predicate) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (predicate.test(list.get(mid))) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
