package com.hzdl.edg.domain;

import cn.hutool.core.date.DateUtil;
import com.hzdl.common.annotation.Excel;
import com.hzdl.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 存证对象 edg_evidence
 *
 * @author hzdl
 * @date 2020-08-19
 */
public class Evidence extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 文件名称
     */
    @Excel(name = "文件名称")
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
     * 描述
     */
    private String description;

    /**
     * 热度
     */
    @Excel(name = "热度")
    private Long heat;

    /**
     * 查询条件
     */
    private Date startTime;
    private Date endTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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

    public void setHeat(Long heat) {
        this.heat = heat;
    }

    public Long getHeat() {
        return heat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        if (startTime != null) {
            this.startTime = DateUtil.beginOfDay(startTime);
        }
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        if (endTime != null) {
            this.endTime = DateUtil.endOfDay(endTime);
        }
    }
}
