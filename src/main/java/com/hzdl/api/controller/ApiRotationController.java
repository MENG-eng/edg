package com.hzdl.api.controller;

import com.hzdl.cms.domain.Rotation;
import com.hzdl.cms.service.IRotationService;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 轮播图接口Controller
 *
 * @author hzdl
 * @date 2020-08-28
 */
@Api(tags = "博客接口")
@RestController
@RequestMapping("/api/cms/rotation")
public class ApiRotationController extends BaseController {
    @Autowired
    private IRotationService rotationService;

    /**
     * 获取所有的轮播图
     */
    @ApiOperation("获取所有的轮播图")
    @GetMapping("/list/all")
    public TableDataInfo list() {
        List<Rotation> list = rotationService.selectRotationList(null);
        return getDataTable(list);
    }
}
