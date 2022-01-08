package com.hzdl.edg.domain.vo;

import java.io.Serializable;

/**
 * 饼图/柱图统计VO
 *
 * @author 34277
 * @date 2020/9/2
 */
public class StatisticsVO implements Serializable {

    private String name;
    private Integer cnt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

}
