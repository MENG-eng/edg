package com.hzdl.edg.controller;

import cn.hutool.core.date.DateUtil;
import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.common.utils.poi.ExcelUtil;
import com.hzdl.edg.domain.EvidenceException;
import com.hzdl.edg.service.IEvidenceExceptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 存证异常Controller
 *
 * @author hzdl
 * @date 2020-08-17
 */
@Api(tags="存证异常")
@RestController
@RequestMapping("/edg/exception")
public class EvidenceExceptionController extends BaseController
{
    @Autowired
    private IEvidenceExceptionService evidenceExceptionService;

    /**
     * 查询存证异常列表
     */
    @ApiOperation("查询存证异常列表")
    @PreAuthorize("@ss.hasPermi('edg:exception:list')")
    @GetMapping("/list")
    public TableDataInfo list(EvidenceException evidenceException,
                              @ApiParam("开始日期") @RequestParam(value = "startTime", required = false) Date beginDate,
                              @ApiParam("结束日期") @RequestParam(value = "endTime", required = false) Date endDate) {
        if (beginDate != null) {
            beginDate = DateUtil.beginOfDay(beginDate);
        }
        if (endDate != null) {
            endDate = DateUtil.endOfDay(endDate);
        }
        evidenceException.getParams().put("beginDate", beginDate);
        evidenceException.getParams().put("endDate", endDate);
        List<EvidenceException> list = evidenceExceptionService.selectEvidenceExceptionList(evidenceException);
        return getDataTable(list);
    }

    /**
     * 导出存证异常列表
     */
    @ApiOperation("导出存证异常列表")
    @PreAuthorize("@ss.hasPermi('edg:exception:export')")
    @Log(title = "存证异常", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EvidenceException evidenceException)
    {
        List<EvidenceException> list = evidenceExceptionService.selectEvidenceExceptionList(evidenceException);
        ExcelUtil<EvidenceException> util = new ExcelUtil<EvidenceException>(EvidenceException.class);
        return util.exportExcel(list, "exception");
    }

    /**
     * 获取存证异常详细信息
     */
    @ApiOperation("获取存证异常详细信息")
    @PreAuthorize("@ss.hasPermi('edg:exception:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(evidenceExceptionService.selectEvidenceExceptionById(id));
    }

    /**
     * 新增存证异常
     */
    @ApiOperation("新增存证异常")
    @PreAuthorize("@ss.hasPermi('edg:exception:add')")
    @Log(title = "存证异常", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EvidenceException evidenceException)
    {
        return toAjax(evidenceExceptionService.insertEvidenceException(evidenceException));
    }

    /**
     * 修改存证异常
     */
    @ApiOperation("修改存证异常")
    @PreAuthorize("@ss.hasPermi('edg:exception:edit')")
    @Log(title = "存证异常", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EvidenceException evidenceException)
    {
        return toAjax(evidenceExceptionService.updateEvidenceException(evidenceException));
    }

    /**
     * !删除存证异常
     */
    @ApiOperation("!删除存证异常")
    @PreAuthorize("@ss.hasPermi('edg:exception:remove')")
    @Log(title = "存证异常", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(evidenceExceptionService.deleteEvidenceExceptionByIds(ids));
    }
}
