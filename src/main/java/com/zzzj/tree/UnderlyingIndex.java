package com.zzzj.tree;


/**
 * @author zzzj
 * @create 2021-06-18 10:47
 */
public class UnderlyingIndex {

    private Integer id;

    private Character nextC;

    private Integer index;

    private String fullText;

    public UnderlyingIndex(Integer id, Character nextC, String fullText, int index) {
        this.id = id;
        this.nextC = nextC;
        this.fullText = fullText;
        this.index = index;
    }

    public UnderlyingIndex(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UnderlyingIndex{" +
                "id=" + id +
                ", nextC=" + nextC +
                '}';
    }

    // 只要id相同就视为相同
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnderlyingIndex that = (UnderlyingIndex) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getNextC() {
        return nextC;
    }

    public void setNextC(Character nextC) {
        this.nextC = nextC;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
