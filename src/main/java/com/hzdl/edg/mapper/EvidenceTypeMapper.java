package com.hzdl.edg.mapper;

import java.util.List;
import java.util.Set;

import com.hzdl.edg.domain.EvidenceType;

/**
 * 存证类型Mapper接口
 *
 * @author hzdl
 * @date 2020-08-17
 */
public interface EvidenceTypeMapper {
    /**
     * 查询存证类型
     *
     * @param id 存证类型ID
     * @return 存证类型
     */
    EvidenceType selectEvidenceTypeById(Long id);


    /**
     * 查询存证类型id,parentId,部门id,ancestors,和存证类型名称
     * @return
     */
    List<EvidenceType> selectIdNameAndAncestors();

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
     * 删除存证类型
     *
     * @param id 存证类型ID
     * @return 结果
     */
    int deleteEvidenceTypeById(Long id);

    /**
     * 批量删除存证类型
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEvidenceTypeByIds(Long[] ids);

    /**
     * 通过id查询祖先列表
     * @param id
     * @return
     */
    String selectAncestorsById(Long id);

    /**
     * 查询所有存证类型id
     * @return
     */
    Set<Long> selectIds();
}
