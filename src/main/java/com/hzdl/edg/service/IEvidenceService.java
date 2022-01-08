package com.hzdl.edg.service;

import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.block.CommonResponse;
import com.hzdl.edg.domain.vo.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 存证Service接口
 *
 * @author hzdl
 * @date 2020-08-17
 */
public interface IEvidenceService {
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
     * 批量删除存证
     *
     * @param ids 需要删除的存证ID
     * @return 结果
     */
    int deleteEvidenceByIds(Long[] ids);

    /**
     * 删除存证信息
     *
     * @param id 存证ID
     * @return 结果
     */
    int deleteEvidenceById(Long id);

    /**
     * 创建存证
     *
     * @param vo 存证内容
     * @return 是否创建成功
     * @author 34277
     * @date 2020/9/1
     */
    AjaxResult create(EvidenceVO vo);

    /**
     * 复制存证
     *
     * @param evidenceNodeId 节点id
     * @return 结果
     * @author 34277
     * @date 2020/9/2
     */
    AjaxResult copy(Long evidenceNodeId);

    /**
     * 增加存证
     *
     * @param vo 增加存证信息
     * @return 结果
     * @author 34277
     * @date 2020/9/2
     */
    AjaxResult add(EvidenceAddVO vo);

    /**
     * 修改存证
     *
     * @param vo 修改存证数据
     * @return 结果
     * @author 34277
     * @date 2020/9/2
     */
    AjaxResult modify(EvidenceModifyVO vo);

    /**
     * 个人中心存证列表
     *
     * @return 个人存证
     * @author 34277
     * @date 2020/9/2
     */
    List<EvidenceListVO> personalEvidenceList();

    /**
     * 个人中心操作记录
     *
     * @return 节点记录
     * @author 34277
     * @date 2020/9/2
     */
    List<EvidenceNodeVO> personalEvidenceNodeListLatest();

    /**
     * 所有操作记录
     *
     * @return 节点记录
     * @author 34277
     * @date 2020/9/2
     */
    List<EvidenceNodeVO> allEvidenceNodeListLatest();

    /**
     * 更多个人操作记录
     *
     * @return 节点记录
     * @author 34277
     * @date 2020/9/3
     */
    List<EvidenceNodeVO> personalEvidenceNodeList(Date beginDate, Date endDate, String optType, String fileName, Long evidenceType);

    /**
     * 查询存证列表 按照热度排序
     *
     * @param evidence 查询条件
     * @return 存证列表
     * @author 34277
     * @date 2020/9/3
     */
    List<Evidence> traceCenterList(Evidence evidence);

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
     * 各能源数据本月存证统计
     *
     * @return 统计结果
     * @author 34277
     * @date 2020/9/4
     */
    List<EvidenceMonthStatisticsVO> evidenceMonthStatistics();

    /**
     * 能源数据文件总量
     *
     * @return 文件总量（GB）
     * @author FY
     * @date 2020/9/11
     */
    double fileSize();

    /**
     * 校验存证树
     *
     * @param evidenceId 存证id
     * @return 校验结果
     * @author FY
     * @date 2020/09/14
     */
    CommonResponse verify(Long evidenceId);

    /**
     * 我的存证列表
     *
     * @param evidence 查询参数
     * @return 复制的存证列表
     */
    List<EvidenceListVO> myEvidenceList(Evidence evidence);

    /**
     * 我复制的存证列表
     *
     * @param evidence 查询参数
     * @return 复制的存证列表
     */
    List<EvidenceListVO> copyEvidenceList(Evidence evidence);

    /**
     * 预览
     *
     * @param evidenceNodeId 起始节点id
     * @return 节点列表
     * @author FY
     * @date 2020/09/28
     */
    List<EvidenceNodePreviewVO> detail(Long evidenceNodeId);

    /**
     * 根据一级存证类型统计存证
     */
    List<CommonCountVo> countByType();

    /**
     * 根据一级存证类型统计共享占比
     * @return
     */
    List<EvidenceSharedCountVo> countCopyByType();

    /**
     * 根据一级存证类型统计存证刷新率
     * @return
     */
    List<CommonCountVo> countRefreshRate();

    /**
     * 根据机构id统计该机构发布的数据总数
     * @return
     */
	Integer countGroupByDeptdeptId(Long deptId);
}
