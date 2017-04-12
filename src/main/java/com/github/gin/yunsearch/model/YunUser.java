package com.github.gin.yunsearch.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 *  @author GinPonson
 */
@Entity
@Table(name = "yun_user")
public class YunUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "follow_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date followTime;

    @Column(name = "username")
    private String username;

    @Column(name = "uk")
    private Long uk;

    @Column(name = "pubshare_count")
    private Integer pubshareCount;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Version
    private Integer version ;

    @Column(name = "follow_crawled")
    private boolean followCrawled;

    @Column(name = "pubshare_crawled")
    private boolean pubshareCrawled;

    @Column(name = "fans_crawled")
    private boolean fansCrawled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUk() {
        return uk;
    }

    public void setUk(Long uk) {
        this.uk = uk;
    }

    public Integer getPubshareCount() {
        return pubshareCount;
    }

    public void setPubshareCount(Integer pubshareCount) {
        this.pubshareCount = pubshareCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isFollowCrawled() {
        return followCrawled;
    }

    public void setFollowCrawled(boolean followCrawled) {
        this.followCrawled = followCrawled;
    }

    public boolean isPubshareCrawled() {
        return pubshareCrawled;
    }

    public void setPubshareCrawled(boolean pubshareCrawled) {
        this.pubshareCrawled = pubshareCrawled;
    }

    public boolean isFansCrawled() {
        return fansCrawled;
    }

    public void setFansCrawled(boolean fansCrawled) {
        this.fansCrawled = fansCrawled;
    }

}
