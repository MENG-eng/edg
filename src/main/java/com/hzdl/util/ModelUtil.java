package com.hzdl.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;

/**
 * model类型转换工具类
 *
 * @author FanYu
 * @date 2019/12/4 14:00
 */
public class ModelUtil {

    private final static ModelMapper INSTANCE = new ModelMapper();

    static {
        INSTANCE.getConfiguration().setFullTypeMatchingRequired(true);
        INSTANCE.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * 获取ModelMapper实例
     */
    public static ModelMapper instance() {
        return INSTANCE;
    }

    /**
     * 转换单个对象
     */
    public static <D> D map(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        return INSTANCE.map(source, destinationType);
    }

    /**
     * 转换List
     */
    public static <D> List<D> mapList(Object source, TypeToken typeToken) {
        if (source == null) {
            return null;
        }
        return INSTANCE.map(source, typeToken.getType());
    }
}
