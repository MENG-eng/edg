package com.hzdl.edg.domain.block;

/**
 * 存证响应数据
 *
 * @author 34277
 * @date 2020/9/8
 */
public class EvidenceResponse extends CommonResponse {
    /**
     * 区块链上的唯一Id
     */
    private String keyId;
    /**
     * 交易ID
     */
    private String txId;
    /**
     * 区块高度
     */
    private Long blockHeight;
    /**
     * 区块Hash值
     */
    private String blockHash;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(Long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    @Override
    public String toString() {
        return "EvidenceResponse{" +
                "keyId='" + keyId + '\'' +
                ", txId='" + txId + '\'' +
                ", blockHeight=" + blockHeight +
                ", blockHash='" + blockHash + '\'' +
                '}';
    }
}
