package com.hzdl.edg.mapper;

import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.vo.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 存证Mapper接口
 *
 * @author hzdl
 * @date 2020-08-17
 */
public interface EvidenceMapper {
    /**
     * 查询存证
     *
     * @param id 存证ID
     * @return 存证
     */
    Evidence selectEvidenceById(Long id);

    /**
     * 查询存证列表
     *
     * @param evidence 存证
     * @return 存证集合
     */
    List<Evidence> selectEvidenceList(Evidence evidence);

    /**
     * 新增存证
     *
     * @param evidence 存证
     * @return 结果
     */
    int insertEvidence(Evidence evidence);

    /**
     * 修改存证
     *
     * @param evidence 存证
     * @return 结果
     */
    int updateEvidence(Evidence evidence);

    /**
     * 删除存证
     *
     * @param id 存证ID
     * @return 结果
     */
    int deleteEvidenceById(Long id);

    /**
     * 批量删除存证
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEvidenceByIds(Long[] ids);

    /**
     * 查询机构的存证
     *
     * @param deptId 机构id
     * @return 存证列表
     * @author 34277
     * @date 2020/9/2
     */
    List<EvidenceListVO> personalEvidenceList(Long deptId);

    /**
     * 个人中心操作记录
     *
     * @param deptId 机构id
     * @return 节点记录
     * @author 34277
     * @date 2020/9/2
     */
    List<EvidenceNodeVO> personalEvidenceNodeListLatest(Long deptId);

    List<EvidenceNodeVO> allEvidenceNodeListLatest();

    /**
     * 更多操作记录
     *
     * @param deptId 机构id
     * @return 节点记录
     * @author 34277
     * @date 2020/9/2
     */
    List<EvidenceNodeVO> personalEvidenceNodeList(Long deptId, Date beginDate, Date endDate,
                                                  String optType,
                                                  String fileName,
                                                  Set<Long> types);

    /**
     * 查询存证列表（按照热度排序）
     *
     * @param evidence 存证
     * @return 存证集合
     */
    List<Evidence> traceCenterList(Evidence evidence, Set<Long> types);

    /**
     * 存证统计
     *
     * @return 统计结果
     * @author 34277
     * @date 2020/9/3
     */
    EvidenceStatisticsVO evidenceStatistics();

    /**
     * 用户活跃度
     *
     * @return 活跃度
     * @author 34277
     * @date 2020/9/4
     */
    double userActivity();

    /**
     * 各类型存证占比统计
     *
     * @return 统计结果
     * @author 34277
     * @date 2020/9/4
     */
    List<StatisticsVO> evidenceTypeStatistics();

    /**
     * 校验存证名称是否重复
     *
     * @param fileName 要校验的存证名称
     * @return true-重复；false-不重复
     * @author 34277
     * @date 2020/9/4
     */
    boolean validFileName(String fileName);

    /**
     * 存证量和异常量统计
     *
     * @return 统计结果
     * @author 34277
     * @date 2020/9/4
     */
    List<EvidenceAndExceptionStatisticsVO> evidenceAndExceptionStatistics();

    /**
     * 能源数据本月存证统计
     *
     * @return 统计结果
     * @author 34277
     * @date 2020/9/4
     */
    List<StatisticsVO> evidenceMonthStatistics(Long evidenceTypeId);

    /**
     * 更新存证热度
     *
     * @param evidence 存证数据
     * @return 更新结果
     * @author 34277
     * @date 2020/9/7
     */
    int updateEvidenceHeat(Evidence evidence);

    List<EvidenceListVO> allEvidenceList(Evidence evidence);

    List<EvidenceListVO> myEvidenceList(Evidence evidence);

    List<EvidenceListVO> copyEvidenceList(Evidence evidence);

    /**
     * 存在节点被复制的存证id
     */
    Set<Long> selectCopiedEviIds();

    /**
     * 根据存证id分别查询节点的最小创建时间,最大创建时间及祖先列表
     * @return
     */
    List<EvidenceRefreshVo> selectTimeAncestor();

    /**
     * 根据机构id统计该机构发布的数据总数
     * @return
     */

	Integer countGroupByDeptdeptId(Long deptId);
}
