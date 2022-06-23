package com.zzzj.simulation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-06-22 11:24
 */
public class Leet1333 {

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        return Arrays.stream(restaurants)
                .filter(restaurant -> veganFriendly == 0 || restaurant[2] == 1)
                .filter(restaurant -> restaurant[3] <= maxPrice)
                .filter(restaurant -> restaurant[4] <= maxDistance)
                .sorted((o1, o2) -> {
                    if (o1[1] == o2[1]) {
                        return o2[0] - o1[0];
                    }
                    return o2[1] - o1[1];
                })
                .map(restaurant -> restaurant[0])
                .collect(Collectors.toList());

    }

}
