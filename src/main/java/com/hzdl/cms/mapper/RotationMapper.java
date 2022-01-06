package com.hzdl.cms.mapper;

import com.hzdl.cms.domain.Rotation;
import com.hzdl.cms.vo.RotationVo;

import java.util.List;

/**
 * 轮播图管理Mapper接口
 *
 * @author hzdl
 * @date 2020-08-28
 */
public interface RotationMapper {
    /**
     * 查询轮播图管理
     *
     * @param rotationId 轮播图管理ID
     * @return 轮播图管理
     */
    Rotation selectRotationById(Long rotationId);

    /**
     * 查询轮播图Vo
     *
     * @param rotationId 轮播图管理ID
     * @return 轮播图Vo
     */
    RotationVo selectRotationVoById(Long rotationId);

    /**
     * 查询轮播图管理列表
     *
     * @param rotation 轮播图管理
     * @return 轮播图管理集合
     */
    List<Rotation> selectRotationList(Rotation rotation);

    /**
     * 查询轮播图管理列表Vo
     *
     * @param rotation 轮播图管理
     * @return 轮播图管理集合
     */
    List<RotationVo> selectRotationListVo(Rotation rotation);

    /**
     * 新增轮播图管理
     *
     * @param rotation 轮播图管理
     * @return 结果
     */
    int insertRotation(Rotation rotation);

    /**
     * 修改轮播图管理
     *
     * @param rotation 轮播图管理
     * @return 结果
     */
    int updateRotation(Rotation rotation);

    /**
     * 删除轮播图管理
     *
     * @param rotationId 轮播图管理ID
     * @return 结果
     */
    int deleteRotationById(Long rotationId);

    /**
     * 批量删除轮播图管理
     *
     * @param rotationIds 需要删除的数据ID
     * @return 结果
     */
    int deleteRotationByIds(Long[] rotationIds);
}
