package com.hzdl.edg.controller;

import cn.hutool.core.date.DateUtil;
import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.common.utils.SecurityUtils;
import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.EvidenceNode;
import com.hzdl.edg.domain.block.CommonResponse;
import com.hzdl.edg.domain.vo.*;
import com.hzdl.edg.service.IEvidenceNodeService;
import com.hzdl.edg.service.IEvidenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 存证Controller
 *
 * @author hzdl
 * @date 2020-08-17
 */
@RestController
@RequestMapping("/edg/evidence")
@Api(tags = "存证管理")
public class EvidenceController extends BaseController {
    @Autowired
    private IEvidenceService evidenceService;
    @Autowired
    private IEvidenceNodeService evidenceNodeService;
    /*
    * !根据id查询详情
    * */
    @ApiOperation("!根据id查询详情")
    @PreAuthorize("@ss.hasPermi('edg:evidence:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(evidenceService.selectEvidenceById(id));
    }

    /**
     * 存证详情
     * @param evidenceNodeId
     * @return AjaxResult
     */
    @ApiOperation("存证详情")
    @PreAuthorize("@ss.hasPermi('edg:evidence:content')")
    @GetMapping("/detail/{evidenceNodeId}")
    public AjaxResult detail(@PathVariable Long evidenceNodeId) {
        List<EvidenceNodePreviewVO> list = evidenceService.detail(evidenceNodeId);
        return AjaxResult.success(list);
    }
    /*
    * !校验存证名称是否重复
    * */
    @ApiOperation("!校验存证名称是否重复")
    @GetMapping("valid/{fileName}")
    public AjaxResult validFileName(@PathVariable String fileName) {
        return AjaxResult.success(evidenceService.validFileName(fileName));
    }

    @GetMapping("/verify/{evidenceId}")
    @ApiOperation("校验存证树")
    public AjaxResult verify(@PathVariable Long evidenceId) {
        CommonResponse result = evidenceService.verify(evidenceId);
        return AjaxResult.success(result);
    }

    @ApiOperation("创建存证")
    @PreAuthorize("@ss.hasPermi('edg:evidence:create')")
    @Log(title = "存证", businessType = BusinessType.INSERT)
    @PostMapping("create")
    public AjaxResult create(@RequestBody EvidenceVO evidence) {
        return evidenceService.create(evidence);
    }

    @ApiOperation("复制存证")
    @PreAuthorize("@ss.hasPermi('edg:evidence:copy')")
    @Log(title = "存证", businessType = BusinessType.INSERT)
    @PostMapping("copy")
    public AjaxResult copy(Long evidenceNodeId) {
        return evidenceService.copy(evidenceNodeId);
    }

    @ApiOperation("增加存证")
    @PreAuthorize("@ss.hasPermi('edg:evidence:add')")
    @Log(title = "存证", businessType = BusinessType.INSERT)
    @PostMapping("add")
    public AjaxResult add(@RequestBody EvidenceAddVO vo) {
        return evidenceService.add(vo);
    }

    @ApiOperation("修改存证")
    @PreAuthorize("@ss.hasPermi('edg:evidence:modify')")
    @Log(title = "存证", businessType = BusinessType.INSERT)
    @PostMapping("modify")
    public AjaxResult modify(@RequestBody EvidenceModifyVO vo) {
        return evidenceService.modify(vo);
    }

    /**
     * !删除存证
     */
    @ApiOperation("!删除存证")
    @PreAuthorize("@ss.hasPermi('edg:evidence:remove')")
    @Log(title = "存证", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(evidenceService.deleteEvidenceByIds(ids));
    }


    @GetMapping("/personal/list/latest")
    @ApiOperation("最近存证列表")
    public AjaxResult personalListLatest() {
        List<EvidenceListVO> list = evidenceService.personalEvidenceList();
        return AjaxResult.success(list);
    }

    @GetMapping("/personal/node/list/latest")
    @ApiOperation("最近操作记录")
    public AjaxResult personalEvidenceNodeListLatest() {
        List<EvidenceNodeVO> list = evidenceService.personalEvidenceNodeListLatest();
        return AjaxResult.success(list);
    }

    @GetMapping("/all/node/list/latest")
    @ApiOperation("最近操作记录（所有人）")
    public AjaxResult allEvidenceNodeListLatest() {
        List<EvidenceNodeVO> list = evidenceService.allEvidenceNodeListLatest();
        return AjaxResult.success(list);
    }

    @GetMapping("/personal/node/list")
    @ApiOperation("更多操作记录")
    public TableDataInfo personalEvidenceNodeList(@ApiParam("开始日期") @RequestParam(value = "startTime", required = false) Date beginDate,
                                                  @ApiParam("结束日期") @RequestParam(value = "endTime", required = false) Date endDate,
                                                  @ApiParam("操作类型") String optType,
                                                  @ApiParam("文件名") @RequestParam(value = "fileName", required = false) String fileName,
                                                  @ApiParam("存证类型") @RequestParam(value = "evidenceType", required = false) Long evidenceType) {
        if (beginDate != null) {
            beginDate = DateUtil.beginOfDay(beginDate);
        }
        if (endDate != null) {
            endDate = DateUtil.endOfDay(endDate);
        }
        List<EvidenceNodeVO> list = evidenceService.personalEvidenceNodeList(beginDate, endDate, optType, fileName, evidenceType);
        return getDataTable(list);
    }
    /*
    * !更多个人存证分页
    * */
    @GetMapping("/personal/list")
    @ApiOperation("!更多个人存证分页")
    public TableDataInfo personalList(Evidence evidence) {
        evidence.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        List<Evidence> list = evidenceService.selectEvidenceList(evidence);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('edg:evidence:list')")
    @GetMapping("/list/my")
    @ApiOperation("我的存证分页")
    public TableDataInfo listMy(Evidence evidence) {
        List<EvidenceListVO> list = evidenceService.myEvidenceList(evidence);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('edg:evidence:list')")
    @GetMapping("/list/copy")
    @ApiOperation("我复制的存证分页")
    public TableDataInfo listCopy(Evidence evidence) {
        List<EvidenceListVO> list = evidenceService.copyEvidenceList(evidence);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('edg:evidence:list')")
    @GetMapping("/list/trace")
    @ApiOperation("溯源中心存证分页")
    public TableDataInfo traceCenterList(Evidence evidence) {
        List<Evidence> list = evidenceService.traceCenterList(evidence);
        return getDataTable(list);
    }

    @GetMapping("/file/size/statistics")
    @ApiOperation("数据存量统计（GB）")
    public AjaxResult fileSize() {
        double fileSize = evidenceService.fileSize();
        return AjaxResult.success(fileSize);
    }

    @GetMapping("/month/statistics")
    @ApiOperation("各能源数据本月存证统计")
    public AjaxResult evidenceMonthStatistics() {
        List<EvidenceMonthStatisticsVO> list = evidenceService.evidenceMonthStatistics();
        return AjaxResult.success(list);
    }

    @GetMapping("/exception/statistics")
    @ApiOperation("存证量和异常量统计")
    public AjaxResult evidenceAndExceptionStatistics() {
        List<EvidenceAndExceptionStatisticsVO> list = evidenceService.evidenceAndExceptionStatistics();
        return AjaxResult.success(list);
    }

    @GetMapping("/type/statistics")
    @ApiOperation("各类型存证占比统计")
    public AjaxResult evidenceTypeStatistics() {
        List<StatisticsVO> list = evidenceService.evidenceTypeStatistics();
        return AjaxResult.success(list);
    }

    @GetMapping("/user/activity")
    @ApiOperation("用户活跃度")
    public AjaxResult userActivity() {
        double activity = evidenceService.userActivity();
        return AjaxResult.success(activity);
    }

    @GetMapping("/statistics")
    @ApiOperation("存证统计")
    public AjaxResult evidenceStatistics() {
        EvidenceStatisticsVO vo = evidenceService.evidenceStatistics();
        return AjaxResult.success(vo);
    }

    @GetMapping("/quote/top10/all/statistics")
    @ApiOperation("所有存证被引用次数TOP10")
    public AjaxResult quoteTop10AllStatistics() {
        List<StatisticsVO> list = evidenceNodeService.quoteTop10AllStatistics();
        return AjaxResult.success(list);
    }

    @GetMapping("/quote/top10/statistics")
    @ApiOperation("个人存证被引用次数TOP10")
    public AjaxResult quoteTop10PersonalStatistics() {
        List<StatisticsVO> list = evidenceNodeService.quoteTop10PersonalStatistics();
        return AjaxResult.success(list);
    }

    @GetMapping("/quote/statistics")
    @ApiOperation("存证被引用占比统计")
    public AjaxResult quoteStatistics() {
        List<StatisticsVO> list = evidenceNodeService.quoteStatistics();
        return AjaxResult.success(list);
    }

    @GetMapping("/opt/type/statistics")
    @ApiOperation("操作类型统计")
    public AjaxResult optTypeStatistics() {
        OptTypeStatisticsVO vo = evidenceNodeService.optTypeStatistics();
        return AjaxResult.success(vo);
    }
    @PreAuthorize("@ss.hasPermi('edg:evidence:list')")
    @GetMapping("/list")
    @ApiOperation("存证查询分页")
    public TableDataInfo list(Evidence evidence) {
        List<Evidence> list = evidenceService.selectEvidenceList(evidence);
        return getDataTable(list);
    }
    /**
     * !根据存证id,查看所有节点
     */
    @PreAuthorize("@ss.hasPermi('edg:evidence:view')")
    @GetMapping("/view/{evidenceNodeId}")
    @ApiOperation("!存证查看所有节点")
    public AjaxResult view(@PathVariable Long evidenceNodeId) {
        List<EvidenceNode> list = evidenceNodeService.selectByEvidenceId(evidenceNodeId);
        return AjaxResult.success(list);
    }

    @PreAuthorize("@ss.hasPermi('edg:evidence:list')")
    @GetMapping("/count/type")
    @ApiOperation("根据一级存证类型统计存证类型")
    public AjaxResult countByType() {
        List<CommonCountVo> list = evidenceService.countByType();
        return AjaxResult.success(list);
    }

    @PreAuthorize("@ss.hasPermi('edg:evidence:list')")
    @GetMapping("/count/type/copy")
    @ApiOperation("根据一级存证类型统计存证共享率")
    public AjaxResult countCopyByType() {
        List<EvidenceSharedCountVo> list = evidenceService.countCopyByType();
        return AjaxResult.success(list);
    }

    @PreAuthorize("@ss.hasPermi('edg:evidence:list')")
    @GetMapping("/count/refresh")
    @ApiOperation("根据一级存证类型统计存证刷新率")
    public AjaxResult countRefreshRate() {
        List<CommonCountVo> list = evidenceService.countRefreshRate();
        return AjaxResult.success(list);
    }

}
