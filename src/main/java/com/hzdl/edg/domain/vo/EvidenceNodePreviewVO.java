package com.hzdl.edg.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 存证节点预览对象
 *
 * @author hzdl
 * @date 2020-09-01
 */
@ApiModel("存证节点预览对象")
public class EvidenceNodePreviewVO implements Serializable {

    @ApiModelProperty("节点id")
    private Long id;
    @ApiModelProperty("父节点id")
    private Long parentId;
    @ApiModelProperty("机构名称")
    private String deptName;
    @ApiModelProperty("操作类型（NEW-新建；ADD-增加；MODIFY-修改；COPY-复制）")
    private String optType;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("数据文件大小（KB）")
    private Long totalSize;
    @ApiModelProperty("版本号")
    private String version;
    @ApiModelProperty("区块链上的id")
    private String keyId;
    @ApiModelProperty("文件摘要")
    private String fileHash;
    @ApiModelProperty("链上摘要")
    private String blockHash;
    @ApiModelProperty("所在区块号")
    private Long blockHeight;
    @ApiModelProperty("交易id")
    private String txid;
    @ApiModelProperty("操作人昵称")
    private String operatorName;
    @ApiModelProperty("操作时间")
    private Date createTime;
    @ApiModelProperty("节点类型（0-主链；1-从链；2-原始）")
    private Integer nodeType;
    @ApiModelProperty("附件总Hash")
    private String attachmentTotalHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(Long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getAttachmentTotalHash() {
        return attachmentTotalHash;
    }

    public void setAttachmentTotalHash(String attachmentTotalHash) {
        this.attachmentTotalHash = attachmentTotalHash;
    }
}
