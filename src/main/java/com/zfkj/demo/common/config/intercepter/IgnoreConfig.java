package com.zfkj.demo.common.config.intercepter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 忽略信息配置
 *
 * @author lijunlin
 * @date 2022年1月12日
 */
@Configuration
@ConfigurationProperties("ignore")
@Data
public class IgnoreConfig {
    List<String> urls;
    List<String> tokenurls;
}
