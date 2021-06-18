package com.zzzj.tree;

import com.sun.istack.internal.Nullable;

/**
 * @author zzzj
 * @create 2021-06-18 10:47
 */
public class UnderlyingIndex {

    private Integer id;

    @Nullable
    private Character nextC;

    public UnderlyingIndex(Integer id, Character nextC) {
        this.id = id;
        this.nextC = nextC;
    }

    public UnderlyingIndex(Integer id) {
        this.id = id;
    }

}
