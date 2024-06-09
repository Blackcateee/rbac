package com.zmxstudy.rbac.config;

import com.zmxstudy.rbac.vo.Result;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;

import java.lang.reflect.Type;

/**
 * springdoc 配置类
 *
 * @author star
 */
@Configuration
public class SpringDocConfig {
    /**
     * springdoc基础配置
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title("RBAC权限管理系统")
                        .description("API接口文档")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("课件地址")
                        .url("http://zmxstudy.com"));
    }

    /**
     * 处理 ResponseBodyAdvice 产生的 springdoc 无法识别返回值类型的问题
     * 第二种方式是 implements OperationCustomizer 接口
     */
    //@Bean
    public ReturnTypeParser returnTypeParser() {
        return new ReturnTypeParser() {
            @Override
            public Type getReturnType(MethodParameter methodParameter) {
                Type returnType = ReturnTypeParser.super.getReturnType(methodParameter);
                Class<?> parameterType = methodParameter.getParameterType();
                // 资源文件或者已经被包装了, 直接返回
                if (parameterType.isAssignableFrom(Resource.class) || parameterType.isAssignableFrom(Result.class)) {
                    return returnType;
                }
                // void转为 Result<Object>
                if (parameterType.isAssignableFrom(void.class)) {
                    return TypeUtils.parameterize(Result.class, Object.class);
                }
                // 转换成 Result
                return TypeUtils.parameterize(Result.class, returnType);
            }
        };
    }
}