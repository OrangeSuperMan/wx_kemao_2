package edu.gdkm.weixin.processors.impl;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.gdkm.weixin.domain.User;
import edu.gdkm.weixin.domain.event.EventInMessage;
import edu.gdkm.weixin.processors.EventMessageProcessor;
import edu.gdkm.weixin.repository.UserRepository;
import edu.gdkm.weixin.service.WeiXinProxy;


@Service("subscribeMessageProcessor")
public class SubscribeEventMessageProcessor implements EventMessageProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(SubscribeEventMessageProcessor.class);
//	@Autowired
//	private AccessTokenManager accessTokenManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WeiXinProxy weixinProxy;

	@Override
	public void onMessage(EventInMessage msg) {
		// TODO Auto-generated method stub
		LOG.trace("关注消息处理器：" + msg);
//		1、检查用户是否有关注，如果已关注不需要处理
		String openId = msg.getFromUserName();
		String account=msg.getToUserName();
		User user = this.userRepository.findByOpenId(openId);
//		2、如果用户未关注，则需要调用远程接口获取用户的资料
		if (user == null || user.getStatus() != User.Status.IS_SUBSCRIBE) {
//		2.1.先要获取令牌
//		2.2、调用远程接口
			User wxUser = weixinProxy.getUser(account, openId);
			if (wxUser == null) {
				return;
			}
//		3.把用户信息保存到数据库
			if (user != null) {
				wxUser.setId(user.getId());
				wxUser.setSubTime(user.getSubTime());
			}else {
				wxUser.setSubTime(new Date());
			}
			wxUser.setStatus(User.Status.IS_SUBSCRIBE);
			this.userRepository.save(wxUser);
			this.weixinProxy.sendText(account, openId,"欢迎关注我的公众号");
		}
//		String token = accessTokenManager.getToken("null");
//		LOG.trace("访问令牌：" + token);

	}

}
