package com.hzdl.edg.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 存证统计
 *
 * @author 34277
 * @date 2020/9/2
 */
@ApiModel("存证统计")
public class EvidenceStatisticsVO implements Serializable {

    @ApiModelProperty("今日存证量")
    private Integer todayEvidenceCount;
    @ApiModelProperty("昨日存证量")
    private Integer yesterdayEvidenceCount;
    @ApiModelProperty("总存证量")
    private Integer totalEvidenceCount;
    @ApiModelProperty("今日共享次数")
    private Integer todayEvidenceCopyCount;
    @ApiModelProperty("昨日共享次数")
    private Integer yesterdayEvidenceCopyCount;

    public Integer getTodayEvidenceCount() {
        return todayEvidenceCount;
    }

    public void setTodayEvidenceCount(Integer todayEvidenceCount) {
        this.todayEvidenceCount = todayEvidenceCount;
    }

    public Integer getYesterdayEvidenceCount() {
        return yesterdayEvidenceCount;
    }

    public void setYesterdayEvidenceCount(Integer yesterdayEvidenceCount) {
        this.yesterdayEvidenceCount = yesterdayEvidenceCount;
    }

    public Integer getTotalEvidenceCount() {
        return totalEvidenceCount;
    }

    public void setTotalEvidenceCount(Integer totalEvidenceCount) {
        this.totalEvidenceCount = totalEvidenceCount;
    }

    public Integer getTodayEvidenceCopyCount() {
        return todayEvidenceCopyCount;
    }

    public void setTodayEvidenceCopyCount(Integer todayEvidenceCopyCount) {
        this.todayEvidenceCopyCount = todayEvidenceCopyCount;
    }

    public Integer getYesterdayEvidenceCopyCount() {
        return yesterdayEvidenceCopyCount;
    }

    public void setYesterdayEvidenceCopyCount(Integer yesterdayEvidenceCopyCount) {
        this.yesterdayEvidenceCopyCount = yesterdayEvidenceCopyCount;
    }

    @Override
    public String toString() {
        return "EvidenceStatisticsVO{" +
                "todayEvidenceCount=" + todayEvidenceCount +
                ", yesterdayEvidenceCount=" + yesterdayEvidenceCount +
                ", totalEvidenceCount=" + totalEvidenceCount +
                ", todayEvidenceCopyCount=" + todayEvidenceCopyCount +
                ", yesterdayEvidenceCopyCount=" + yesterdayEvidenceCopyCount +
                '}';
    }
}
