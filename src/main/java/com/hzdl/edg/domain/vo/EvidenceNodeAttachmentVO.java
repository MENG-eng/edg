package com.hzdl.edg.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 存证节点文件回显VO
 *
 * @author hzdl
 * @date 2020-09-01
 */
@ApiModel("存证节点文件回显VO")
public class EvidenceNodeAttachmentVO implements Serializable {

    @ApiModelProperty("数据文件")
    private EvidenceVO.Attachment dataFile;
    @ApiModelProperty("附件")
    private List<EvidenceVO.Attachment> attachments;

    public EvidenceVO.Attachment getDataFile() {
        return dataFile;
    }

    public void setDataFile(EvidenceVO.Attachment dataFile) {
        this.dataFile = dataFile;
    }

    public List<EvidenceVO.Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<EvidenceVO.Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "EvidenceNodeAttachmentVO{" +
                "dataFile=" + dataFile +
                ", attachments=" + attachments +
                '}';
    }
}
