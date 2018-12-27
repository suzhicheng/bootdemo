package com.bootdemo.common.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.bootdemo.common.config.Constant;
import com.bootdemo.common.utils.HttpServletUtils;
import com.bootdemo.common.utils.R;
import com.bootdemo.common.utils.ShiroUtils;
import com.bootdemo.system.common.log.entity.LogEntity;
import com.bootdemo.system.common.log.service.LogService;
import com.bootdemo.system.system.user.entity.UserEntity;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class BDExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    LogService logService;
//
//    /**
//     * 自定义异常
//     */
//    @ExceptionHandler(BDException.class)
//    public R handleBDException(BDException e) {
//        logger.error(e.getMessage(), e);
//        R r = new R();
//        r.put("code", e.getCode());
//        r.put("msg", e.getMessage());
//        return r;
//    }
//
//    @ExceptionHandler(DuplicateKeyException.class)
//    public R handleDuplicateKeyException(DuplicateKeyException e) {
//        logger.error(e.getMessage(), e);
//        return R.error("数据库中已存在该记录");
//    }
//
//    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
//    public R noHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException e) {
//        logger.error(e.getMessage(), e);
//        return R.error(404, "没找找到页面");
//    }

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return R.error(403, "未授权");
        }
        return new ModelAndView("error/403");
    }


    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request) {
        LogEntity logDO = new LogEntity();
        logDO.setCreateDate(new Date());
        logDO.setOperation(Constant.LOG_ERROR);
        logDO.setMethod(request.getRequestURL().toString());
        logDO.setParams(e.toString());
        UserEntity current = ShiroUtils.getUser();
        if(null!=current){
            logDO.setUserId(current.getId());
            logDO.setUserName(current.getUserName());
        }
        logService.save(logDO);
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return R.error(500, "服务器错误，请联系管理员");
        }
        return new ModelAndView("error/500");
    }
}
