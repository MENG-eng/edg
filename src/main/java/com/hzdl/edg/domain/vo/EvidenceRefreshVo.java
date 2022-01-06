package com.hzdl.edg.domain.vo;

/**
 * 统计存证刷新率VO
 */
public class EvidenceRefreshVo {

    /**
     * 存证类型id
     */
    private Long typeId;
    /**
     * 存证id
     */
    private Long evidenceId;

    /**
     * 最小创建时间
     */
    private String minCreateTime;

    /**
     * 最大创建时间
     */
    private String maxCreateTime;

    /**
     * 祖先列表
     */
    private String ancestors;

    public EvidenceRefreshVo() {
    }

    public EvidenceRefreshVo(Long typeId, Long evidenceId, String minCreateTime, String maxCreateTime, String ancestors) {
        this.typeId = typeId;
        this.evidenceId = evidenceId;
        this.minCreateTime = minCreateTime;
        this.maxCreateTime = maxCreateTime;
        this.ancestors = ancestors;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Long evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getMinCreateTime() {
        return minCreateTime;
    }

    public void setMinCreateTime(String minCreateTime) {
        this.minCreateTime = minCreateTime;
    }

    public String getMaxCreateTime() {
        return maxCreateTime;
    }

    public void setMaxCreateTime(String maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    @Override
    public String toString() {
        return "EvidenceRefreshVo{" +
                "typeId=" + typeId +
                ", evidenceId=" + evidenceId +
                ", minCreateTime='" + minCreateTime + '\'' +
                ", maxCreateTime='" + maxCreateTime + '\'' +
                ", ancestors='" + ancestors + '\'' +
                '}';
    }
}
