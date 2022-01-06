package com.hzdl.cms.service.impl;

import java.util.List;
import com.hzdl.common.utils.DateUtils;
import com.hzdl.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hzdl.cms.mapper.CategoryMapper;
import com.hzdl.cms.domain.Category;
import com.hzdl.cms.service.ICategoryService;

/**
 * 栏目管理Service业务层处理
 *
 * @author hzdl
 * @date 2020-08-20
 */
@Service
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询栏目管理
     *
     * @param categoryId 栏目管理ID
     * @return 栏目管理
     */
    @Override
    public Category selectCategoryById(Long categoryId)
    {
        return categoryMapper.selectCategoryById(categoryId);
    }

    /**
     * 查询栏目管理列表
     *
     * @param category 栏目管理
     * @return 栏目管理
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 查询栏目列表接口
     *
     * @param category 栏目管理
     * @return 栏目集合
     */
    @Override
    public List<Category> selectCategoryListVo(Category category) {
        return categoryMapper.selectCategoryListVo(category);
    }

    /**
     * 新增栏目管理
     *
     * @param category 栏目管理
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        category.setCreateBy(SecurityUtils.getUsername());
        category.setCreateTime(DateUtils.getNowDate());
        category.setUpdateBy(SecurityUtils.getUsername());
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改栏目管理
     *
     * @param category 栏目管理
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        category.setUpdateBy(SecurityUtils.getUsername());
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除栏目管理
     *
     * @param categoryIds 需要删除的栏目管理ID
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(Long[] categoryIds)
    {
        return categoryMapper.deleteCategoryByIds(categoryIds);
    }

    /**
     * 删除栏目管理信息
     *
     * @param categoryId 栏目管理ID
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long categoryId)
    {
        return categoryMapper.deleteCategoryById(categoryId);
    }
}
