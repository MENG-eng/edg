package com.hzdl.edg.controller;

import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.common.utils.poi.ExcelUtil;
import com.hzdl.edg.domain.Attachment;
import com.hzdl.edg.service.IAttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 附件Controller
 *
 * @author hzdl
 * @date 2020-08-17
 */
@RestController
@RequestMapping("/edg/attachment")
@Api(tags = "附件管理")
public class AttachmentController extends BaseController {
    @Autowired
    private IAttachmentService attachmentService;

    /**
     * !根据业务id查询附件
     */
    @ApiOperation("!根据业务id查询附件")
    @GetMapping("/list/{objectId}")
    public AjaxResult list(@PathVariable Long objectId) {
        List<Attachment> list = attachmentService.selectAttachmentListByObjectId(objectId);
        return AjaxResult.success(list);
    }

    /**
     * 查询附件列表
     */
    @ApiOperation("查询附件列表")
    @PreAuthorize("@ss.hasPermi('edg:attachment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Attachment attachment) {
        startPage();
        List<Attachment> list = attachmentService.selectAttachmentList(attachment);
        return getDataTable(list);
    }

    /**
     * 导出附件列表
     */
    @ApiOperation("导出附件列表")
    @PreAuthorize("@ss.hasPermi('edg:attachment:export')")
    @Log(title = "附件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Attachment attachment) {
        List<Attachment> list = attachmentService.selectAttachmentList(attachment);
        ExcelUtil<Attachment> util = new ExcelUtil<Attachment>(Attachment.class);
        return util.exportExcel(list, "attachment");
    }

    /**
     * 获取附件详细信息
     */
    @ApiOperation("获取附件详细信息")
    @PreAuthorize("@ss.hasPermi('edg:attachment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(attachmentService.selectAttachmentById(id));
    }

    /**
     * 新增附件
     */
    @ApiOperation("新增附件")
    @PreAuthorize("@ss.hasPermi('edg:attachment:add')")
    @Log(title = "附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Attachment attachment) {
        return toAjax(attachmentService.insertAttachment(attachment));
    }

    /**
     * 修改附件
     */
    @ApiOperation("修改附件")
    @PreAuthorize("@ss.hasPermi('edg:attachment:edit')")
    @Log(title = "附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Attachment attachment) {
        return toAjax(attachmentService.updateAttachment(attachment));
    }

    /**
     * !删除附件
     */
    @ApiOperation("!删除附件")
    @PreAuthorize("@ss.hasPermi('edg:attachment:remove')")
    @Log(title = "附件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(attachmentService.deleteAttachmentByIds(ids));
    }
}
