package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zzzj
 * @create 2023-01-17 12:10
 */
public class Leet630 {

    public static void main(String[] args) {
        int N = 10000;

        for (int i = 0; i < N; i++) {

            int M = 50;

            int[][] data = new int[M][];

            for (int j = 0; j < M; j++) {
                int duration = LeetUtils.random.nextInt(100) + 1;

                data[j] = new int[]{duration, LeetUtils.random.nextInt(100) + 1 + duration};
            }

            CopyableIterator<int[][]> iterator = new CopyableIterator<>(data, ArrayUtil::copy);

            if (scheduleCourse(iterator.next()) != right(iterator.next())) {
                System.out.println("Error");
                System.out.println(Arrays.deepToString(iterator.next()));
                System.out.println(scheduleCourse(iterator.next()));
                System.out.println(right(iterator.next()));
                return;
            }

        }

        System.out.println("Ok");
    }

    // 最多可以修几门课
    public static int scheduleCourse(int[][] courses) {
        int N = courses.length;

        // 按照结束时间排序
        // 越早结束的排越前面
        Arrays.sort(courses, Comparator.comparingInt(o -> o[1]));

        // 大根堆: 用于已经学习了的课程的耗时
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int cur = 0;

        for (int i = 0; i < N; i++) {
            int[] course = courses[i];

            int duration = course[0];
            int endTime = course[1];

            if (cur + duration > endTime) {
                if (!queue.isEmpty() && queue.peek() > duration && cur - queue.peek() + duration <= endTime) {
                    cur -= queue.remove();
                    cur += duration;
                    queue.add(duration);
                }
            } else {
                cur += duration;
                queue.add(duration);
            }

        }

        return queue.size();
    }

    public static int right(int[][] courses) {
        //根据课程结束时间升序排列
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));
        //课程用时的大根优先级队列
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> (b - a));
        int times = 0;
        for (int i = 0; i < courses.length; i++) {
            //如果此课程可以学习，则学习，总用时增加，此课程用时入堆
            if (times + courses[i][0] <= courses[i][1]) {
                times += courses[i][0];
                queue.add(courses[i][0]);
                //如果不能学习此课程，因为此课程结束时间比之前所有的都晚，存在两种情况：
                //1.此课程用时比之前某个课程少：则学习此课程，放弃之前用时最长的课程
                //2.此课程用时比之前所有课程多：则不学习此课程，可以理解为学习此课程，同时放弃之前用时最长的课程（此课程）
                //则此种情况，学习此课程并放弃之前用时最长的课程（总用时减去大根堆堆顶）
            } else {
                queue.add(courses[i][0]);
                times = times + courses[i][0] - queue.poll();
            }
        }
        return queue.size();
    }

}
