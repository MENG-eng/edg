package com.hzdl.edg.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 各能源数据本月存证统计
 *
 * @author 34277
 * @date 2020/9/2
 */
public class EvidenceMonthStatisticsVO implements Serializable {

    private String type;
    private List<StatisticsVO> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StatisticsVO> getData() {
        return data;
    }

    public void setData(List<StatisticsVO> data) {
        this.data = data;
    }
}
