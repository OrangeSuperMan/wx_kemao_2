package edu.gdkm.weixin.domain.text;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.gdkm.weixin.domain.InMessage;
@XmlRootElement(name="xml")   //   文件格式为xml
@XmlAccessorType(XmlAccessType.FIELD)
public class TextInMessage extends InMessage{

	private static final long serialVersionUID = 1L;
	
	
	 @XmlElement(name="Content")   
		private String content;


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "TextInMessage [content=" + content + ", getToUserName()=" + getToUserName() + ", getFromUserName()="
				+ getFromUserName() + ", getCreateTime()=" + getCreateTime() + ", getMsgType()=" + getMsgType()
				+ ", getMsgId()=" + getMsgId() + "]";
	}
	 
	 
	

}
