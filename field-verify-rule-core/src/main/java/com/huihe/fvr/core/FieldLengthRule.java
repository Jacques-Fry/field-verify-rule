package com.huihe.fvr.core;

import com.huihe.fvr.core.annotation.DoubleField;
import com.huihe.fvr.core.annotation.FloatField;
import com.huihe.fvr.core.annotation.IntegerField;
import com.huihe.fvr.core.annotation.StringField;
import com.huihe.fvr.core.exception.FieldLengthConstantException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段长度验证
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/6/30 17:51
 */
public interface FieldLengthRule {

    /**
     * 处理字符串
     *
     * @param field 字段
     * @param o     对象
     * @author Jacques·Fry
     * @since 2021/07/01 11:05
     */
    static void handleString(Field field, Object o) throws IllegalAccessException {
        StringField rule = field.getAnnotation(StringField.class);
        Object data = field.get(o);
        String value = null;
        if (data != null) {
            value = data.toString();
        }
        // 非空验证
        if (rule.notnull() && StringUtils.isBlank(value)) {
            throw new FieldLengthConstantException(rule.name() + " 不能为空");
        }
        int length = 0;
        if (StringUtils.isNotBlank(value)) {
            // 正则表达式
            if (StringUtils.isNotBlank(rule.regexp())) {
                Pattern pattern = Pattern.compile(rule.regexp());
                Matcher matcher = pattern.matcher(value);
                // 字符串是否与正则表达式相匹配
                if (!matcher.matches()) {
                    if (StringUtils.isNotBlank(rule.message())) {
                        throw new FieldLengthConstantException(rule.message());
                    } else {
                        throw new FieldLengthConstantException(rule.name() + " 不符合规则");
                    }
                }
            }
            length = value.length();
        }
        // 长度验证
        if (length < rule.min() || length > rule.max()) {
            if (StringUtils.isNotBlank(rule.message())) {
                throw new FieldLengthConstantException(rule.message());
            } else if (rule.min() <= 0) {
                throw new FieldLengthConstantException(rule.name() + " 不能超过" + rule.max() + " 个字符");
            } else {
                throw new FieldLengthConstantException(rule.name() + " 的字符长度范围在 " + rule.min() + "-" + rule.max() + " 之间");
            }
        }

    }

    /**
     * 处理整数
     *
     * @param field 字段
     * @param o     对象
     * @author Jacques·Fry
     * @since 2021/07/01 11:05
     */
    static void handleInteger(Field field, Object o) throws IllegalAccessException {
        IntegerField rule = field.getAnnotation(IntegerField.class);
        Object data = field.get(o);
        int value = 0;
        if (data != null) {
            value = Integer.parseInt(data.toString());
        }
        // 长度验证
        if (value < rule.min() || value > rule.max()) {
            if (StringUtils.isNotBlank(rule.message())) {
                throw new FieldLengthConstantException(rule.message());
            } else {
                throw new FieldLengthConstantException(rule.name() + " 的范围在 " + rule.min() + "-" + rule.max() + " 之间");
            }
        }
    }

    /**
     * 处理双精点小数
     *
     * @param field 字段
     * @param o     对象
     * @author Jacques·Fry
     * @since 2021/07/01 14:54
     */
    static void handleDouble(Field field, Object o) throws IllegalAccessException {
        DoubleField rule = field.getAnnotation(DoubleField.class);
        Object data = field.get(o);
        double value = 0;
        if (data != null) {
            value = Double.parseDouble(data.toString());
        }
        // 长度验证
        if (value < rule.min() || value > rule.max()) {
            if (StringUtils.isNotBlank(rule.message())) {
                throw new FieldLengthConstantException(rule.message());
            } else {
                throw new FieldLengthConstantException(rule.name() + " 的范围在 " + rule.min() + "-" + rule.max() + " 之间");
            }
        }
    }

    /**
     * 处理浮动小数
     *
     * @param field 字段
     * @param o     对象
     * @author Jacques·Fry
     * @since 2021/07/01 11:05
     */
    static void handleFloat(Field field, Object o) throws IllegalAccessException {
        FloatField rule = field.getAnnotation(FloatField.class);
        Object data = field.get(o);
        float value = 0;
        if (data != null) {
            value = Float.parseFloat(data.toString());
        }
        // 长度验证
        if (value < rule.min() || value > rule.max()) {
            if (StringUtils.isNotBlank(rule.message())) {
                throw new FieldLengthConstantException(rule.message());
            } else {
                throw new FieldLengthConstantException(rule.name() + " 的范围在 " + rule.min() + "-" + rule.max() + " 之间");
            }
        }
    }

    /**
     * 验证
     *
     * @author Jacques·Fry
     * @since 2021/06/30 19:01
     */
    default void verify() {
        try {
            // 获取对象的class
            Class<?> clazz = this.getClass();
            // 获取对象的属性列表
            Field[] fields = clazz.getDeclaredFields();
            // 遍历属性列表field
            for (Field field : fields) {
                //设置允许通过反射访问私有变量
                field.setAccessible(true);
                if (field.isAnnotationPresent(StringField.class)) {
                    handleString(field, this);
                } else if (field.isAnnotationPresent(IntegerField.class)) {
                    handleInteger(field, this);
                } else if (field.isAnnotationPresent(DoubleField.class)) {
                    handleDouble(field, this);
                } else if (field.isAnnotationPresent(FloatField.class)) {
                    handleFloat(field, this);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
