package com.hzdl.share.service.impl;

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
import com.hzdl.edg.domain.Attachment;
import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.EvidenceException;
import com.hzdl.edg.domain.EvidenceNode;
import com.hzdl.edg.domain.block.EvidenceRequest;
import com.hzdl.edg.domain.block.EvidenceResponse;
import com.hzdl.edg.domain.vo.EvidenceVO;
import com.hzdl.edg.service.IAttachmentService;
import com.hzdl.edg.service.IEvidenceExceptionService;
import com.hzdl.edg.service.IEvidenceNodeService;
import com.hzdl.share.domain.Subsidy;
import com.hzdl.share.domain.vo.SubsidyVO;
import com.hzdl.share.mapper.SubsidyMapper;
import com.hzdl.share.service.ISubsidyService;
import com.hzdl.util.EvidenceUtil;
import com.hzdl.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class SubsidyServiceImpl implements ISubsidyService {
    @Autowired
    private SubsidyMapper subsidyMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IEvidenceExceptionService evidenceExceptionService;
    @Autowired
    private IEvidenceNodeService evidenceNodeService;
    @Autowired
    private IAttachmentService attachmentService;

    @Override
    public List<Subsidy> selectSubsidyList(Subsidy subsidy) {
        startPage();
        return subsidyMapper.selectSubsidyList(subsidy);
    }

    @Override
    public int insertSubsidy(Subsidy subsidy) {
        return subsidyMapper.insertSubsidy(subsidy);
    }

    /*
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult create(EvidenceVO vo) {
        String username = SecurityUtils.getUsername();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getUserId();
        String nickName = user.getNickName();
        Date nowDate = DateUtils.getNowDate();
        SysDept loginDept = SecurityUtils.getLoginDept();
        Long deptId = loginDept.getDeptId();
        String deptName = loginDept.getDeptName();
        // ????????????
        EvidenceVO.Attachment dataFile = vo.getDataFile();
        String dataFileUri = dataFile.getUri();
        String fileName = dataFile.getFileName();
        Long fileSize = dataFile.getSize();
        String dataFileHash = dataFile.getHash();
        String attachmentTotalHash = vo.getAttachmentTotalHash();
        // ??????
        List<EvidenceVO.Attachment> attachmentList = vo.getAttachments();
        List<String> attachmentFileUris = new ArrayList<>(attachmentList.size());
        for (EvidenceVO.Attachment attachment : attachmentList) {
            attachmentFileUris.add(attachment.getUri());
        }
        EvidenceResponse evidenceResponse;
        try {
            // ??????
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
                // ????????????????????????????????????
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
            // ????????????????????????????????????
            EvidenceException evidenceException = new EvidenceException();
            evidenceException.setFileName(vo.getFileName());
            evidenceException.setEvidenceType(vo.getEvidenceType());
            evidenceException.setEvidenceTypeName(vo.getEvidenceTypeName());
            evidenceException.setOptType(EvidenceConstant.OPT_CREATE);
            evidenceException.setDescription("??????????????????");
            evidenceExceptionService.insertEvidenceException(evidenceException);
            return AjaxResult.error(evidenceException.getDescription());
        }

        Subsidy subsidy = ModelUtil.map(vo, Subsidy.class);
        // ??????????????????
        subsidy.setCreateTime(nowDate);
        subsidy.setShareState(0);
        subsidy.setApplyName(username);
        subsidy.setShareName("00");
        subsidyMapper.insertSubsidy(subsidy);

        // ????????????
        EvidenceNode evidenceNode = new EvidenceNode();
        evidenceNode.setDeptId(deptId);
        evidenceNode.setDeptName(deptName);
        evidenceNode.setAncestors("0");
        evidenceNode.setEvidenceId(subsidy.getSubsidyId());
        evidenceNode.setOptType(EvidenceConstant.OPT_CREATE);
        evidenceNode.setDescription(subsidy.getDescription());
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

        // ????????????
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
        // ??????
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
*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult create(SubsidyVO vo) {
        String username = SecurityUtils.getUsername();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getUserId();
        String nickName = user.getNickName();
        Date nowDate = DateUtils.getNowDate();
        SysDept loginDept = SecurityUtils.getLoginDept();
        Long deptId = loginDept.getDeptId();
        String deptName = loginDept.getDeptName();
        // ????????????
        SubsidyVO.Attachment dataFile = vo.getDataFile();
        String dataFileUri = dataFile.getUri();
        String fileName = dataFile.getFileName();
        Long fileSize = dataFile.getSize();
        String dataFileHash = dataFile.getHash();
        String attachmentTotalHash = vo.getAttachmentTotalHash();
        // ??????
        List<SubsidyVO.Attachment> attachmentList = vo.getAttachments();
        List<String> attachmentFileUris = new ArrayList<>(attachmentList.size());
        for (SubsidyVO.Attachment attachment : attachmentList) {
            attachmentFileUris.add(attachment.getUri());
        }
        EvidenceResponse evidenceResponse;
        try {
            // ??????
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
                // ????????????????????????????????????
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
            // ????????????????????????????????????
            EvidenceException evidenceException = new EvidenceException();
            evidenceException.setFileName(vo.getFileName());
            evidenceException.setEvidenceType(vo.getEvidenceType());
            evidenceException.setEvidenceTypeName(vo.getEvidenceTypeName());
            evidenceException.setOptType(EvidenceConstant.OPT_CREATE);
            evidenceException.setDescription("??????????????????");
            evidenceExceptionService.insertEvidenceException(evidenceException);
            return AjaxResult.error(evidenceException.getDescription());
        }

        Subsidy subsidy = ModelUtil.map(vo, Subsidy.class);
        // ??????????????????
        subsidy.setCreateTime(nowDate);
        subsidy.setShareState(0);
        subsidy.setApplyName(username);
        subsidyMapper.insertSubsidy(subsidy);

        // ????????????
        EvidenceNode evidenceNode = new EvidenceNode();
        evidenceNode.setDeptId(deptId);
        evidenceNode.setDeptName(deptName);
        evidenceNode.setAncestors("0");
        evidenceNode.setEvidenceId(subsidy.getId());
        evidenceNode.setOptType(EvidenceConstant.OPT_CREATE);
        evidenceNode.setDescription(subsidy.getDescription());
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

        // ????????????
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
        // ??????
        for (SubsidyVO.Attachment attachment : attachmentList) {
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

    private void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
}
