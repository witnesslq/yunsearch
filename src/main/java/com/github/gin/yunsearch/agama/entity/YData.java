package com.github.gin.yunsearch.agama.entity;

import com.github.gin.agama.annotation.Json;
import com.github.gin.agama.site.entity.AgamaEntity;

import java.util.Date;

/**
 * @author GinPonson
 */
public class YData extends AgamaEntity {

    @Json("$.shareid")
    private long shareId;

    @Json("$.data_id")
    private long dataId;

    @Json("$.title")
    private String shareName;

    @Json("$.uk")
    private long uk;

    @Json("$.desc")
    private String description;

    @Json("$.feed_time")
    private Date shareTime;

    @Json("$.avatar_url")
    private String avatarUrl;

    public long getShareId() {
        return shareId;
    }

    public void setShareId(long shareId) {
        this.shareId = shareId;
    }

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public long getUk() {
        return uk;
    }

    public void setUk(long uk) {
        this.uk = uk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
