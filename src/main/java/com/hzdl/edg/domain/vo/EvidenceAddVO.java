package com.hzdl.edg.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 存证增加VO
 *
 * @author hzdl
 * @date 2020-08-19
 */
@ApiModel("存证增加")
public class EvidenceAddVO implements Serializable {

    @ApiModelProperty("存证节点id")
    private Long evidenceNodeId;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("增加的附件")
    private List<EvidenceVO.Attachment> attachments;
    @ApiModelProperty("附件总Hash")
    private String attachmentTotalHash;

    public Long getEvidenceNodeId() {
        return evidenceNodeId;
    }

    public void setEvidenceNodeId(Long evidenceNodeId) {
        this.evidenceNodeId = evidenceNodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EvidenceVO.Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<EvidenceVO.Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getAttachmentTotalHash() {
        return attachmentTotalHash;
    }

    public void setAttachmentTotalHash(String attachmentTotalHash) {
        this.attachmentTotalHash = attachmentTotalHash;
    }
}
