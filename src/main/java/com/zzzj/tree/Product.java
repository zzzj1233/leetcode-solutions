package com.zzzj.tree;


import java.util.Date;

public class Product {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 产品代码
     */
    private String code;

    /**
     * 交易所代码
     */
    private String codeExchange;

    /**
     * 彭博代码
     */
    private String codeBloomberg;

    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 市场
     */
    private String market;

    /**
     * 报价货币
     */
    private String quoteCurrency;

    /**
     * 产品类型：1产品，2挂钩标的，3货币对
     */
    private Integer type;

    /**
     * 0启用，1删除
     */
    private Integer status;

    /**
     * 置顶标志
     */
    private Integer topFlag;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    public Product() {

    }

    public Product(Integer id, String code, String name, String nameEn, String market) {
        this.id = id;
        this.code = code;
        this.nameEn = nameEn;
        this.name = name;
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

    public String getCodeExchange() {
        return codeExchange;
    }

    public void setCodeExchange(String codeExchange) {
        this.codeExchange = codeExchange;
    }

    public String getCodeBloomberg() {
        return codeBloomberg;
    }

    public void setCodeBloomberg(String codeBloomberg) {
        this.codeBloomberg = codeBloomberg;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(Integer topFlag) {
        this.topFlag = topFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", codeExchange='" + codeExchange + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", name='" + name + '\'' +
                ", market='" + market + '\'' +
                '}';
    }
}