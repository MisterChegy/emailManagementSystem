package com.service.admin;

import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.dao.EmailDao;
import com.po.Buser;

@Service("adminEmailAndUserService")
@Transactional
public class AdminEmailAndUserServiceImpl implements AdminEmailAndUserService{
	@Autowired
	private EmailDao emailDao;
	@Override
	public String selectBuserEmail(Model model) {
		//查询所有用户
		List<Buser> lb = emailDao.selectBuserEmail();
		model.addAttribute("allUsers",lb);
		return "admin/adminUser";
	}

	@Override
	public String selectAllEmail(Model model) {
		//查询所有Email
		List<Map<String, Object>> le = emailDao.selectAllEmail();
		model.addAttribute("allEmails",le);
		return "admin/adminEmail";
	}
	@Override
	public String delete(Integer id) {
		emailDao.delete(id);
		JOptionPane.showMessageDialog(null, "删除成功！");
		return "forward:/admin/selectEmail";
	}

	@Override
	public String lock(Integer id) {
		emailDao.lock(id);
		JOptionPane.showMessageDialog(null, "成功锁定！");
		return "forward:/admin/selectUser";
	}

	@Override
	public String unLock(Integer id) {
		emailDao.unLock(id);
		JOptionPane.showMessageDialog(null, "成功解锁！");
		return "forward:/admin/selectUser";
	}
}
