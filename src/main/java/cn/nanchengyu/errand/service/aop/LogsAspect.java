package cn.nanchengyu.errand.service.aop;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.nanchengyu.errand.common.HoneyLogs;
import cn.nanchengyu.errand.entity.Logs;
import cn.nanchengyu.errand.entity.User;
import cn.nanchengyu.errand.service.LogsService;
import cn.nanchengyu.errand.utils.IpUtils;
import cn.nanchengyu.errand.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: LOgsAspect
 * Package: cn.nanchengyu.errand.service.aop
 * Description:
 *
 * @Author 南城余
 * @Create 2024/4/7 18:29
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class LogsAspect {
    @Autowired
    LogsService logsService;

    @AfterReturning(pointcut = "@annotation(honeyLogs)", returning = "jsonResult")
    public void recordLog(JoinPoint joinPoint, HoneyLogs honeyLogs, Object jsonResult) {
        //获取当前登录用户的信息
        User loginUser = TokenUtils.getCurrentUser();
        if (loginUser == null) {
            //登录 注册
            Object[] args = joinPoint.getArgs();
            if (ArrayUtil.isNotEmpty(args)) {
                if (args[0] instanceof User) {
                    loginUser = (User) args[0];

                }
            }
        }
        if (loginUser == null) {
            log.error("记录日志报错信息，未获取当前操作用户信息");
            return;
        }
        //获取当前登录用户的IP
        //获取HttpServletRequest对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String ipAddr = IpUtils.getIpAddr(request);
        //创建日志的对象
        Logs logs = Logs.builder()
                .id(loginUser.getId())
                .type(honeyLogs.type().getValue())
                .operation(honeyLogs.operation())
                .time(DateUtil.now())
                .ip(ipAddr)
                .build();
        //异步插入数据库
        ThreadUtil.execAsync(()->{
            logsService.save(logs);

        });

    }

}
