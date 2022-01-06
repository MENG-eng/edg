package com.hzdl.cms.vo;

/**
 * 根据部门统计文章及类型用VO
 */
public class ArticleTypeDeptVo {
    /**
     * 部门id
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 文章类型统计
     */
    private int countType;

    /**
     * 文章数统计
     */
    private int countArticle;

    public ArticleTypeDeptVo() {
    }

    public ArticleTypeDeptVo(Long id, String name, int countType, int countArticle) {
        this.id = id;
        this.name = name;
        this.countType = countType;
        this.countArticle = countArticle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountType() {
        return countType;
    }

    public void setCountType(int countType) {
        this.countType = countType;
    }

    public int getCountArticle() {
        return countArticle;
    }

    public void setCountArticle(int countArticle) {
        this.countArticle = countArticle;
    }

    @Override
    public String toString() {
        return "ArticleTypeDeptVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countType=" + countType +
                ", countArticle=" + countArticle +
                '}';
    }
}
