package com.huihe.fvr.pojo;


import com.huihe.fvr.core.FieldLengthRule;
import com.huihe.fvr.core.annotation.DoubleField;
import com.huihe.fvr.core.annotation.FloatField;
import com.huihe.fvr.core.annotation.IntegerField;
import com.huihe.fvr.core.annotation.StringField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户类
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 9:16
 */
@Getter
@Setter
public class User implements Serializable, FieldLengthRule {
    private static final long serialVersionUID = 1L;

    @IntegerField(name = "ID", min = 1, max = 10)
    private Integer id;

    @StringField(name = "账号", max = 50, notnull = true, regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    private String username;

    @StringField(name = "密码", max = 16, message = "密码不能超过16位")
    private String password;

    @DoubleField(name = "余额", min = -100, max = 999)
    private Double balance;

    @FloatField(name = "信用额度", min = 1, max = 999)
    private Float amount;

}
