package cn.nanchengyu.errand.entity;

import lombok.Data;

/**
 * ClassName: Account
 * Package: cn.nanchengyu.errand.entity
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/24 18:25
 * @Version 1.0
 */
@Data
public class Account {

    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色
     */
    private String role;

    private String newPassword;

    private String token;


}
