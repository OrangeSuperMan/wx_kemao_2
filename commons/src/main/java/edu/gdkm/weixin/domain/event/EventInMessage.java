package edu.gdkm.weixin.domain.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.gdkm.weixin.domain.InMessage;

@XmlRootElement(name = "xml") // 文件格式为xml
@XmlAccessorType(XmlAccessType.FIELD)
public class EventInMessage extends InMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "Event")
	private String event;
	@XmlElement(name = "EventKey")
	private String eventKey;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@Override
	public String toString() {
		return "EventInMessage [event=" + event + ", eventKey=" + eventKey + ", getToUserName()=" + getToUserName()
				+ ", getFromUserName()=" + getFromUserName() + ", getCreateTime()=" + getCreateTime()
				+ ", getMsgType()=" + getMsgType() + ", getMsgId()=" + getMsgId() + "]";
	}

}
