package com.hzdl.edg.domain.vo;

import java.io.Serializable;

/**
 * 饼图/存证量和异常量统计VO
 *
 * @author 34277
 * @date 2020/9/2
 */
public class EvidenceAndExceptionStatisticsVO implements Serializable {

    private String date;
    private Integer evidenceCnt;
    private Integer exceptionCnt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getEvidenceCnt() {
        return evidenceCnt;
    }

    public void setEvidenceCnt(Integer evidenceCnt) {
        this.evidenceCnt = evidenceCnt;
    }

    public Integer getExceptionCnt() {
        return exceptionCnt;
    }

    public void setExceptionCnt(Integer exceptionCnt) {
        this.exceptionCnt = exceptionCnt;
    }
}
