package com.hzdl.edg.mapper;

import com.hzdl.edg.domain.EvidenceException;

import java.util.List;

/**
 * 存证异常Mapper接口
 *
 * @author hzdl
 * @date 2020-08-17
 */
public interface EvidenceExceptionMapper {
    /**
     * 查询存证异常
     *
     * @param id 存证异常ID
     * @return 存证异常
     */
    EvidenceException selectEvidenceExceptionById(Long id);

    /**
     * 查询存证异常列表
     *
     * @param evidenceException 存证异常
     * @return 存证异常集合
     */
    List<EvidenceException> selectEvidenceExceptionList(EvidenceException evidenceException);

    /**
     * 新增存证异常
     *
     * @param evidenceException 存证异常
     * @return 结果
     */
    int insertEvidenceException(EvidenceException evidenceException);

    /**
     * 修改存证异常
     *
     * @param evidenceException 存证异常
     * @return 结果
     */
    int updateEvidenceException(EvidenceException evidenceException);

    /**
     * 删除存证异常
     *
     * @param id 存证异常ID
     * @return 结果
     */
    int deleteEvidenceExceptionById(Long id);

    /**
     * 批量删除存证异常
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEvidenceExceptionByIds(Long[] ids);
}
