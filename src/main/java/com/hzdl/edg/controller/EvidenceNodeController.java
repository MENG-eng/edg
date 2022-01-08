package com.hzdl.edg.controller;

import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
import com.hzdl.common.core.redis.RedisCache;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.common.utils.poi.ExcelUtil;
import com.hzdl.constant.EvidenceConstant;
import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.EvidenceNode;
import com.hzdl.edg.domain.block.CommonResponse;
import com.hzdl.edg.domain.vo.EvidenceNodeAttachmentVO;
import com.hzdl.edg.domain.vo.EvidenceNodeVO;
import com.hzdl.edg.service.IEvidenceNodeService;
import com.hzdl.edg.service.IEvidenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存证节点Controller
 *
 * @author hzdl
 * @date 2020-08-17
 */
@RestController
@RequestMapping("/edg/node")
@Api(tags = "存证节点")
public class EvidenceNodeController extends BaseController {
    @Autowired
    private IEvidenceNodeService evidenceNodeService;
    @Autowired
    private IEvidenceService evidenceService;
    @Autowired
    private RedisCache redisCache;

    /**
     * 管理员查看存证
     */
    @PreAuthorize("@ss.hasPermi('edg:evidence:view')")
    @GetMapping("/admin/evidence/{evidenceId}")
    @ApiOperation("管理员查看存证")
    public AjaxResult adminView(@PathVariable Long evidenceId) {
        Evidence evidence = evidenceService.selectEvidenceById(evidenceId);
        List<EvidenceNode> list = evidenceNodeService.selectByEvidenceId(evidenceId);
        Map<String, Object> map = new HashMap<String, Object>(3) {
            {
                put("evidence", evidence);
                put("nodes", list);
                put("lastTraceInfo", redisCache.getCacheObject(EvidenceConstant.CACHE_LAST_TRACE_EVIDENCE + evidenceId));
            }
        };
        return AjaxResult.success(map);
    }

    /**
     * 普通用户查看存证
     */
    @PreAuthorize("@ss.hasPermi('edg:evidence:view')")
    @GetMapping("/evidence/{evidenceId}")
    @ApiOperation("普通用户查看存证")
    public AjaxResult userView(@PathVariable Long evidenceId) {
        Evidence evidence = evidenceService.selectEvidenceById(evidenceId);
        List<EvidenceNode> list = evidenceNodeService.selectByEvidenceIdWithoutCopyLeafNode(evidenceId);
        Map<String, Object> map = new HashMap<String, Object>(2) {
            {
                put("evidence", evidence);
                put("nodes", list);
            }
        };
        return AjaxResult.success(map);
    }

    /**
     * 校验
     */
    @GetMapping("/verify/{evidenceNodeId}")
    @ApiOperation("校验")
    public AjaxResult verify(@PathVariable Long evidenceNodeId) {
        CommonResponse result = evidenceNodeService.verify(evidenceNodeId);
        return AjaxResult.success(result);
    }

    @GetMapping("/last/verify/info/{evidenceNodeId}")
    @ApiOperation("最后一次校验信息")
    public AjaxResult getLastVerifyInfo(@PathVariable Long evidenceNodeId) {
        Object object = redisCache.getCacheObject(EvidenceConstant.CACHE_LAST_TRACE_NODE + evidenceNodeId);
        return AjaxResult.success(object);
    }

    /**
     * 预览存证节点
     */
    @PreAuthorize("@ss.hasPermi('edg:detail:preview')")
    @GetMapping("/view/{evidenceNodeId}")
    @ApiOperation("预览存证节点")
    public AjaxResult view(@PathVariable Long evidenceNodeId) {
        List<EvidenceNode> list = evidenceNodeService.view(evidenceNodeId);
        return AjaxResult.success(list);
    }

    /**
     * 查询存证节点列表
     */
    @GetMapping("/list")
    @ApiOperation("查询存证节点列表")
    public TableDataInfo list(EvidenceNode evidenceNode) {
        startPage();
        List<EvidenceNodeVO> list = evidenceNodeService.selectEvidenceNodeVOList(evidenceNode);
        return getDataTable(list);
    }

    /**
     * 导出存证节点列表
     */
    @ApiOperation("导出存证节点列表")
    @PreAuthorize("@ss.hasPermi('edg:node:export')")
    @Log(title = "存证节点", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EvidenceNode evidenceNode) {
        List<EvidenceNode> list = evidenceNodeService.selectEvidenceNodeList(evidenceNode);
        ExcelUtil<EvidenceNode> util = new ExcelUtil<EvidenceNode>(EvidenceNode.class);
        return util.exportExcel(list, "node");
    }

    /**
     * !获取存证节点详细信息
     */
    @ApiOperation("!获取存证节点详细信息")
    @PreAuthorize("@ss.hasPermi('edg:node:query')")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Long id) {
        return AjaxResult.success(evidenceNodeService.selectEvidenceNodeById(id));
    }

    /**
     * !获取附件信息
     */
    @GetMapping("/attachment/{id}")
    @ApiOperation("!获取附件信息")
    public AjaxResult getAttachmentByEvidenceNodeId(@PathVariable("id") Long id) {
        EvidenceNodeAttachmentVO vo = evidenceNodeService.getAttachmentByEvidenceNodeId(id);
        return AjaxResult.success(vo);
    }

    /**
     * 新增存证节点
     */
    @ApiOperation("新增存证节点")
    @PreAuthorize("@ss.hasPermi('edg:node:add')")
    @Log(title = "存证节点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EvidenceNode evidenceNode) {
        return toAjax(evidenceNodeService.insertEvidenceNode(evidenceNode));
    }

    /**
     * 修改存证节点
     */
    @ApiOperation("修改存证节点")
    @PreAuthorize("@ss.hasPermi('edg:node:edit')")
    @Log(title = "存证节点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EvidenceNode evidenceNode) {
        return toAjax(evidenceNodeService.updateEvidenceNode(evidenceNode));
    }

    /**
     * !删除存证节点
     */
    @ApiOperation("!删除存证节点")
    @PreAuthorize("@ss.hasPermi('edg:node:remove')")
    @Log(title = "存证节点", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(evidenceNodeService.deleteEvidenceNodeByIds(ids));
    }
}
