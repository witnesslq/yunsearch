package com.github.gin.yunsearch.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author GinPonson
 */
@Entity
@Table(name = "yun_data")
public class YunData {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "share_id")
    @JSONField(name = "share_id")
    private Long shareId;

    @Column(name = "data_id")
    @JSONField(name = "data_id")
    private Long dataId;

    @Column(name = "share_name")
    @JSONField(name = "share_name")
    private String shareName;

    @Column(name = "uk")
    @JSONField(name = "uk")
    private Long uk;

    @Column(name = "description")
    @JSONField(name = "description")
    private String description;

    @Column(name = "share_time")
    @JSONField(name = "share_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shareTime;

    @Column(name = "avatar_url")
    @JSONField(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "update_time")
    @JSONField(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Version
    private Integer version;

    @Column(name = "is_single_share")
    private boolean isSingleShare;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public Long getUk() {
        return uk;
    }

    public void setUk(Long uk) {
        this.uk = uk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isSingleShare() {
        return isSingleShare;
    }

    public void setSingleShare(boolean singleShare) {
        isSingleShare = singleShare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YunData data = (YunData) o;
        return Objects.equals(shareId, data.shareId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shareId);
    }
}
