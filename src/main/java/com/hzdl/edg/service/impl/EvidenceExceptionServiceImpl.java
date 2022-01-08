package com.hzdl.edg.service.impl;

import com.github.pagehelper.PageHelper;
import com.hzdl.common.core.page.PageDomain;
import com.hzdl.common.core.page.TableSupport;
import com.hzdl.common.utils.DateUtils;
import com.hzdl.common.utils.SecurityUtils;
import com.hzdl.common.utils.StringUtils;
import com.hzdl.common.utils.sql.SqlUtil;
import com.hzdl.edg.domain.EvidenceException;
import com.hzdl.edg.mapper.EvidenceExceptionMapper;
import com.hzdl.edg.mapper.EvidenceTypeMapper;
import com.hzdl.edg.service.IEvidenceExceptionService;
import com.hzdl.edg.service.IEvidenceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 存证异常Service业务层处理
 *
 * @author hzdl
 * @date 2020-08-17
 */
@Service
public class EvidenceExceptionServiceImpl implements IEvidenceExceptionService {
    @Autowired
    private EvidenceExceptionMapper evidenceExceptionMapper;
    @Autowired
    private IEvidenceTypeService evidenceTypeService;

    /**
     * 查询存证异常
     *
     * @param id 存证异常ID
     * @return 存证异常
     */
    @Override
    public EvidenceException selectEvidenceExceptionById(Long id) {
        return evidenceExceptionMapper.selectEvidenceExceptionById(id);
    }

    /**
     * 查询存证异常列表
     *
     * @param evidenceException 存证异常
     * @return 存证异常
     */
    @Override
    public List<EvidenceException> selectEvidenceExceptionList(EvidenceException evidenceException) {
        Set<Long> types = new HashSet<>();
        Long type = evidenceException.getEvidenceType();
        if (type != null) {
            types = evidenceTypeService.selectSonIds(type);
        } else {
            types = evidenceTypeService.selectIds();
        }
        evidenceException.getParams().put("types", types);
        startPage();
        return evidenceExceptionMapper.selectEvidenceExceptionList(evidenceException);
    }

    private void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 新增存证异常
     *
     * @param evidenceException 存证异常
     * @return 结果
     */
    @Override
    public int insertEvidenceException(EvidenceException evidenceException) {
        evidenceException.setCreateBy(SecurityUtils.getUsername());
        evidenceException.setCreateTime(DateUtils.getNowDate());
        return evidenceExceptionMapper.insertEvidenceException(evidenceException);
    }

    /**
     * 修改存证异常
     *
     * @param evidenceException 存证异常
     * @return 结果
     */
    @Override
    public int updateEvidenceException(EvidenceException evidenceException) {
        evidenceException.setUpdateBy(SecurityUtils.getUsername());
        evidenceException.setUpdateTime(DateUtils.getNowDate());
        return evidenceExceptionMapper.updateEvidenceException(evidenceException);
    }

    /**
     * 批量删除存证异常
     *
     * @param ids 需要删除的存证异常ID
     * @return 结果
     */
    @Override
    public int deleteEvidenceExceptionByIds(Long[] ids) {
        return evidenceExceptionMapper.deleteEvidenceExceptionByIds(ids);
    }

    /**
     * 删除存证异常信息
     *
     * @param id 存证异常ID
     * @return 结果
     */
    @Override
    public int deleteEvidenceExceptionById(Long id) {
        return evidenceExceptionMapper.deleteEvidenceExceptionById(id);
    }
}
