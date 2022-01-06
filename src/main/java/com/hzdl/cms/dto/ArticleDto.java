package com.hzdl.cms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class ArticleDto implements Serializable {

    private static final long serialVersionUID = 2199591120588683092L;

    /** 标题 */
    @ApiModelProperty(value = "文章标题")
    private String title;

    /** 文章栏目 */
    @ApiModelProperty(value = "文章栏目")
    private Long categoryId;

    /** 文章标签 */
    @ApiModelProperty(value = "文章标签")
    private String tagIds;

    /** 机构名称 */
    @ApiModelProperty(value = "机构名称")
    private String deptName;

    /** 存证名称 */
    @ApiModelProperty(value = "存证名称")
    private String evidenceName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName;
    }
}
