package com.zzzj.simple;

/**
 * @author zzzj
 * @create 2023-04-12 16:40
 */
public class Leet2469 {

    public static double[] convertTemperature(double celsius) {

        return new double[]{
                celsius + 273.15,
                celsius * 1.80 + 32
        };
    }

}
