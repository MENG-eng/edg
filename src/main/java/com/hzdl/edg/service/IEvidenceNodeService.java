package com.hzdl.edg.service;

import com.hzdl.edg.domain.EvidenceNode;
import com.hzdl.edg.domain.block.CommonResponse;
import com.hzdl.edg.domain.vo.EvidenceNodeAttachmentVO;
import com.hzdl.edg.domain.vo.EvidenceNodeVO;
import com.hzdl.edg.domain.vo.OptTypeStatisticsVO;
import com.hzdl.edg.domain.vo.StatisticsVO;

import java.util.List;

/**
 * 存证节点Service接口
 *
 * @author hzdl
 * @date 2020-08-17
 */
public interface IEvidenceNodeService {
    /**
     * 查询存证节点
     *
     * @param id 存证节点ID
     * @return 存证节点
     */
    EvidenceNode selectEvidenceNodeById(Long id);

    /**
     * 查询存证节点列表
     *
     * @param evidenceNode 存证节点
     * @return 存证节点集合
     */
    List<EvidenceNode> selectEvidenceNodeList(EvidenceNode evidenceNode);

    /**
     * 新增存证节点
     *
     * @param evidenceNode 存证节点
     * @return 结果
     */
    int insertEvidenceNode(EvidenceNode evidenceNode);

    /**
     * 修改存证节点
     *
     * @param evidenceNode 存证节点
     * @return 结果
     */
    int updateEvidenceNode(EvidenceNode evidenceNode);

    /**
     * 批量删除存证节点
     *
     * @param ids 需要删除的存证节点ID
     * @return 结果
     */
    int deleteEvidenceNodeByIds(Long[] ids);

    /**
     * 删除存证节点信息
     *
     * @param id 存证节点ID
     * @return 结果
     */
    int deleteEvidenceNodeById(Long id);

    /**
     * 预览存证节点
     *
     * @param evidenceNodeId 所选的节点id
     * @return 包含所选节点和之前的所有节点
     */
    List<EvidenceNode> view(Long evidenceNodeId);

    /**
     * 统计本机构不同操作类型的数量
     *
     * @return 统计结果
     * @author 34277
     * @date 2020/9/2
     */
    OptTypeStatisticsVO optTypeStatistics();

    /**
     * 存证数据被引用情况统计
     *
     * @return 统计结果
     * @author 34277
     * @date 2020/9/2
     */
    List<StatisticsVO> quoteStatistics();

    /**
     * 个人存证被引用top10
     *
     * @return top引用数据
     * @author 34277
     * @date 2020/9/3
     */
    List<StatisticsVO> quoteTop10PersonalStatistics();

    /**
     * 通过存证id查询所有存证节点
     *
     * @param evidenceId 存证id
     * @return 节点列表
     * @author 34277
     * @date 2020/9/3
     */
    List<EvidenceNode> selectByEvidenceId(Long evidenceId);

    /**
     * 所有存证被引用top10
     *
     * @return top引用数据
     * @author 34277
     * @date 2020/9/3
     */
    List<StatisticsVO> quoteTop10AllStatistics();

    /**
     * 验证节点
     *
     * @param evidenceNodeId 最后一个节点的id
     * @return 是否通过验证
     * @author FY
     * @date 2020/9/9
     */
    CommonResponse verify(Long evidenceNodeId);

    /**
     * 获取附件信息
     *
     * @param id 节点id
     * @return 数据文件和附件信息
     * @author FY
     * @date 2020/09/11
     */
    EvidenceNodeAttachmentVO getAttachmentByEvidenceNodeId(Long id);

    List<EvidenceNodeVO> selectEvidenceNodeVOList(EvidenceNode evidenceNode);

    /**
     * 通过祖籍列表查询
     *
     * @param ancestors 祖籍列表
     * @return 查询结果
     * @author FY
     * @date 2020/09/28
     */
    List<EvidenceNode> selectByAncestors(String ancestors);

    /**
     * 判断存证是否存在
     *
     * @param evidenceNode 存证节点
     * @return 是否存在
     * @author LIDD
     * @date 2020/09/29
     */
    boolean evidenceNodeExist(EvidenceNode evidenceNode);

    /**
     * 复制溯源判断
     *
     * @param evidenceNode 最后一个存证节点
     * @return 是否存在
     * @author LIDD
     * @date 2020/09/30
     */
    public CommonResponse copyVerify(EvidenceNode evidenceNode);

    List<EvidenceNode> selectByEvidenceIdWithoutCopyLeafNode(Long evidenceId);
}
