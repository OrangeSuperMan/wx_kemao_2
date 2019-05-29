package edu.gdkm.weixin.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import edu.gdkm.weixin.CommonsConfig;

@SpringBootApplication
@ComponentScan("edu.gdkm")
@EntityScan("edu.gdkm")
public class SelfMenuApplication implements CommonsConfig {
	public static void main(String[] args) {
		SpringApplication.run(SelfMenuApplication.class, args);
	}
}
