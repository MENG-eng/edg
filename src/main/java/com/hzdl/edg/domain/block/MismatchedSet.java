package com.hzdl.edg.domain.block;

public class MismatchedSet {
    /**
     * 文件名|版本号|账户名|时间戳
     */
    private String keyId;
    /**
     * 错误信息
     */
    private String msg;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
