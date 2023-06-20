package com.wetoband.template.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wetoband.template.annotation.LogOperate;
import com.wetoband.template.constant.LogOperateType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用，在需要打印日志的方法上增加注解信息 LogOperate
 * @LogOperate(value = "新增数据标准信息", type = LogOperateType.ADD)
 */
@Aspect
@Component
public class LogOperateAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogOperateAspect.class);


    /**
     * 描述：日志操作
     *
     * @param
     * @return void
     * @author fangzhao at 2020/4/7 16:52 
     */
    @Pointcut("@annotation(com.wetoband.template.annotation.LogOperate)")
//    @Pointcut("execution (* com.blp.service..*.*(..))")
    public void logOperateAnnotation() {}

    /**
     * 描述：环绕通知
     *
     * @param joinPoint
     * @return java.lang.Object
     * @author fangzhao at 2020/4/7 16:54
     */
    @Around("logOperateAnnotation()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogOperate logOperate = method.getAnnotation(LogOperate.class);
        String value = "";
        String msg = "";
        if (null != logOperate) {
            // 注解上的描述
            value = logOperate.value();
            LogOperateType logOperateType = logOperate.type();
            msg = logOperateType.getMsg();
        }
        // 请求的类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        List<Object> list = new ArrayList<>();
        String json = null;
        if (null != args && 0 < args.length) {
            for (Object arg : args) {
                if (arg instanceof HttpServletResponse || arg instanceof HttpServletRequest || arg instanceof MultipartFile) {
                } else {
                    list.add(arg);
                }
            }
        }

        try {
            json = new ObjectMapper().writeValueAsString(list);
        } catch (Exception e) {
            logger.error("JSON 转换异常：{}", e.getMessage());
        }

        logger.info("请求开始，对象：{}，执行操作：{}, 类型：{}, 参数：{}", className + "-" + methodName, value, msg, json);

        Object proceed = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        if (logger.isDebugEnabled()) {
            logger.debug("请求结束，{}，耗时： {}   最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    proceed,
                    (endTime - beginTime),
                    Runtime.getRuntime().maxMemory() / 1024 / 1024,
                    Runtime.getRuntime().totalMemory() / 1024 / 1024,
                    Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }
        return proceed;
    }
}