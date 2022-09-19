package com.zfkj.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @Author lijunlin
 * @Description swagger配置类
 * @Date 2021/12/22 10:50
 **/
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("AdminApis")
                .apiInfo(apiInfo_service())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zfkj.demo.controller.admin")) // 设置扫描路径
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    @Bean
    public Docket createWebApi(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("WebApis")
                .apiInfo(apiInfo_app())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zfkj.demo.controller.publicuser")) // 设置扫描路径
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }




    private SecurityScheme securityScheme() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(
                new SecurityReference("Authorization", authorizationScopes));
    }

    private ApiInfo apiInfo_app() {
        return new ApiInfoBuilder()
                .title("前端服务API")
                .description("前端API")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    private ApiInfo apiInfo_service() {
        return new ApiInfoBuilder()
                .title("数据服务API")
                .description("数据服务API")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}
