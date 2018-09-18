package com.poc.springbootaspectlogging.config.annotation.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ControllerLogImpl {

    private final ObjectMapper objectMapper;

    @Around("@annotation(com.poc.springbootaspectlogging.config.annotation.ControllerLog)")
    public Object logControllerPayload(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            log.info("Method {} Arguments {}", method.getName(), objectMapper.writeValueAsString(joinPoint.getArgs()));
        } catch (Exception e) {

        }

        Object response = joinPoint.proceed();

        try {
            log.info("Method {} Response {}", method.getName(), objectMapper.writeValueAsString(response));
        } catch (Exception e) {
        }

        return response;
    }


}
