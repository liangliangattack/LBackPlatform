package com.liang.pro.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "lplatform..question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 发起人
     */
    private Integer creator;

    /**
     * 评论数
     */
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * 浏览次数
     */
    @Column(name = "view_count")
    private Integer viewCount;

    /**
     * 收藏次数
     */
    @Column(name = "star_count")
    private Integer starCount;

    /**
     * 标签
     */
    private String tag;

    /**
     * 内容
     */
    private String content;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取发起人
     *
     * @return creator - 发起人
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * 设置发起人
     *
     * @param creator 发起人
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * 获取评论数
     *
     * @return comment_count - 评论数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置评论数
     *
     * @param commentCount 评论数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 获取浏览次数
     *
     * @return view_count - 浏览次数
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 设置浏览次数
     *
     * @param viewCount 浏览次数
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 获取收藏次数
     *
     * @return star_count - 收藏次数
     */
    public Integer getStarCount() {
        return starCount;
    }

    /**
     * 设置收藏次数
     *
     * @param starCount 收藏次数
     */
    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    /**
     * 获取标签
     *
     * @return tag - 标签
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置标签
     *
     * @param tag 标签
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}