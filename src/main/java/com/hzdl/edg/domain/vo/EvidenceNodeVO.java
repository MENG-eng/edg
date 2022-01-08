package com.hzdl.edg.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 存证节点VO
 *
 * @author hzdl
 * @date 2020-09-01
 */
public class EvidenceNodeVO implements Serializable {

    /** 主键 */
    private Long nodeId;

    /** 存证id */
    private Long evidenceId;

    /** 操作类型（CREATE-新建；ADD-增加；MODIFY-修改；COPY-复制） */
    private String optType;

    /** 描述 */
    private String description;

    /** 文件大小（KB） */
    private Long totalSize;

    /** 版本号 */
    private String version;

    /** 操作人 */
    private String operator;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 存证类型名称
     */
    private String evidenceTypeName;

    /**
     * 创建时间
     * @return
     */
    private Date evidenceNodeCreateTime;

    /**
     * 附件总Hash
     */
    private String attachmentTotalHash;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Long evidenceId) {
        this.evidenceId = evidenceId;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public Date getEvidenceNodeCreateTime() {
        return evidenceNodeCreateTime;
    }

    public void setEvidenceNodeCreateTime(Date evidenceNodeCreateTime) {
        this.evidenceNodeCreateTime = evidenceNodeCreateTime;
    }

    public String getAttachmentTotalHash() {
        return attachmentTotalHash;
    }

    public void setAttachmentTotalHash(String attachmentTotalHash) {
        this.attachmentTotalHash = attachmentTotalHash;
    }

    @Override
    public String toString() {
        return "EvidenceNodeVO{" +
                "nodeId=" + nodeId +
                ", evidenceId=" + evidenceId +
                ", optType='" + optType + '\'' +
                ", description='" + description + '\'' +
                ", totalSize=" + totalSize +
                ", version='" + version + '\'' +
                ", operator='" + operator + '\'' +
                ", fileName='" + fileName + '\'' +
                ", evidenceTypeName='" + evidenceTypeName + '\'' +
                ", evidenceNodeCreateTime=" + evidenceNodeCreateTime +
                ", attachmentTotalHash='" + attachmentTotalHash + '\'' +
                '}';
    }
}
