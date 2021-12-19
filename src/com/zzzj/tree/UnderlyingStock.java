package com.zzzj.tree;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2021-06-17 15:41
 */
public class UnderlyingStock {

    private Underlying[] stock;

    private Map<Character, HashSet<UnderlyingIndex>> codeReverseIndexes;

    private Map<Character, HashSet<UnderlyingIndex>> nameEnReverseIndexes;

    private Map<Character, HashSet<UnderlyingIndex>> nameChReverseIndexes;

    public UnderlyingStock(List<Underlying> underlyings) {
        // 1. 初始化索引仓库
        initStock(underlyings);
        // 2. 建立倒排索引
        analyse();
    }

    private void initStock(List<Underlying> underlyings) {
        Integer maxId = underlyings.stream().max(Comparator.comparingInt(Underlying::getId)).map(Underlying::getId).get();
        this.stock = new Underlying[maxId + 1];
        // 初始化哈希表
        for (int i = 0; i < underlyings.size(); i++) {
            this.stock[underlyings.get(i).getId()] = underlyings.get(i);
        }
    }

    private void analyse() {
        this.codeReverseIndexes = new HashMap<>(64);
        this.nameEnReverseIndexes = new HashMap<>(64);
        this.nameChReverseIndexes = new HashMap<>(64);
        analyse(codeReverseIndexes, Underlying::getCode);
        analyse(nameEnReverseIndexes, Underlying::getNameEn);
        analyse(nameChReverseIndexes, Underlying::getNameCh);
    }

    private void analyse(Map<Character, HashSet<UnderlyingIndex>> reverseIndexes,
                         Function<Underlying, String> underlyingFunction
    ) {
        final int SPACE = 32;

        for (Underlying underlying : this.stock) {

            if (underlying == null) {
                continue;
            }

            String indexedProp = underlyingFunction.apply(underlying);

            for (int i = 0; i < indexedProp.length(); i++) {
                char c = indexedProp.charAt(i);

                if (c == SPACE) {
                    continue;
                }

                Character nextC = null;

                for (int addI = 1; i + addI < indexedProp.length(); addI++) {

                    if (indexedProp.charAt(i + addI) != SPACE) {
                        nextC = indexedProp.charAt(i + addI);
                        break;
                    }

                }

                if (c >= 'A' && c <= 'Z') {
                    // 转小写
                    reverseIndexes.computeIfAbsent((char) (c + 32), k -> new HashSet<>(8)).add(new UnderlyingIndex(underlying.getId(), nextC));
                } else {
                    reverseIndexes.computeIfAbsent(c, k -> new HashSet<>(8)).add(new UnderlyingIndex(underlying.getId(), nextC));
                }
            }
        }
    }

    private Set<Underlying> search(String keyword, Map<Character, HashSet<UnderlyingIndex>> reverseIndexes) {
        char firstC = keyword.charAt(0);

        if (firstC >= 'A' && firstC <= 'Z') {
            // 转小写
            firstC = (char) (firstC + 32);
        }

        Set<UnderlyingIndex> indices = reverseIndexes.get(firstC);

        if (indices == null || indices.isEmpty()) {
            return Collections.emptySet();
        }

        // 只有一个word
        if (keyword.length() == 1) {
            return indices.stream().map(underlyingIndex -> this.stock[underlyingIndex.getId()]).collect(Collectors.toSet());
        }

        for (int i = 1; i < keyword.length(); i++) {
            char nextC = keyword.charAt(i);
            if (nextC >= 'A' && nextC <= 'Z') {
                // 转小写
                nextC = (char) (nextC + 32);
            }
            char finalNextC = nextC;

            // nextChar != null and nextChar == keyWord[i]
            indices = indices.stream().filter(underlyingIndex -> {
                return underlyingIndex.getNextC() != null && underlyingIndex.getNextC() == finalNextC;
            }).collect(Collectors.toSet());
        }

        return indices.stream().map(underlyingIndex -> this.stock[underlyingIndex.getId()]).collect(Collectors.toSet());
    }

    public Collection<Underlying> search(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Collections.emptyList();
        }

        // 去掉所有空格
        keyword = keyword.replaceAll(" ", "");

        if (keyword.isEmpty()) {
            return Collections.emptyList();
        }

        Set<Underlying> codeSet = search(keyword, codeReverseIndexes);
        Set<Underlying> nameEnSet = search(keyword, nameEnReverseIndexes);
        Set<Underlying> nameChSet = search(keyword, nameChReverseIndexes);

        Set<Underlying> result = new HashSet<>(codeSet.size() + nameChSet.size() + nameEnSet.size());

        // 取并集
        result.addAll(codeSet);
        result.addAll(nameEnSet);
        result.addAll(nameChSet);

        return result;
    }

    public static void main(String[] args) {
        ArrayList<Underlying> list = new ArrayList<>(8);
        list.add(new Underlying(1, "BABA", "阿里巴巴", "AliBaBa", "US"));
        list.add(new Underlying(2, "AMZN", "亚马逊", "Amazon", "US"));
        list.add(new Underlying(3, "NFLX", "美国奈马飞公司", "Netflix", "US"));
        list.add(new Underlying(4, "MSFT", "美国微软公司", "MicroSoft", "US"));
        list.add(new Underlying(5, "AAPL", "美国苹果公司", "Apple", "US"));
        list.add(new Underlying(6, "GOOG", "美国谷歌公司", "Google", "US"));
        list.add(new Underlying(7, "CABC", "阿里亚马苹果谷歌", "BaMiplgl", "US"));
        list.add(new Underlying(8, "Test", "app", "AB", "US"));

        UnderlyingStock stock = new UnderlyingStock(list);

        System.out.println(stock.search("ma"));
    }

}
