package com.service.before;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.po.Email;

public interface EmailService {
	public String write(Email myemail, Model model, HttpServletRequest request,  HttpSession session);
	public String recieve(Model model, HttpSession session);
	public String detail(Model model, Integer id);
	public String deleteOne(Integer id);
	public String deleteMore(Integer ids[]);
	public String reEmailInit(Integer id, Model model);
	public String send(Model model, HttpSession session);
}
