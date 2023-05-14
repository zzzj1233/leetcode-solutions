package com.zzzj.design;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-10-10 18:38
 */
public class Leet2353 {

    public static void main(String[] args) {
        FoodRatings foodRatings = new FoodRatings(
                new String[]{"emgqdbo", "jmvfxjohq", "qnvseohnoe", "yhptazyko", "ocqmvmwjq"},
                new String[]{"snaxol", "snaxol", "snaxol", "fajbervsj", "fajbervsj"},
                new int[]{2, 6, 18, 6, 5}
        );

        foodRatings.changeRating("qnvseohnoe", 11);
        System.out.println(foodRatings.highestRated("fajbervsj"));
    }

    private static class FoodRatings {

        Map<String, String> foodMap = new HashMap<>();

        Map<String, Integer> foodMap2 = new HashMap<>();

        Map<String, TreeMap<Integer, TreeSet<String>>> curMap = new HashMap<>();

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            int N = foods.length;
            for (int i = 0; i < N; i++) {
                foodMap.put(foods[i], cuisines[i]);
                foodMap2.put(foods[i], ratings[i]);
                curMap.computeIfAbsent(cuisines[i], s -> new TreeMap<>())
                        .computeIfAbsent(ratings[i], integer -> new TreeSet<>())
                        .add(foods[i]);
            }
        }

        public void changeRating(String food, int newRating) {
            String cuisine = foodMap.get(food);

            Integer oldRate = foodMap2.put(food, newRating);

            Set<String> oldFoodSet = curMap.get(cuisine).get(oldRate);

            oldFoodSet.remove(food);

            if (oldFoodSet.isEmpty()) {
                curMap.get(cuisine).remove(oldRate);
            }

            curMap.get(cuisine).computeIfAbsent(newRating, it -> new TreeSet<>())
                    .add(food);
        }

        public String highestRated(String cuisine) {
            TreeSet<String> value = curMap.get(cuisine).lastEntry().getValue();

            return value.isEmpty() ? "" : value.first();
        }
    }

}
