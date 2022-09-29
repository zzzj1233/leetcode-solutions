package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-09-28 18:59
 */
public class Leet1235 {


    public static void main(String[] args) {


        System.out.println(jobScheduling222(LeetUtils.convertInts1("[1, 3, 3, 4, 5]"),
                LeetUtils.convertInts1("[2, 5, 5, 7, 6]"),
                LeetUtils.convertInts1("[1, 4, 3, 2, 5]")
        ));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int N = 50;
            int[] start = ArrayUtil.generateArray(N, 1, 50);
            int[] end = ArrayUtil.generateArray(N, 1, 50);
            int[] profit = ArrayUtil.generateArray(N, 1, 50);

            Arrays.sort(start);

            for (int j = 0; j < N; j++) {
                if (end[j] <= start[j]) {
                    end[j] = start[j] + end[j];
                }
            }

            if (jobScheduling222(start, end, profit) != right(start, end, profit)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(start));
                System.out.println(Arrays.toString(end));
                System.out.println(Arrays.toString(profit));
                System.out.println(jobScheduling(start, end, profit));
                System.out.println(right(start, end, profit));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;

        Job[] jobs = new Job[N];

        for (int i = 0; i < N; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs, Comparator.comparingInt(o -> o.end));

        Job[] dp = new Job[N];

        dp[0] = new Job(jobs[0]);

        int ans = jobs[0].profit;

        for (int i = 1; i < N; i++) {
            Job job = jobs[i];
            if (job.start >= dp[i - 1].end) {
                dp[i] = new Job(job.end, dp[i - 1].profit + job.profit);
            } else {
                // 二分
                int index = binarySearch(dp, i - 1, job.start);

                int pf = index == -1 ? job.profit : job.profit + dp[index].profit;

                dp[i] = new Job(job.end, pf);
            }

            ans = Math.max(ans, dp[i].profit);

        }

        return ans;
    }

    public static int jobScheduling222(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;

        Job[] jobs = new Job[N];

        for (int i = 0; i < N; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs, Comparator.comparingInt(o -> o.end));

        Job[] dp = new Job[N];

        dp[0] = new Job(jobs[0]);

        int ans = jobs[0].profit;

        for (int i = 1; i < N; i++) {
            Job job = jobs[i];
            if (job.start >= dp[i - 1].end) {
                dp[i] = new Job(job.end, dp[i - 1].profit + job.profit);
            } else {
                // 二分
                int index = binarySearch(dp, i - 1, job.start);

                int pf = index == -1 ? job.profit : job.profit + dp[index].profit;

                if (pf > dp[i - 1].profit) {
                    dp[i] = new Job(job.end, pf);
                } else {
                    dp[i] = new Job(dp[i - 1]);
                }
            }
        }

        return dp[N - 1].profit;
    }

    public static int binarySearch(Job[] arr, int R, int start) {
        int left = 0;
        int right = R;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (arr[mid].end > start) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private static class Job {
        int start;
        int end;
        int profit;

        public Job(Job job) {
            this.start = job.start;
            this.end = job.end;
            this.profit = job.profit;
        }

        public Job(int end, int profit) {
            this.end = end;
            this.profit = profit;
        }

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return start + " --- " + end + " ===> " + profit;
        }
    }

    public static int right(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = startTime[i];
            arr[i][1] = endTime[i];
            arr[i][2] = profit[i];
        }
        Arrays.sort(arr, (a1, a2) -> a1[1] - a2[1]);
        int dp[] = new int[n];
        dp[0] = arr[0][2];
        for (int i = 1; i < n; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                int mid = left + (right - left + 1) / 2;
                if (arr[mid][1] <= arr[i][0]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            if (arr[left][1] <= arr[i][0]) {
                dp[i] = Math.max(dp[i - 1], dp[left] + arr[i][2]);
            } else {
                dp[i] = Math.max(dp[i - 1], arr[i][2]);
            }
        }
        return dp[n - 1];
    }

}
