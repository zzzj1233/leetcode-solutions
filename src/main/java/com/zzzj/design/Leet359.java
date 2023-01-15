package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;

public class Leet359 {


    private static class Logger {

        private Map<String, Integer> map = new HashMap<>();

        public Logger() {

        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            Integer preTime = map.getOrDefault(message, -1);

            if (timestamp >= preTime) {
                map.put(message, timestamp + 10);
                return true;
            }

            return false;
        }
    }

}
