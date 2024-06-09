package com.zmxstudy.rbac.handler;

import com.zmxstudy.rbac.vo.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Controller中统一返回结果处理
 *
 * @author star
 */
@ControllerAdvice
public class ControllerResponseBodyHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 如果已经是 Result 包装过的就跳过
        return !(returnType.getParameterType().isAssignableFrom(Result.class)
                // springdoc 的 Controller 不需要拦截，包括如下：
                // org.springdoc.webmvc.api.OpenApiWebMvcResource
                // org.springdoc.webmvc.ui.SwaggerConfigResource
                || returnType.getDeclaringClass().getName().contains("springdoc")
                // 如果标注有 @IgnoreResponseBody 注解的也跳过
                // || return !returnType.hasMethodAnnotation(IgnoreResponseBody.class);
        );
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }
        return Result.ok(body);
    }
}
