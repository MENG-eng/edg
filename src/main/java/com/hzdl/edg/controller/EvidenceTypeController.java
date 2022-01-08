package com.hzdl.edg.controller;

import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.common.utils.StringUtils;
import com.hzdl.common.utils.poi.ExcelUtil;
import com.hzdl.edg.domain.EvidenceType;
import com.hzdl.edg.service.IEvidenceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 存证类型Controller
 *
 * @author hzdl
 * @date 2020-08-17
 */
@RestController
@RequestMapping("/edg/type")
@Api(tags = "存证类型管理")
public class EvidenceTypeController extends BaseController {
    @Autowired
    private IEvidenceTypeService evidenceTypeService;

    /**
     * 查询存证类型（下拉列表用）
     */
    @ApiOperation("查询存证类型（下拉列表用）")
    @GetMapping("/select")
    public AjaxResult listSelect() {
        EvidenceType evidenceType = new EvidenceType();
        evidenceType.setEnable(true);
//        evidenceType.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        List<EvidenceType> list = evidenceTypeService.selectEvidenceTypeList(evidenceType);
        return AjaxResult.success(list);
    }

    /**
     * 查询所有存证类型
     */
    @ApiOperation("查询所有存证类型")
    @PreAuthorize("@ss.hasPermi('edg:type:list')")
    @GetMapping("/list")
    public AjaxResult listAll(EvidenceType evidenceType) {
        List<EvidenceType> list = evidenceTypeService.selectEvidenceTypeList(evidenceType);
        return AjaxResult.success(list);
    }

    /**
     * !查询所有存证类型（排除节点）
     */
    @ApiOperation("!查询所有存证类型（排除节点）")
    @PreAuthorize("@ss.hasPermi('edg:type:list')")
    @GetMapping("/list/exclude/{id}")
    public AjaxResult excludeChild(@PathVariable(value = "id", required = false) Long id) {
        List<EvidenceType> evidenceTypes = evidenceTypeService.selectEvidenceTypeList(new EvidenceType());
        evidenceTypes.removeIf(evidenceType -> evidenceType.getId().longValue() == id
                || ArrayUtils.contains(StringUtils.split(evidenceType.getAncestors(), ","), id + ""));
        return AjaxResult.success(evidenceTypes);
    }

    /**
     * 导出存证类型列表
     */
    @ApiOperation("导出存证类型列表")
    @PreAuthorize("@ss.hasPermi('edg:type:export')")
    @Log(title = "存证类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EvidenceType evidenceType) {
        List<EvidenceType> list = evidenceTypeService.selectEvidenceTypeList(evidenceType);
        ExcelUtil<EvidenceType> util = new ExcelUtil<EvidenceType>(EvidenceType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 获取存证类型详细信息
     */
    @ApiOperation("获取存证类型详细信息")
    @PreAuthorize("@ss.hasPermi('edg:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(evidenceTypeService.selectEvidenceTypeById(id));
    }

    /**
     * 新增存证类型
     */
    @ApiOperation("新增存证类型")
    @PreAuthorize("@ss.hasPermi('edg:type:add')")
    @Log(title = "存证类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EvidenceType evidenceType) {
        return toAjax(evidenceTypeService.insertEvidenceType(evidenceType));
    }

    /**
     * 修改存证类型
     */
    @ApiOperation("修改存证类型")
    @PreAuthorize("@ss.hasPermi('edg:type:edit')")
    @Log(title = "存证类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EvidenceType evidenceType) {
        return toAjax(evidenceTypeService.updateEvidenceType(evidenceType));
    }

    /**
     * 启用禁用存证类型
     */
    @ApiOperation("启用禁用存证类型")
    @PreAuthorize("@ss.hasPermi('edg:type:enable')")
    @Log(title = "启用禁用存证类型", businessType = BusinessType.UPDATE)
    @PutMapping("/enable/{id}")
    public AjaxResult enable(@PathVariable Long id) {
        EvidenceType evidenceType = evidenceTypeService.selectEvidenceTypeById(id);
        evidenceType.setEnable(!evidenceType.getEnable());
        return toAjax(evidenceTypeService.updateEvidenceType(evidenceType));
    }

    /**
     * !删除存证类型
     */
    @ApiOperation("!删除存证类型")
    @PreAuthorize("@ss.hasPermi('edg:type:remove')")
    @Log(title = "存证类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(evidenceTypeService.deleteEvidenceTypeByIds(ids));
    }

    /**
     * 根据一级存证类型统计部门
     */
    @ApiOperation("根据一级存证类型统计部门")
    @PreAuthorize("@ss.hasPermi('edg:type:list')")
    @Log(title = "存证类型", businessType = BusinessType.OTHER)
    @GetMapping("/count/dept")
    public AjaxResult countDeptByType()  {
        return AjaxResult.success(evidenceTypeService.countDeptByType());
    }

    /**
     * 根据一级存证类型按存证类型统计存证类型
     * 一级存证类型本身也计入
     */
    @ApiOperation("根据存证类型统计存证类型")
    @PreAuthorize("@ss.hasPermi('edg:type:list')")
    @Log(title = "存证类型", businessType = BusinessType.OTHER)
    @GetMapping("/count/type")
    public AjaxResult countByType()  {
        return AjaxResult.success(evidenceTypeService.countByType());
    }


}
