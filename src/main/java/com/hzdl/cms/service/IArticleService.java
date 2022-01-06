package com.hzdl.cms.service;

import com.hzdl.api.vo.ArticleCountVo;
import com.hzdl.api.vo.ArticleDetailNewVo;
import com.hzdl.api.vo.ArticleNewVo;
import com.hzdl.cms.domain.Article;
import com.hzdl.cms.dto.ArticleDto;
import com.hzdl.cms.vo.ArticleTypeDeptVo;
import com.hzdl.cms.vo.ArticleVo;
import com.hzdl.edg.domain.vo.CommonCountVo;

import java.util.List;

/**
 * 文章管理Service接口
 *
 * @author hzdl
 * @date 2020-08-26
 */
public interface IArticleService
{
    /**
     * 查询文章管理
     *
     * @param articleId 文章管理ID
     * @return 文章管理
     */
    Article selectArticleById(Long articleId);

    /**
     * 查询最新文章详情
     * @param articleId
     * @return Article
     */
    ArticleDetailNewVo selectArticleDetailNewVoById(Long articleId);

    /**
     * 查询文章Vo
     *
     * @param articleId 文章ID
     * @return 文章Vo
     */
    ArticleVo selectArticleVoById(Long articleId);

    /**
     * 查询文章管理列表
     *
     * @param article 文章管理
     * @return 文章管理集合
     */
    List<Article> selectArticleList(Article article);

    /**
     * 查询文章管理列表Vo
     *
     * @param articleDto 文章管理
     * @return 文章管理
     */
    List<ArticleVo> selectArticleListVo(ArticleDto articleDto);

    /**
     * 查询最新文章管理列表Vo
     *
     * @param article 文章管理
     * @return 文章管理
     */
    List<ArticleNewVo> selectArticleListNewVo(Article article);

    /**
     * 查询文章月统计
     * @param deptId
     * @return List<ArticleCountVo>
     */
    List<ArticleCountVo> countMonth(Long deptId);

    /**
     * 查询文章日统计
     * @param deptId 机构id
     * @param year 年
     * @param month 月
     * @return List<ArticleCountVo>
     */
    List<ArticleCountVo> countDays(Long deptId, Integer year, Integer month);

    /**
     * 查询文章栏目统计
     * @param deptId 机构id
     * @return List<ArticleCountVo>
     */
    List<ArticleCountVo> countCategory(Long deptId);



    /**
     * 新增文章管理
     *
     * @param article 文章管理
     * @return 结果
     */
    int insertArticle(Article article);

    /**
     * 修改文章管理
     *
     * @param article 文章管理
     * @return 结果
     */
    int updateArticle(Article article);

    /**
     * 批量删除文章管理
     *
     * @param articleIds 需要删除的文章管理ID
     * @return 结果
     */
    int deleteArticleByIds(Long[] articleIds);

    /**
     * 删除文章管理信息
     *
     * @param articleId 文章管理ID
     * @return 结果
     */
    int deleteArticleById(Long articleId);


    /**
     *查询文章标签
     * @param deptId
     * @return
     */
    List<ArticleCountVo> countTags(Long deptId);

    /**
     * 根据一级存证类型统计文章
     * @return
     */
    List<CommonCountVo> countByType();

    /**
     * 根据部门统计文章数和存证类型数
     * @return
     */
    List<ArticleTypeDeptVo> countByDept();
    /*
     * 根据机构id统计该机构发布的文章总数
     * @return
     */
    Integer countGroupByDept(Long deptId);
}
