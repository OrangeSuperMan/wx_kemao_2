package edu.gdkm.weixin.service;

public interface AccessTokenManager {

	/**
	 * 始终此方法都要求返回一个合法的令牌，如果没有获得令牌应该抛出异常。
	 * 微信公众号的微信号，要根据微信号找到对应的开发者id，要通过开发者id和密码才能得到正确的令牌。
	 * 没有获得令牌则抛出异常
	 */
	String getToken(String account) throws RuntimeException;
}
