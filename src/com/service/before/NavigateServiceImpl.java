package com.service.before;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.po.Email;
import com.dao.EmailDao;
import com.po.Buser;
@Service("navigateService")
@Transactional
public class NavigateServiceImpl implements NavigateService{
	@Autowired
	private EmailDao emailDao;
	@Override
	public String toWrite(Model model) {
		List<Buser> bemailsoption = emailDao.selectBuserEmail();
		model.addAttribute("bemailsoption", bemailsoption);
		model.addAttribute("myemail", new Email());
		return "before/write";
	}

}
