package com.hzdl.cms.controller;

import com.hzdl.cms.domain.Rotation;
import com.hzdl.cms.service.IRotationService;
import com.hzdl.cms.vo.RotationVo;
import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
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
 * 轮播图管理Controller
 *
 * @author hzdl
 * @date 2020-08-28
 */
@Api(tags="轮播图管理")
@RestController
@RequestMapping("/cms/rotation")
public class RotationController extends BaseController {
    @Autowired
    private IRotationService rotationService;

    /**
     * !查询轮播图管理列表
     */
    @ApiOperation("!查询轮播图管理列表")
    @PreAuthorize("@ss.hasPermi('cms:rotation:list')")
    @GetMapping("/list")
    public TableDataInfo list(Rotation rotation) {
        startPage();
        List<Rotation> list = rotationService.selectRotationList(rotation);
        return getDataTable(list);
    }

    /**
     * 查询轮播图管理列表Vo
     */
    @ApiOperation("查询轮播图管理列表Vo")
    @PreAuthorize("@ss.hasPermi('cms:rotation:list')")
    @GetMapping("/list/vo")
    public TableDataInfo listVo(Rotation rotation) {
        startPage();
        List<RotationVo> list = rotationService.selectRotationListVo(rotation);
        return getDataTable(list);
    }

    /**
     * 导出轮播图管理列表
     */
    @ApiOperation("导出轮播图管理列表")
    @PreAuthorize("@ss.hasPermi('cms:rotation:export')")
    @Log(title = "轮播图管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Rotation rotation) {
        List<Rotation> list = rotationService.selectRotationList(rotation);
        ExcelUtil<Rotation> util = new ExcelUtil<Rotation>(Rotation.class);
        return util.exportExcel(list, "rotation");
    }

    /**
     * !获取轮播图管理详细信息
     */
    @ApiOperation("!获取轮播图管理详细信息")
    @PreAuthorize("@ss.hasPermi('cms:rotation:query')")
    @GetMapping(value = "/{rotationId}")
    public AjaxResult getInfo(@PathVariable("rotationId") Long rotationId) {
        return AjaxResult.success(rotationService.selectRotationById(rotationId));
    }

    /**
     * 查询轮播图Vo
     *
     * @param rotationId 轮播图管理ID
     * @return 轮播图Vo
     */
    @ApiOperation("查询轮播图Vo")
    @PreAuthorize("@ss.hasPermi('cms:rotation:query')")
    @GetMapping(value = "/vo/{rotationId}")
    public AjaxResult selectRotationVoById(@PathVariable("rotationId") Long rotationId) {
        return AjaxResult.success(rotationService.selectRotationVoById(rotationId));
    }

    /**
     * 新增轮播图管理
     */
    @ApiOperation("新增轮播图管理")
    @PreAuthorize("@ss.hasPermi('cms:rotation:add')")
    @Log(title = "轮播图管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Rotation rotation) {
        return toAjax(rotationService.insertRotation(rotation));
    }

    /**
     * 修改轮播图管理
     */
    @ApiOperation("修改轮播图管理")
    @PreAuthorize("@ss.hasPermi('cms:rotation:edit')")
    @Log(title = "轮播图管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Rotation rotation) {
        return toAjax(rotationService.updateRotation(rotation));
    }

    /**
     * !删除轮播图管理
     */
    @ApiOperation("!删除轮播图管理")
    @PreAuthorize("@ss.hasPermi('cms:rotation:remove')")
    @Log(title = "轮播图管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rotationIds}")
    public AjaxResult remove(@PathVariable Long[] rotationIds) {
        return toAjax(rotationService.deleteRotationByIds(rotationIds));
    }

    /**
     * 启用轮播图
     */
    @ApiOperation("启用轮播图")
    @PreAuthorize("@ss.hasPermi('cms:rotation:edit')")
    @Log(title = "栏目管理" , businessType = BusinessType.UPDATE)
    @PutMapping("/enable/{rotationId}")
    public AjaxResult enable(@PathVariable("rotationId") Long rotationId) {
        Rotation rotation = new Rotation();
        rotation.setRotationId(rotationId);
        rotation.setStatus(StatusEnum.ENABLE.getCode());
        return toAjax(rotationService.updateRotation(rotation));
    }

    /**
     * 停用轮播图
     */
    @ApiOperation("停用轮播图")
    @PreAuthorize("@ss.hasPermi('cms:rotation:edit')")
    @Log(title = "栏目管理" , businessType = BusinessType.UPDATE)
    @PutMapping("/disable/{rotationId}")
    public AjaxResult disable(@PathVariable("rotationId") Long rotationId) {
        Rotation rotation = new Rotation();
        rotation.setRotationId(rotationId);
        rotation.setStatus(StatusEnum.DISABLE.getCode());
        return toAjax(rotationService.updateRotation(rotation));
    }
}
