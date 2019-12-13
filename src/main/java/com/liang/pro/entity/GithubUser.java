package com.liang.pro.entity;

import lombok.Data;

/**
 * @author 梁波 liangliangattack
 * @date 2019/9/17 16:02
 */
@Data
public class GithubUser {

    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
    private String login;

}
