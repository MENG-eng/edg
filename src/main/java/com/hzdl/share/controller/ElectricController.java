package com.hzdl.share.controller;

import cn.hutool.core.date.DateUtil;
import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.common.utils.poi.ExcelUtil;
import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.EvidenceException;
import com.hzdl.share.domain.Electric;
import com.hzdl.share.service.IElectricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 电量数据Controller
 *
 * @author hzdl
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/share/electric")
@Api(tags = "电量数据管理")
public class ElectricController extends BaseController {
    @Autowired
    private IElectricService electricService;
    /**
     * 查询电量数据列表
     */
    @GetMapping("/list")
    @ApiOperation("存证电量数据分页")
    public TableDataInfo list(Electric electric,
                              @ApiParam("开始日期") @RequestParam(value = "startTime", required = false) Date beginDate,
                              @ApiParam("结束日期") @RequestParam(value = "endTime", required = false) Date endDate) {
        if (beginDate != null) {
            beginDate = DateUtil.beginOfDay(beginDate);
        }
        if (endDate != null) {
            endDate = DateUtil.endOfDay(endDate);
        }
        electric.getParams().put("beginDate", beginDate);
        electric.getParams().put("endDate", endDate);
        List<Electric> list = electricService.selectElectricList(electric);
        return getDataTable(list);
    }

    /**
     * 导出电量数据列表
     */
    @ApiOperation("导出电量数据列表")
    //@PreAuthorize("@ss.hasPermi('edg:exception:export')")
    @Log(title = "电量数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Electric electric)
    {
        List<Electric> list = electricService.selectElectricList(electric);
        ExcelUtil<Electric> util = new ExcelUtil<Electric>(Electric.class);
        return util.exportExcel(list, "electric");
    }
}
