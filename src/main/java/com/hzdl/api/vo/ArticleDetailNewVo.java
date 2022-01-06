package com.hzdl.api.vo;

import com.hzdl.edg.domain.EvidenceNode;
import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

/**
 * 文章管理对象 cms_article
 *
 * @author hzdl
 * @date 2020-08-26
 */
@ApiModel
public class ArticleDetailNewVo extends ArticleNewVo {
    private static final long serialVersionUID = 1861417830662447721L;

    /** 存证id */
    private Long evidenceId;

    /** 存证名称 */
    private String evidenceName;

    /** 存证类型名称 */
    private String evidenceTypeName;

    /** 存证溯源节点数据 */
    private List<EvidenceNode> evidenceNodeList;

    /** 存证创建时间 */
    private Date evidenceCreateTime;



    public Long getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Long evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName;
    }

    public String getEvidenceTypeName() {
        return evidenceTypeName;
    }

    public void setEvidenceTypeName(String evidenceTypeName) {
        this.evidenceTypeName = evidenceTypeName;
    }

    public List<EvidenceNode> getEvidenceNodeList() {
        return evidenceNodeList;
    }

    public void setEvidenceNodeList(List<EvidenceNode> evidenceNodeList) {
        this.evidenceNodeList = evidenceNodeList;
    }

    public Date getEvidenceCreateTime() {
        return evidenceCreateTime;
    }

    public void setEvidenceCreateTime(Date evidenceCreateTime) {
        this.evidenceCreateTime = evidenceCreateTime;
    }
}
