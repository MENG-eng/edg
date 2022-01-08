package com.hzdl.edg.domain.block;

import java.util.Date;

/**
 * 最后一次溯源记录对象
 *
 * @author FY
 * @date 2020/09/16
 */
public class CommonResponseCache extends CommonResponse {

    /**
     * 溯源时间
     */
    private Date date;
    /**
     * 溯源操作人
     */
    private String operator;
    /**
     * 溯源操作人昵称
     */
    private String operatorName;
    /**
     * 溯源时的节点数量
     */
    private Integer nodeCount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperator() {
        return operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
    }
}
