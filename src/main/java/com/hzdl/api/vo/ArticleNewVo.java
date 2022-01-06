package com.hzdl.api.vo;

import com.hzdl.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;

/**
 * 文章管理对象 cms_article
 *
 * @author hzdl
 * @date 2020-08-26
 */
@ApiModel
public class ArticleNewVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章id */
    private Long articleId;

    /** 标题 */
    private String title;

    /** 关键词 */
    private String keywords;

    /** 栏目名称 */
    private String categoryName;

    /** 标签名称 */
    private String tagNames;

    /** 所属机构名称 */
    private String deptName;

    /** 内容概述 */
    private String content;

    /** 浏览次数 */
    private Integer viewCount;

    /** 热度 */
    private Integer heat;

    public void setArticleId(Long articleId)
    {
        this.articleId = articleId;
    }

    public Long getArticleId()
    {
        return articleId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }

    public String getKeywords()
    {
        return keywords;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
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

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }
}
