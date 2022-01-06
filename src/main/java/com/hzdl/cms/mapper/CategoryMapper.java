package com.hzdl.cms.mapper;

import java.util.List;
import com.hzdl.cms.domain.Category;

/**
 * 栏目管理Mapper接口
 *
 * @author hzdl
 * @date 2020-08-20
 */
public interface CategoryMapper
{
    /**
     * 查询栏目管理
     *
     * @param categoryId 栏目管理ID
     * @return 栏目管理
     */
    Category selectCategoryById(Long categoryId);

    /**
     * 查询栏目管理列表
     *
     * @param category 栏目管理
     * @return 栏目管理集合
     */
    List<Category> selectCategoryList(Category category);

    /**
     * 查询栏目列表接口
     *
     * @param category 栏目管理
     * @return 栏目集合
     */
    List<Category> selectCategoryListVo(Category category);

    /**
     * 新增栏目管理
     *
     * @param category 栏目管理
     * @return 结果
     */
    int insertCategory(Category category);

    /**
     * 修改栏目管理
     *
     * @param category 栏目管理
     * @return 结果
     */
    int updateCategory(Category category);

    /**
     * 删除栏目管理
     *
     * @param categoryId 栏目管理ID
     * @return 结果
     */
    int deleteCategoryById(Long categoryId);

    /**
     * 批量删除栏目管理
     *
     * @param categoryIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCategoryByIds(Long[] categoryIds);
}
