package com.hzdl.edg.domain.vo;

import java.io.Serializable;

/**
 * 操作类型统计VO
 *
 * @author 34277
 * @date 2020/9/2
 */
public class OptTypeStatisticsVO implements Serializable {

    private Integer createCount;
    private Integer copyCount;
    private Integer addCount;
    private Integer modifyCount;

    public Integer getCreateCount() {
        return createCount;
    }

    public void setCreateCount(Integer createCount) {
        this.createCount = createCount;
    }

    public Integer getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(Integer copyCount) {
        this.copyCount = copyCount;
    }

    public Integer getAddCount() {
        return addCount;
    }

    public void setAddCount(Integer addCount) {
        this.addCount = addCount;
    }

    public Integer getModifyCount() {
        return modifyCount;
    }

    public void setModifyCount(Integer modifyCount) {
        this.modifyCount = modifyCount;
    }

    @Override
    public String toString() {
        return "OptTypeStatisticsVO{" +
                "createCount=" + createCount +
                ", copyCount=" + copyCount +
                ", addCount=" + addCount +
                ", modifyCount=" + modifyCount +
                '}';
    }
}
