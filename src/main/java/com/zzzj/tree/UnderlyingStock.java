package com.zzzj.tree;

import com.sun.istack.internal.Nullable;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2021-06-17 15:41
 */
public class UnderlyingStock {

    private Product[] stock;

    private UnderlyingIndexes codeReverseIndexes;

    private UnderlyingIndexes nameEnReverseIndexes;

    private UnderlyingIndexes nameChReverseIndexes;

    private UnderlyingIndexes exchangeCodeReverseIndexes;

    private static final int SPACE = 32;

    private ReentrantReadWriteLock.ReadLock readLock;

    private ReentrantReadWriteLock.WriteLock writeLock;

    private static final Logger log = LoggerFactory.getLogger(UnderlyingStock.class);

    public UnderlyingStock(List<Product> underlyings) {
        // 1. 实例化锁
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        this.readLock = rwLock.readLock();
        this.writeLock = rwLock.writeLock();

        // 2. 初始化
        this.initialize(underlyings);
    }

    private void initialize(List<Product> underlyings) {
        try {
            writeLock.lock();
            // 1. 初始化索引仓库
            initStock(underlyings);

            // 2. 建立倒排索引
            analyse();
        } catch (Throwable throwable) {

        } finally {
            writeLock.lock();
        }
    }

    private void initStock(List<Product> underlyings) {
        Integer maxId = underlyings.stream().max(Comparator.comparingInt(Product::getId)).map(Product::getId).get();
        this.stock = new Product[maxId + 1];
        // 初始化哈希表
        for (int i = 0; i < underlyings.size(); i++) {
            this.stock[underlyings.get(i).getId()] = underlyings.get(i);
        }
    }

    private void analyse() {
        long start = System.currentTimeMillis();
        writeLock.lock();
        try {
            this.codeReverseIndexes = new UnderlyingIndexes(new HashMap<>(64), 1.5);
            this.nameEnReverseIndexes = new UnderlyingIndexes(new HashMap<>(64));
            this.nameChReverseIndexes = new UnderlyingIndexes(new HashMap<>(64));
            this.exchangeCodeReverseIndexes = new UnderlyingIndexes(new HashMap<>(64));
            analyse(codeReverseIndexes.getReverseIndexes(), Product::getCode);
            analyse(nameEnReverseIndexes.getReverseIndexes(), Product::getNameEn);
            analyse(nameChReverseIndexes.getReverseIndexes(), Product::getName);
            analyse(exchangeCodeReverseIndexes.getReverseIndexes(), Product::getCodeExchange);
        } finally {
            // log~
            writeLock.unlock();
        }
    }

    private void analyse(
            Map<Character, HashSet<UnderlyingIndex>> reverseIndexes,
            Function<Product, String> propFun
    ) {

        for (Product product : this.stock) {

            if (product == null) {
                continue;
            }

            String indexedProp = propFun.apply(product);

            for (int i = 0; i < indexedProp.length(); i++) {
                char c = indexedProp.charAt(i);

                if (c == SPACE) {
                    continue;
                }

                Character nextC = null;

                // ignore space
                for (int addI = 1; i + addI < indexedProp.length(); addI++) {

                    if (indexedProp.charAt(i + addI) != SPACE) {
                        nextC = indexedProp.charAt(i + addI);
                        break;
                    }

                }

                if (c >= 'A' && c <= 'Z') {
                    // 转小写
                    c += SPACE;
                }
                reverseIndexes.computeIfAbsent(c, k -> new HashSet<>(8)).add(new UnderlyingIndex(product.getId(), nextC, indexedProp, i));
            }
        }
    }

    @Nullable
    public Collection<Product> search(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Collections.emptyList();
        }

        // 去掉所有空格
        keyword = keyword.replaceAll(" ", "");

        if (keyword.isEmpty()) {
            return Collections.emptyList();
        }

        Map<UnderlyingIndex, Double> searchResult = new HashMap<>();

        search(keyword, codeReverseIndexes, searchResult);
        search(keyword, exchangeCodeReverseIndexes, searchResult);
        search(keyword, nameEnReverseIndexes, searchResult);
        search(keyword, nameChReverseIndexes, searchResult);

        // 1. 按照score排序
        // 2. 如果score相同,按照长度排序
        // 3. 如果score相同,长度相同,最后按字母A-Z排序，例如LILA应该排在LIVN前面

        return searchResult
                .entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    // 1. 比score
                    int scoreSubtract = Double.compare(o1.getValue(), o2.getValue());
                    if (scoreSubtract != 0) {
                        return -scoreSubtract;
                    }
                    // 2. 比length
                    int lengthSubtract = o1.getKey().getFullText().length() - o2.getKey().getFullText().length();
                    if (lengthSubtract != 0) {
                        return lengthSubtract;
                    }
                    // 3. 比字母排序
                    return o1.getKey().getFullText().toUpperCase(Locale.ROOT).compareTo(o2.getKey().getFullText().toUpperCase(Locale.ROOT));
                }).map(entry -> stock[entry.getKey().getId()])
                .collect(Collectors.toList());
    }

    private void search(
            String keyword,
            UnderlyingIndexes underlyingIndexes,
            Map<UnderlyingIndex, Double> searchResult
    ) {
        // 第一个字母
        char firstC = keyword.charAt(0);

        if (firstC >= 'A' && firstC <= 'Z') {
            // 转小写
            firstC += 32;
        }

        Map<Character, HashSet<UnderlyingIndex>> reverseIndexes = underlyingIndexes.getReverseIndexes();

        Set<UnderlyingIndex> indices = reverseIndexes.get(firstC);

        if (indices == null || indices.isEmpty()) {
            return;
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

        indices.forEach(underlyingIndex -> {
            // score = 下标 * 权重
            double score = (100 - underlyingIndex.getIndex()) * underlyingIndexes.getBoost();
            searchResult.compute(underlyingIndex, (underlyingId, oldScore) -> {
                return oldScore == null ? score : Math.max(oldScore, score);
            });
        });

    }

    public static void main(String[] args) {
        ArrayList<Product> list = new ArrayList<>(16);

        list.add(new Product(1, "BABA", "阿里巴巴", "AliBaBa", "US"));
        list.add(new Product(2, "AMZN", "亚马逊", "Bmazon", "US"));
        list.add(new Product(3, "NFLX", "美国奈马飞公司", "Netflix", "US"));
        list.add(new Product(4, "MSFT", "美国微软公司", "MicroSoft", "US"));
        list.add(new Product(5, "AAPL", "美国苹果公司", "Apple", "US"));
        list.add(new Product(6, "GOOG", "美国谷歌公司", "Google", "US"));
        list.add(new Product(7, "CABC", "阿里亚马苹果谷歌", "BaMiplgl", "US"));
        list.add(new Product(8, "bama", "Test", "AB", "US"));
        list.add(new Product(9, "bama公司", "Test", "AB", "US"));
        list.add(new Product(10, "公司bama", "Test", "AB", "US"));

        UnderlyingStock stock = new UnderlyingStock(list);

        System.out.println(stock.search("公司"));
    }

}
