package com.hzdl.edg.domain;

import com.hzdl.common.annotation.Excel;
import com.hzdl.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 附件对象 edg_attachment
 *
 * @author hzdl
 * @date 2020-08-19
 */
@ApiModel("附件")
public class Attachment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 对象id
     */
    @Excel(name = "对象id")
    @ApiModelProperty("对象id")
    private Long objectId;

    /**
     * 类型编码
     */
    @Excel(name = "类型编码")
    @ApiModelProperty("类型编码（0-json原始文件；1-附件）")
    private String bizType;

    /**
     * 文件名称
     */
    @Excel(name = "文件名称")
    @ApiModelProperty("文件名称")
    private String fileName;

    /**
     * 文件类型
     */
    @Excel(name = "文件类型")
    @ApiModelProperty("文件类型")
    private String fileType;

    /**
     * 文件大小（KB）
     */
    @Excel(name = "文件大小", readConverterExp = "K=B")
    @ApiModelProperty("文件大小（KB）")
    private Long fileSize;

    /**
     * 文件路径
     */
    @ApiModelProperty("文件路径")
    private String filePath;

    @ApiModelProperty("文件哈希")
    private String fileHash;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Long sort;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }

}
