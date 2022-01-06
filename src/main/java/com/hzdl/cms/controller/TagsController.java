package com.hzdl.cms.controller;

import com.hzdl.cms.domain.Tags;
import com.hzdl.cms.service.ITagsService;
import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.common.utils.poi.ExcelUtil;
import com.hzdl.enums.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签管理Controller
 *
 * @author hzdl
 * @date 2020-08-24
 */
@RestController
@RequestMapping("/cms/tags")
@Api(tags = "内容管理")
public class TagsController extends BaseController {
    @Autowired
    private ITagsService tagsService;

    /**
     * 查询标签管理列表
     */
    @ApiOperation("查询标签管理列表")
    @PreAuthorize("@ss.hasPermi('cms:tags:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tags tags) {
        startPage();
        List<Tags> list = tagsService.selectTagsList(tags);
        return getDataTable(list);
    }

    /**
     * 查询所有标签
     */
    @ApiOperation("查询所有标签")
    @GetMapping("/list/all")
    public TableDataInfo listAll(@ApiParam(value = "标签名称") String tagName,
                                 @ApiParam(value = "标签类型") String tagType,
                                 @ApiParam(value = "标签名称") Integer status) {
        Tags tags = new Tags();
        tags.setTagName(tagName);
        tags.setTagType(tagType);
        tags.setStatus(status);
        List<Tags> list = tagsService.selectTagsList(tags);
        return getDataTable(list);
    }

    /**
     * 导出标签管理列表
     */
    @ApiOperation("导出标签管理列表")
    @PreAuthorize("@ss.hasPermi('cms:tags:export')")
    @Log(title = "标签管理" , businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Tags tags) {
        List<Tags> list = tagsService.selectTagsList(tags);
        ExcelUtil<Tags> util = new ExcelUtil<Tags>(Tags.class);
        return util.exportExcel(list, "tags");
    }

    /**
     * 获取标签管理详细信息
     */
    @ApiOperation("获取标签管理详细信息")
    @PreAuthorize("@ss.hasPermi('cms:tags:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId) {
        return AjaxResult.success(tagsService.selectTagsById(tagId));
    }

    /**
     * 新增标签管理
     */
    @ApiOperation("新增标签管理")
    @PreAuthorize("@ss.hasPermi('cms:tags:add')")
    @Log(title = "标签管理" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tags tags) {
        return toAjax(tagsService.insertTags(tags));
    }

    /**
     * 修改标签管理
     */
    @ApiOperation("修改标签管理")
    @PreAuthorize("@ss.hasPermi('cms:tags:edit')")
    @Log(title = "标签管理" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tags tags) {
        return toAjax(tagsService.updateTags(tags));
    }

    /**
     * !删除标签管理
     */
    @ApiOperation("!删除标签管理")
    @PreAuthorize("@ss.hasPermi('cms:tags:remove')")
    @Log(title = "标签管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds) {
        return toAjax(tagsService.deleteTagsByIds(tagIds));
    }

    /**
     * 启用标签
     */
    @ApiOperation("启用标签")
    @PreAuthorize("@ss.hasPermi('cms:tags:edit')")
    @Log(title = "标签管理" , businessType = BusinessType.UPDATE)
    @PutMapping("/enable/{tagId}")
    public AjaxResult enable(@PathVariable("tagId") Long tagId) {
        Tags tags = new Tags();
        tags.setTagId(tagId);
        tags.setStatus(StatusEnum.ENABLE.getCode());
        return toAjax(tagsService.updateTags(tags));
    }

    /**
     * 停用标签
     */
    @ApiOperation("停用标签")
    @PreAuthorize("@ss.hasPermi('cms:tags:edit')")
    @Log(title = "标签管理" , businessType = BusinessType.UPDATE)
    @PutMapping("/disable/{tagId}")
    public AjaxResult disable(@PathVariable("tagId") Long tagId) {
        Tags tags = new Tags();
        tags.setTagId(tagId);
        tags.setStatus(StatusEnum.DISABLE.getCode());
        return toAjax(tagsService.updateTags(tags));
    }
}
