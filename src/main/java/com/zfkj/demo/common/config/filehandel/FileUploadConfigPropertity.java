package com.zfkj.demo.common.config.filehandel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传路径配置
 *
 * @author lijunlin
 * @date 2022/1/24 16:36
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "filesave")
public class FileUploadConfigPropertity {
    private String windows;
    private String linux;
    private String uploadHead;
}
