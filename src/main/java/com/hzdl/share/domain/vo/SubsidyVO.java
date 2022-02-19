package com.hzdl.share.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 共享存证VO
 *
 * @author hzdl
 * @date 2022-02-19
 */
@ApiModel("共享存证")
public class SubsidyVO implements Serializable {
    @ApiModelProperty("文件名称")
    private String fileName;
    @ApiModelProperty("存证类型编码")
    private Long evidenceType;
    @ApiModelProperty("存证类型名称")
    private String evidenceTypeName;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("数据文件（Json）")
    private Attachment dataFile;
    @ApiModelProperty("附件")
    private List<Attachment> attachments;
    @ApiModelProperty("附件总Hash")
    private String attachmentTotalHash;
    @ApiModelProperty("共享方昵称")
    private String shareName;

    public static class Attachment implements Serializable {

        private String fileName;
        private String hash;
        private String uri;
        private Long size;

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(Long evidenceType) {
        this.evidenceType = evidenceType;
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

    public Attachment getDataFile() {
        return dataFile;
    }

    public void setDataFile(Attachment dataFile) {
        this.dataFile = dataFile;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getAttachmentTotalHash() {
        return attachmentTotalHash;
    }

    public void setAttachmentTotalHash(String attachmentTotalHash) {
        this.attachmentTotalHash = attachmentTotalHash;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }
}
