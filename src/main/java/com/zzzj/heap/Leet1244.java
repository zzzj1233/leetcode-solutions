package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-06-23 10:34
 */
public class Leet1244 {

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();

        LinkedList<Object> result = LeetUtils.executeExpression("[\"Leaderboard\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"top\",\"reset\",\"reset\",\"addScore\",\"top\"]",
                "[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]",
                leaderboard
        );

        System.out.println(result);

    }

    private static class Leaderboard {

        Map<Integer, Integer> idMap = new HashMap<>();

        TreeMap<Integer, Set<Integer>> scoreMap = new TreeMap<>((o1, o2) -> o2 - o1);

        public Leaderboard() {

        }

        public void addScore(int playerId, int score) {
            if (!idMap.containsKey(playerId)) {
                idMap.put(playerId, score);
                scoreMap.computeIfAbsent(score, integer -> new HashSet<>())
                        .add(playerId);
            } else {
                Integer oldScore = idMap.get(playerId);
                int newScore = score + oldScore;

                idMap.put(playerId, newScore);

                scoreMap.get(oldScore).remove(playerId);
                scoreMap.computeIfAbsent(newScore, integer -> new HashSet<>()).add(playerId);
            }
        }

        public int top(int K) {
            int result = 0;

            int calculated = 0;

            int highest = scoreMap.firstKey() + 1;

            while (calculated < K) {
                Map.Entry<Integer, Set<Integer>> entry = scoreMap.higherEntry(highest);
                result += entry.getKey() * (Math.min((K - calculated), entry.getValue().size()));
                calculated += entry.getValue().size();
                highest = entry.getKey();
            }

            return result;
        }

        public void reset(int playerId) {
            Integer oldScore = idMap.remove(playerId);
            scoreMap.get(oldScore).remove(playerId);
        }
    }
}
