package com.hzdl.edg.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hzdl.edg.domain.EvidenceType;
import com.hzdl.edg.domain.vo.CommonCountVo;


/**
 * 存证类型Service接口
 *
 * @author hzdl
 * @date 2020-08-17
 */
public interface IEvidenceTypeService {
    /**
     * 查询存证类型
     *
     * @param id 存证类型ID
     * @return 存证类型
     */
    EvidenceType selectEvidenceTypeById(Long id);

    /**
     * 查询存证类型列表
     *
     * @param evidenceType 存证类型
     * @return 存证类型集合
     */
    List<EvidenceType> selectEvidenceTypeList(EvidenceType evidenceType);

    /**
     * 新增存证类型
     *
     * @param evidenceType 存证类型
     * @return 结果
     */
    int insertEvidenceType(EvidenceType evidenceType);

    /**
     * 修改存证类型
     *
     * @param evidenceType 存证类型
     * @return 结果
     */
    int updateEvidenceType(EvidenceType evidenceType);

    /**
     * 批量删除存证类型
     *
     * @param ids 需要删除的存证类型ID
     * @return 结果
     */
    int deleteEvidenceTypeByIds(Long[] ids);

    /**
     * 删除存证类型信息
     *
     * @param id 存证类型ID
     * @return 结果
     */
    int deleteEvidenceTypeById(Long id);

    /**
     * 得到所有的存证类型id
     * @return
     */
    Set<Long> selectIds();

    /**
     * 得到所有的一级存证类型id
     */
    Map<Long, String> selectTopIds();

    /**
     * 得到一级存证类型id下的其他id
     * 包含一级id
     */
    Map<Long, Set<Long>> selectAllIds();

    /**
     * 根据一级存证类型统计部门
     */
    List<CommonCountVo> countDeptByType();

    /**
     * 根据一级存证类型统计
     * 一级存证类型计入
     * @return
     */
    List<CommonCountVo> countByType();

    /**
     * 根据存证类型查下属类型,包含自己
     */
    Set<Long> selectSonIds(Long id);


}
