package com.hzdl.cms.domain;

import com.hzdl.common.annotation.Excel;

/**
 * 标签管理对象 cms_tags
 *
 * @author hzdl
 * @date 2020-08-24
 */
public class Tags
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long tagId;

    /** 标签类型，如s系统标签，p个人标签 */
    @Excel(name = "标签类型，如s系统标签，p个人标签")
    private String tagType;

    /** 谁增加的该标签 */
    private String userId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String tagName;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 状态 0正常 1停用 */
    @Excel(name = "状态 0正常 1停用")
    private Integer status;

    /** 链接 */
    private String url;

    /** 机构id */
    private Long deptId;

    public void setTagId(Long tagId)
    {
        this.tagId = tagId;
    }

    public Long getTagId()
    {
        return tagId;
    }
    public void setTagType(String tagType)
    {
        this.tagType = tagType;
    }

    public String getTagType()
    {
        return tagType;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setTagName(String tagName)
    {
        this.tagName = tagName;
    }

    public String getTagName()
    {
        return tagName;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "tagId=" + tagId +
                ", tagType='" + tagType + '\'' +
                ", userId='" + userId + '\'' +
                ", tagName='" + tagName + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", url='" + url + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}
