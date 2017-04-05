package com.github.gin.yunsearch.agama.entity;

import com.github.gin.agama.annotation.Json;
import com.github.gin.agama.annotation.Prey;
import com.github.gin.agama.site.entity.JsonEntity;

import java.util.Date;

/**
 * @author GinPonson
 * @since 2017/4/3
 */
@Json("$.fans_list")
@Prey(matchUrl = "http://pan.baidu.com/pcloud/friend/getfanslist")
public class Fans extends JsonEntity {
    @Json("$.avatar_url")
    private String avatarUrl;

    @Json("$.follow_time")
    private Date followTime;

    @Json("$.fans_uname")
    private String username;

    @Json("$.fans_uk")
    private long uk;

    @Json("$.follow_count")
    private int followCount;

    @Json("$.fans_count")
    private int fansCount;

    @Json("$.pubshare_count")
    private int pubshareCount;

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

    public long getUk() {
        return uk;
    }

    public void setUk(long uk) {
        this.uk = uk;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getPubshareCount() {
        return pubshareCount;
    }

    public void setPubshareCount(int pubshareCount) {
        this.pubshareCount = pubshareCount;
    }

}
