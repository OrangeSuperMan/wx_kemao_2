package edu.gdkm.weixin.menu.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "wx_self_menu_button")
public class MenuButton {
	@Id
	@Column(length = 36)
//	 使用UUID2算法生成主键的值，分布式系统里面不能使用自增长作为主键值
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	private String id;
	@OneToMany(cascade = CascadeType.ALL)
//  当前类对应的表是主表，@JoinColumn会在子表生成一个名叫parent_id的外键列
	@JoinColumn(name = "parent_id")
	private List<MenuButton> subMenus = new LinkedList<>();

	/** 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型 */
	private String type;
	/** 菜单标题，不超过16个字节，子菜单不超过60个字节 */
	private String name;
	/** 菜单KEY值，用于消息接口推送，不超过128字节 */
	// key是数据库关键字，所以数据库不能以key作为列名！
	// 使用反单引号可以把关键字作为列名来使用！
	@Column(name = "`key`")
	private String key;
	/** 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。 */
	private String url;
	/** 调用新增永久素材接口返回的合法media_id */
	private String mediaId;
	/** 小程序的appid（仅认证公众号可配置） */
	private String appId;
	/** 小程序的页面路径 */
	private String pagePath;

	// 下面两个属性，只是为了维持页面的状态，所以不需要保存到数据库
	@Transient
	private boolean show;
	@Transient
	private boolean active;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<MenuButton> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<MenuButton> subMenus) {
		this.subMenus = subMenus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
