package com.zzzj.leet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-09-13 16:28
 */
public class Leet811 {


    public static void main(String[] args) {
//        System.out.println(subdomainVisits(new String[]{"9001 discuss.leetcode.com" }));
        System.out.println(subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" }));
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> count = new HashMap<>();

        for (String cpdomain : cpdomains) {
            String[] arr = cpdomain.split(" ");
            int c = Integer.parseInt(arr[0]);
            String str = arr[1];

            int index = str.indexOf('.');

            count.put(str, count.getOrDefault(str, 0) + c);

            while (index != -1) {
                str = str.substring(index + 1);
                count.put(str, count.getOrDefault(str, 0) + c);
                index = str.indexOf('.');
            }
        }

        return count.entrySet()
                .stream()
                .map(entry -> entry.getValue() + " " + entry.getKey())
                .collect(Collectors.toList());
    }

}
