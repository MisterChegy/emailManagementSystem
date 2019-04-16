package com.service.before;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.po.Buser;

public interface UserService {
	public String register(Buser buser,Model model, HttpSession session, String code);
	public String login(Buser buser,Model model, HttpSession session, String code);
	public String toLogin(Model model);
	public String toRegister(Model model);
	public String update(Model model,  HttpSession session, String bpwd);
}
    