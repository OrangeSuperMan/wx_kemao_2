package edu.gdkm.weixin.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="xml")   //文件格式为xml
@XmlAccessorType(XmlAccessType.FIELD)  //从字段获取配置信息
public abstract class InMessage implements Serializable{
	private static final long serialVersionUID = 1L;
//使用JAXB方式转换
    @XmlElement(name="ToUserName")   
	private String toUserName;
    
    @XmlElement(name="FromUserName") 
	private String fromUserName;
    
    @XmlElement(name="CreateTime") 
	private long createTime;
    
    @XmlElement(name="MsgType") 
	private String msgType;
    
    @XmlElement(name="MsgId") 
	private Long msgId;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
    
	abstract public String toString();
    
	
	
}
