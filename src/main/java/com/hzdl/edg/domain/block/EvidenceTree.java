package com.hzdl.edg.domain.block;

import java.io.Serializable;
import java.util.List;

/**
 * 存证请求数据
 *
 * @author 34277
 * @date 2020/9/8
 */
public class EvidenceTree implements Serializable {
    /**
     * 文件名|版本号|账户名|时间戳
     */
    private String keyId;
    /**
     * 子级
     */
    private List<EvidenceTree> childNodes;

    public EvidenceTree() {
    }

    public EvidenceTree(String keyId, List<EvidenceTree> childNodes) {
        this.keyId = keyId;
        this.childNodes = childNodes;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public List<EvidenceTree> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<EvidenceTree> childNodes) {
        this.childNodes = childNodes;
    }

    @Override
    public String toString() {
        return "EvidenceVerifyTree{" +
                "keyId='" + keyId + '\'' +
                ", childNodes=" + childNodes +
                '}';
    }
}
