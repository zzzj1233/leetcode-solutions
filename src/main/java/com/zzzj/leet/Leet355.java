package com.zzzj.leet;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-05-17 17:17
 */
public class Leet355 {

    private static class Tweet {
        int id;
        int time;
        Tweet next;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }

    }

    private static class Twitter {

        public int publishTime;

        Map<Integer, Tweet> twitters;

        Map<Integer, Set<Integer>> followers;

        PriorityQueue<Tweet> queue;

        // 根据给定的 tweetId 和 userId 创建一条新推文。每次调用此函数都会使用一个不同的 tweetId 。
        // List<Integer> getNewsFeed(int userId) 检索当前用户新闻推送中最近10 条推文的 ID 。新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。推文必须 按照时间顺序由最近到最远排序
        public Twitter() {
            twitters = new HashMap<>(64);
            followers = new HashMap<>(64);
            queue = new PriorityQueue<>(Comparator.comparingInt(o -> ((Tweet) o).time).reversed());
        }

        public void postTweet(int userId, int tweetId) {
            Tweet old = twitters.get(userId);
            Tweet newTweet = new Tweet(tweetId, publishTime++);
            newTweet.next = old;
            twitters.put(userId, newTweet);
        }

        public List<Integer> getNewsFeed(int userId) {
            queue.clear();

            Set<Integer> users = followers.getOrDefault(userId, Collections.emptySet());

            List<Integer> ans = new ArrayList<>();

            Tweet userTweet = twitters.get(userId);

            if (userTweet != null) {
                queue.add(userTweet);
            }

            users.stream()
                    .map(twitters::get)
                    .filter(Objects::nonNull)
                    .forEach(queue::add);

            while (!queue.isEmpty() && ans.size() < 10) {
                Tweet tweet = queue.remove();
                ans.add(tweet.id);
                if (tweet.next != null) {
                    queue.add(tweet.next);
                }
            }

            return ans;
        }

        public void follow(int followerId, int followeeId) {
            followers.computeIfAbsent(followerId, ignore -> new HashSet<>())
                    .add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            Set<Integer> set = followers.get(followerId);

            if (set != null) {
                set.remove(followeeId);
            }

        }

    }
}
