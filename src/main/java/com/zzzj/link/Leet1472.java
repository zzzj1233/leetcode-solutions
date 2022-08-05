package com.zzzj.link;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-03 15:16
 */
public class Leet1472 {

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // 你原本在浏览 "leetcode.com" 。访问 "google.com"
        browserHistory.visit("facebook.com");     // 你原本在浏览 "google.com" 。访问 "facebook.com"
        browserHistory.visit("youtube.com");      // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
        browserHistory.back(1);                   // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
        browserHistory.back(1);                   // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
        browserHistory.forward(1);                // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
        browserHistory.visit("linkedin.com");     // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
        browserHistory.forward(2);                // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
        browserHistory.back(2);
        browserHistory.back(7);
    }

    private static class BrowserHistory {

        List<String> list = new ArrayList<>();

        int visitIndex;

        int size;

        public BrowserHistory(String homepage) {
            visit(homepage);
        }

        public void visit(String url) {
            if (visitIndex >= list.size()) {
                list.add(url);
            } else {
                list.set(visitIndex, url);
            }
            visitIndex++;
            size = visitIndex;
        }

        public String back(int steps) {
            visitIndex = Math.max(1, visitIndex - steps);
            return list.get(visitIndex - 1);
        }

        public String forward(int steps) {
            visitIndex = Math.min(visitIndex + steps, size);
            return list.get(visitIndex - 1);
        }
    }

}
