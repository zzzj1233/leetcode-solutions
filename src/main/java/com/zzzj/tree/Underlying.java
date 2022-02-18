package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2021-06-18 10:39
 */
public class Underlying {

    private Integer id;

    private String code;

    private String nameEn;

    private String nameCh;

    private String market;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Underlying that = (Underlying) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (nameEn != null ? !nameEn.equals(that.nameEn) : that.nameEn != null) return false;
        if (nameCh != null ? !nameCh.equals(that.nameCh) : that.nameCh != null) return false;
        return market != null ? market.equals(that.market) : that.market == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameCh != null ? nameCh.hashCode() : 0);
        result = 31 * result + (market != null ? market.hashCode() : 0);
        return result;
    }

    public Underlying(Integer id, String code, String nameEn, String nameCh, String market) {
        this.id = id;
        this.code = code;
        this.nameEn = nameEn;
        this.nameCh = nameCh;
        this.market = market;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }

    @Override
    public String toString() {
        return "Underlying{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", nameCh='" + nameCh + '\'' +
                ", market='" + market + '\'' +
                '}';
    }
}
