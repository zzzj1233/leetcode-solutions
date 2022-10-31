package com.zzzj.leet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Leet1311 {

    public static void main(String[] args) {
    }

    public static List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {

        LinkedList<Integer> queue = new LinkedList<>();

        int curLevel = -1;

        boolean[] visited = new boolean[101];

        visited[id] = true;

        queue.add(id);

        while (!queue.isEmpty()) {
            curLevel++;

            int size = queue.size();

            if (curLevel == level) {
                Map<String, Integer> counter = new HashMap<>();

                for (Integer it : queue) {
                    List<String> videos = watchedVideos.get(it);
                    for (String video : videos) {
                        counter.put(video, counter.getOrDefault(video, 0) + 1);
                    }
                }

                return counter.entrySet()
                        .stream()
                        .sorted((o1, o2) -> {
                            if (o1.getValue().equals(o2.getValue())) {
                                return o1.getKey().compareTo(o2.getKey());
                            }
                            return o1.getValue() - o2.getValue();
                        }).map(Map.Entry::getKey)
                        .collect(Collectors.toList());
            }

            for (int i = 0; i < size; i++) {
                Integer first = queue.removeFirst();

                int[] others = friends[first];

                for (int other : others) {
                    if (!visited[other]) {
                        queue.addLast(other);
                        visited[other] = true;
                    }
                }

            }

        }

        return null;
    }

}
