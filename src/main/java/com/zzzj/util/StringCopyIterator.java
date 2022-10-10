package com.zzzj.util;

import java.util.Iterator;

/**
 * @author zzzj
 * @create 2022-09-29 17:12
 */
public class StringCopyIterator implements Iterator<String> {

    private final String origin;

    public StringCopyIterator(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        return new String(origin.toCharArray());
    }

}
