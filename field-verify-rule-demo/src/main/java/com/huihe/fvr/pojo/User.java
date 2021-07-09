package com.huihe.fvr.pojo;


import com.huihe.fvr.core.FieldVerifyRule;
import com.huihe.fvr.core.annotation.*;
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
public class User implements Serializable, FieldVerifyRule {
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

    @IntegerEnumField(name = "用户类型", value = {1, 2, 3},message = "用户类型只能是 1:普通 | 2:管理员 | 3:超级管理员")
    private int type;

    @StringEnumField(name = "性别", value = {"男", "女"})
    private String sex;

}
