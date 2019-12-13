package com.liang.pro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "lplatform.l_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_id")
    private String accountId;

    private String password;

    private String name;

    private String token;

    private String avatarUrl;

    /**
     * 创建time
     */
    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "modified_time")
    private Long modifiedTime;


}