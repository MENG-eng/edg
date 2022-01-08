package com.hzdl.api.controller;

import com.hzdl.api.vo.DeptVO;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.domain.entity.SysDept;
import com.hzdl.system.service.ISysDeptService;
import com.hzdl.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门信息
 */
@Api(tags = "博客接口")
@RestController
@RequestMapping("/api/system/dept")
public class ApiSysDeptController extends BaseController {

    @Autowired
    private ISysDeptService deptService;
    /*@Autowired
    private ArticleServiceImpl articleServiceImpl;
    @Autowired
    private EvidenceServiceImpl evidenceServiceImpl;

    @ApiOperation("获取机构详情")
    @GetMapping("/info/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId) {
        //根据机构ID获取机构信息
        SysDept sysDept = deptService.selectDeptById(deptId);
        //根据机构ID获取该机构所发布的文章总数
        Integer articleCount = articleServiceImpl.countGroupByDept(deptId);
        //根据机构ID获取该机构所发布的存证总数
        Integer evidenceCount = evidenceServiceImpl.countGroupByDeptdeptId(deptId);
        Map<String, Object> params = new HashMap<String, Object>();
        //将文章总数，存证总数封装到map中
        params.put("articleCount", articleCount);
        params.put("evidenceCount", evidenceCount);
        sysDept.setParams(params);
        return AjaxResult.success(sysDept);
    }*/

    @ApiOperation("获取二级机构")
    @GetMapping("/list/second")
    public AjaxResult getDeptListSecond() {
        List<SysDept> list = deptService.getDeptListSecond();
        return AjaxResult.success(ModelUtil.mapList(list, new TypeToken<List<DeptVO>>() {}));
    }

}
