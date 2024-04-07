package cn.nanchengyu.errand.controller;

import cn.hutool.core.util.StrUtil;
import cn.nanchengyu.errand.common.AuthAccess;
import cn.nanchengyu.errand.common.HoneyLogs;
import cn.nanchengyu.errand.common.LogType;
import cn.nanchengyu.errand.common.Result;
import cn.nanchengyu.errand.entity.User;
import cn.nanchengyu.errand.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: AccountController
 * Package: cn.nanchengyu.errand.controller
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/30 22:11
 * @Version 1.0
 */
@RestController
public class AccountController {
    @Resource
    UserService userService;
    @HoneyLogs(operation = "用户管理",type = LogType.LOGIN)

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        // 判断前端传来数据是否为空
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("输入账号或者密码为空");
        }

         user =  userService.login(user);
        return Result.success(user);
    }

    @AuthAccess
    @PostMapping("/register")
    @HoneyLogs(operation = "用户管理",type = LogType.REGISTER)
    public Result Register(@RequestBody User user) {
        // 判断前端传来数据是否为空
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())|| StrUtil.isBlank(user.getRole())) {
            return Result.error("输入账号或者密码为空");
        }
        if (user.getUsername().length()>10||user.getPassword().length()>10) {
            return Result.error("输入账号或者密码超长");
        }

        userService.register(user);
        return Result.success("register success");
    }

    @AuthAccess
    @PutMapping("/password")
    public Result password(@RequestBody User user){
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPhone())) {
            return Result.error("数据输入不合法");
        }
        userService.restPassword(user);
        return Result.success();
    }
}
