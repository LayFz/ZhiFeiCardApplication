package com.zfkj.demo.common.config;

import com.zfkj.demo.common.config.intercepter.AuthIntercepter;
import com.zfkj.demo.common.config.intercepter.IgnoreConfig;
import com.zfkj.demo.common.config.intercepter.StaticIntercepter;
import com.zfkj.demo.common.utils.FileTempPath;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 注册拦截器
 *
 * @author lijunlin
 * @date 2022年1月12日
 */
@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final IgnoreConfig ignoreConfig;
    private final AuthIntercepter authIntercepter;
    private final FileTempPath fileTempPath;
    private final StaticIntercepter staticIntercepter;

    /**
     * 文件路径映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/download/**").addResourceLocations("file:" + fileTempPath.downloadPath());
        registry.addResourceHandler("/upload/img/**").addResourceLocations("file:" + fileTempPath.imgSrcPath());
        registry.addResourceHandler("/upload/video/**").addResourceLocations("file:" + fileTempPath.videoSrcPath());
        registry.addResourceHandler("/upload/file/**").addResourceLocations("file:" + fileTempPath.filePath());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 权限拦截器 拦截所有请求  可配置白名单
        registry.addInterceptor(authIntercepter)
                .addPathPatterns("/**")
                .excludePathPatterns(ignoreConfig.getUrls())
        ;
        // 静态资源拦截
        registry.addInterceptor(staticIntercepter)
                .addPathPatterns(ignoreConfig.getUrls())
        ;
    }
}
