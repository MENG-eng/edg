package com.hzdl.cms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hzdl.cms.mapper.TagsMapper;
import com.hzdl.cms.domain.Tags;
import com.hzdl.cms.service.ITagsService;

/**
 * 标签管理Service业务层处理
 *
 * @author hzdl
 * @date 2020-08-24
 */
@Service
public class TagsServiceImpl implements ITagsService
{
    @Autowired
    private TagsMapper tagsMapper;

    /**
     * 查询标签管理
     *
     * @param tagId 标签管理ID
     * @return 标签管理
     */
    @Override
    public Tags selectTagsById(Long tagId)
    {
        return tagsMapper.selectTagsById(tagId);
    }

    /**
     * 查询标签管理列表
     *
     * @param tags 标签管理
     * @return 标签管理
     */
    @Override
    public List<Tags> selectTagsList(Tags tags)
    {
        return tagsMapper.selectTagsList(tags);
    }

    /**
     * 新增标签管理
     *
     * @param tags 标签管理
     * @return 结果
     */
    @Override
    public int insertTags(Tags tags)
    {
        return tagsMapper.insertTags(tags);
    }

    /**
     * 修改标签管理
     *
     * @param tags 标签管理
     * @return 结果
     */
    @Override
    public int updateTags(Tags tags)
    {
        return tagsMapper.updateTags(tags);
    }

    /**
     * 批量删除标签管理
     *
     * @param tagIds 需要删除的标签管理ID
     * @return 结果
     */
    @Override
    public int deleteTagsByIds(Long[] tagIds)
    {
        return tagsMapper.deleteTagsByIds(tagIds);
    }

    /**
     * 删除标签管理信息
     *
     * @param tagId 标签管理ID
     * @return 结果
     */
    @Override
    public int deleteTagsById(Long tagId)
    {
        return tagsMapper.deleteTagsById(tagId);
    }
}
