package com.hzdl.edg.domain.vo;

import com.hzdl.common.core.domain.BaseEntity;

/**
 * 根据一级存证类型统计 通用Vo
 */
public class CommonCountVo extends BaseEntity {
    /**
     * 一级存证类型id
     */
    private Long id;

    /**
     * 一级存证类型名称
     */
    private String name;

    /**
     * 通用统计
     */
    private int count;

    public CommonCountVo() {
    }

    public CommonCountVo(Long id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "EvidenceCountByTypeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
