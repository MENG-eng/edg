package com.hzdl.edg.domain;

import com.hzdl.common.annotation.Excel;
import com.hzdl.common.core.domain.BaseEntity;

/**
 * 存证异常对象 edg_evidence_exception
 *
 * @author hzdl
 * @date 2020-08-19
 */
public class EvidenceException extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 存证id
     */
    @Excel(name = "存证id")
    private Long evidenceId;

    /**
     * 文件名
     */
    @Excel(name = "文件名")
    private String fileName;

    /**
     * 存证类型编码
     */
    private Long evidenceType;

    /**
     * 存证类型名称
     */
    @Excel(name = "存证类型名称")
    private String evidenceTypeName;

    /**
     * 操作类型
     */
    @Excel(name = "操作类型")
    private String optType;

    /**
     * 异常描述
     */
    @Excel(name = "异常描述")
    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEvidenceId(Long evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Long getEvidenceId() {
        return evidenceId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setEvidenceType(Long evidenceType) {
        this.evidenceType = evidenceType;
    }

    public Long getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceTypeName(String evidenceTypeName) {
        this.evidenceTypeName = evidenceTypeName;
    }

    public String getEvidenceTypeName() {
        return evidenceTypeName;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOptType() {
        return optType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
