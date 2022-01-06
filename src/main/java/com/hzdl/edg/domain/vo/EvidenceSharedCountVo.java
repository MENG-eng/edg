package com.hzdl.edg.domain.vo;

/**
 * 根据一级存证类型进行存证共享统计用VO
 */
public class EvidenceSharedCountVo extends CommonCountVo {
    private int sharedCount;

    public EvidenceSharedCountVo() {
    }

    public EvidenceSharedCountVo(Long id, String name, int count, int sharedCount) {
        super(id, name, count);
        this.sharedCount = sharedCount;
    }

    public int getSharedCount() {
        return sharedCount;
    }

    public void setSharedCount(int sharedCount) {
        this.sharedCount = sharedCount;
    }
}
