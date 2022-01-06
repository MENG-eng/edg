package com.hzdl.cms.controller;

import com.hzdl.cms.domain.Article;
import com.hzdl.cms.dto.ArticleDto;
import com.hzdl.cms.service.IArticleService;
import com.hzdl.cms.vo.ArticleVo;
import com.hzdl.common.annotation.Log;
import com.hzdl.common.core.controller.BaseController;
import com.hzdl.common.core.domain.AjaxResult;
import com.hzdl.common.core.page.TableDataInfo;
import com.hzdl.common.enums.BusinessType;
import com.hzdl.common.utils.SecurityUtils;
import com.hzdl.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章管理Controller
 *
 * @author hzdl
 * @date 2020-08-26
 */
@Api(tags = "内容管理")
@RestController
@RequestMapping("/cms/article")
public class ArticleController extends BaseController
{
    @Autowired
    private IArticleService articleService;

    /**
     * 查询文章管理列表
     */
    @ApiOperation("查询文章管理列表")
    // 检查用户是否有权限
    @PreAuthorize("@ss.hasPermi('cms:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(Article article)
    {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }

    /**
     * 查询文章管理列表
     */
    @ApiOperation("查询文章管理列表VO")
    @PreAuthorize("@ss.hasPermi('cms:article:list')")
    @GetMapping("/list/vo")
    public TableDataInfo listVo(ArticleDto articleDto)
    {
        startPage();
        List<ArticleVo> list = articleService.selectArticleListVo(articleDto);
        return getDataTable(list);
    }

    /**
     * 导出文章管理列表
     */
    @ApiOperation("导出文章管理列表")
    @PreAuthorize("@ss.hasPermi('cms:article:export')")
    @Log(title = "文章管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Article article)
    {
        List<Article> list = articleService.selectArticleList(article);
        ExcelUtil<Article> util = new ExcelUtil<Article>(Article.class);
        return util.exportExcel(list, "article");
    }

    /**
     * !获取文章管理详细信息
     */
    @ApiOperation("!获取文章管理详细信息")
    @PreAuthorize("@ss.hasPermi('cms:article:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId)
    {
        return AjaxResult.success(articleService.selectArticleById(articleId));
    }

    /**
     * 获取文章管理详细信息
     */
    @ApiOperation("获取文章管理详细信息VO")
    @PreAuthorize("@ss.hasPermi('cms:article:query')")
    @GetMapping(value = "/vo/{articleId}")
    public AjaxResult getInfoVo(@PathVariable("articleId") Long articleId)
    {
        return AjaxResult.success(articleService.selectArticleVoById(articleId));
    }

    /**
     * 新增文章管理
     */
    @ApiOperation("新增文章管理")
    @PreAuthorize("@ss.hasPermi('cms:article:add')")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Article article)
    {
        // 设置机构id
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        article.setDeptId(deptId);
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改文章管理
     */
    @ApiOperation("修改文章管理")
    @PreAuthorize("@ss.hasPermi('cms:article:edit')")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Article article)
    {
        return toAjax(articleService.updateArticle(article));
    }

    /**
     * !删除文章管理
     */
    @ApiOperation("!删除文章管理")
    @PreAuthorize("@ss.hasPermi('cms:article:remove')")
    @Log(title = "文章管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds)
    {
        return toAjax(articleService.deleteArticleByIds(articleIds));
    }

    /**
     * 根据存证类型统计文章
     */
    @ApiOperation("根据存证类型统计文章")
    @PreAuthorize("@ss.hasPermi('cms:article:list')")
    @Log(title = "文章管理", businessType = BusinessType.OTHER)
    @GetMapping("/count/type")
    public AjaxResult countByType()
    {
        return AjaxResult.success(articleService.countByType());
    }

    /**
     * 根据部门统计文章及存证类型
     */
    @ApiOperation("根据部门统计文章及存证类型")
    @PreAuthorize("@ss.hasPermi('cms:article:list')")
    @Log(title = "文章管理", businessType = BusinessType.OTHER)
    @GetMapping("/count/dept")
    public AjaxResult countByDept()
    {
        return AjaxResult.success(articleService.countByDept());
    }
}
