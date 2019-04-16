package com.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.admin.AdminEmailAndUserService;

@Controller
@RequestMapping("/admin") 
public class AdminEmailAndUserController extends BaseController{
	@Autowired
	private AdminEmailAndUserService adminEmailAndUserService;
	@RequestMapping("/selectEmail")
	public String selectAllEmail(Model model) {
		return adminEmailAndUserService.selectAllEmail(model);
	}
	@RequestMapping("/delete")
	public String delete(Integer id) {
		return adminEmailAndUserService.delete(id);
	}
	@RequestMapping("/selectUser")
	public String selectUser(Model model) {
		return adminEmailAndUserService.selectBuserEmail(model);
	}
	@RequestMapping("/lock")
	public String lock(Integer id) {
		return adminEmailAndUserService.lock(id);
	}
	@RequestMapping("/unLock")
	public String unLock(Integer id) {
		return adminEmailAndUserService.unLock(id);
	}
	
}
