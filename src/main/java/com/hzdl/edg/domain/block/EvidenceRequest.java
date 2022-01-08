package com.hzdl.edg.domain.block;

import java.io.Serializable;
import java.util.List;

/**
 * 存证请求数据
 *
 * @author 34277
 * @date 2020/9/8
 */
public class EvidenceRequest implements Serializable {
    /**
     * 文件名|版本号|账户名|时间戳
     */
    private String keyId;
    /**
     * 版本号
     */
    private String version;
    /**
     * 用户名
     */
    private String username;
    /**
     * 操作类型
     */
    private String operationType;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 存证名称
     */
    private String fileName;
    /**
     * 数据文件大小
     */
    private String fileSize;
    /**
     * 文件哈希
     */
    private String fileHash;
    /**
     * 附件总哈希值
     */
    private String attachmentTotalHash;
    /**
     * 相对路径
     */
    private String uri;
    /**
     * 父节点keyID
     */
    private String parentKeyId;
    /**
     * 多个附件路径
     */
    private List<String> attachmentFileUris;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getAttachmentTotalHash() {
        return attachmentTotalHash;
    }

    public void setAttachmentTotalHash(String attachmentTotalHash) {
        this.attachmentTotalHash = attachmentTotalHash;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getParentKeyId() {
        return parentKeyId;
    }

    public void setParentKeyId(String parentKeyId) {
        this.parentKeyId = parentKeyId;
    }

    public List<String> getAttachmentFileUris() {
        return attachmentFileUris;
    }

    public void setAttachmentFileUris(List<String> attachmentFileUris) {
        this.attachmentFileUris = attachmentFileUris;
    }

    @Override
    public String toString() {
        return "EvidenceRequest{" +
                "keyId='" + keyId + '\'' +
                ", version='" + version + '\'' +
                ", username='" + username + '\'' +
                ", operationType='" + operationType + '\'' +
                ", dataType='" + dataType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileHash='" + fileHash + '\'' +
                ", uri='" + uri + '\'' +
                ", parentKeyId='" + parentKeyId + '\'' +
                ", attachmentFileUris=" + attachmentFileUris +
                '}';
    }
}
