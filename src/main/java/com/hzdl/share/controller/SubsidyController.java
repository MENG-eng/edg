package com.hzdl.share.controller;

import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.edg.domain.vo.EvidenceAddVO;
import com.hzdl.edg.domain.vo.EvidenceVO;
import com.hzdl.edg.service.IEvidenceNodeService;
import com.hzdl.edg.service.IEvidenceService;
import com.hzdl.share.domain.Electric;
import com.hzdl.share.domain.Subsidy;
import com.hzdl.share.domain.vo.SubsidyVO;
import com.hzdl.share.service.ISubsidyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 共享Controller
 *
 * @author hzdl
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/share/subsidy")
@Api(tags = "共享管理")
public class SubsidyController extends BaseController {
    @Autowired
    private ISubsidyService subsidyService;
    @Autowired
    private IEvidenceService evidenceService;
    @Autowired
    private IEvidenceNodeService evidenceNodeService;
    /**
     * 查询共享列表
     */
    @GetMapping("/list")
    @ApiOperation("共享存证分页")
    public TableDataInfo selectSubsidyList(Subsidy subsidy) {
        List<Subsidy> list = subsidyService.selectSubsidyList(subsidy);
        return getDataTable(list);
    }

    @ApiOperation("增加共享")
    //@PreAuthorize("@ss.hasPermi('edg:evidence:add')")
    @Log(title = "共享", businessType = BusinessType.INSERT)
    @PostMapping("insert")
    public AjaxResult insert(@RequestBody Subsidy subsidy) {
        return toAjax(subsidyService.insertSubsidy(subsidy));
    }

    @ApiOperation("创建共享存证")
    @Log(title = "存证", businessType = BusinessType.INSERT)
    @PostMapping("create")
    public AjaxResult create(@RequestBody SubsidyVO subsidy) {
        return subsidyService.create(subsidy);
    }
}
