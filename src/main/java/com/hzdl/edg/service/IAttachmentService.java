package com.hzdl.edg.service;

import java.util.List;

import com.hzdl.edg.domain.Attachment;

/**
 * 附件Service接口
 *
 * @author hzdl
 * @date 2020-08-17
 */
public interface IAttachmentService {
    /**
     * 查询附件
     *
     * @param id 附件ID
     * @return 附件
     */
    Attachment selectAttachmentById(Long id);

    /**
     * 查询附件列表
     *
     * @param attachment 附件
     * @return 附件集合
     */
    List<Attachment> selectAttachmentList(Attachment attachment);

    /**
     * 根据业务id查询附件列表
     *
     * @param objectId 业务id
     * @return 附件集合
     */
    List<Attachment> selectAttachmentListByObjectId(Long objectId);

    /**
     * 新增附件
     *
     * @param attachment 附件
     * @return 结果
     */
    int insertAttachment(Attachment attachment);

    /**
     * 修改附件
     *
     * @param attachment 附件
     * @return 结果
     */
    int updateAttachment(Attachment attachment);

    /**
     * 批量删除附件
     *
     * @param ids 需要删除的附件ID
     * @return 结果
     */
    int deleteAttachmentByIds(Long[] ids);

    /**
     * 删除附件信息
     *
     * @param id 附件ID
     * @return 结果
     */
    int deleteAttachmentById(Long id);

    /**
     * 批量保存附件
     *
     * @param attachments 附件数据
     * @return 保存结果
     * @author 34277
     * @date 2020/9/2
     */
    int batchInsertAttachment(List<Attachment> attachments);

}
