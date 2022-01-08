package com.hzdl.edg.service.impl;

import com.hzdl.common.core.redis.RedisCache;
import com.hzdl.common.utils.SecurityUtils;
import com.hzdl.constant.EvidenceConstant;
import com.hzdl.edg.domain.Attachment;
import com.hzdl.edg.domain.EvidenceNode;
import com.hzdl.edg.domain.block.CommonResponse;
import com.hzdl.edg.domain.block.CommonResponseCache;
import com.hzdl.edg.domain.block.EvidenceTree;
import com.hzdl.edg.domain.vo.*;
import com.hzdl.edg.mapper.EvidenceNodeMapper;
import com.hzdl.edg.service.IAttachmentService;
import com.hzdl.edg.service.IEvidenceNodeService;
import com.hzdl.util.EvidenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 存证节点Service业务层处理
 *
 * @author hzdl
 * @date 2020-08-17
 */
@Service
public class EvidenceNodeServiceImpl implements IEvidenceNodeService {
    @Autowired
    private EvidenceNodeMapper evidenceNodeMapper;
    @Autowired
    private IAttachmentService attachmentService;
    @Autowired
    private RedisCache redisCache;

    /**
     * 查询存证节点
     *
     * @param id 存证节点ID
     * @return 存证节点
     */
    @Override
    public EvidenceNode selectEvidenceNodeById(Long id) {
        return evidenceNodeMapper.selectEvidenceNodeById(id);
    }

    /**
     * 查询存证节点列表
     *
     * @param evidenceNode 存证节点
     * @return 存证节点
     */
    @Override
    public List<EvidenceNode> selectEvidenceNodeList(EvidenceNode evidenceNode) {
        return evidenceNodeMapper.selectEvidenceNodeList(evidenceNode);
    }

    /**
     * 新增存证节点
     *
     * @param evidenceNode 存证节点
     * @return 结果
     */
    @Override
    public int insertEvidenceNode(EvidenceNode evidenceNode) {
        return evidenceNodeMapper.insertEvidenceNode(evidenceNode);
    }

    /**
     * 修改存证节点
     *
     * @param evidenceNode 存证节点
     * @return 结果
     */
    @Override
    public int updateEvidenceNode(EvidenceNode evidenceNode) {
        return evidenceNodeMapper.updateEvidenceNode(evidenceNode);
    }

    /**
     * 批量删除存证节点
     *
     * @param ids 需要删除的存证节点ID
     * @return 结果
     */
    @Override
    public int deleteEvidenceNodeByIds(Long[] ids) {
        return evidenceNodeMapper.deleteEvidenceNodeByIds(ids);
    }

    /**
     * 删除存证节点信息
     *
     * @param id 存证节点ID
     * @return 结果
     */
    @Override
    public int deleteEvidenceNodeById(Long id) {
        return evidenceNodeMapper.deleteEvidenceNodeById(id);
    }

    @Override
    public List<EvidenceNode> view(Long evidenceNodeId) {
        EvidenceNode evidenceNode = evidenceNodeMapper.selectEvidenceNodeById(evidenceNodeId);
        // 该节点的祖级节点ids
        String ancestors = evidenceNode.getAncestors();
        // 祖级节点列表
        List<EvidenceNode> ancestorsEvidenceNode = evidenceNodeMapper.selectByAncestors(ancestors);
        ancestorsEvidenceNode.add(evidenceNode);
        // 递归按照父子关系排序节点
        return sort(ancestorsEvidenceNode, 0L);
    }

    @Override
    public OptTypeStatisticsVO optTypeStatistics() {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        if (SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            deptId = null;
        }
        return evidenceNodeMapper.optTypeStatistics(deptId);
    }

    @Override
    public List<StatisticsVO> quoteStatistics() {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        if (SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            deptId = null;
        }
        return evidenceNodeMapper.quoteStatistics(deptId);
    }

    @Override
    public List<StatisticsVO> quoteTop10PersonalStatistics() {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        if (SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            deptId = null;
        }
        return evidenceNodeMapper.quoteTop10PersonalStatistics(deptId);
    }

    @Override
    public List<EvidenceNode> selectByEvidenceId(Long evidenceId) {
        return evidenceNodeMapper.selectByEvidenceId(evidenceId);
    }

    @Override
    public List<StatisticsVO> quoteTop10AllStatistics() {
        return evidenceNodeMapper.quoteTop10AllStatistics();
    }

    @Override
    public CommonResponse verify(Long evidenceNodeId) {
        // 当前节点
        EvidenceNode evidenceNode = evidenceNodeMapper.selectEvidenceNodeById(evidenceNodeId);
        if (evidenceNode == null) {
            return new CommonResponse(false, "找不到当前节点");
        }
        EvidenceNode rootNode = null;
        List<EvidenceNode> nodes = new ArrayList<>();
        if (evidenceNode.getParentId() == 0) {
            rootNode = evidenceNode;
        } else {
            // 父级节点
            List<EvidenceNode> parentNodes = evidenceNodeMapper.selectByAncestors(evidenceNode.getAncestors());
            nodes.addAll(parentNodes);
            for (EvidenceNode node : parentNodes) {
                if (node.getParentId() == 0) {
                    rootNode = evidenceNode;
                    break;
                }
            }
        }
        if (rootNode == null) {
            return new CommonResponse(false, "未找到根节点");
        }
        // 添加当前节点
        nodes.add(evidenceNode);
        // 子级节点
        List<EvidenceNode> childNodes = evidenceNodeMapper.selectByEvidenceId(evidenceNode.getEvidenceId());
        nodes.addAll(childNodes);
        EvidenceTree tree = new EvidenceTree(rootNode.getKeyId(), EvidenceServiceImpl.findChild(rootNode.getId(), nodes));
        List<List<String>> list = EvidenceServiceImpl.findChildren(tree);
        CommonResponse commonResponse = EvidenceUtil.verifyTree(list);
        // 更新缓存中的最后一次溯源情况
        CommonResponseCache commonResponseCache = new CommonResponseCache();
        commonResponseCache.setSuccess(commonResponse.getSuccess());
        commonResponseCache.setErr(commonResponse.getErr());
        commonResponseCache.setDate(new Date());
        commonResponseCache.setOperator(SecurityUtils.getUsername());
        commonResponseCache.setOperator(SecurityUtils.getLoginUser().getUser().getNickName());
        commonResponseCache.setMismatchedSet(commonResponse.getMismatchedSet());
        // 由于移除了根节点所以数量+1
        commonResponseCache.setNodeCount(nodes.size());
        redisCache.setCacheObject(EvidenceConstant.CACHE_LAST_TRACE_NODE + evidenceNodeId, commonResponseCache);
        return commonResponse;
    }

    @Override
    public EvidenceNodeAttachmentVO getAttachmentByEvidenceNodeId(Long id) {
        List<Attachment> attachments = attachmentService.selectAttachmentListByObjectId(id);
        EvidenceNodeAttachmentVO vo = new EvidenceNodeAttachmentVO();
        List<EvidenceVO.Attachment> attachmentList = new ArrayList<>(attachments.size());
        for (Attachment attachment : attachments) {
            EvidenceVO.Attachment tmp = new EvidenceVO.Attachment();
            tmp.setFileName(attachment.getFileName());
            tmp.setUri(attachment.getFilePath());
            tmp.setSize(attachment.getFileSize());
            tmp.setHash(attachment.getFileHash());
            if ("0".equals(attachment.getBizType())) {
                vo.setDataFile(tmp);
                continue;
            }
            attachmentList.add(tmp);
        }
        vo.setAttachments(attachmentList);
        return vo;
    }

    @Override
    public List<EvidenceNodeVO> selectEvidenceNodeVOList(EvidenceNode evidenceNode) {
        return evidenceNodeMapper.selectEvidenceNodeVOList(evidenceNode);
    }

    @Override
    public List<EvidenceNode> selectByAncestors(String ancestors) {
        return evidenceNodeMapper.selectByAncestors(ancestors);
    }

    private List<EvidenceNode> sort(List<EvidenceNode> beSortList, Long parenId) {
        List<EvidenceNode> sortedList = new ArrayList<>(beSortList.size());
        Iterator<EvidenceNode> iterator = beSortList.iterator();
        while (iterator.hasNext()) {
            EvidenceNode currentNode = iterator.next();
            if (parenId.equals(currentNode.getParentId())) {
                sortedList.add(currentNode);
                iterator.remove();
                sortedList.addAll(sort(beSortList, currentNode.getId()));
            }
        }
        return sortedList;
    }

    @Override
    public boolean evidenceNodeExist(EvidenceNode evidenceNode) {
        return evidenceNodeMapper.evidenceNodeExist(evidenceNode);
    }

    @Override
    public CommonResponse copyVerify(EvidenceNode evidenceNode) {
        // 祖级节点列表
        List<EvidenceNode> ancestorsEvidenceNode = evidenceNodeMapper.selectByAncestors(evidenceNode.getAncestors());
        ancestorsEvidenceNode.add(evidenceNode);
        // 递归按照父子关系排序节点
        List<EvidenceNode> evidenceNodes = sort(ancestorsEvidenceNode, 0L);
        List<String> keyIds = new ArrayList<>(evidenceNodes.size());
        for (EvidenceNode node : evidenceNodes) {
            keyIds.add(node.getKeyId());
        }
        return EvidenceUtil.verifyChain(keyIds);
    }

    @Override
    public List<EvidenceNode> selectByEvidenceIdWithoutCopyLeafNode(Long evidenceId) {
        return evidenceNodeMapper.selectByEvidenceIdWithoutCopyLeafNode(evidenceId);
    }
}
