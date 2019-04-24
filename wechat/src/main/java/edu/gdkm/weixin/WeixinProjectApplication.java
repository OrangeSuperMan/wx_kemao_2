package edu.gdkm.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import edu.gdkm.weixin.domain.InMessage;
import edu.gdkm.weixin.json.JsonRedisSerializer;


@SpringBootApplication
@ComponentScan("edu.gdkm")
public class WeixinProjectApplication {
	@Bean
	public RedisTemplate<String, ? extends InMessage> inMessageTemplate(
			@Autowired RedisConnectionFactory connectionFactory) {

		RedisTemplate<String, ? extends InMessage> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
//		 使用序列化程序完成对象的序列化和反序列化，可以自定义
		template.setValueSerializer(new JsonRedisSerializer<InMessage>());

		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(WeixinProjectApplication.class, args);
	}

}
