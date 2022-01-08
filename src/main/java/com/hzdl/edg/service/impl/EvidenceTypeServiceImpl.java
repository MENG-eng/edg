package com.hzdl.edg.service.impl;

import java.util.*;

import com.hzdl.common.utils.DateUtils;
import com.hzdl.common.utils.SecurityUtils;
import com.hzdl.edg.domain.vo.CommonCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hzdl.edg.mapper.EvidenceTypeMapper;
import com.hzdl.edg.domain.EvidenceType;
import com.hzdl.edg.service.IEvidenceTypeService;

/**
 * 存证类型Service业务层处理
 *
 * @author hzdl
 * @date 2020-08-17
 */
@Service
public class EvidenceTypeServiceImpl implements IEvidenceTypeService {
    @Autowired
    private EvidenceTypeMapper evidenceTypeMapper;

    /**
     * 查询存证类型
     *
     * @param id 存证类型ID
     * @return 存证类型
     */
    @Override
    public EvidenceType selectEvidenceTypeById(Long id) {
        return evidenceTypeMapper.selectEvidenceTypeById(id);
    }

    /**
     * 查询存证类型列表
     *
     * @param evidenceType 存证类型
     * @return 存证类型
     */
    @Override
    public List<EvidenceType> selectEvidenceTypeList(EvidenceType evidenceType) {
        return evidenceTypeMapper.selectEvidenceTypeList(evidenceType);
    }

    /**
     * 新增存证类型
     *
     * @param evidenceType 存证类型
     * @return 结果
     */
    @Override
    public int insertEvidenceType(EvidenceType evidenceType) {
        evidenceType.setCreateBy(SecurityUtils.getUsername());
        evidenceType.setCreateTime(DateUtils.getNowDate());
        String parentAncestors = evidenceTypeMapper.selectAncestorsById(evidenceType.getParentId());
        evidenceType.setAncestors(parentAncestors +","+ evidenceType.getParentId().toString());
        return evidenceTypeMapper.insertEvidenceType(evidenceType);
    }

    /**
     * 修改存证类型
     *
     * @param evidenceType 存证类型
     * @return 结果
     */
    @Override
    public int updateEvidenceType(EvidenceType evidenceType) {
        evidenceType.setUpdateBy(SecurityUtils.getUsername());
        evidenceType.setUpdateTime(DateUtils.getNowDate());
        return evidenceTypeMapper.updateEvidenceType(evidenceType);
    }

    /**
     * 批量删除存证类型
     *
     * @param ids 需要删除的存证类型ID
     * @return 结果
     */
    @Override
    public int deleteEvidenceTypeByIds(Long[] ids) {
        return evidenceTypeMapper.deleteEvidenceTypeByIds(ids);
    }

    /**
     * 删除存证类型信息
     *
     * @param id 存证类型ID
     * @return 结果
     */
    @Override
    public int deleteEvidenceTypeById(Long id) {
        return evidenceTypeMapper.deleteEvidenceTypeById(id);
    }

    /**
     * 得到所有存证类型id
     */
    @Override
    public Set<Long> selectIds() {
        Set<Long> set = evidenceTypeMapper.selectIds();
        return set;
    }

    /**
     * 得到所有一级存证类型id及name
     * @return
     */
    @Override
    public Map<Long, String> selectTopIds() {
        List<EvidenceType> list =  evidenceTypeMapper.selectIdNameAndAncestors();
        Map<Long, String> topIds = new HashMap<>();
        for (EvidenceType eviType : list) {
            if (Objects.equals(Long.valueOf(0), eviType.getParentId())) {
                topIds.put(eviType.getId(), eviType.getName());
            }
        }
        return topIds;
    }

    /**
     * 得到一级存证类型id及其下的其他id
     * 包含自身
     * @return
     */
    @Override
    public Map<Long, Set<Long>> selectAllIds() {
        List<EvidenceType> list = evidenceTypeMapper.selectIdNameAndAncestors();
        List<Long> topIds = new ArrayList<>();
        Map<Long, Set<Long>> allIds = new HashMap<>();
        for (EvidenceType eviType : list) {
            if (Objects.equals(Long.valueOf(0), eviType.getParentId())) {
                topIds.add(eviType.getId());
                Set<Long> set = new HashSet<>();
                set.add(eviType.getId());
                allIds.put(eviType.getId(), set);
            }
        }

        for (EvidenceType eviType : list) {
            if (!Objects.equals(Long.valueOf(0), eviType.getParentId())) {
                String[] ancestors = eviType.getAncestors().split(",");
                for (String ancestor : ancestors) {
                    if (topIds.contains(Long.parseLong(ancestor))) {
                        Long ancestorId = Long.parseLong(ancestor);
                        allIds.get(ancestorId).add(eviType.getId());
                    }
                }
            }
        }
        return allIds;
    }

    /**
     * 根据一级存证类型统计部门
     */
    @Override
    public List<CommonCountVo> countDeptByType() {
        List<EvidenceType> list = evidenceTypeMapper.selectIdNameAndAncestors();
        List<CommonCountVo> countList = new ArrayList<>();
        // 一级存证类型id, 一级存证类型及子存证类型下的deptid
        Map<Long, Set<Long>> countMap = new HashMap<>();

        Map<Long, String> topIds = selectTopIds();
        Map<Long, Set<Long>> ids = selectAllIds();

        for (EvidenceType evi : list) {
            if (topIds.keySet().contains(evi.getId())) {
                Set<Long> set = new HashSet<>();
                set.add(evi.getDeptId());
                countMap.put(evi.getId(), set);
                CommonCountVo countVO = new CommonCountVo();
                countVO.setId(evi.getId());
                countVO.setName(evi.getName());
                countList.add(countVO);
            }
        }
        for (EvidenceType evi : list) {
            if (!Objects.equals(Long.valueOf(0), evi.getParentId())) {
                String[] ancestors = evi.getAncestors().split(",");
                for (String ancestor : ancestors) {
                    if (topIds.keySet().contains(Long.parseLong(ancestor))) {
                        Long ancestorId = Long.parseLong(ancestor);
                        countMap.get(ancestorId).add(evi.getDeptId());
                    }
                }
            }
        }
        for (Map.Entry<Long, Set<Long>> entry : countMap.entrySet()) {
            for (CommonCountVo vo : countList) {
                if (Objects.equals(entry.getKey(), vo.getId())) {
                    vo.setCount(entry.getValue().size());
                }
            }
        }
        return countList;
    }



    /**
     * 根据一级存证类型统计存证类型
     * 一级存证计入
     * @return
     */
    @Override
    public List<CommonCountVo> countByType() {
        List<CommonCountVo> countList = new ArrayList<>();

        Map<Long, String> topIds = selectTopIds();
        Map<Long, Set<Long>> allIds = selectAllIds();
        for (Long id : topIds.keySet()) {
            CommonCountVo countVo = new CommonCountVo();
            countVo.setId(id);
            countVo.setName(topIds.get(id));
            countVo.setCount(allIds.get(id).size());
            countList.add(countVo);
        }
        return countList;
    }

    /**
     * 根据存证类型查下属类型,包含自己
     */
    @Override
    public Set<Long> selectSonIds(Long id) {
        List<EvidenceType> list = evidenceTypeMapper.selectIdNameAndAncestors();
        Set<Long> set = new HashSet<>();
        set.add(id);
        for (EvidenceType type : list) {
            if (type.getAncestors().contains(id.toString())) {
                set.add(type.getId());
            }
        }
        return set;

    }

}
