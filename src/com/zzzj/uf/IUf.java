package com.zzzj.uf;

/**
 * @author Zzzj
 * @create 2021-05-06 21:56
 */

// union find
public interface IUf {

    int getSize();

    void union(int i, int j);

    boolean isConnected(int i, int j);

}
