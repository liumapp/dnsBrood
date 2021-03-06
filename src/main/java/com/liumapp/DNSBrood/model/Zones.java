package com.liumapp.DNSBrood.model;

/**
 * Created by liumapp on 7/19/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class Zones {

    private int id;

    private String userNumber;

    private String domain;

    private String value;

    private String type;

    private long createTime;

    private long updateTime;

    public String getUserNumber() {
        return userNumber;
    }

    public int getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public Zones() {

    }

    public Zones(String userNumber, String domain, String value, String type) {
        this.userNumber = userNumber;
        this.domain = domain;
        this.value = value;
        this.type = type;
    }

    public Zones(String userNumber, String domain, String value, String type, long updateTime) {
        this.userNumber = userNumber;
        this.domain = domain;
        this.value = value;
        this.type = type;
        this.updateTime = updateTime;
    }

    public Zones(String userNumber, String domain, String value, String type, long createTime, long updateTime) {
        this.userNumber = userNumber;
        this.domain = domain;
        this.value = value;
        this.type = type;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Zones{" +
                "id=" + id +
                ", userNumber='" + userNumber + '\'' +
                ", domain='" + domain + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
