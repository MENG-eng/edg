package com.hzdl.enums;

public enum StatusEnum {
    ENABLE(0,"正常"),DISABLE(1,"停用");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String description;

    StatusEnum(int code, String description){
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 获取状态描述
     * @param code
     * @return String
     */
    public static String getDescriptionByCode(int code){
        for (StatusEnum status : values()) {
            if (status.code.equals(code)){
                return status.description;
            }
        }
        return "";
    }
}
