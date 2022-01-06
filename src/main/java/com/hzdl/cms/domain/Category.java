package com.hzdl.cms.domain;

import com.hzdl.common.annotation.Excel;
import com.hzdl.common.core.domain.TreeEntity;

/**
 * 栏目管理对象 cms_category
 *
 * @author hzdl
 * @date 2020-08-20
 */
public class Category extends TreeEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    @Excel(name = "分类名称")
    private String categoryName;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Long sort;

    /**
     * 分类描述
     */
    @Excel(name = "分类描述")
    private String description;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private Integer status;

    /**
     * 机构id
     */
    @Excel(name = "机构id")
    private Long deptId;



    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", sort=" + sort +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", deptId=" + deptId +
                '}';
    }
}
