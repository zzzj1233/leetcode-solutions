package com.zzzj.design;


/**
 * @author zzzj
 * @create 2023-05-12 15:36
 */
public class Leet1603 {


    private static class ParkingSystem {

        int[] cnt = new int[4];

        public ParkingSystem(int big, int medium, int small) {
            cnt[1] = big;
            cnt[2] = medium;
            cnt[3] = small;
        }

        public boolean addCar(int carType) {
            return cnt[carType]-- > 0;
        }

    }

}
