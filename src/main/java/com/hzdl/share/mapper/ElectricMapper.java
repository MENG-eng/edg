package com.hzdl.share.mapper;

import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.vo.*;
import com.hzdl.share.domain.Electric;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 电量Mapper接口
 *
 * @author hzdl
 * @date 2022-02-14
 */
public interface ElectricMapper {
    /**
     * 查询电量数据列表
     *
     * @param electric 电量数据
     * @return 电量数据集合
     */
    List<Electric> selectElectricList(Electric electric);

    ///**
    // * 查询电量数据
    // *
    // * @param electricId 电量ID
    // * @return 电量数据
    // */
    //Electric selectElectricById(Long electricId);
    //
    //
    ///**
    // * 新增电量数据
    // *
    // * @param electric 电量数据
    // * @return 结果
    // */
    //int insertElectric(Electric electric);
    //
    ///**
    // * 修改电量数据
    // *
    // * @param electric 电量数据
    // * @return 结果
    // */
    //int updateElectric(Electric electric);
    //
    ///**
    // * 删除电量数据
    // *
    // * @param electricId 电量ID
    // * @return 结果
    // */
    //int deleteElectricById(Long electricId);
    //
    ///**
    // * 校验电量数据名称是否重复
    // *
    // * @param dateName 要校验的电量数据名称
    // * @return true-重复；false-不重复
    // */
    //boolean validDateName(String dateName);

}
