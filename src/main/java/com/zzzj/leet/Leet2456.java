package com.zzzj.leet;

import java.util.*;
import java.util.stream.Collectors;

public class Leet2456 {

    public static void main(String[] args) {
        System.out.println(mostPopularCreator(
                new String[]{"alice", "bob", "alice", "chris"},
                new String[]{"one", "two", "three", "one"},
                new int[]{5, 10, 5, 4}
        ));
    }

    public static List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {

        int N = creators.length;

        Map<String, Creator> creatorMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            creatorMap.computeIfAbsent(creators[i], Creator::new)
                    .addVideo(ids[i], views[i]);
        }

        List<Creator> sorted = creatorMap.values()
                .stream()
                .peek(Creator::finish)
                .sorted((o1, o2) -> o2.totalViewCount - o1.totalViewCount)
                .collect(Collectors.toList());

        int highest = sorted.get(0).totalViewCount;

        List<List<String>> ans = new ArrayList<>();

        for (Creator creator : sorted) {
            if (creator.totalViewCount != highest) {
                break;
            }

            ans.add(
                    Arrays.asList(creator.name, creator.topVideo)
            );
        }

        return ans;
    }

    private static class Creator {
        String name;
        Map<String, Integer> videos;

        private int totalViewCount;
        private String topVideo;

        public Creator(String name) {
            this.name = name;
            this.videos = new HashMap<>();
        }

        public void addVideo(String videoName, int viewCount) {
            videos.compute(videoName, (s, oldCount) -> oldCount == null ? viewCount : viewCount + oldCount);
        }

        public void finish() {
            topVideo = null;
            int topCount = -1;

            for (Map.Entry<String, Integer> entry : videos.entrySet()) {

                String video = entry.getKey();

                Integer view = entry.getValue();

                totalViewCount += view;

                if (view > topCount || (view == topCount && video.compareTo(topVideo) < 0)) {
                    topVideo = video;
                    topCount = view;
                }
            }
        }

    }

}
