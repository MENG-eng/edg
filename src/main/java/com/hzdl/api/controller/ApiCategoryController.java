package com.hzdl.api.controller;

import com.hzdl.cms.domain.Category;
import com.hzdl.cms.service.ICategoryService;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 栏目接口Controller
 *
 * @author hzdl
 * @date 2020-08-20
 */
@Api(tags = "博客接口")
@RestController
@RequestMapping("/api/cms/category")
public class ApiCategoryController extends BaseController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * !获取所有栏目
     */
    @ApiOperation("!获取所有栏目")
    @GetMapping("/list/all")
    public AjaxResult listVo(@ApiParam(value = "机构id", required = true) @NotNull(message = "机构id不能为空！") Long deptId) {
        List<Category> list = categoryService.selectCategoryListVo(null);
        return AjaxResult.success(list);
    }
}
