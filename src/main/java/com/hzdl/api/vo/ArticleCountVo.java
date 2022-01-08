package com.hzdl.api.vo;

import java.io.Serializable;

public class ArticleCountVo implements Serializable {
    private static final long serialVersionUID = -882864220877270664L;

    /** 统计数量 */
    private Integer nums;

    /** 维度标识 */
    private Long dimensionId;

    /** 统计维度 */
    private String dimension;

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Long getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Long dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
}
