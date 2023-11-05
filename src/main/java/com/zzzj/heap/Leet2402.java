package com.zzzj.heap;

import cn.hutool.core.lang.Pair;
import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-08-24 18:02
 */
public class Leet2402 {

    public static void main(String[] args) {


        for (int i = 0; i < 10000; i++) {
            int M = 500;
            int L = 1;
            int R = M << 2;

            int[][] meetings = new int[M][2];

            Set<Integer> exists = new HashSet<>();

            for (int j = 0; j < M; j++) {

                meetings[j][0] = -1;
                while (meetings[j][0] == -1 || !exists.add(meetings[j][0])) {
                    int n1 = LeetUtils.random.nextInt(R) + L;
                    int n2 = LeetUtils.random.nextInt(R) + L;
                    meetings[j][0] = Math.min(n1, n2);
                    meetings[j][1] = Math.max(n1, n2);
                    if (meetings[j][1] == meetings[j][0])
                        meetings[j][1]++;
                }

            }

            int N = LeetUtils.random.nextInt(M) + 1;

            CopyableIterator<int[][]> it = new CopyableIterator<>(meetings, ArrayUtil::copy);

            int r1 = mostBooked(N, it.next());

            int r2 = right(N, it.next());

            if (r1 != r2) {
                int[][] sorted = it.next();
                Arrays.sort(sorted, Comparator.comparingInt(o -> o[0]));
                System.out.println("Error");
                System.out.println("N = " + N);
                System.out.println("meetings = " + Arrays.deepToString(sorted));
                System.out.println("mostBooked(N, it.next()) = " + r1);
                System.out.println("right(N, it.next()) = " + r2);
                return;
            }
        }

        System.out.println("Ok");
    }

    private static final int NUM = 0;

    private static final int END = 1;

    private static final int CNT = 2;

    public static int mostBooked(int n, int[][] meetings) {

        int N = meetings.length;

        // 按照开始时间排序
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));

        long[][] office = new long[n][3];

        for (int i = 0; i < n; i++) {
            office[i][NUM] = i;
        }

        PriorityQueue<Integer> q1 = new PriorityQueue<>(Comparator.comparingLong((Integer o) -> office[o][END]).thenComparingLong(o -> office[o][NUM]));

        PriorityQueue<Integer> q2 = new PriorityQueue<>(Comparator.comparingLong(o -> office[o][NUM]));

        for (int i = 0; i < n; i++)
            q2.add(i);

        for (int i = 0; i < N; i++) {
            int[] meeting = meetings[i];
            int start = meeting[0];
            int end = meeting[1];

            while (!q1.isEmpty() && office[q1.peek()][END] <= start)
                q2.add(q1.remove());

            // 没有空闲的, 那就用结束时间最小的
            if (q2.isEmpty()) {
                Integer nearestEnd = q1.remove();
                office[nearestEnd][END] += end - start;
                office[nearestEnd][CNT]++;
                q1.add(nearestEnd);
            } else { // 有空闲的
                Integer free = q2.remove();
                office[free][END] = end;
                office[free][CNT]++;
                q1.add(free);
            }

        }

        return Arrays.stream(office).max((o1, o2) -> {
            int diff = Long.compare(o1[CNT], o2[CNT]);
            return diff == 0 ? Long.compare(o2[NUM], o1[NUM]) : diff;
        }).map(ints -> (int) ints[NUM]).orElse(0);
    }

    public static int right(int n, int[][] meetings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((m1, m2) -> m1[0] - m2[0]);
        pq.addAll(Arrays.asList(meetings));
        int[] arr = new int[n];
        //(下次开放时间,会议室编号)
        PriorityQueue<Pair<Long, Integer>> pq1 = new PriorityQueue<>((p1, p2) -> {
            long i1 = p1.getKey() - p2.getKey();
            int i2 = p1.getValue() - p2.getValue();
            if (i1 == 0) return i2;
            return i1 < 0 ? -1 : 1;
        });
        for (int i = 0; i < n; i++) {
            pq1.offer(new Pair<Long, Integer>(0L, i));
        }
        while (!pq.isEmpty()) {
            int[] meeting = pq.poll();
            PriorityQueue<Pair<Long, Integer>> pq2 = new PriorityQueue<>((p1, p2) -> (int) (p1.getValue() - p2.getValue()));
            Pair<Long, Integer> room = pq1.peek();
            if (room.getKey() <= meeting[0]) {
                while (!pq1.isEmpty() && pq1.peek().getKey() <= meeting[0]) {
                    room = pq1.poll();
                    pq2.offer(room);
                }
                room = pq2.poll();
                pq1.addAll(pq2);
                pq1.offer(new Pair<Long, Integer>((long) meeting[1], room.getValue()));
                arr[(int) room.getValue()]++;
            } else {
                int time = meeting[1] - meeting[0];
                room = pq1.poll();
                pq1.offer(new Pair<Long, Integer>(room.getKey() + time, room.getValue()));
                arr[room.getValue()]++;
            }
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == max) {
                return i;
            }
        }
        return -1;
    }

}
