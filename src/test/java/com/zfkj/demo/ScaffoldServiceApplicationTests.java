package com.zfkj.demo;

import com.zfkj.demo.common.config.redis.JedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScaffoldServiceApplicationTests {

	@Autowired
	private JedisService jedisService;

	@Test
	void contextLoads() {
		String aaa = jedisService.setObject("wechat:session_key" + "111", "123");
		System.out.println(aaa);
		String bbb = jedisService.setJson("wechat:session_key" + "222", "234");
		System.out.println(bbb);
	}

}
