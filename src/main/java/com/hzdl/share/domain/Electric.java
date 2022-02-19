package com.hzdl.share.domain;

import com.hzdl.common.annotation.Excel;
import com.hzdl.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 电量数据对象 share_electric
 *
 * @author hzdl
 * @date 2022-02-14
 */
public class Electric extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 电量数据id
     */
    private Long electricId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 电量
     */
    private Long electricQuantity;

    /**
     * 电压
     */
    private Long electricVoltage;

    /**
     * 数据名称
     */
    private String dateName;

    /**
     * 查询条件
     */
    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public Electric() {
    }

    public Long getElectricId() {
        return electricId;
    }

    public void setElectricId(Long electricId) {
        this.electricId = electricId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getElectricQuantity() {
        return electricQuantity;
    }

    public void setElectricQuantity(Long electricQuantity) {
        this.electricQuantity = electricQuantity;
    }

    public Long getElectricVoltage() {
        return electricVoltage;
    }

    public void setElectricVoltage(Long electricVoltage) {
        this.electricVoltage = electricVoltage;
    }
}
