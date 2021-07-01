package com.huihe.flr.core;

import com.huihe.flr.annotation.DoubleField;
import com.huihe.flr.annotation.FloatField;
import com.huihe.flr.annotation.IntegerField;
import com.huihe.flr.annotation.StringField;
import com.huihe.flr.exception.FieldLengthConstantException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 字段长度验证
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/6/30 17:51
 */
public interface FieldLengthRule {

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
                    StringField rule = field.getAnnotation(StringField.class);
                    Object o = field.get(this);
                    String value = null;
                    if (o != null) {
                        value = o.toString();
                    }
                    // 非空验证
                    if (rule.notnull() && StringUtils.isBlank(value)) {
                        throw new FieldLengthConstantException(rule.name() + " 不能为空");
                    }
                    int length = 0;
                    if (StringUtils.isNotBlank(value)) {
                        length = value.length();
                    }
                    // 长度验证
                    if (length < rule.min() || length > rule.max()) {
                        if (StringUtils.isNotBlank(rule.message())) {
                            throw new FieldLengthConstantException(rule.message());
                        } else {
                            throw new FieldLengthConstantException(rule.name() + " 的字符长度范围在 " + rule.min() + "-" + rule.max() + " 之间");
                        }
                    }
                } else if (field.isAnnotationPresent(IntegerField.class)) {
                    IntegerField rule = field.getAnnotation(IntegerField.class);
                    Object o = field.get(this);
                    int value = 0;
                    if (o != null) {
                        value = Integer.parseInt(o.toString());
                    }
                    // 长度验证
                    if (value < rule.min() || value > rule.max()) {
                        if (StringUtils.isNotBlank(rule.message())) {
                            throw new FieldLengthConstantException(rule.message());
                        } else {
                            throw new FieldLengthConstantException(rule.name() + " 的范围在 " + rule.min() + "-" + rule.max() + " 之间");
                        }
                    }
                } else if (field.isAnnotationPresent(DoubleField.class)) {
                    DoubleField rule = field.getAnnotation(DoubleField.class);
                    Object o = field.get(this);
                    double value = 0;
                    if (o != null) {
                        value = Double.parseDouble(o.toString());
                    }
                    // 长度验证
                    if (value < rule.min() || value > rule.max()) {
                        if (StringUtils.isNotBlank(rule.message())) {
                            throw new FieldLengthConstantException(rule.message());
                        } else {
                            throw new FieldLengthConstantException(rule.name() + " 的范围在 " + rule.min() + "-" + rule.max() + " 之间");
                        }
                    }
                } else if (field.isAnnotationPresent(FloatField.class)) {
                    FloatField rule = field.getAnnotation(FloatField.class);
                    Object o = field.get(this);
                    float value = 0;
                    if (o != null) {
                        value = Float.parseFloat(o.toString());
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
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
