package com.hzdl.edg.service.impl;

import java.util.List;

import com.hzdl.common.utils.DateUtils;
import com.hzdl.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hzdl.edg.mapper.AttachmentMapper;
import com.hzdl.edg.domain.Attachment;
import com.hzdl.edg.service.IAttachmentService;

/**
 * 附件Service业务层处理
 *
 * @author hzdl
 * @date 2020-08-17
 */
@Service
public class AttachmentServiceImpl implements IAttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 查询附件
     *
     * @param id 附件ID
     * @return 附件
     */
    @Override
    public Attachment selectAttachmentById(Long id) {
        return attachmentMapper.selectAttachmentById(id);
    }

    /**
     * 查询附件列表
     *
     * @param attachment 附件
     * @return 附件
     */
    @Override
    public List<Attachment> selectAttachmentList(Attachment attachment) {
        return attachmentMapper.selectAttachmentList(attachment);
    }

    @Override
    public List<Attachment> selectAttachmentListByObjectId(Long objectId) {
        Attachment attachment = new Attachment();
        attachment.setObjectId(objectId);
        return attachmentMapper.selectAttachmentList(attachment);
    }

    /**
     * 新增附件
     *
     * @param attachment 附件
     * @return 结果
     */
    @Override
    public int insertAttachment(Attachment attachment) {
        attachment.setCreateBy(SecurityUtils.getUsername());
        attachment.setCreateTime(DateUtils.getNowDate());
        return attachmentMapper.insertAttachment(attachment);
    }

    /**
     * 修改附件
     *
     * @param attachment 附件
     * @return 结果
     */
    @Override
    public int updateAttachment(Attachment attachment) {
        attachment.setUpdateBy(SecurityUtils.getUsername());
        attachment.setUpdateTime(DateUtils.getNowDate());
        return attachmentMapper.updateAttachment(attachment);
    }

    /**
     * 批量删除附件
     *
     * @param ids 需要删除的附件ID
     * @return 结果
     */
    @Override
    public int deleteAttachmentByIds(Long[] ids) {
        return attachmentMapper.deleteAttachmentByIds(ids);
    }

    /**
     * 删除附件信息
     *
     * @param id 附件ID
     * @return 结果
     */
    @Override
    public int deleteAttachmentById(Long id) {
        return attachmentMapper.deleteAttachmentById(id);
    }

    @Override
    public int batchInsertAttachment(List<Attachment> attachments) {
        return attachmentMapper.batchInsertAttachment(attachments);
    }
}
