package com.service.admin;

import org.springframework.ui.Model;

public interface AdminEmailAndUserService {
	public String selectBuserEmail(Model model);
	public String selectAllEmail(Model model);
	public String delete(Integer id);
	public String lock(Integer id);
	public String unLock(Integer id);
}
