package edu.gdkm.weixin.domain.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.gdkm.weixin.domain.InMessage;
@XmlRootElement(name="xml")   //文件格式为xml
@XmlAccessorType(XmlAccessType.FIELD)
public class ImageInMessage extends InMessage {

	private static final long serialVersionUID = 1L;


	 @XmlElement(name="PicUrl")   
		private String imageUrl;
	 
	 @XmlElement(name="MediaId")   
		private String MediaId;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	@Override
	public String toString() {
		return "ImageInMessage [imageUrl=" + imageUrl + ", MediaId=" + MediaId + ", getToUserName()=" + getToUserName()
				+ ", getFromUserName()=" + getFromUserName() + ", getCreateTime()=" + getCreateTime()
				+ ", getMsgType()=" + getMsgType() + ", getMsgId()=" + getMsgId() + "]";
	}

	
 
	 
}
