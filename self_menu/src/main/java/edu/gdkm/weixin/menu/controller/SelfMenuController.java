package edu.gdkm.weixin.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.gdkm.weixin.menu.domain.SelfMenu;
import edu.gdkm.weixin.menu.service.SelfMenuService;

@Controller
@RequestMapping("/kemao_2/menu")
public class SelfMenuController {

	@Autowired
	private SelfMenuService menuService;

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("/WEB-INF/views/kemao_2/menu/index.jsp");
	}

//	表明对外提供json数据
	@GetMapping(produces = "application/json")
	@ResponseBody
//	此时方法的返回类型可以是任意类型，spring会自动转换为Json
	public SelfMenu data() {
		return menuService.getMenu();
	}

	@PostMapping
	@ResponseBody
//	@RequestBody的作用：把整个请求体转换为Java对象
	public String save(@RequestBody SelfMenu selfMenu) {
		this.menuService.save(selfMenu);
		return "保存成功";
	}
}
