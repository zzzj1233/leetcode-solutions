package com.zzzj.leet;

import com.zzzj.util.Unresolved;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-09-26 15:27
 */
@Unresolved
public class Leet853 {

    // 10
    //[8,3,7,4,6,5]
    //[4,4,4,4,4,4]
    public static void main(String[] args) {
        System.out.println(carFleet(10, new int[]{8, 3, 7, 4, 6, 5}, new int[]{4, 4, 4, 4, 4, 4}));
    }

    public static int carFleet(int target, int[] position, int[] speed) {

        int N = position.length;

        Car[] cars = new Car[N];

        for (int i = 0; i < N; i++) {
            cars[i] = new Car(position[i], Math.ceil((target - position[i]) / ((double) speed[i])));
        }

        Arrays.sort(cars, Comparator.comparingInt(o -> o.position));

        int ans = 0;

        for (int i = N - 2; i >= 0; i--) {
            // 是否可以和上一辆车组成车队?
            Car prev = cars[i + 1];

            Car cur = cars[i];

            // 上一辆车在我前面,并且它达到的时间 < 我到达的时间, 那么我们一定不可遇
            if (prev.time < cur.time) {
                ans++;
            } else {  // 可以组成车队
                cars[i].time = prev.time;
            }
        }

        // 最后一个车队
        return ans + 1;
    }

    private static class Car {
        int position;
        double time;

        public Car(int position, double time) {
            this.position = position;
            this.time = time;
        }
    }

}
