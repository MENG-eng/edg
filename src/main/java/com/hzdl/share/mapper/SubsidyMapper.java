package com.hzdl.share.mapper;

import com.hzdl.edg.domain.Evidence;
import com.hzdl.share.domain.Electric;
import com.hzdl.share.domain.Subsidy;

import java.util.List;

/**
 * 补贴Mapper接口
 *
 * @author hzdl
 * @date 2022-02-18
 */
public interface SubsidyMapper {
    /**
     * 查询补贴数据列表
     *
     * @param subsidy 补贴
     * @return 补贴集合
     */
    List<Subsidy> selectSubsidyList(Subsidy subsidy);

    /**
     * 新增补贴数据
     *
     * @param subsidy 补贴
     * @return 结果
     */
    int insertSubsidy(Subsidy subsidy);
}
