package com.hzdl.api.vo;

/**
 * 机构VO
 *
 * @author FY
 * @date 2021/01/18
 */
public class DeptVO {
    private Long deptId;
    private String deptName;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
