package com.zfkj.demo;

import com.zfkj.demo.common.config.reqloghandel.EnableServletRequestLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi //开启swagger3.0服务
@EnableServletRequestLog //开启请求url param 信息打印
@SpringBootApplication
public class ScaffoldServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScaffoldServiceApplication.class, args);
	}

}
