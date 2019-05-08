package edu.gdkm.weixin.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.gdkm.weixin.domain.InMessage;
import edu.gdkm.weixin.service.MessageConvertHelper;

@RestController
@RequestMapping("/kemao_2/message/receiver")
public class MessageReceiverController {
//	 日志记录器
	private static final Logger LOG = LoggerFactory.getLogger(MessageReceiverController.class);
	@Autowired
	@Qualifier("inMessageTemplate")
	private RedisTemplate<String, ? extends InMessage> inMessageTemplate;

	@GetMapping
	public String echo(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {

		return echostr;

	}

	@PostMapping
	public String onMessage(@RequestBody String xml) throws IOException {
		LOG.trace("收到的消息原文：\n{}\n--------------", xml);

//		 转换消息
//		 转换消息1.获取消息的类型	
//		String type=xml.substring(xml.indexOf("<MsgType><![CDATA[")+18);
//		type=type.substring(0,type.indexOf("]"));

		InMessage inMessage = MessageConvertHelper.convert(xml);

//		消息无法转换
		if (inMessage == null) {
			LOG.error("消息无法转换！原文：\n{}\n", xml);
			return "success";
		}
		LOG.debug("转换后的消息对象\n{}\n", inMessage);

//		 把消息丢入队列
//		 1.完成对象的序列化
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); // 字节输出流
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(inMessage);

//		byte[] data = bos.toByteArray();

//		 2.把序列化后对象放入队列里面
		String channel = "kemao_2_" + inMessage.getMsgType();
//		 直接把对象发送出去，调用ValueSerializer来实现对象的序列化和反序列化
		inMessageTemplate.convertAndSend(channel, inMessage);

//		inMessageTemplate.execute(new RedisCallback<InMessage>(){
//
//			@Override
//			public InMessage doInRedis(RedisConnection connection) throws DataAccessException {
//				// TODO Auto-generated method stub
//				String channel="kemao_2"+inMessage.getMsgType();
//				connection.publish(channel.getBytes(), data);
//				
//				return null;
//			}});

//		 消息队列中的消息
//		 产生客服消息
		return "success";
	}
}
