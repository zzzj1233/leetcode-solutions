package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-05-06 15:58
 */
public class Leet1797 {

    private static class AuthenticationManager {

        private final int ttl;

        private final Map<String, Integer> expire = new HashMap<>();

        public AuthenticationManager(int timeToLive) {
            this.ttl = timeToLive;
        }

        public void generate(String tokenId, int currentTime) {
            expire.put(tokenId, currentTime + this.ttl);
        }

        public void renew(String tokenId, int currentTime) {
            Integer prev = expire.get(tokenId);
            if (prev == null || prev <= currentTime) return;
            generate(tokenId, currentTime);
        }

        public int countUnexpiredTokens(int currentTime) {
            return (int) expire.values().stream()
                    .filter(v -> v > currentTime)
                    .count();
        }

    }

}
