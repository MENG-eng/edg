package com.hzdl.edg.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 列表存证对象
 *
 * @author hzdl
 * @date 2020-08-19
 */
@ApiModel("列表存证对象")
public class EvidenceListVO implements Serializable {

    @ApiModelProperty("存证id")
    private Long id;
    @ApiModelProperty("存证名称")
    private String fileName;
    @ApiModelProperty("存证类型名称")
    private String evidenceTypeName;
    @ApiModelProperty("存证描述")
    private String description;
    @ApiModelProperty("存证热度")
    private Long heat;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("起始节点id")
    private String originNodeId;
    @ApiModelProperty("起始版本")
    private String originVersion;
    @ApiModelProperty("附件总Hash")
    private String attachmentTotalHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEvidenceTypeName() {
        return evidenceTypeName;
    }

    public void setEvidenceTypeName(String evidenceTypeName) {
        this.evidenceTypeName = evidenceTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getHeat() {
        return heat;
    }

    public void setHeat(Long heat) {
        this.heat = heat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOriginNodeId() {
        return originNodeId;
    }

    public void setOriginNodeId(String originNodeId) {
        this.originNodeId = originNodeId;
    }

    public String getOriginVersion() {
        return originVersion;
    }

    public void setOriginVersion(String originVersion) {
        this.originVersion = originVersion;
    }

    public String getAttachmentTotalHash() {
        return attachmentTotalHash;
    }

    public void setAttachmentTotalHash(String attachmentTotalHash) {
        this.attachmentTotalHash = attachmentTotalHash;
    }
}
