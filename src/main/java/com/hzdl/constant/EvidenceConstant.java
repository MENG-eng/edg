package com.hzdl.constant;

/**
 * 存证相关常量
 *
 * @author 34277
 * @date 2020/9/1
 */
public class EvidenceConstant {
    /**
     * 创建
     */
    public final static String OPT_CREATE = "CREATE";
    /**
     * 复制
     */
    public final static String OPT_COPY = "COPY";
    /**
     * 修改
     */
    public final static String OPT_MODIFY = "MODIFY";
    /**
     * 增加
     */
    public final static String OPT_ADD = "ADD";

    /**
     * 初始版本
     */
    public final static String ORIGINAL_VERSION = "V1.0";

    /**
     * 原始文件
     */
    public final static String FILE_TYPE_ORIGINAL = "0";
    /**
     * 附件
     */
    public final static String FILE_TYPE_ATTACHMENT = "1";
    /**
     * 存证最后一次溯源情况
     */
    public final static String CACHE_LAST_TRACE_EVIDENCE = "evidence:trace:last:evidence:";
    /**
     * 节点最后一次溯源情况
     */
    public final static String CACHE_LAST_TRACE_NODE = "evidence:trace:last:node:";

    /**
     * 区块服务接口
     */
    public final static String evidenceServer = "10.112.106.62:3000";
    /**
     * 分布式文件系统接口
     */
    public final static String fileServer = "10.112.106.62:3080";
}
