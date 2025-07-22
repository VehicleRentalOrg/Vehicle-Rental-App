package com.vehiclerental.vehicle_rental_app.advice;

import com.vehiclerental.vehicle_rental_app.model.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class BaseResponseEnhancer implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // Apply only if the response is of BaseResponse or subclass
        return BaseResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof BaseResponse baseResponse) {
            HttpServletRequest httpServletRequest =
                    ((org.springframework.web.context.request.ServletRequestAttributes)
                            org.springframework.web.context.request.RequestContextHolder
                                    .currentRequestAttributes()).getRequest();

            Long startTime = (Long) httpServletRequest.getAttribute("startTime");
            long duration = (startTime != null) ? (System.currentTimeMillis() - startTime) : 0;

            baseResponse.setPath(httpServletRequest.getRequestURI());
            baseResponse.setResponseTime(duration + " ms");
            baseResponse.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        }

        return body;
    }
}
