package com.zzzj.tree;

import java.util.HashSet;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-12-03 15:58
 */
public class UnderlyingIndexes {

    private Map<Character, HashSet<UnderlyingIndex>> reverseIndexes;

    private final double boost;

    public UnderlyingIndexes(Map<Character, HashSet<UnderlyingIndex>> reverseIndexes, double boost) {
        this.reverseIndexes = reverseIndexes;
        this.boost = boost;
    }

    public UnderlyingIndexes(Map<Character, HashSet<UnderlyingIndex>> reverseIndexes) {
        this(reverseIndexes, 1);
    }

    public Map<Character, HashSet<UnderlyingIndex>> getReverseIndexes() {
        return reverseIndexes;
    }

    public void setReverseIndexes(Map<Character, HashSet<UnderlyingIndex>> reverseIndexes) {
        this.reverseIndexes = reverseIndexes;
    }

    public double getBoost() {
        return boost;
    }

}
