package com.hzdl.cms.service.impl;

import com.hzdl.api.vo.ArticleCountVo;
import com.hzdl.api.vo.ArticleDetailNewVo;
import com.hzdl.api.vo.ArticleNewVo;
import com.hzdl.cms.domain.Article;
import com.hzdl.cms.dto.ArticleDto;
import com.hzdl.cms.mapper.ArticleMapper;
import com.hzdl.cms.service.IArticleService;
import com.hzdl.cms.vo.ArticleTypeDeptVo;
import com.hzdl.cms.vo.ArticleVo;
import com.hzdl.common.utils.DateUtils;
import com.hzdl.common.utils.SecurityUtils;
import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.EvidenceNode;
import com.hzdl.edg.domain.EvidenceType;
import com.hzdl.edg.domain.vo.ArticleEvidenceVo;
import com.hzdl.edg.domain.vo.CommonCountVo;
import com.hzdl.edg.mapper.EvidenceMapper;
import com.hzdl.edg.mapper.EvidenceNodeMapper;
import com.hzdl.edg.mapper.EvidenceTypeMapper;
import com.hzdl.edg.service.IEvidenceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 文章管理Service业务层处理
 *
 * @author hzdl
 * @date 2020-08-26
 */
@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IEvidenceTypeService evidenceTypeService;

    @Autowired
    private EvidenceNodeMapper evidenceNodeMapper;


    @Autowired
    private EvidenceTypeMapper evidenceTypeMapper;
    /**
     * 查询文章
     *
     * @param articleId 文章ID
     * @return 文章
     */
    @Override
    public Article selectArticleById(Long articleId) {
        return articleMapper.selectArticleById(articleId);
    }

    /**
     * 查询最新文章详情
     * @param articleId
     * @return Article
     */
    @Override
    public ArticleDetailNewVo selectArticleDetailNewVoById(Long articleId) {
        ArticleDetailNewVo articleDetailNewVo = articleMapper.selectArticleDetailNewVoById(articleId);
        List<EvidenceNode> evidenceNodeList = evidenceNodeMapper.selectByEvidenceId(articleDetailNewVo.getEvidenceId());
        articleDetailNewVo.setEvidenceNodeList(evidenceNodeList);

        return articleDetailNewVo;
    }

    /**
     * 查询文章Vo
     *
     * @param articleId 文章ID
     * @return 文章Vo
     */
    @Override
    public ArticleVo selectArticleVoById(Long articleId) {
        return articleMapper.selectArticleVoById(articleId);
    }

    /**
     * 查询文章管理列表
     *
     * @param article 文章管理
     * @return 文章管理
     */
    @Override
    public List<Article> selectArticleList(Article article) {
        return articleMapper.selectArticleList(article);
    }

    /**
     * 查询文章管理列表Vo
     *
     * @param articleDto 文章管理
     * @return 文章管理
     */
    @Override
    public List<ArticleVo> selectArticleListVo(ArticleDto articleDto) {
        return articleMapper.selectArticleListVo(articleDto);
    }

    /**
     * 查询最新文章管理列表Vo
     *
     * @param article 文章管理
     * @return 文章管理
     */
    @Override
    public List<ArticleNewVo> selectArticleListNewVo(Article article) {
        if(null!=article&&null!=article.getParams()){
            if(null!=article.getParams().get("evidenceType")){
                Long  pid=Long.parseLong(article.getParams().get("evidenceType").toString());
                List<Long> childEvidenceType=new ArrayList<Long>();
                EvidenceType evidenceType = new EvidenceType();
                evidenceType.setEnable(true);
                List<EvidenceType> evidenceTypeList=evidenceTypeMapper.selectEvidenceTypeList(evidenceType);
                childEvidenceType=treeEvidenceTypeList(evidenceTypeList,pid,childEvidenceType);
                childEvidenceType.add(pid);
                article.getParams().put("evidenceTypes", childEvidenceType);
            }
        }else{
            article.getParams().put("evidenceTypes", null);
        }
        return articleMapper.selectArticleListNewVo(article);
    }

    /**
     * 查询文章月统计
     * @param deptId
     * @return List<ArticleCountVo>
     */
    @Override
    public List<ArticleCountVo> countMonth(Long deptId) {
        return articleMapper.countMonths(deptId);
    }

    /**
     * 查询文章日统计
     * @param deptId 机构id
     * @param year 年
     * @param month 月
     * @return List<ArticleCountVo>
     */
    @Override
    public List<ArticleCountVo> countDays(Long deptId, Integer year, Integer month) {
        return articleMapper.countDays(deptId,year,month);
    }

    /**
     * 查询文章统计
     * @param deptId 机构id
     * @return List<ArticleCountVo>
     */
    @Override
    public List<ArticleCountVo> countCategory(Long deptId) {
        return articleMapper.countCategory(deptId);
    }

    /**
     * 查询文章标签统计
     * @param deptId 机构id
     * @return List<ArticleCountVo>
     */
    @Override
    public List<ArticleCountVo> countTags(Long deptId) {
        return articleMapper.countTags(deptId);
    }

    /**
     * 根据一级存证类型统计文章
     * 一级存证类型也计入
     * @return
     */
    @Override
    public List<CommonCountVo> countByType() {
        List<ArticleEvidenceVo> list = articleMapper.selectArticleEvidenceList();
        List<CommonCountVo> countList = new ArrayList<>();
        //避免每次都要对countList进行遍历
        //在数据收集后只进行一次遍历
        HashMap<Long, CommonCountVo> countMap = new HashMap<>();

        Map<Long, String> topIds = evidenceTypeService.selectTopIds();
        Map<Long, Set<Long>> allIds = evidenceTypeService.selectAllIds();

        for (Long id : topIds.keySet()) {
            CommonCountVo vo = new CommonCountVo();
            vo.setId(id);
            vo.setName(topIds.get(id));
            vo.setCount(0);
            countMap.put(id, vo);
        }

        for (ArticleEvidenceVo vo : list) {
            for (Long id : topIds.keySet()) {
                if (Objects.equals(id, vo.getEvidenceTypeId()) || allIds.get(id).contains(vo.getEvidenceTypeId())) {
                    CommonCountVo countVo = countMap.get(id);
                    countVo.setCount(countVo.getCount() + 1);
                }
            }
        }

        for (Long id : countMap.keySet()) {
            countList.add(countMap.get(id));
        }

        return countList;
    }

    /**
     * 根据部门统计文章数和文章类型数
     * @return
     */

    @Override
    public List<ArticleTypeDeptVo> countByDept() {
        List<ArticleTypeDeptVo> list = articleMapper.selectArticleTypeDept();
        return list;
    }


    /**
     * 新增文章管理
     *
     * @param article 文章管理
     * @return 结果
     */
    @Override
    public int insertArticle(Article article) {
        article.setCreateBy(SecurityUtils.getUsername());
        article.setCreateTime(DateUtils.getNowDate());
        article.setUpdateBy(SecurityUtils.getUsername());
        article.setUpdateTime(DateUtils.getNowDate());
        return articleMapper.insertArticle(article);
    }

    /**
     * 修改文章管理
     *
     * @param article 文章管理
     * @return 结果
     */
    @Override
    public int updateArticle(Article article) {
        article.setUpdateBy(SecurityUtils.getUsername());
        article.setUpdateTime(DateUtils.getNowDate());
        return articleMapper.updateArticle(article);
    }

    /**
     * 批量删除文章管理
     *
     * @param articleIds 需要删除的文章管理ID
     * @return 结果
     */
    @Override
    public int deleteArticleByIds(Long[] articleIds) {
        return articleMapper.deleteArticleByIds(articleIds);
    }

    /**
     * 删除文章管理信息
     *
     * @param articleId 文章管理ID
     * @return 结果
     */
    @Override
    public int deleteArticleById(Long articleId) {
        return articleMapper.deleteArticleById(articleId);
    }
    /**
     * 获取某个父节点下面的所有子节点
     * @param evidenceTypeList
     * @param pid
     * @param childEvidenceType
     * @return
     */
    public static List<Long> treeEvidenceTypeList(List<EvidenceType> evidenceTypeList, Long pid,List<Long> childEvidenceType) {
        for (int i=0;i<evidenceTypeList.size();i++) {
            EvidenceType type=evidenceTypeList.get(i);
            //遍历出父id等于参数的id，add进子节点集合
            if (type.getParentId().longValue() ==pid.longValue()) {
                //递归遍历下一级
                treeEvidenceTypeList(evidenceTypeList, type.getId(),childEvidenceType);
                childEvidenceType.add(type.getId());
            }
        }
        return childEvidenceType;
    }

    /*
     * 根据机构id统计该机构发布的文章总数
     * @return
     */
    @Override
	public Integer countGroupByDept(Long deptId) {
		return articleMapper.countGroupByDept(deptId);
	}

}
