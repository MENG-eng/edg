package com.hzdl.cms.service.impl;

import com.hzdl.cms.domain.Rotation;
import com.hzdl.cms.mapper.RotationMapper;
import com.hzdl.cms.service.IRotationService;
import com.hzdl.cms.vo.RotationVo;
import com.hzdl.common.utils.DateUtils;
import com.hzdl.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图管理Service业务层处理
 *
 * @author hzdl
 * @date 2020-08-28
 */
@Service
public class RotationServiceImpl implements IRotationService {
    @Autowired
    private RotationMapper rotationMapper;

    /**
     * 查询轮播图管理
     *
     * @param rotationId 轮播图管理ID
     * @return 轮播图管理
     */
    @Override
    public Rotation selectRotationById(Long rotationId) {
        return rotationMapper.selectRotationById(rotationId);
    }

    /**
     * 查询轮播图Vo
     *
     * @param rotationId 轮播图管理ID
     * @return 轮播图Vo
     */
    @Override
    public RotationVo selectRotationVoById(Long rotationId) {
        return rotationMapper.selectRotationVoById(rotationId);
    }

    /**
     * 查询轮播图管理列表
     *
     * @param rotation 轮播图管理
     * @return 轮播图管理
     */
    @Override
    public List<Rotation> selectRotationList(Rotation rotation) {
        return rotationMapper.selectRotationList(rotation);
    }

    /**
     * 查询轮播图管理列表Vo
     *
     * @param rotation 轮播图管理
     * @return 轮播图管理
     */
    @Override
    public List<RotationVo> selectRotationListVo(Rotation rotation) {
        return rotationMapper.selectRotationListVo(rotation);
    }

    /**
     * 新增轮播图管理
     *
     * @param rotation 轮播图管理
     * @return 结果
     */
    @Override
    public int insertRotation(Rotation rotation) {
        rotation.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        rotation.setCreateBy(SecurityUtils.getUsername());
        rotation.setCreateTime(DateUtils.getNowDate());
        rotation.setUpdateBy(SecurityUtils.getUsername());
        rotation.setUpdateTime(DateUtils.getNowDate());
        return rotationMapper.insertRotation(rotation);
    }

    /**
     * 修改轮播图管理
     *
     * @param rotation 轮播图管理
     * @return 结果
     */
    @Override
    public int updateRotation(Rotation rotation) {
        rotation.setUpdateBy(SecurityUtils.getUsername());
        rotation.setUpdateTime(DateUtils.getNowDate());
        return rotationMapper.updateRotation(rotation);
    }

    /**
     * 批量删除轮播图管理
     *
     * @param rotationIds 需要删除的轮播图管理ID
     * @return 结果
     */
    @Override
    public int deleteRotationByIds(Long[] rotationIds) {
        return rotationMapper.deleteRotationByIds(rotationIds);
    }

    /**
     * 删除轮播图管理信息
     *
     * @param rotationId 轮播图管理ID
     * @return 结果
     */
    @Override
    public int deleteRotationById(Long rotationId) {
        return rotationMapper.deleteRotationById(rotationId);
    }
}
