package edu.gdkm.weixin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

//	通过客服接口主动发送微信用户信息消息
public class OutMessage {

	@JsonProperty("toUser")
	private String toUser;

	@JsonProperty("msgType")
	private String messageType;

	public OutMessage(String toUser, String messageType) {
		super();
		this.toUser = toUser;
		this.messageType = messageType;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
