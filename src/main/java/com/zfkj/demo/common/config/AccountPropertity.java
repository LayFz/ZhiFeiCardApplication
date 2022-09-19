package com.zfkj.demo.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lijunlin
 * @Description
 * @Date 2022/5/19 20:45
 **/
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "account")
public class AccountPropertity {
    //超级管理员账号
    private String admin;
}
