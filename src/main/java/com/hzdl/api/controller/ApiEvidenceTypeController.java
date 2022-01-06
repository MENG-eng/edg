package com.hzdl.api.controller;

import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.edg.domain.EvidenceType;
import com.hzdl.edg.service.IEvidenceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 存证类型Controller
 *
 * @author hzdl
 * @date 2020-08-17
 */
@Api(tags = "博客接口")
@RestController
@RequestMapping("/api/edg/type")
public class ApiEvidenceTypeController extends BaseController {
    @Autowired
    private IEvidenceTypeService evidenceTypeService;

    /**
     * 获取所有存证类型
     */
    @ApiOperation("获取一级级存证类型")
    @GetMapping("/list/one")
    public AjaxResult listAll() {
        EvidenceType evidenceType = new EvidenceType();
        evidenceType.setEnable(true);
        evidenceType.setParentId(0l);
        List<EvidenceType> list = evidenceTypeService.selectEvidenceTypeList(evidenceType);
        return AjaxResult.success(list);
    }

}
