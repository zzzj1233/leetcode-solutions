package com.zzzj.leet;

import java.util.Iterator;

/**
 * @author zzzj
 * @create 2022-05-10 18:56
 */
public class Leet284 {

    static class PeekingIterator implements Iterator<Integer> {

        private final Iterator<Integer> iterator;

        Integer next;

        public PeekingIterator(Iterator<Integer> iterator) {
            this.iterator = iterator;
            if (iterator.hasNext()) {
                next = iterator.next();
            }
        }

        public Integer peek() {
            return null;
        }

        @Override
        public Integer next() {
            Integer ret = next;

            if (iterator.hasNext()) {
                next = iterator.next();
            } else {
                next = null;
            }

            return ret;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

    }


}
