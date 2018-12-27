package com.bootdemo.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootdemo.common.annotation.Log;
import com.bootdemo.common.utils.HttpContextUtils;
import com.bootdemo.common.utils.IPUtils;
import com.bootdemo.common.utils.JSONUtils;
import com.bootdemo.common.utils.ShiroUtils;
import com.bootdemo.system.common.log.entity.LogEntity;
import com.bootdemo.system.common.log.service.LogService;
import com.bootdemo.system.system.user.entity.UserEntity;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    LogService logService;


    @Pointcut("@annotation(com.bootdemo.common.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogEntity sysLog = new LogEntity();
        Log syslog = method.getAnnotation(Log.class);
        if (syslog != null) {
            // 注解上的描述
            sysLog.setOperation(syslog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
            sysLog.setParams(params);
        } catch (Exception e) {

        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 用户名
        UserEntity currUser = ShiroUtils.getUser();
        if (null == currUser) {
            if (null != sysLog.getParams()) {
                sysLog.setUserId(null);
                sysLog.setUserName(sysLog.getParams());
            } else {
                sysLog.setUserId(null);
                sysLog.setUserName("获取用户信息为空");
            }
        } else {
            sysLog.setUserId(ShiroUtils.getUserId());
            sysLog.setUserName(ShiroUtils.getUser().getUserName());
        }
        sysLog.setTime((int) time);
        // 系统当前时间
        Date date = new Date();
        sysLog.setCreateDate(date);
        // 保存系统日志
        logService.save(sysLog);
    }
}
