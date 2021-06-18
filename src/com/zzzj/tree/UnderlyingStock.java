package com.zzzj.tree;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-06-17 15:41
 */
public class UnderlyingStock {

    private Underlying[] stock;

    private Map<Character, UnderlyingIndex> reverseIndexes;

    public UnderlyingStock(Collection<Underlying> underlyings) {
        // 1. 初始化索引仓库
        initStock(underlyings);
        // 2. 建立倒排索引
        analyse();
    }

    private void initStock(Collection<Underlying> underlyings) {
        Integer maxId = underlyings.stream().max(Comparator.comparingInt(Underlying::getId)).map(Underlying::getId).get();
        this.stock = new Underlying[maxId + 1];
        underlyings.forEach(underlying -> this.stock[underlying.getId()] = underlying);
    }

    private void analyse() {
        reverseIndexes = new HashMap<>(stock.length);

        final int SPACE = 32;

        for (Underlying underlying : this.stock) {
            String underlyingCode = underlying.getCode();
            for (int i = 0; i < underlyingCode.length(); i++) {
                char c = underlyingCode.charAt(i);

                if ((int) c == SPACE) {
                    continue;
                }

                Character nextC = null;

                for (int addI = 1; i + addI < underlyingCode.length(); addI++) {

                    if (underlyingCode.charAt(i + addI) != SPACE) {

                    }

                }

                // new UnderlyingIndex(underlying.getId(), )
            }
        }

    }

    public static void main(String[] args) {
//        ArrayList<Underlying> list = new ArrayList<>(8);
//        list.add(new Underlying(1, "BABA", "US"));
//        list.add(new Underlying(2, "AMZN", "US"));
//        list.add(new Underlying(3, "NFLX", "US"));
//        list.add(new Underlying(4, "MSFT", "US"));
//        list.add(new Underlying(5, "AAPL", "US"));
//        list.add(new Underlying(6, "GOOG", "US"));
//
//        new UnderlyingStock(list);
    }

}
