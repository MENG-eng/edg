package com.hzdl.cms.service;

import java.util.List;
import com.hzdl.cms.domain.Tags;

/**
 * 标签管理Service接口
 * 
 * @author hzdl
 * @date 2020-08-24
 */
public interface ITagsService 
{
    /**
     * 查询标签管理
     * 
     * @param tagId 标签管理ID
     * @return 标签管理
     */
    Tags selectTagsById(Long tagId);

    /**
     * 查询标签管理列表
     * 
     * @param tags 标签管理
     * @return 标签管理集合
     */
    List<Tags> selectTagsList(Tags tags);

    /**
     * 新增标签管理
     * 
     * @param tags 标签管理
     * @return 结果
     */
    int insertTags(Tags tags);

    /**
     * 修改标签管理
     * 
     * @param tags 标签管理
     * @return 结果
     */
    int updateTags(Tags tags);

    /**
     * 批量删除标签管理
     * 
     * @param tagIds 需要删除的标签管理ID
     * @return 结果
     */
    int deleteTagsByIds(Long[] tagIds);

    /**
     * 删除标签管理信息
     * 
     * @param tagId 标签管理ID
     * @return 结果
     */
    int deleteTagsById(Long tagId);
}
