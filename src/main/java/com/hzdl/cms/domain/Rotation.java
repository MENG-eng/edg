package com.hzdl.cms.domain;

import com.hzdl.common.annotation.Excel;
import com.hzdl.common.core.domain.BaseEntity;

/**
 * 轮播图管理对象 cms_rotation
 *
 * @author hzdl
 * @date 2020-08-28
 */
public class Rotation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 轮播图id
     */
    private Long rotationId;

    /**
     * 轮播图名称
     */
    @Excel(name = "轮播图名称")
    private String rotationName;

    /**
     * 轮播图url
     */
    @Excel(name = "轮播图url")
    private String rotationUrl;

    /**
     * 关联对象id
     */
    @Excel(name = "关联对象id")
    private String relateObjId;

    /**
     * 所属机构id
     */
    @Excel(name = "所属机构id")
    private Long deptId;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Integer sort;

    /**
     * 状态（0：正常，1：停用）
     */
    @Excel(name = "状态", readConverterExp = "0=：正常，1：停用")
    private Integer status;

    public void setRotationId(Long rotationId) {
        this.rotationId = rotationId;
    }

    public Long getRotationId() {
        return rotationId;
    }

    public void setRotationName(String rotationName) {
        this.rotationName = rotationName;
    }

    public String getRotationName() {
        return rotationName;
    }

    public void setRotationUrl(String rotationUrl) {
        this.rotationUrl = rotationUrl;
    }

    public String getRotationUrl() {
        return rotationUrl;
    }

    public void setRelateObjId(String relateObjId) {
        this.relateObjId = relateObjId;
    }

    public String getRelateObjId() {
        return relateObjId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

}
