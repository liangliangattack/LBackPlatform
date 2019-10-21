package com.liang.pro.entity;

import javax.persistence.*;

@Table(name = "lplatform..l_user")
public class LUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_id")
    private String accountId;

    private String password;

    private String name;

    private String token;

    /**
     * 创建time
     */
    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "modified_time")
    private Long modifiedTime;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return account_id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取创建time
     *
     * @return create_time - 创建time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建time
     *
     * @param createTime 创建time
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modified_time
     */
    public Long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * @param modifiedTime
     */
    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}