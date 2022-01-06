package com.hzdl.edg.mapper;

import com.hzdl.edg.domain.EvidenceNode;
import com.hzdl.edg.domain.vo.EvidenceNodeVO;
import com.hzdl.edg.domain.vo.OptTypeStatisticsVO;
import com.hzdl.edg.domain.vo.StatisticsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 存证节点Mapper接口
 *
 * @author hzdl
 * @date 2020-08-17
 */
public interface EvidenceNodeMapper {
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
     * 删除存证节点
     *
     * @param id 存证节点ID
     * @return 结果
     */
    int deleteEvidenceNodeById(Long id);

    /**
     * 批量删除存证节点
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEvidenceNodeByIds(Long[] ids);

    /**
     * 查询id在ancestors中的节点
     *
     * @param ancestors ids
     * @return 节点列表
     */
    List<EvidenceNode> selectByAncestors(@Param("ancestors") String ancestors);

    /**
     * 统计本机构不同操作类型的数量
     *
     * @param deptId 机构id
     * @return 统计结果
     * @author 34277
     * @date 2020/9/2
     */
    OptTypeStatisticsVO optTypeStatistics(Long deptId);

    /**
     * 存证数据被引用情况统计
     *
     * @param deptId 机构id
     * @return 统计结果
     * @author 34277
     * @date 2020/9/2
     */
    List<StatisticsVO> quoteStatistics(Long deptId);

    /**
     * 个人存证被引用top10
     *
     * @param deptId 机构id
     * @return top引用数据
     * @author 34277
     * @date 2020/9/3
     */
    List<StatisticsVO> quoteTop10PersonalStatistics(Long deptId);

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
     * 存证被引用top10
     *
     * @return top引用数据
     * @author 34277
     * @date 2020/9/3
     */
    List<StatisticsVO> quoteTop10AllStatistics();

    /**
     * 查询keyId是否存在
     *
     * @param keyId 要查询的keyId
     * @return 是否存在
     * @author FY
     * @date 2020/09/22
     */
    boolean keyIdExist(String keyId);

    List<EvidenceNodeVO> selectEvidenceNodeVOList(EvidenceNode evidenceNode);

    /**
     * 判断存证是否存在
     *
     * @param evidenceNode 存证节点
     * @return 是否存在
     * @author LIDD
     * @date 2020/09/29
     */
    boolean evidenceNodeExist(EvidenceNode evidenceNode);

    List<EvidenceNode> selectByEvidenceIdWithoutCopyLeafNode(Long evidenceId);
}
