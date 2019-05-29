package edu.gdkm.weixin.menu.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.gdkm.weixin.menu.domain.MenuButton;
import edu.gdkm.weixin.menu.domain.SelfMenu;
import edu.gdkm.weixin.menu.repository.SelfMenuRepository;
import edu.gdkm.weixin.menu.service.SelfMenuService;
import edu.gdkm.weixin.service.WeiXinProxy;

@Service
public class SelfMenuServiceImpl implements SelfMenuService {

	private static final Logger LOG = LoggerFactory.getLogger(SelfMenuServiceImpl.class);
	@Autowired
	private SelfMenuRepository menuRepository;
	@Autowired
	private WeiXinProxy weiXinProxy;

	@Override
	public SelfMenu getMenu() {
		// TODO Auto-generated method stub
		List<SelfMenu> all = this.menuRepository.findAll();
		if (all.isEmpty()) {
			return new SelfMenu();
		}
//		不为空直接返回第一个菜单，不考虑多个菜单的问题
		return all.get(0);
	}

	@Override
	public void save(SelfMenu selfMenu) {
		// TODO Auto-generated method stub
		selfMenu.getSubMenus().forEach(b1 -> {
			if (!b1.getSubMenus().isEmpty()) {
				b1.setAppId(null);
				b1.setKey(null);
				b1.setMediaId(null);
				b1.setPagePath(null);
				b1.setType(null);
				b1.setUrl(null);
			}
		});
//		由于只考虑一组菜单，所以直接删除数据库所有的菜单数据，全部重新插入即可
		this.menuRepository.deleteAll();
		this.menuRepository.save(selfMenu);

//		把菜单转换为json字符串，调用weixinProxy对象发送数据到公众号
		ObjectMapper mapper = new ObjectMapper();
//		创建一个JSON节点
		ObjectNode buttonNode = mapper.createObjectNode();
		ArrayNode buttonsNode = mapper.createArrayNode();

		buttonNode.set("button", buttonsNode);
		selfMenu.getSubMenus().forEach(b1 -> {
			/*
			 * 处理一级菜单 如果没有一级菜单，那么需要type、key等属性，否则需要name属性
			 */
			ObjectNode menu1 = mapper.createObjectNode();
			buttonsNode.add(menu1);// 加入一级菜单
			menu1.put("name", b1.getName());
			
// 			没有下一级，需要有type、key等各种属性
			if (b1.getSubMenus().isEmpty()) {
				setValues(menu1, b1);
			} else {
				ArrayNode subButtons = mapper.createArrayNode();
				menu1.set("sub_button", subButtons);
				b1.getSubMenus().forEach(b2 -> {
					ObjectNode menu2 = mapper.createObjectNode();
					subButtons.add(menu2); // 加入二级菜单
					menu2.put("name", b2.getName());

//					处理Key,type属性
					setValues(menu2, b2);
				});
			}
		});

		try {
			String json = mapper.writeValueAsString(buttonNode);
			this.weiXinProxy.createMenu(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			LOG.error("更新微信公众号菜单出现问题：" + e.getLocalizedMessage(), e);
		}
	}

	private void setValues(ObjectNode menu, MenuButton b) {
		// TODO Auto-generated method stub
		menu.put("type", b.getType());
		
		if (b.getType().equals("miniprogram")) {
			menu.put("appid", b.getAppId());
			menu.put("url", b.getUrl());
			menu.put("pagepath", b.getPagePath());
		} else if ("click".equals(b.getType())//
				|| "scancode_push".equals(b.getType())//
				|| "scancode_waitmsg".equals(b.getType())//
				|| "pic_sysphoto".equals(b.getType())//
				|| "pic_photo_or_album".equals(b.getType())//
				|| "pic_weixin".equals(b.getType())//
				|| "location_select".equals(b.getType())) {
			menu.put("key", b.getKey());
		} else if ("media_id".equals(b.getType())//
				|| "view_limited".equals(b.getType())) {
			menu.put("media_id", b.getMediaId());
		} else if ("view".equals(b.getType())) {
			menu.put("url", b.getUrl());
		}
	}

}
