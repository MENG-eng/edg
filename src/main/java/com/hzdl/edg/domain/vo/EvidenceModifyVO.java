package com.hzdl.edg.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 存证增加VO
 *
 * @author hzdl
 * @date 2020-08-19
 */
@ApiModel("存证修改")
public class EvidenceModifyVO extends EvidenceAddVO {

    @ApiModelProperty("增加的数据文件")
    private EvidenceVO.Attachment dataFile;

    public EvidenceVO.Attachment getDataFile() {
        return dataFile;
    }

    public void setDataFile(EvidenceVO.Attachment dataFile) {
        this.dataFile = dataFile;
    }
}
