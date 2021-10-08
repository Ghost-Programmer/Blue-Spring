package com.blue.project.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;


@Aspect
@Component
public class LoggerAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String POINTCUT = "within(com.blue.project..*)";

    @Around(POINTCUT)
    @SneakyThrows
    public Object logArroundExec(ProceedingJoinPoint pjp) {
        if(log.isTraceEnabled()) {
            log.trace("before {}", constructLogMsg(pjp));
        }

        Object proceed = pjp.proceed();

        if(log.isTraceEnabled()) {
            if (Objects.nonNull(proceed)) {
                log.trace("after {} with result: {}", constructLogMsg(pjp), proceed.toString());
            } else {
                log.info("after {}", constructLogMsg(pjp));
            }
        }
        return proceed;
    }

    @AfterThrowing(pointcut = POINTCUT, throwing = "e")
    public void logAfterException(JoinPoint jp, Exception e) {
        log.error("Exception during: {} with ex: {}", constructLogMsg(jp),  e.toString(),e);
    }

    private String constructLogMsg(JoinPoint jp) {
        String args = Arrays.asList(jp.getArgs()).stream().map(String::valueOf).collect(Collectors.joining(",", "[", "]"));

        StringBuilder sb = new StringBuilder("@");
        sb.append(jp.getSignature().toShortString());
        sb.append(":");
        sb.append(args);
        return sb.toString();
    }
}
