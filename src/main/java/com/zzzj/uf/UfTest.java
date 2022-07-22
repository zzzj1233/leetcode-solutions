package com.zzzj.uf;

import java.util.Random;

/**
 * @author Zzzj
 * @create 2021-05-06 23:00
 */
public class UfTest {

    public static void test(IUf uf, int m) {
        Random random = new Random();

        long start = System.currentTimeMillis();

        for (int i = 0; i < m; i++) {
            uf.union(random.nextInt(uf.getSize()), random.nextInt(uf.getSize()));
        }

        for (int i = 0; i < m; i++) {
            uf.isConnected(random.nextInt(uf.getSize()), random.nextInt(uf.getSize()));
        }

        System.out.println("spend : " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void test10w() {
        int size = 100000;
        int m = 100000;

        // 4430ms
        test(new UnionFind1(size), m);
        // 9941ms
        test(new UnionFind2(size), m);
        // 18ms
        test(new UnionFind3(size), m);

//        test(new Leet827.UnionFind(size), m);
    }

    public static void test1000w() {
        int size = 10000000;
        int m = 10000000;

        // 4309ms
        test(new UnionFind3(size), m);

        // 4172ms
        test(new UnionFind4(size), m);

        // 3656ms
        test(new UnionFind5(size), m);

//        test(new Leet827.UnionFind(size), m);
    }

    public static void main(String[] args) {
//        test10w();
        test1000w();
    }

}
