package edu.gdkm.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("edu.gdkm")
public class WeixinProjectApplication implements CommonsConfig {
	public static void main(String[] args) {
		SpringApplication.run(WeixinProjectApplication.class, args);
	}

}
