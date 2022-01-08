package com.hzdl.api.controller;

import com.hzdl.api.vo.ArticleCountVo;
import com.hzdl.api.vo.ArticleDetailNewVo;
import com.hzdl.api.vo.ArticleNewVo;
import com.hzdl.cms.domain.Article;
import com.hzdl.cms.service.IArticleService;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
import com.hzdl.system.domain.SysNotice;
import com.hzdl.system.service.ISysNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 文章管理Controller
 *
 * @author hzdl
 * @date 2020-08-26
 */
@Api(tags = "博客接口")
@RestController
@RequestMapping("/api/cms/article")
public class ApiArticleController extends BaseController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ISysNoticeService noticeService;


    /**
     * 获取文章月份统计列表
     */
    @ApiOperation("获取文章月份统计列表")
    @GetMapping("/count/month")
    public TableDataInfo countMonth(@ApiParam(value = "机构id", required = true) @NotNull(message = "机构id不能为空！") Long deptId) {
        List<ArticleCountVo> list = articleService.countMonth(deptId);
        return getDataTable(list);
    }

    /**
     * 获取文章日份统计列表
     */
    @ApiOperation("获取文章日份统计列表")
    @GetMapping("/count/days")
    public TableDataInfo countDays(@ApiParam(value = "机构id", required = true) @NotNull(message = "机构id不能为空！") Long deptId,
                                   @ApiParam(value = "年份", required = true) Integer year,
                                   @ApiParam(value = "月份", required = true) Integer month) {
        List<ArticleCountVo> list = articleService.countDays(deptId, year, month);
        return getDataTable(list);
    }

    /**
     * 获取文章栏目统计列表
     */
    @ApiOperation("获取文章栏目统计列表")
    @GetMapping("/count/category")
    public TableDataInfo countCategory(@ApiParam(value = "机构id", required = true) @NotNull(message = "机构id不能为空！") Long deptId) {
        List<ArticleCountVo> list = articleService.countCategory(deptId);
        return getDataTable(list);
    }

    /**
     * 获取文章标签统计列表
     */
    @ApiOperation("获取文章标签统计列表")
    @GetMapping("/count/tags")
    public TableDataInfo countTags(@ApiParam(value = "机构id", required = true) @NotNull(message = "机构id不能为空！") Long deptId) {
        List<ArticleCountVo> list = articleService.countTags(deptId);
        return getDataTable(list);
    }

    /**
     * 获取文章列表
     */
    @ApiOperation("获取最新文章列表")
    @GetMapping("/list/new")
    public TableDataInfo listNewVo(@ApiParam(value = "机构id", required = true) @NotNull(message = "机构id不能为空！") Long deptId,
                                   @ApiParam(value = "栏目id") Long categoryId,
                                   @ApiParam(value = "数据类型") Long evidenceType,
                                   @ApiParam(value = "月维度") String monthDimension,
                                   @ApiParam(value = "日维度") String dayDimension,
                                   @ApiParam(value = "关键词") String keywords,
                                   @ApiParam(value = "标签id") String tagId) {
        Article article = new Article();
        article.setDeptId(deptId);
        article.setCategoryId(categoryId);
        article.setKeywords(keywords);
        article.getParams().put("evidenceType", evidenceType);
        article.getParams().put("monthDimension", monthDimension);
        article.getParams().put("dayDimension", dayDimension);
        article.getParams().put("tagId", tagId);
        startPage();
        List<ArticleNewVo> list = articleService.selectArticleListNewVo(article);
        return getDataTable(list);
    }

    /**
     * 获取最新文章详情
     */
    @ApiOperation("获取最新文章详情")
    @GetMapping("/detail/new/{articleId}")
    public AjaxResult listNewVo(@ApiParam(value = "机构id", required = true) @NotNull(message = "机构id不能为空！") @PathVariable("articleId") Long articleId) {
        ArticleDetailNewVo articleDetailNewVo = articleService.selectArticleDetailNewVoById(articleId);
        return AjaxResult.success(articleDetailNewVo);
    }

    /**
     * 获取所有公告
     */
    @ApiOperation("获取所有公告")
    @GetMapping("/notice/list")
    public AjaxResult getNoticeList() {
        SysNotice sysNotice = new SysNotice();
        sysNotice.setStatus("0");
        List<SysNotice> sysNotices = noticeService.selectNoticeList(sysNotice);
        return AjaxResult.success(sysNotices);
    }


}
