package com.hzdl.edg.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.domain.entity.SysDept;
import com.hzdl.common.core.domain.entity.SysUser;
import com.hzdl.common.core.page.PageDomain;
import com.hzdl.common.core.page.TableSupport;
import com.hzdl.common.core.redis.RedisCache;
import com.hzdl.common.utils.DateUtils;
import com.hzdl.common.utils.SecurityUtils;
import com.hzdl.common.utils.StringUtils;
import com.hzdl.common.utils.sql.SqlUtil;
import com.hzdl.constant.EvidenceConstant;
import com.hzdl.edg.domain.*;
import com.hzdl.edg.domain.block.*;
import com.hzdl.edg.domain.vo.*;
import com.hzdl.edg.mapper.EvidenceMapper;
import com.hzdl.edg.service.*;
import com.hzdl.util.EvidenceUtil;
import com.hzdl.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 存证Service业务层处理
 *
 * @author hzdl
 * @date 2020-08-17
 */
@Service
public class EvidenceServiceImpl implements IEvidenceService {
    @Autowired
    private EvidenceMapper evidenceMapper;
    @Autowired
    private IEvidenceNodeService evidenceNodeService;
    @Autowired
    private IAttachmentService attachmentService;
    @Autowired
    private IEvidenceTypeService evidenceTypeService;
    @Autowired
    private IEvidenceExceptionService evidenceExceptionService;
    @Autowired
    private RedisCache redisCache;


    /**
     * 查询存证
     *
     * @param id 存证ID
     * @return 存证
     */
    @Override
    public Evidence selectEvidenceById(Long id) {
        return evidenceMapper.selectEvidenceById(id);
    }

    /**
     * 查询存证列表
     *
     * @param evidence 存证
     * @return 存证
     */
    @Override
    public List<Evidence> selectEvidenceList(Evidence evidence) {
        Set<Long> types;
        if (evidence.getEvidenceType() != null) {
            types = evidenceTypeService.selectSonIds(evidence.getEvidenceType());
        } else {
            types = evidenceTypeService.selectIds();
        }

        evidence.getParams().put("types", types);
        startPage();
        return evidenceMapper.selectEvidenceList(evidence);
    }

    /**
     * 新增存证
     *
     * @param evidence 存证
     * @return 结果
     */
    @Override
    public int insertEvidence(Evidence evidence) {
        evidence.setCreateBy(SecurityUtils.getUsername());
        evidence.setCreateTime(DateUtils.getNowDate());
        return evidenceMapper.insertEvidence(evidence);
    }

    /**
     * 修改存证
     *
     * @param evidence 存证
     * @return 结果
     */
    @Override
    public int updateEvidence(Evidence evidence) {
        evidence.setUpdateBy(SecurityUtils.getUsername());
        evidence.setUpdateTime(DateUtils.getNowDate());
        return evidenceMapper.updateEvidence(evidence);
    }

    /**
     * 批量删除存证
     *
     * @param ids 需要删除的存证ID
     * @return 结果
     */
    @Override
    public int deleteEvidenceByIds(Long[] ids) {
        return evidenceMapper.deleteEvidenceByIds(ids);
    }

    /**
     * 删除存证信息
     *
     * @param id 存证ID
     * @return 结果
     */
    @Override
    public int deleteEvidenceById(Long id) {
        return evidenceMapper.deleteEvidenceById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult create(EvidenceVO vo) {
        boolean fileNameFlag = validFileName(vo.getFileName());
        if (fileNameFlag) {
            return AjaxResult.error("存证名称已存在");
        }
        String username = SecurityUtils.getUsername();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getUserId();
        String nickName = user.getNickName();
        Date nowDate = DateUtils.getNowDate();
        SysDept loginDept = SecurityUtils.getLoginDept();
        Long deptId = loginDept.getDeptId();
        String deptName = loginDept.getDeptName();
        // 数据文件
        EvidenceVO.Attachment dataFile = vo.getDataFile();
        String dataFileUri = dataFile.getUri();
        String fileName = dataFile.getFileName();
        Long fileSize = dataFile.getSize();
        String dataFileHash = dataFile.getHash();
        String attachmentTotalHash = vo.getAttachmentTotalHash();
        // 附件
        List<EvidenceVO.Attachment> attachmentList = vo.getAttachments();
        List<String> attachmentFileUris = new ArrayList<>(attachmentList.size());
        for (EvidenceVO.Attachment attachment : attachmentList) {
            attachmentFileUris.add(attachment.getUri());
        }
        EvidenceResponse evidenceResponse;
        try {
            // 存证
            EvidenceRequest evidenceRequest = new EvidenceRequest();
            evidenceRequest.setVersion(EvidenceConstant.ORIGINAL_VERSION);
            evidenceRequest.setUsername(username);
            evidenceRequest.setOperationType(EvidenceConstant.OPT_CREATE);
            evidenceRequest.setDataType(vo.getEvidenceTypeName());
            evidenceRequest.setFileName(vo.getFileName());
            evidenceRequest.setFileSize(fileSize + "");
            evidenceRequest.setFileHash(dataFileHash);
            evidenceRequest.setAttachmentTotalHash(attachmentTotalHash);
            evidenceRequest.setUri(dataFileUri);
            evidenceRequest.setAttachmentFileUris(attachmentFileUris);
            evidenceResponse = EvidenceUtil.doEvidence(evidenceRequest);
            if (!evidenceResponse.getSuccess()) {
                // 请求失败，记录异存证异常
                EvidenceException evidenceException = new EvidenceException();
                evidenceException.setFileName(vo.getFileName());
                evidenceException.setEvidenceType(vo.getEvidenceType());
                evidenceException.setEvidenceTypeName(vo.getEvidenceTypeName());
                evidenceException.setOptType(EvidenceConstant.OPT_CREATE);
                evidenceException.setDescription(evidenceResponse.getErr());
                evidenceExceptionService.insertEvidenceException(evidenceException);
                return AjaxResult.error(evidenceException.getDescription());
            }
        } catch (Exception e) {
            // 请求失败，记录异存证异常
            EvidenceException evidenceException = new EvidenceException();
            evidenceException.setFileName(vo.getFileName());
            evidenceException.setEvidenceType(vo.getEvidenceType());
            evidenceException.setEvidenceTypeName(vo.getEvidenceTypeName());
            evidenceException.setOptType(EvidenceConstant.OPT_CREATE);
            evidenceException.setDescription("数据上链失败");
            evidenceExceptionService.insertEvidenceException(evidenceException);
            return AjaxResult.error(evidenceException.getDescription());
        }

        Evidence evidence = ModelUtil.map(vo, Evidence.class);
        // 保存存证
        evidence.setDeptId(deptId);
        evidence.setCreateBy(username);
        evidence.setCreateTime(nowDate);
        evidence.setUpdateBy(username);
        evidence.setUpdateTime(nowDate);
        evidenceMapper.insertEvidence(evidence);

        // 保存节点
        EvidenceNode evidenceNode = new EvidenceNode();
        evidenceNode.setDeptId(deptId);
        evidenceNode.setDeptName(deptName);
        evidenceNode.setAncestors("0");
        evidenceNode.setEvidenceId(evidence.getId());
        evidenceNode.setOptType(EvidenceConstant.OPT_CREATE);
        evidenceNode.setDescription(evidence.getDescription());
        evidenceNode.setTotalSize(fileSize);
        evidenceNode.setVersion(EvidenceConstant.ORIGINAL_VERSION);
        evidenceNode.setKeyId(evidenceResponse.getKeyId());
        evidenceNode.setTxid(evidenceResponse.getTxId());
        evidenceNode.setFileHash(dataFileHash);
        evidenceNode.setAttachmentTotalHash(attachmentTotalHash);
        evidenceNode.setBlockHash(evidenceResponse.getBlockHash());
        evidenceNode.setBlockHeight(evidenceResponse.getBlockHeight());
        evidenceNode.setOperatorId(userId);
        evidenceNode.setOperatorName(nickName);
        evidenceNode.setCreateBy(username);
        evidenceNode.setCreateTime(nowDate);
        evidenceNode.setUpdateBy(username);
        evidenceNode.setUpdateTime(nowDate);
        evidenceNodeService.insertEvidenceNode(evidenceNode);

        // 原始文件
        Attachment dataFileAttachment = new Attachment();
        dataFileAttachment.setObjectId(evidenceNode.getId());

        dataFileAttachment.setFileName(fileName);
        dataFileAttachment.setBizType(EvidenceConstant.FILE_TYPE_ORIGINAL);
        dataFileAttachment.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
        dataFileAttachment.setFileSize(fileSize);
        dataFileAttachment.setFilePath(dataFileUri);
        dataFileAttachment.setFileHash(dataFileHash);
        dataFileAttachment.setCreateBy(username);
        dataFileAttachment.setCreateTime(nowDate);
        dataFileAttachment.setUpdateBy(username);
        dataFileAttachment.setUpdateTime(nowDate);

        List<Attachment> attachments = new ArrayList<>();
        attachments.add(dataFileAttachment);
        // 附件
        for (EvidenceVO.Attachment attachment : attachmentList) {
            Attachment tmp = new Attachment();
            tmp.setObjectId(evidenceNode.getId());
            String attachmentFileName = attachment.getFileName();
            tmp.setFileName(attachmentFileName);
            tmp.setBizType(EvidenceConstant.FILE_TYPE_ATTACHMENT);
            tmp.setFileType(attachmentFileName.substring(attachmentFileName.lastIndexOf(".") + 1));
            tmp.setFileSize(attachment.getSize());
            tmp.setFilePath(attachment.getUri());
            tmp.setFileHash(attachment.getHash());
            tmp.setCreateBy(username);
            tmp.setCreateTime(nowDate);
            tmp.setUpdateBy(username);
            tmp.setUpdateTime(nowDate);
            attachments.add(tmp);
        }

        if (attachments.size() > 0) {
            attachmentService.batchInsertAttachment(attachments);
        }

        return AjaxResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult copy(Long evidenceNodeId) {
        EvidenceNode evidenceNode = evidenceNodeService.selectEvidenceNodeById(evidenceNodeId);
        if (evidenceNode == null) {
            return AjaxResult.error("找不到当前节点");
        }
        if(evidenceNode.getOptType().equals("COPY")){
            return AjaxResult.error("复制节点不能再复制");
        }
        Evidence evidence = evidenceMapper.selectEvidenceById(evidenceNode.getEvidenceId());
        if (evidence == null) {
            return AjaxResult.error("找不到当前节点对应的存证");
        }
        SysDept loginDept = SecurityUtils.getLoginDept();
        Long deptId = loginDept.getDeptId();
        String version = evidenceNode.getVersion();
        EvidenceNode node = new EvidenceNode();
        node.setDeptId(deptId);
        node.setParentId(evidenceNodeId);
        boolean evidenceNodeExist = evidenceNodeService.evidenceNodeExist(node);
        if (evidenceNodeExist) {
            return AjaxResult.error("您已拥有该存证");
        }
        //溯源
        CommonResponse copyVerify = evidenceNodeService.copyVerify(evidenceNode);
        if (!copyVerify.getSuccess()) {
            return AjaxResult.error("", copyVerify.getMismatchedSet());
        }
        List<Attachment> attachments = attachmentService.selectAttachmentListByObjectId(evidenceNodeId);
        List<String> attachmentFileUris = new ArrayList<>(attachments.size());
        Attachment dataFile = null;
        for (Attachment attachment : attachments) {
            if ("1".equals(attachment.getBizType())) {
                attachmentFileUris.add(attachment.getFilePath());
            } else if ("0".equals(attachment.getBizType())) {
                dataFile = attachment;
            }
        }
        EvidenceResponse evidenceResponse;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getUserId();
        String nickName = user.getNickName();
        String deptName = loginDept.getDeptName();
        String username = SecurityUtils.getUsername();
        Date nowDate = DateUtils.getNowDate();

        try {
            // 存证
            EvidenceRequest evidenceRequest = new EvidenceRequest();
            evidenceRequest.setParentKeyId(evidenceNode.getKeyId());
            evidenceRequest.setVersion(version);
            evidenceRequest.setUsername(username);
            evidenceRequest.setOperationType(EvidenceConstant.OPT_COPY);
            evidenceRequest.setDataType(evidence.getEvidenceTypeName());
            evidenceRequest.setFileName(evidence.getFileName());
            evidenceRequest.setFileSize(evidenceNode.getTotalSize() + "");
            evidenceRequest.setFileHash(evidenceNode.getFileHash());
            evidenceRequest.setAttachmentTotalHash(evidenceNode.getAttachmentTotalHash());
            evidenceRequest.setUri(Objects.requireNonNull(dataFile).getFilePath());
            evidenceRequest.setAttachmentFileUris(attachmentFileUris);
            evidenceResponse = EvidenceUtil.doEvidence(evidenceRequest);
            if (!evidenceResponse.getSuccess()) {
                // 请求失败，记录异存证异常
                EvidenceException evidenceException = new EvidenceException();
                evidenceException.setEvidenceId(evidence.getId());
                evidenceException.setFileName(evidence.getFileName());
                evidenceException.setEvidenceType(evidence.getEvidenceType());
                evidenceException.setEvidenceTypeName(evidence.getEvidenceTypeName());
                evidenceException.setOptType(EvidenceConstant.OPT_COPY);
                evidenceException.setDescription(evidenceResponse.getErr());
                evidenceExceptionService.insertEvidenceException(evidenceException);
                return AjaxResult.error(evidenceException.getDescription());
            }
        } catch (Exception e) {
            // 请求失败，记录异存证异常
            EvidenceException evidenceException = new EvidenceException();
            evidenceException.setEvidenceId(evidence.getId());
            evidenceException.setFileName(evidence.getFileName());
            evidenceException.setEvidenceType(evidence.getEvidenceType());
            evidenceException.setEvidenceTypeName(evidence.getEvidenceTypeName());
            evidenceException.setOptType(EvidenceConstant.OPT_COPY);
            evidenceException.setDescription("数据上链失败");
            evidenceExceptionService.insertEvidenceException(evidenceException);
            return AjaxResult.error(evidenceException.getDescription());
        }

        // 存证热度+1
        evidence.setHeat(evidence.getHeat() + 1);
        evidenceMapper.updateEvidenceHeat(evidence);
        evidenceNode.setParentId(evidenceNodeId);
        evidenceNode.setDeptId(deptId);
        evidenceNode.setDeptName(deptName);
        evidenceNode.setAncestors(evidenceNode.getAncestors() + "," + evidenceNodeId);
        evidenceNode.setOptType(EvidenceConstant.OPT_COPY);
        evidenceNode.setOperatorId(userId);
        evidenceNode.setOperatorName(nickName);
        evidenceNode.setKeyId(evidenceResponse.getKeyId());
        evidenceNode.setTxid(evidenceResponse.getTxId());
        evidenceNode.setBlockHeight(evidenceResponse.getBlockHeight());
        evidenceNode.setBlockHash(evidenceResponse.getBlockHash());
        evidenceNode.setCreateBy(username);
        evidenceNode.setCreateTime(nowDate);
        evidenceNode.setUpdateBy(username);
        evidenceNode.setUpdateTime(nowDate);
        evidenceNodeService.insertEvidenceNode(evidenceNode);
        // 复制一份指向
        for (Attachment attachment : attachments) {
            attachment.setObjectId(evidenceNode.getId());
            attachment.setCreateBy(username);
            attachment.setCreateTime(nowDate);
            attachment.setUpdateBy(username);
            attachment.setUpdateTime(nowDate);
        }

        if (attachments.size() > 0) {
            attachmentService.batchInsertAttachment(attachments);
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult add(EvidenceAddVO vo) {
        // 附件文件
        Long evidenceNodeId = vo.getEvidenceNodeId();
        EvidenceNode evidenceNode = evidenceNodeService.selectEvidenceNodeById(evidenceNodeId);
        // 存证热度+1
        Evidence evidence = evidenceMapper.selectEvidenceById(evidenceNode.getEvidenceId());
        String attachmentTotalHash = vo.getAttachmentTotalHash();
        if (attachmentTotalHash.equals(evidenceNode.getAttachmentTotalHash())) {
            return AjaxResult.error("附件未变化");
        }
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getUserId();
        String nickName = user.getNickName();
        SysDept loginDept = SecurityUtils.getLoginDept();
        Long deptId = loginDept.getDeptId();
        String deptName = loginDept.getDeptName();
        String username = SecurityUtils.getUsername();
        Date nowDate = DateUtils.getNowDate();
        // 生成新的版本号
        String[] split = evidenceNode.getVersion().split("\\.");
        String newVersion = split[0] + "." + (Integer.parseInt(split[1]) + 1);
        List<Attachment> oldAttachments = attachmentService.selectAttachmentListByObjectId(evidenceNodeId);

        // 处理数据文件和附件
        List<EvidenceVO.Attachment> addAttachments = vo.getAttachments();
        List<String> attachmentFileUris = new ArrayList<>(addAttachments.size() + oldAttachments.size());
        Attachment dataFile = null;
        for (Attachment attachment : oldAttachments) {
            if ("1".equals(attachment.getBizType())) {
                attachmentFileUris.add(attachment.getFilePath());
            } else if ("0".equals(attachment.getBizType())) {
                dataFile = attachment;
            }
        }
        for (EvidenceVO.Attachment attachment : addAttachments) {
            attachmentFileUris.add(attachment.getUri());
        }

        EvidenceResponse evidenceResponse;
        try {
            // 存证
            EvidenceRequest evidenceRequest = new EvidenceRequest();
            evidenceRequest.setParentKeyId(evidenceNode.getKeyId());
            evidenceRequest.setVersion(newVersion);
            evidenceRequest.setUsername(username);
            evidenceRequest.setOperationType(EvidenceConstant.OPT_ADD);
            evidenceRequest.setDataType(evidence.getEvidenceTypeName());
            evidenceRequest.setFileName(evidence.getFileName());
            evidenceRequest.setFileSize(evidenceNode.getTotalSize() + "");
            evidenceRequest.setFileHash(evidenceNode.getFileHash());
            evidenceRequest.setAttachmentTotalHash(attachmentTotalHash);
            evidenceRequest.setUri(Objects.requireNonNull(dataFile).getFilePath());
            evidenceRequest.setAttachmentFileUris(attachmentFileUris);
            evidenceResponse = EvidenceUtil.doEvidence(evidenceRequest);

            if (!evidenceResponse.getSuccess()) {
                // 请求失败，记录异存证异常
                EvidenceException evidenceException = new EvidenceException();
                evidenceException.setEvidenceId(evidence.getId());
                evidenceException.setFileName(evidence.getFileName());
                evidenceException.setEvidenceType(evidence.getEvidenceType());
                evidenceException.setEvidenceTypeName(evidence.getEvidenceTypeName());
                evidenceException.setOptType(EvidenceConstant.OPT_COPY);
                evidenceException.setDescription(evidenceResponse.getErr());
                evidenceExceptionService.insertEvidenceException(evidenceException);
                return AjaxResult.error(evidenceException.getDescription());
            }
        } catch (Exception e) {
            // 请求失败，记录异存证异常
            EvidenceException evidenceException = new EvidenceException();
            evidenceException.setEvidenceId(evidence.getId());
            evidenceException.setFileName(evidence.getFileName());
            evidenceException.setEvidenceType(evidence.getEvidenceType());
            evidenceException.setEvidenceTypeName(evidence.getEvidenceTypeName());
            evidenceException.setOptType(EvidenceConstant.OPT_COPY);
            evidenceException.setDescription("数据上链失败");
            evidenceExceptionService.insertEvidenceException(evidenceException);
            return AjaxResult.error(evidenceException.getDescription());
        }

        // 存证热度+1
        evidence.setHeat(evidence.getHeat() + 1);
        evidenceMapper.updateEvidenceHeat(evidence);

        // 复制节点
        evidenceNode.setParentId(evidenceNodeId);
        evidenceNode.setDeptId(deptId);
        evidenceNode.setDeptName(deptName);
        evidenceNode.setAncestors(evidenceNode.getAncestors() + "," + evidenceNodeId);
        evidenceNode.setOptType(EvidenceConstant.OPT_ADD);
        evidenceNode.setDescription(vo.getDescription());
        evidenceNode.setKeyId(evidenceResponse.getKeyId());
        evidenceNode.setAttachmentTotalHash(attachmentTotalHash);
        evidenceNode.setTxid(evidenceResponse.getTxId());
        evidenceNode.setBlockHeight(evidenceResponse.getBlockHeight());
        evidenceNode.setBlockHash(evidenceResponse.getBlockHash());
        evidenceNode.setVersion(newVersion);
        evidenceNode.setOperatorId(userId);
        evidenceNode.setOperatorName(nickName);
        evidenceNode.setCreateBy(username);
        evidenceNode.setCreateTime(nowDate);
        evidenceNode.setUpdateBy(username);
        evidenceNode.setUpdateTime(nowDate);
        evidenceNodeService.insertEvidenceNode(evidenceNode);

        // 复制附件
        for (Attachment attachment : oldAttachments) {
            attachment.setCreateBy(username);
            attachment.setCreateTime(nowDate);
            attachment.setUpdateBy(username);
            attachment.setUpdateTime(nowDate);
            attachment.setObjectId(evidenceNode.getId());
        }

        for (EvidenceVO.Attachment attachment : addAttachments) {
            Attachment tmp = new Attachment();
            tmp.setObjectId(evidenceNode.getId());
            String attachmentFileName = attachment.getFileName();
            tmp.setFileName(attachmentFileName);
            tmp.setBizType(EvidenceConstant.FILE_TYPE_ATTACHMENT);
            tmp.setFileType(attachmentFileName.substring(attachmentFileName.lastIndexOf(".") + 1));
            tmp.setFileSize(attachment.getSize());
            tmp.setFilePath(attachment.getUri());
            tmp.setFileHash(attachment.getHash());
            tmp.setCreateBy(username);
            tmp.setCreateTime(nowDate);
            tmp.setUpdateBy(username);
            tmp.setUpdateTime(nowDate);
            oldAttachments.add(tmp);
        }

        if (oldAttachments.size() > 0) {
            attachmentService.batchInsertAttachment(oldAttachments);
        }

        return AjaxResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult modify(EvidenceModifyVO vo) {
        Long evidenceNodeId = vo.getEvidenceNodeId();
        EvidenceNode evidenceNode = evidenceNodeService.selectEvidenceNodeById(evidenceNodeId);
        Evidence evidence = evidenceMapper.selectEvidenceById(evidenceNode.getEvidenceId());
        String attachmentTotalHash = vo.getAttachmentTotalHash();
        // 数据文件
        EvidenceVO.Attachment dataFile = vo.getDataFile();
        String dataFileHash = dataFile.getHash();
        if (attachmentTotalHash == null) {
            if (evidenceNode.getAttachmentTotalHash() == null && dataFileHash.equals(evidenceNode.getFileHash())) {
                return AjaxResult.error("附件未变化");
            }
        } else {
            if (attachmentTotalHash.equals(evidenceNode.getAttachmentTotalHash()) && dataFileHash.equals(evidenceNode.getFileHash())) {
                return AjaxResult.error("附件未变化");
            }
        }
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getUserId();
        String nickName = user.getNickName();
        SysDept loginDept = SecurityUtils.getLoginDept();
        Long deptId = loginDept.getDeptId();
        String deptName = loginDept.getDeptName();
        String username = SecurityUtils.getUsername();
        Date nowDate = DateUtils.getNowDate();
        // 生成新的版本号
        String[] split = evidenceNode.getVersion().split("\\.");
        String newVersion = "V" + (Integer.parseInt(split[0].replace("V", "")) + 1) + ".0";
        // 附件
        List<EvidenceVO.Attachment> attachmentList = vo.getAttachments();
        List<String> attachmentFileUris = new ArrayList<>(attachmentList.size());

        for (EvidenceVO.Attachment attachment : attachmentList) {
            attachmentFileUris.add(attachment.getUri());
        }
        EvidenceResponse evidenceResponse;
        try {
            // 存证
            EvidenceRequest evidenceRequest = new EvidenceRequest();
            evidenceRequest.setParentKeyId(evidenceNode.getKeyId());
            evidenceRequest.setVersion(newVersion);
            evidenceRequest.setUsername(username);
            evidenceRequest.setOperationType(EvidenceConstant.OPT_MODIFY);
            evidenceRequest.setDataType(evidence.getEvidenceTypeName());
            evidenceRequest.setFileName(evidence.getFileName());
            evidenceRequest.setFileSize(dataFile.getSize() + "");
            evidenceRequest.setFileHash(dataFileHash);
            evidenceRequest.setAttachmentTotalHash(attachmentTotalHash);
            evidenceRequest.setUri(dataFile.getUri());
            evidenceRequest.setAttachmentFileUris(attachmentFileUris);
            evidenceResponse = EvidenceUtil.doEvidence(evidenceRequest);

            if (!evidenceResponse.getSuccess()) {
                // 请求失败，记录异存证异常
                EvidenceException evidenceException = new EvidenceException();
                evidenceException.setEvidenceId(evidence.getId());
                evidenceException.setFileName(evidence.getFileName());
                evidenceException.setEvidenceType(evidence.getEvidenceType());
                evidenceException.setEvidenceTypeName(evidence.getEvidenceTypeName());
                evidenceException.setOptType(EvidenceConstant.OPT_MODIFY);
                evidenceException.setDescription(evidenceResponse.getErr());
                evidenceExceptionService.insertEvidenceException(evidenceException);
                return AjaxResult.error(evidenceException.getDescription());
            }
        } catch (Exception e) {
            // 请求失败，记录异存证异常
            EvidenceException evidenceException = new EvidenceException();
            evidenceException.setEvidenceId(evidence.getId());
            evidenceException.setFileName(evidence.getFileName());
            evidenceException.setEvidenceType(evidence.getEvidenceType());
            evidenceException.setEvidenceTypeName(evidence.getEvidenceTypeName());
            evidenceException.setOptType(EvidenceConstant.OPT_MODIFY);
            evidenceException.setDescription("数据上链失败");
            evidenceExceptionService.insertEvidenceException(evidenceException);
            return AjaxResult.error(evidenceException.getDescription());
        }

        // 存证热度+1
        evidence.setHeat(evidence.getHeat() + 1);
        evidenceMapper.updateEvidenceHeat(evidence);

        evidenceNode.setParentId(evidenceNodeId);
        evidenceNode.setDeptId(deptId);
        evidenceNode.setDeptName(deptName);
        evidenceNode.setAncestors(evidenceNode.getAncestors() + "," + evidenceNodeId);
        evidenceNode.setOptType(EvidenceConstant.OPT_MODIFY);
        evidenceNode.setDescription(vo.getDescription());
        evidenceNode.setTotalSize(dataFile.getSize());
        evidenceNode.setVersion(newVersion);
        evidenceNode.setKeyId(evidenceResponse.getKeyId());
        evidenceNode.setTxid(evidenceResponse.getTxId());
        evidenceNode.setBlockHeight(evidenceResponse.getBlockHeight());
        evidenceNode.setBlockHash(evidenceResponse.getBlockHash());
        evidenceNode.setFileHash(dataFileHash);
        evidenceNode.setAttachmentTotalHash(attachmentTotalHash);
        evidenceNode.setOperatorId(userId);
        evidenceNode.setOperatorName(nickName);
        evidenceNode.setCreateBy(username);
        evidenceNode.setCreateTime(nowDate);
        evidenceNode.setUpdateBy(username);
        evidenceNode.setUpdateTime(nowDate);
        evidenceNodeService.insertEvidenceNode(evidenceNode);

        // 原始文件
        Attachment dataFileAttachment = new Attachment();
        dataFileAttachment.setObjectId(evidenceNode.getId());
        String dataFileName = dataFile.getFileName();
        dataFileAttachment.setFileName(dataFileName);
        dataFileAttachment.setBizType(EvidenceConstant.FILE_TYPE_ORIGINAL);
        dataFileAttachment.setFileType(dataFileName.substring(dataFileName.lastIndexOf(".") + 1));
        dataFileAttachment.setFileSize(dataFile.getSize());
        dataFileAttachment.setFilePath(dataFile.getUri());
        dataFileAttachment.setFileHash(dataFileHash);
        dataFileAttachment.setCreateBy(username);
        dataFileAttachment.setCreateTime(nowDate);
        dataFileAttachment.setUpdateBy(username);
        dataFileAttachment.setUpdateTime(nowDate);

        List<Attachment> attachments = new ArrayList<>();
        attachments.add(dataFileAttachment);
        // 附件
        for (EvidenceVO.Attachment attachment : attachmentList) {
            Attachment tmp = new Attachment();
            tmp.setObjectId(evidenceNode.getId());
            String attachmentFileName = attachment.getFileName();
            tmp.setFileName(attachmentFileName);
            tmp.setBizType(EvidenceConstant.FILE_TYPE_ATTACHMENT);
            tmp.setFileType(attachmentFileName.substring(attachmentFileName.lastIndexOf(".") + 1));
            tmp.setFileSize(attachment.getSize());
            tmp.setFilePath(attachment.getUri());
            tmp.setFileHash(attachment.getHash());
            tmp.setCreateBy(username);
            tmp.setCreateTime(nowDate);
            tmp.setUpdateBy(username);
            tmp.setUpdateTime(nowDate);
            attachments.add(tmp);
        }

        if (attachments.size() > 0) {
            attachmentService.batchInsertAttachment(attachments);
        }

        return AjaxResult.success();
    }

    @Override
    public List<EvidenceListVO> personalEvidenceList() {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        if (SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            deptId = null;
        }
        return evidenceMapper.personalEvidenceList(deptId);
    }

    @Override
    public List<EvidenceNodeVO> personalEvidenceNodeListLatest() {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        if (SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            deptId = null;
        }
        return evidenceMapper.personalEvidenceNodeListLatest(deptId);
    }

    @Override
    public List<EvidenceNodeVO> allEvidenceNodeListLatest() {
        return evidenceMapper.allEvidenceNodeListLatest();
    }

    @Override
    public List<EvidenceNodeVO> personalEvidenceNodeList(Date beginDate, Date endDate,
                                                         String optType,
                                                         String fileName,
                                                         Long evidenceType) {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        if (SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            deptId = null;
        }
        Set<Long> types;
        if (evidenceType != null) {
            types = evidenceTypeService.selectSonIds(evidenceType);
        } else {
            types = evidenceTypeService.selectIds();
        }
        startPage();
        return evidenceMapper.personalEvidenceNodeList(deptId, beginDate, endDate, optType, fileName, types);
    }

    @Override
    public List<Evidence> traceCenterList(Evidence evidence) {
        Long id = evidence.getEvidenceType();
        Set<Long> types;
        if (id == null) {
            types = evidenceTypeService.selectIds();
        } else {
            types = evidenceTypeService.selectSonIds(id);
        }
        startPage();
        return evidenceMapper.traceCenterList(evidence, types);
    }

    private void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    @Override
    public EvidenceStatisticsVO evidenceStatistics() {
        return evidenceMapper.evidenceStatistics();
    }

    @Override
    public double userActivity() {
        return evidenceMapper.userActivity();
    }

    @Override
    public List<StatisticsVO> evidenceTypeStatistics() {

        return evidenceMapper.evidenceTypeStatistics();
    }

    @Override
    public boolean validFileName(String fileName) {
        return evidenceMapper.validFileName(fileName);
    }

    @Override
    public List<EvidenceAndExceptionStatisticsVO> evidenceAndExceptionStatistics() {
        return evidenceMapper.evidenceAndExceptionStatistics();
    }

    @Override
    public List<EvidenceMonthStatisticsVO> evidenceMonthStatistics() {
        List<EvidenceType> evidenceTypes = evidenceTypeService.selectEvidenceTypeList(null);
        List<EvidenceMonthStatisticsVO> result = new ArrayList<>();
        for (EvidenceType evidenceType : evidenceTypes) {
            List<StatisticsVO> statistics = evidenceMapper.evidenceMonthStatistics(evidenceType.getId());
            EvidenceMonthStatisticsVO vo = new EvidenceMonthStatisticsVO();
            vo.setType(evidenceType.getName());
            vo.setData(statistics);
            result.add(vo);
        }
        return result;
    }

    @Override
    public double fileSize() {
        // 调用文件服务器查询文件总大小
        return EvidenceUtil.fileSize();
    }

    @Override
    public CommonResponse verify(Long evidenceId) {
        List<EvidenceNode> evidenceNodes = evidenceNodeService.selectByEvidenceId(evidenceId);
        EvidenceNode rootNode = null;
        for (EvidenceNode evidenceNode : evidenceNodes) {
            if (evidenceNode.getParentId() == 0) {
                rootNode = evidenceNode;
                break;
            }
        }
        if (rootNode == null) {
            return new CommonResponse(false, "未找到根节点");
        }
        evidenceNodes.remove(rootNode);
        EvidenceTree tree = new EvidenceTree(rootNode.getKeyId(), findChild(rootNode.getId(), evidenceNodes));
        List<List<String>> list = findChildren(tree);
        CommonResponse commonResponse = EvidenceUtil.verifyTree(list);
        // 更新缓存中的最后一次溯源情况
        CommonResponseCache commonResponseCache = new CommonResponseCache();
        commonResponseCache.setSuccess(commonResponse.getSuccess());
        commonResponseCache.setErr(commonResponse.getErr());
        commonResponseCache.setDate(new Date());
        commonResponseCache.setOperator(SecurityUtils.getUsername());
        commonResponseCache.setOperatorName(SecurityUtils.getLoginUser().getUser().getNickName());
        commonResponseCache.setMismatchedSet(commonResponse.getMismatchedSet());
        // 由于移除了根节点所以数量+1
        commonResponseCache.setNodeCount(evidenceNodes.size() + 1);
        redisCache.setCacheObject(EvidenceConstant.CACHE_LAST_TRACE_EVIDENCE + evidenceId, commonResponseCache);
        return commonResponse;
    }

    @Override
    public List<EvidenceListVO> myEvidenceList(Evidence evidence) {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        if (SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            deptId = null;
        }
        Map<String, Object> map = new HashMap<>(1);
        Set<Long> types;
        evidence.setDeptId(deptId);
        if(evidence.getEvidenceType() != null) {
            types = evidenceTypeService.selectSonIds(evidence.getEvidenceType());
            map.put("types", types);
        } else {
            types = evidenceTypeService.selectIds();
            map.put("types", types);
        }
        evidence.setParams(map);
        startPage();
        return evidenceMapper.myEvidenceList(evidence);
    }

    @Override
    public List<EvidenceListVO> copyEvidenceList(Evidence evidence) {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        if (SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            deptId = null;
        }
        evidence.setDeptId(deptId);
        Map<String, Object> map = new HashMap<>(1);
        Set<Long> types;
        evidence.setDeptId(deptId);
        if(evidence.getEvidenceType() != null) {
            types = evidenceTypeService.selectSonIds(evidence.getEvidenceType());
            map.put("types", types);
        } else {
            types = evidenceTypeService.selectIds();
            map.put("types", types);
        }
        evidence.setParams(map);
        startPage();
        return evidenceMapper.copyEvidenceList(evidence);
    }

    /**
     * 根据一级存证类型统计存证
     *
     * @return
     */
    @Override
    public List<CommonCountVo> countByType() {
        List<Evidence> list = evidenceMapper.selectEvidenceList(null);

        List<CommonCountVo> countList = new ArrayList<>();
        HashMap<Long, CommonCountVo> countMap = new HashMap<>();

        Map<Long, String> topIds = evidenceTypeService.selectTopIds();
        Map<Long, Set<Long>> allIds = evidenceTypeService.selectAllIds();

        for (Long id : topIds.keySet()) {
            CommonCountVo countVo = new CommonCountVo();
            countVo.setId(id);
            countVo.setName(topIds.get(id));
            countVo.setCount(0);
            countMap.put(id, countVo);
        }

        for (Evidence evi : list) {
            for (Long id : topIds.keySet()) {
                if (Objects.equals(id, evi.getEvidenceType()) || allIds.get(id).contains(evi.getEvidenceType())) {
                    CommonCountVo countVo = countMap.get(id);
                    countVo.setCount(countVo.getCount() + 1);
                }
            }
        }

        for (Long id : countMap.keySet()) {
            countList.add(countMap.get(id));
        }

        return countList;
    }

    /**
     * 根据一级存证类型统计存在存证节点被复制的存证占比
     *
     * @return
     */
    @Override
    public List<EvidenceSharedCountVo> countCopyByType() {
        List<Evidence> list = evidenceMapper.selectEvidenceList(null);

        List<EvidenceSharedCountVo> countList = new ArrayList<>();
        HashMap<Long, EvidenceSharedCountVo> countMap = new HashMap<>();

        Set<Long> sharedEviIds = evidenceMapper.selectCopiedEviIds();
        Map<Long, String> topIds = evidenceTypeService.selectTopIds();
        Map<Long, Set<Long>> allIds = evidenceTypeService.selectAllIds();


        for (Long id : topIds.keySet()) {
            EvidenceSharedCountVo countVo = new EvidenceSharedCountVo();
            countVo.setId(id);
            countVo.setName(topIds.get(id));
            countVo.setCount(0);
            countVo.setSharedCount(0);
            countMap.put(id, countVo);
        }

        for (Evidence evi : list) {
            for (Long id : topIds.keySet()) {
                if (Objects.equals(id, evi.getEvidenceType()) || allIds.get(id).contains(evi.getEvidenceType())) {
                    EvidenceSharedCountVo countVo = countMap.get(id);
                    countVo.setCount(countVo.getCount() + 1);
                    if (sharedEviIds.contains(evi.getId())) {
                        countVo.setSharedCount(countVo.getSharedCount() + 1);
                    }
                }
            }
        }

        for (Long id : countMap.keySet()) {
            countList.add(countMap.get(id));
        }

        return countList;
    }

    /**
     * 根据一级存证类型统计存证刷新率<=1的存证数
     * 刷新率=(存证节点最新创建时间-存证节点最小创建时间)/最新创建时间对应的存证节点的祖先链
     *
     * @return
     */
    @Override
    public List<CommonCountVo> countRefreshRate() {
        List<CommonCountVo> countList = new ArrayList<>(10);
        HashMap<Long, CommonCountVo> countMap = new HashMap<>();

        List<EvidenceRefreshVo> list = evidenceMapper.selectTimeAncestor();
        Map<Long, String> topIds = evidenceTypeService.selectTopIds();
        Map<Long, Set<Long>> allIds = evidenceTypeService.selectAllIds();

        for (Long id : topIds.keySet()) {
            CommonCountVo countVo = new CommonCountVo();
            countVo.setId(id);
            countVo.setName(topIds.get(id));
            countVo.setCount(0);
            HashMap<String, Object> map = new HashMap<>();
            map.put("refreshCount", 0);
            countVo.setParams(map);
            countMap.put(id, countVo);
        }

        for (EvidenceRefreshVo vo : list) {
            for (Long id : topIds.keySet()) {
                if (Objects.equals(id, vo.getTypeId()) || allIds.get(id).contains(vo.getTypeId())) {
                    CommonCountVo countVo = countMap.get(id);
                    countVo.setCount(countVo.getCount() + 1);
                }
            }

            Date early = DateUtils.dateTime("yyyy-MM-dd hh:mm:ss", vo.getMinCreateTime());
            Date late = DateUtils.dateTime("yyyy-MM-dd hh:mm:ss", vo.getMaxCreateTime());
            long dayDistance = DateUtil.between(early, late, DateUnit.DAY);
            int nodes = vo.getAncestors().split(",").length;
            if ((dayDistance / nodes) < 1) {
                for (Long id : topIds.keySet()) {
                    if (Objects.equals(id, vo.getTypeId()) || allIds.get(id).contains(vo.getTypeId())) {
                        CommonCountVo countVo = countMap.get(id);
                        Map<String, Object> map = countVo.getParams();
                        map.put("refreshCount", Integer.parseInt(map.get("refreshCount").toString()) + 1);
                    }
                }
            }
        }

        for (Long id : countMap.keySet()) {
            countList.add(countMap.get(id));
        }

        return countList;
    }

    @Override
    public List<EvidenceNodePreviewVO> detail(Long evidenceNodeId) {
        List<EvidenceNodePreviewVO> result = new ArrayList<>();
        // 获取节点信息
        EvidenceNode evidenceNode = evidenceNodeService.selectEvidenceNodeById(evidenceNodeId);
        if (evidenceNode == null) {
            return result;
        }
        Long evidenceId = evidenceNode.getEvidenceId();
        // 取出存证中的所有节点
        List<EvidenceNode> evidenceNodes = evidenceNodeService.selectByEvidenceId(evidenceId);
        if (evidenceNodes.isEmpty()) {
            return result;
        }
        Long deptId = SecurityUtils.getLoginDept().getDeptId();
        // 获取起始节点的父节点
        List<EvidenceNodePreviewVO> parents = new ArrayList<>();
        if (evidenceNode.getParentId() > 0) {
            List<EvidenceNode> list = evidenceNodeService.selectByAncestors(evidenceNode.getAncestors());
            for (EvidenceNode node : list) {
                EvidenceNodePreviewVO vo = ModelUtil.map(node, EvidenceNodePreviewVO.class);
                vo.setNodeType(2);
                parents.add(vo);
            }
        }
        // 获取起始节点的子节点
        List<EvidenceNodePreviewVO> children = findChildren(evidenceNodes, evidenceNodeId, deptId);
        EvidenceNodePreviewVO originalNode = ModelUtil.map(evidenceNode, EvidenceNodePreviewVO.class);
        originalNode.setNodeType(0);
        result.add(originalNode);
        result.addAll(parents);
        result.addAll(children);
        return result;
    }

    private List<EvidenceNodePreviewVO> findChildren(List<EvidenceNode> evidenceNodes, Long currentId, Long currentDeptId) {
        List<EvidenceNodePreviewVO> result = new ArrayList<>();
        for (EvidenceNode evidenceNode : evidenceNodes) {
            if (currentId.equals(evidenceNode.getParentId())) { // 子节点
                Long evidenceNodeId = evidenceNode.getId();
                EvidenceNodePreviewVO vo = ModelUtil.map(evidenceNode, EvidenceNodePreviewVO.class);
                if (currentDeptId.equals(evidenceNode.getDeptId())) {
                    // 主链，自己创建的
                    vo.setNodeType(0);
                    // 沿主链继续递归子节点
                    result.addAll(findChildren(evidenceNodes, evidenceNodeId, currentDeptId));
                } else {
                    // 从链，不是自己创建的
                    vo.setNodeType(1);
                    // 将后续节点都设为从链
                    result.addAll(findChildrenAndSetSlave(evidenceNodes, evidenceNodeId));
                }
                result.add(vo);
            }
        }
        return result;
    }

    private List<EvidenceNodePreviewVO> findChildrenAndSetSlave(List<EvidenceNode> evidenceNodes, Long evidenceNodeId) {
        List<EvidenceNodePreviewVO> list = new ArrayList<>();
        for (EvidenceNode evidenceNode : evidenceNodes) {
            if (evidenceNodeId.equals(evidenceNode.getParentId())) {
                EvidenceNodePreviewVO vo = ModelUtil.map(evidenceNode, EvidenceNodePreviewVO.class);
                vo.setNodeType(1);
                list.add(vo);
                list.addAll(findChildrenAndSetSlave(evidenceNodes, evidenceNode.getId()));
            }
        }
        return list;
    }

    public static List<List<String>> findChildren(EvidenceTree rootNode) {
        List<List<String>> keyIds = new ArrayList<>();
        List<EvidenceTree> childNodes = rootNode.getChildNodes();
        if (childNodes == null || childNodes.isEmpty()) {
            List<String> temp = new ArrayList<>();
            temp.add(rootNode.getKeyId());
            keyIds.add(temp);
        } else {
            for (EvidenceTree childNode : childNodes) {
                List<List<String>> childList = findChildren(childNode);
                for (List<String> temp : childList) {
                    temp.add(0, rootNode.getKeyId());
                    keyIds.add(temp);
                }
            }
        }
        return keyIds;
    }

    public static List<EvidenceTree> findChild(Long id, List<EvidenceNode> evidenceNodes) {
        List<EvidenceTree> childList = new ArrayList<>();
        for (EvidenceNode evidenceNode : evidenceNodes) {
            if (id.equals(evidenceNode.getParentId())) {
                childList.add(new EvidenceTree(evidenceNode.getKeyId(), findChild(evidenceNode.getId(), evidenceNodes)));
            }
        }
        return childList;
    }

    /**
     * 根据机构id统计该机构发布的数据总数
     * @return
     */
	@Override
	public Integer countGroupByDeptdeptId(Long deptId) {
		return evidenceMapper.countGroupByDeptdeptId(deptId);
	}
}
