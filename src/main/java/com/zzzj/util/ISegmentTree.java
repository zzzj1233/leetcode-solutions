package com.zzzj.util;

/**
 * @author zzzj
 * @create 2024-01-02 17:15
 */
public interface ISegmentTree<T extends Number> {

    void update(T L, T R, T V);

    T query(T L, T R);

}
