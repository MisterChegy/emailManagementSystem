package com.service.before;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.dao.UserDao;
import com.po.Buser;
import com.util.MyUtil;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Override 
	public String register(Buser buser, Model model, HttpSession session, String code) {
		if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("codeError", "验证码错误！");
			return "before/register";
		}
		int n = userDao.register(buser);
		if(n > 0) {
			return "before/login";
		}else {
			model.addAttribute("msg", "注册失败！");
			return "before/register";
		}
	}
	@Override
	public String login(Buser buser, Model model, HttpSession session, String code) {
		if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("msg", "验证码错误！");
			return "before/login";
		}
		Buser ruser = null;
		List<Buser> list = userDao.login(buser);
		if(list.size() > 0) {
			ruser = list.get(0);
		}
		if(ruser != null) {
			session.setAttribute("emailuser", ruser);
			return "before/main";
		}else {
			model.addAttribute("msg", "用户名或密码错误！");
			return "before/login";
		}
	}
	@Override
	public String toLogin(Model model) {
		model.addAttribute("buser", new Buser());
		return "before/login";
	}
	@Override
	public String toRegister(Model model) {
		model.addAttribute("buser", new Buser());
		return "before/register";
	}
	/***
	 * 修改密码
	 */
	@Override
	public String update(Model model, HttpSession session, String bpwd) {
		Buser bs = new Buser();
		bs.setId(MyUtil.getUserId(session));
		bs.setBpwd(bpwd);
		userDao.update(bs);
		model.addAttribute("buser", new Buser());
		return "before/login";
	}

}
