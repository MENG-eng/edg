package com.hzdl.cms.vo;

import com.hzdl.common.annotation.Excel;
import com.hzdl.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;

/**
 * 文章管理对象 cms_article
 *
 * @author hzdl
 * @date 2020-08-26
 */
@ApiModel
public class ArticleVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 关键词
     */
    @Excel(name = "关键词")
    private String keywords;

    /**
     * 文章栏目id
     */
    @Excel(name = "文章栏目id")
    private Long categoryId;

    /**
     * 文章栏目名称
     */
    @Excel(name = "文章栏目名称")
    private String categoryName;

    /**
     * 文章标签id
     */
    @Excel(name = "文章标签id")
    private String tagIds;

    /**
     * 文章标签名称
     */
    @Excel(name = "文章标签名称")
    private String tagNames;

    /**
     * 机构id
     */
    @Excel(name = "机构id")
    private Long deptId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    private String deptName;

    /**
     * 存证id
     */
    @Excel(name = "存证id")
    private Long evidenceId;

    /**
     * 存证名称
     */
    @Excel(name = "存证名称")
    private String evidenceName;

    /**
     * 内容概述
     */
    @Excel(name = "文章概述")
    private String content;

    /**
     * 浏览次数
     */
    @Excel(name = "浏览次数")
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
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
