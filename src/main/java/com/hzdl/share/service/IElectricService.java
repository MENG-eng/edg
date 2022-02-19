package com.hzdl.share.service;

import com.hzdl.share.domain.Electric;

import java.util.List;

/**
 * 电量数据Service接口
 *
 * @author hzdl
 * @date 2022-02-14
 */
public interface IElectricService {
    /**
     * 查询电量数据列表
     *
     * @param electric 电量数据
     * @return 电量数据集合
     */
    List<Electric> selectElectricList(Electric electric);


}
