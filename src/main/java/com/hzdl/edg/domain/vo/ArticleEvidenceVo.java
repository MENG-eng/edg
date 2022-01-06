package com.hzdl.edg.domain.vo;

/**
 * 文章存证关联查询结果
 */
public class ArticleEvidenceVo {
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 存证id
     */
    private Long evidenceId;
    /**
     * 存证类型id
     */
    private Long evidenceTypeId;

    public ArticleEvidenceVo() {
    }

    public ArticleEvidenceVo(Long articleId, Long evidenceId, Long evidenceTypeId) {
        this.articleId = articleId;
        this.evidenceId = evidenceId;
        this.evidenceTypeId = evidenceTypeId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Long evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Long getEvidenceTypeId() {
        return evidenceTypeId;
    }

    public void setEvidenceTypeId(Long evidenceTypeId) {
        this.evidenceTypeId = evidenceTypeId;
    }

    @Override
    public String toString() {
        return "ArticleEvidenceVo{" +
                "articleId=" + articleId +
                ", evidenceId=" + evidenceId +
                ", evidenceTypeId=" + evidenceTypeId +
                '}';
    }
}
