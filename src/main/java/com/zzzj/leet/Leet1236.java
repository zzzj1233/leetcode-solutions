package com.zzzj.leet;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-09-25 21:40
 */
public class Leet1236 {

    interface HtmlParser {
        public List<String> getUrls(String url);
    }

    public static List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> visited = new HashSet<>();

        List<String> ans = new ArrayList<>();

        visited.add(startUrl);

        String startHost = getHostName(startUrl);

        LinkedList<String> queue = new LinkedList<>();

        queue.add(startUrl);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String first = queue.removeFirst();

                ans.add(first);

                List<String> urls = htmlParser.getUrls(first);

                for (String url : urls) {
                    if (!visited.contains(url) && getHostName(url).equals(startHost)) {
                        queue.add(url);
                        visited.add(url);
                    }
                }

            }

        }

        return ans;
    }

    private static String getHostName(String url) {
        int begin = url.indexOf("//");
        int end = url.indexOf("/", begin + 2);
        return url.substring(begin + 2, end == -1 ? url.length() : end);
    }

}
