package edu.gdkm.weixin.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdkm.weixin.domain.User;
import edu.gdkm.weixin.domain.text.TextOutMessage;

@Service // 把当前类的对象加入Spring中管理
public class WeiXinProxy {

	private static final Logger LOG = LoggerFactory.getLogger(WeiXinProxy.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private AccessTokenManager accessTokenManager;

	private HttpClient httpClient = HttpClient.newBuilder().build();

	public User getUser(String account, String openId) {

		String accessToken = accessTokenManager.getToken(account);

		String url = "https://api.weixin.qq.com/cgi-bin/user/info"//
				+ "?access_token=" + accessToken//
				+ "&openid=" + openId//
				+ "&lang=zh_CN";
//		 创建请求
		HttpRequest request = HttpRequest.newBuilder(URI.create(url))//
				.GET()// 以GET方式发送请求
				.build();
		try {
//			 发送请求
//			 BodyHandlers里面包含了一系列的响应体处理程序，能够把响应体转换为需要的数据类型
//			 ofString表示转换为String类型的数据
//			 Charset.forName("UTF-8")表示使用UTF-8的编码转换数据
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString(Charset.forName("UTF-8")));

//			 获取响应体
			String body = response.body();
			LOG.trace("调用远程接口返回的内容：\n{}", body);

			if (!body.contains("errcode")) {
//				 成功
				User user = objectMapper.readValue(body, User.class);
				return user;
			}
		} catch (Exception e) {
			LOG.error("调用远程接口出现错误：" + e.getLocalizedMessage(), e);
		}
		return null;
	}

	public void sendText(String account, String openId, String string) {
		TextOutMessage out = new TextOutMessage(openId, "欢迎关注公众号，回复【帮助】可得到菜单");
		try {
			String json = this.objectMapper.writeValueAsString(out);
			String accessToken = accessTokenManager.getToken(account);
			String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send"//
					+ "?access_token=" + accessToken;
			HttpRequest request = HttpRequest.newBuilder(URI.create(url))//
					.POST(BodyPublishers.ofString(json, Charset.forName("UTF-8")))// 以POST方式发送请求
					.build();
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString(Charset.forName("UTF-8")));
			LOG.trace("发送客服消息的结果：{}", response.body());
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			LOG.trace("发送消息出现问题" + e.getLocalizedMessage(), e);
		}
	}
}
