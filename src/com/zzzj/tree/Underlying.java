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
