package com.hzdl.edg.domain.block;

import java.io.Serializable;
import java.util.List;

/**
 * 存证响应数据
 *
 * @author 34277
 * @date 2020/9/8
 */
public class CommonResponse implements Serializable {
    /**
     * 操作结果
     */
    private Boolean success;
    /**
     * 错误信息
     */
    private String err;

    List<MismatchedSet> mismatchedSet;
    public CommonResponse() {
    }

    public CommonResponse(Boolean success, String err) {
        this.success = success;
        this.err = err;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public List<MismatchedSet> getMismatchedSet() {
        return mismatchedSet;
    }

    public void setMismatchedSet(List<MismatchedSet> mismatchedSet) {
        this.mismatchedSet = mismatchedSet;
    }

    @Override
    public String toString() {
        return "EvidenceResponse{" +
                "status='" + success + '\'' +
                ", err='" + err + '\'' +
                '}';
    }
}
