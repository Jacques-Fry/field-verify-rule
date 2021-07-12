package com.huihe.fvr.core;

import com.huihe.fvr.core.annotation.*;
import com.huihe.fvr.core.exception.FieldVerifyRuleException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段长度验证
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/6/30 17:51
 */
public interface FieldVerifyRule {

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
            throw new FieldVerifyRuleException(rule.name() + " 不能为空");
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
                        throw new FieldVerifyRuleException(rule.message());
                    } else {
                        throw new FieldVerifyRuleException(rule.name() + " 不符合规则");
                    }
                }
            }
            length = value.length();
        }
        // 长度验证
        if (length < rule.min() || length > rule.max()) {
            if (StringUtils.isNotBlank(rule.message())) {
                throw new FieldVerifyRuleException(rule.message());
            } else if (rule.min() <= 0) {
                throw new FieldVerifyRuleException(rule.name() + " 不能超过" + rule.max() + " 个字符");
            } else {
                throw new FieldVerifyRuleException(rule.name() + " 的字符长度范围在 " + rule.min() + "-" + rule.max() + " 之间");
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
                throw new FieldVerifyRuleException(rule.message());
            } else {
                throw new FieldVerifyRuleException(rule.name() + " 的范围在 " + rule.min() + "-" + rule.max() + " 之间");
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
                throw new FieldVerifyRuleException(rule.message());
            } else {
                throw new FieldVerifyRuleException(rule.name() + " 的范围在 " + rule.min() + "-" + rule.max() + " 之间");
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
                throw new FieldVerifyRuleException(rule.message());
            } else {
                throw new FieldVerifyRuleException(rule.name() + " 的范围在 " + rule.min() + "-" + rule.max() + " 之间");
            }
        }
    }


    /**
     * 处理字符串枚举
     *
     * @param field 字段
     * @param o     对象数据
     * @author Jacques·Fry
     * @since 2021/07/09 11:25
     */
    static void handleStringEnum(Field field, Object o) throws IllegalAccessException {
        StringEnumField rule = field.getAnnotation(StringEnumField.class);
        Object data = field.get(o);
        String value = null;
        if (data != null) {
            value = data.toString();
        }
        boolean exists = false;
        // 枚举验证
        for (String item : rule.value()) {
            if (item.equals(value)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new FieldVerifyRuleException(rule.name() + " 的可选值 " + Arrays.toString(rule.value()));
        }
    }

    /**
     * 处理整数枚举
     *
     * @param field 字段
     * @param o     对象数据
     * @author Jacques·Fry
     * @since 2021/07/09 11:25
     */
    static void handleIntegerEnum(Field field, Object o) throws IllegalAccessException {
        IntegerEnumField rule = field.getAnnotation(IntegerEnumField.class);
        Object data = field.get(o);
        int value = 0;
        if (data != null) {
            value = Integer.parseInt(data.toString());
        }
        boolean exists = false;
        // 枚举验证
        for (int item : rule.value()) {
            if (item == value) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new FieldVerifyRuleException(rule.name() + " 的可选值 " + Arrays.toString(rule.value()));
        }
    }

    /**
     * 验证全部字段
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
                distribute(field,this);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证指定字段
     *
     * @param fieldName 字段名
     * @author Jacques·Fry
     * @since 2021/07/09 13:19
     */
    default void verify(@NotNull String fieldName) {
        try {
            // 获取对象的class
            Class<?> clazz = this.getClass();
            // 获取对象的属性字段
            Field field = clazz.getDeclaredField(fieldName);
            distribute(field,this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分发
     *
     * @param field 字段
     * @param o     o
     * @author Jacques·Fry
     * @since 2021/07/09 13:23
     */
    static void distribute(Field field,Object o) throws IllegalAccessException {
        //设置允许通过反射访问私有变量
        field.setAccessible(true);
        if (field.isAnnotationPresent(StringField.class)) {
            handleString(field, o);
        } else if (field.isAnnotationPresent(IntegerField.class)) {
            handleInteger(field, o);
        } else if (field.isAnnotationPresent(DoubleField.class)) {
            handleDouble(field, o);
        } else if (field.isAnnotationPresent(FloatField.class)) {
            handleFloat(field, o);
        } else if (field.isAnnotationPresent(StringEnumField.class)) {
            handleStringEnum(field, o);
        } else if (field.isAnnotationPresent(IntegerEnumField.class)) {
            handleIntegerEnum(field, o);
        }
    }

}
