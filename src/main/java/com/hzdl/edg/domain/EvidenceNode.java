package com.hzdl.edg.domain;

import com.hzdl.common.annotation.Excel;
import com.hzdl.common.core.domain.BaseEntity;

/**
 * 存证节点对象 edg_evidence_node
 *
 * @author hzdl
 * @date 2020-09-01
 */
public class EvidenceNode extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 机构id
     */
    private Long deptId;

    /**
     * 机构名称
     */
    private String deptName;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 存证id
     */
    private Long evidenceId;

    /**
     * 存证状态（0-未存证；1-已存证）
     */
    @Excel(name = "存证状态", readConverterExp = "0=-未存证；1-已存证")
    private String evidenceStatus;

    /**
     * 操作类型（NEW-新建；ADD-增加；MODIFY-修改；COPY-复制）
     */
    @Excel(name = "操作类型")
    private String optType;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String description;

    /**
     * 文件大小（KB）
     */
    @Excel(name = "文件大小", readConverterExp = "K=B")
    private Long totalSize;

    /**
     * 版本号
     */
    @Excel(name = "版本号")
    private String version;

    /**
     * 区块链上的唯一Id
     */
    private String keyId;

    /**
     * 文件哈希值
     */
    @Excel(name = "文件哈希值")
    private String fileHash;

    /**
     * 附件总哈希值
     */
    private String attachmentTotalHash;

    /**
     * 区块哈希值
     */
    @Excel(name = "区块哈希值")
    private String blockHash;

    /**
     * 区块高度
     */
    @Excel(name = "区块高度")
    private Long blockHeight;

    /**
     * 交易id
     */
    private String txid;

    /**
     * 操作人
     */
    @Excel(name = "操作人")
    private Long operatorId;

    private String operatorName;

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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public Long getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Long evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getEvidenceStatus() {
        return evidenceStatus;
    }

    public void setEvidenceStatus(String evidenceStatus) {
        this.evidenceStatus = evidenceStatus;
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

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAttachmentTotalHash() {
        return attachmentTotalHash;
    }

    public void setAttachmentTotalHash(String attachmentTotalHash) {
        this.attachmentTotalHash = attachmentTotalHash;
    }
}
