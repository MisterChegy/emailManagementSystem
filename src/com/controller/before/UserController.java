package com.controller.before;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.Buser;
import com.service.before.UserService;

@Controller
@RequestMapping("/user")   
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/register")
	public String register(@ModelAttribute Buser buser,Model model, HttpSession session, String code) {
		return userService.register(buser, model, session, code);
	}
	@RequestMapping("/login")
	public String login(@ModelAttribute Buser buser,Model model, HttpSession session, String code) {
		return userService.login(buser, model, session, code);
	}
	/**
	 * ת����¼ҳ��
	 */
	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		return userService.toLogin(model);
	}
	/**
	 * ת��ע��ҳ��
	 */
	@RequestMapping("/toRegister")
	public String toRegister(Model model) {
		return userService.toRegister(model);
	}
	/**
	 * �޸�����
	 */
	@RequestMapping("/update")
	public String update(Model model,  HttpSession session, String bpwd) {
		return userService.update(model, session, bpwd);
	}

}
