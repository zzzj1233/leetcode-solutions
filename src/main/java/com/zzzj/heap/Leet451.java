package com.zzzj.heap;

import cn.hutool.core.bean.BeanUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-03-11 15:52
 */
public class Leet451 {

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
    }

    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>(128);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> entry = map.entrySet().stream().sorted(Comparator.comparingInt(o -> (int) ((Map.Entry) o).getValue()).reversed())
                .collect(Collectors.toList());

        StringBuilder ans = new StringBuilder(s.length());

        for (Map.Entry<Character, Integer> item : entry) {
            Character c = item.getKey();
            for (int i = 0; i < item.getValue(); i++) {
                ans.append(c);
            }
        }

        return ans.toString();
    }

}
