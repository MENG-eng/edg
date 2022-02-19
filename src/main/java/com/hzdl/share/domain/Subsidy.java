package com.hzdl.share.domain;

import com.hzdl.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 补贴对象 share_subsidy
 *
 * @author hzdl
 * @date 2022-02-18
 */
public class Subsidy extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 补贴id
     */
    private Long subsidyId;

    /**
     * 共享文件名称
     */
    private String fileName;

    /**
     * 共享描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 共享状态
     * 0审核中，1审核完成
     */
    private int shareState;

    /**
     * 申请方昵称
     */
    private String applyName;

    /**
     * 共享方昵称
     */
    private String shareName;

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    /**
     * 审核人昵称
     */
    private String checkName;

    public Long getSubsidyId() {
        return subsidyId;
    }

    public void setSubsidyId(Long subsidyId) {
        this.subsidyId = subsidyId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getShareState() {
        return shareState;
    }

    public void setShareState(int shareState) {
        this.shareState = shareState;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }
}
