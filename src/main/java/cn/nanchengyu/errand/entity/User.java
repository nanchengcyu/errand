package cn.nanchengyu.errand.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;


/**
 * 用户表
 *
 * @TableName user
 */
@TableName(value = "user")
@Data

public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Alias("id")
    private Integer id;

    /**
     * 用户名
     */
    @Alias("用户名")

    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    @Alias("姓名")
    private String name;

    /**
     * 头像
     */
    @Alias("头像")

    private String avatar;

    /**
     * 角色
     */
    @Alias("角色")

    private String role;

    /**
     * 性别
     */
    @Alias("性别")

    private String sex;

    /**
     * 电话
     */
    @Alias("电话")

    private String phone;

    /**
     * 余额
     */
    @Alias("余额")

    private BigDecimal account;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String token;

}