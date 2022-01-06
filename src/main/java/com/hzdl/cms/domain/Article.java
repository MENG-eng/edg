package com.hzdl.cms.domain;

import com.hzdl.common.annotation.Excel;
import com.hzdl.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 文章管理对象 cms_article
 *
 * @author hzdl
 * @date 2020-08-26
 */
@ApiModel
public class Article extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章Id")
    private Long articleId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "文章标题", required = true)
    @NotBlank(message = "文章标题不能为空！")
    @Excel(name = "标题")
    private String title;

    /**
     * 关键词
     */
    @ApiModelProperty(value = "关键词")
    @Excel(name = "关键词")
    private String keywords;

    /**
     * 所属栏目
     */
    @ApiModelProperty(value = "栏目名称id", required = true)
    @NotNull(message = "栏目名称id不能为空！")
    @Excel(name = "栏目id")
    private Long categoryId;

    /**
     * 所属标签
     */
    @ApiModelProperty(value = "所属标签id，多个用‘，’分割", required = true)
    @NotBlank(message = "所属标签id不能为空！")
    @Excel(name = "所属标签")
    private String tagIds;

    /**
     * 所属机构id
     */
    @ApiModelProperty(hidden = true)
    @Excel(name = "所属机构id")
    private Long deptId;

    /**
     * 所属存证id
     */
    @ApiModelProperty(value = "所属存证id", required = true)
    @NotNull(message = "所属存证id不能为空！")
    @Excel(name = "所属存证id")
    private Long evidenceId;

    /**
     * 内容概述
     */
    @ApiModelProperty(value = "文章概述")
    @Excel(name = "文章概述")
    private String content;

    /**
     * 浏览次数
     */
    @Excel(name = "浏览次数")
    @ApiModelProperty(hidden = true)
    private Integer viewCount;

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setEvidenceId(Long evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Long getEvidenceId() {
        return evidenceId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

}
