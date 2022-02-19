package com.hzdl.share.service;

import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.vo.EvidenceVO;
import com.hzdl.share.domain.Subsidy;
import com.hzdl.share.domain.vo.SubsidyVO;

import java.util.List;

/**
 * 补贴Service接口
 *
 * @author hzdl
 * @date 2022-02-18
 */
public interface ISubsidyService {
    /**
     * 查询补贴列表
     *
     * @param subsidy 补贴
     * @return 补贴集合
     */
    List<Subsidy> selectSubsidyList(Subsidy subsidy);

    /**
     * 新增补贴
     *
     * @param subsidy 补贴
     * @return 结果
     */
    int insertSubsidy(Subsidy subsidy);

    /**
     * 创建存证
     *
     * @param vo 存证内容
     * @return 是否创建成功
     * @author 34277
     * @date 2020/9/1
     */
    AjaxResult create(SubsidyVO vo);
}
