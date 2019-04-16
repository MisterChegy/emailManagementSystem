package com.controller.before;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.Buser;
import com.service.before.NavigateService;
@Controller
public class NavigateController extends BaseBeforeController{
	@Autowired
	private NavigateService navigateService;
	/**
	 * 转到写信页面
	 */
	@RequestMapping("/toWrite")
	public String toWrite(Model model) {
		return navigateService.toWrite(model);
	}
	/***
	 * 安全退出，不需访问数据库，这里不需要service层和dao层
	 */
	@RequestMapping("/exit")
	public String exit(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("buser", new Buser());
		return "before/login";
	}
	/***
	 * 去修改密码页面，不需访问数据库，这里不需要service层和dao层
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(HttpSession session, Model model) {
		model.addAttribute("buser", (Buser)session.getAttribute("emailuser"));
		return "before/update";
	}
}
