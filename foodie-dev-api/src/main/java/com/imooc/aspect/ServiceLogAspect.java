package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ServiceLogAspect {
    public static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);
    public Object recordTimeLog(ProceedingJoinPoint joinPoint){
        // todo 7.30
        return null;
    }
}
