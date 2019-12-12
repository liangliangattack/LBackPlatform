package com.liang.pro.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author 梁波 liangliangattack
 * @date 2019/12/12 14:38
 */
@Data
public class QuestionDto {

    private Integer id;

    /**
     * 标题
     */
    private String title;

    private Date createTime;

    /**
     * 发起人
     */
    private Integer creator;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 浏览次数
     */
    private Integer viewCount;

    /**
     * 收藏次数
     */
    private Integer starCount;

    /**
     * 标签
     */
    private String tag;

    /**
     * 内容
     */
    private String content;
}
