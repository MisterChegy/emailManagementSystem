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
	 * ת��д��ҳ��
	 */
	@RequestMapping("/toWrite")
	public String toWrite(Model model) {
		return navigateService.toWrite(model);
	}
	/***
	 * ��ȫ�˳�������������ݿ⣬���ﲻ��Ҫservice���dao��
	 */
	@RequestMapping("/exit")
	public String exit(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("buser", new Buser());
		return "before/login";
	}
	/***
	 * ȥ�޸�����ҳ�棬����������ݿ⣬���ﲻ��Ҫservice���dao��
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(HttpSession session, Model model) {
		model.addAttribute("buser", (Buser)session.getAttribute("emailuser"));
		return "before/update";
	}
}
