package com.hzdl.cms.controller;

import com.hzdl.cms.domain.Category;
import com.hzdl.cms.service.ICategoryService;
import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.common.utils.poi.ExcelUtil;
import com.hzdl.enums.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 栏目管理Controller
 *
 * @author hzdl
 * @date 2020-08-20
 */
@Api(tags="栏目管理")
@RestController
@RequestMapping("/cms/category")
public class CategoryController extends BaseController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询栏目管理列表
     */
    @ApiOperation("查询栏目管理列表")
    @PreAuthorize("@ss.hasAnyPermi('cms:category:list, cms:article:list')")
    @GetMapping("/list")
    public AjaxResult list(Category category) {
        List<Category> list = categoryService.selectCategoryList(category);
        return AjaxResult.success(list);
    }

    /**
     * 查询所有栏目不包话自己
     */
    @ApiOperation("查询所有栏目不包话自己")
    @PreAuthorize("@ss.hasPermi('cms:category:list')")
    @GetMapping("/list/exclude/{id}")
    public AjaxResult listExclude(@PathVariable Long id) {
        List<Category> listCategory = categoryService.selectCategoryList(null);
        listCategory.removeIf(category -> category.getCategoryId().longValue() == id);
        return AjaxResult.success(listCategory);
    }

    /**
     * 导出栏目管理列表
     */
    @ApiOperation("导出栏目列表")
    @PreAuthorize("@ss.hasPermi('cms:category:export')")
    @Log(title = "栏目管理" , businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Category category) {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        return util.exportExcel(list, "category");
    }

    /**
     * !获取栏目管理详细信息
     */
    @ApiOperation("!获取栏目管理详细信息")
    @PreAuthorize("@ss.hasPermi('cms:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
        return AjaxResult.success(categoryService.selectCategoryById(categoryId));
    }

    /**
     * 新增栏目管理
     */
    @ApiOperation("新增栏目管理")
    @PreAuthorize("@ss.hasPermi('cms:category:add')")
    @Log(title = "栏目管理" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category) {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改栏目管理
     */
    @ApiOperation("修改栏目管理")
    @PreAuthorize("@ss.hasPermi('cms:category:edit')")
    @Log(title = "栏目管理" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category) {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * !删除栏目管理
     */
    @ApiOperation("!删除栏目管理")
    @PreAuthorize("@ss.hasPermi('cms:category:remove')")
    @Log(title = "栏目管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds) {
        return toAjax(categoryService.deleteCategoryByIds(categoryIds));
    }

    /**
     * 启用栏目
     */
    @ApiOperation("启用栏目")
    @PreAuthorize("@ss.hasPermi('cms:category:edit')")
    @Log(title = "栏目管理" , businessType = BusinessType.UPDATE)
    @PutMapping("/enable/{categoryId}")
    public AjaxResult enable(@PathVariable("categoryId") Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setStatus(StatusEnum.ENABLE.getCode());
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 停用栏目
     */
    @ApiOperation("停用栏目")
    @PreAuthorize("@ss.hasPermi('cms:category:edit')")
    @Log(title = "栏目管理" , businessType = BusinessType.UPDATE)
    @PutMapping("/disable/{categoryId}")
    public AjaxResult disable(@PathVariable("categoryId") Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setStatus(StatusEnum.DISABLE.getCode());
        return toAjax(categoryService.updateCategory(category));
    }
}
