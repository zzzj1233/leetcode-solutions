package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-10-11 16:25
 */
public class Leet2343 {

    public static void main(String[] args) {
        // ["64333639502","65953866768","17845691654","87148775908","58954177897","70439926174","48059986638","47548857440","18418180516","06364956881","01866627626","36824890579","14672385151","71207752868"]
        //[[9,4],[6,1],[3,8],[12,9],[11,4],[4,9],[2,7],[10,3],[13,1],[13,1],[6,1],[5,10]]
        System.out.println(Arrays.toString(smallestTrimmedNumbers(new String[]{"64333639502", "65953866768", "17845691654", "87148775908", "58954177897", "70439926174", "48059986638", "47548857440", "18418180516", "06364956881", "01866627626", "36824890579", "14672385151", "71207752868"}, LeetUtils.convertInts("[[9,4],[6,1],[3,8],[12,9],[11,4],[4,9],[2,7],[10,3],[13,1],[13,1],[6,1],[5,10]]"))));
    }

    public static int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {

        int N = queries.length;

        Map<Integer, HashMap<Integer, List<Integer>>> query = new HashMap<>(N);

        for (int i = 0; i < N; i++) {
            query.computeIfAbsent(queries[i][1], integer -> new HashMap<>())
                    .computeIfAbsent(queries[i][0], integer -> new ArrayList<>())
                    .add(i);
        }

        int[] ans = new int[N];

        int M = nums.length;

        int len = nums[0].length();

        for (Map.Entry<Integer, HashMap<Integer, List<Integer>>> entry : query.entrySet()) {
            Integer reserve = entry.getKey();

            HashMap<Integer, List<Integer>> value = entry.getValue();

            // 创建优先队列
            int cut = Math.max(0, len - reserve);

            IndexedString[] strings = new IndexedString[M];

            for (int i = 0; i < M; i++) {
                strings[i] = new IndexedString(nums[i].substring(cut), i);
            }

            Arrays.sort(strings, (o1, o2) -> {
                if (o1.str.compareTo(o2.str) == 0) {
                    return o1.index - o2.index;
                }
                return o1.str.compareTo(o2.str);
            });

            for (Map.Entry<Integer, List<Integer>> entry2 : value.entrySet()) {
                Integer k = entry2.getKey();
                List<Integer> indexes = entry2.getValue();

                for (Integer index : indexes) {
                    ans[index] = strings[k - 1].index;
                }
            }
        }

        return ans;
    }

    public static class IndexedString {
        int index;
        String str;

        public IndexedString(String str, int index) {
            this.index = index;
            this.str = str;
        }

        @Override
        public String toString() {
            return str + " --- " + index;
        }
    }


}
