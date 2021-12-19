package com.zzzj.tree;


/**
 * @author zzzj
 * @create 2021-06-18 10:47
 */
public class UnderlyingIndex {

    private Integer id;

    private Character nextC;

    public UnderlyingIndex(Integer id, Character nextC) {
        this.id = id;
        this.nextC = nextC;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnderlyingIndex that = (UnderlyingIndex) o;

        if (!id.equals(that.id)) return false;
        return nextC != null ? nextC.equals(that.nextC) : that.nextC == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (nextC != null ? nextC.hashCode() : 0);
        return result;
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
}
