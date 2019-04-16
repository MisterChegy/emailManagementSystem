package com.service.before;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.dao.EmailDao;
import com.po.Buser;
import com.po.Email;
import com.po.Rsrecordtable;
import com.util.MyUtil;
@Service("emailService")
@Transactional
public class EmailServiceImpl implements EmailService{
	@Autowired
	private EmailDao emailDao;
	/**
	 * 写信
	 */
	@Override
	public String write(Email myemail, Model model, HttpServletRequest request, HttpSession session) {
		if("submit".equals(myemail.getAct())) {//提交按钮
			emailDao.writeEmail(myemail);//添加邮件，添加成功后，Email的ID自动回填到myemail对象中
			String ids = myemail.getBemailsid();//接收人id以分号;分隔
			System.out.println(ids + "ids");
			String rid[] = ids.split(";");
			String emails = myemail.getBemails();//接收人email以分号;分隔
			String remail[] = emails.split(";");
			int s_id = MyUtil.getUserId(session);
			String s_email = MyUtil.getUserEmail(session);
			//添加邮件记录
			for (int i = 0; i < rid.length; i++) {
				Rsrecordtable rt = new Rsrecordtable();
				rt.setId_s(s_id);//发送人id
				rt.setEmail_s(s_email);
				rt.setId_r(Integer.parseInt(rid[i]));//接收人id
				rt.setEmail_r(remail[i]);//接收人email
				rt.setId_email(myemail.getId());//邮件id
				emailDao.writeEmailRecord(rt);
			}
			return "before/writeSuccess";
		}else {//上传附件
			String attachfile = myemail.getAttachfile();
			String attachfilename = myemail.getAttachfilename();
			/*文件上传到服务器的位置"/uploadfiles"，该位置是指
			workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps，
			发布后使用*/
			String realpath = request.getServletContext().getRealPath("uploadfiles");
			String fileName = myemail.getAttachments().getOriginalFilename(); 
			//原文件名
			if(attachfilename.length() > 0) {
				attachfilename = attachfilename + "|" + fileName;
			}else {
				attachfilename = fileName;
			}
			//新文件名
			String newfilename = MyUtil.getRandom() + MyUtil.getFileExtendsName(fileName);
			if(attachfile.length() > 0) {
				attachfile = attachfile + "|" + newfilename;
			}else {
				attachfile = newfilename;
			}
			File targetFile = new File(realpath, newfilename); 
			if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        } 
			//上传 
	        try {   
	        	myemail.getAttachments().transferTo(targetFile);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
	        myemail.setAttachfilename(attachfilename);
	        myemail.setAttachfile(attachfile);
	        //邮件备选项
	        List<Buser> bemailsoption = emailDao.selectBuserEmail();
			model.addAttribute("bemailsoption", bemailsoption);
			model.addAttribute("myemail", myemail);
			return "before/write";
		}
	}
	/**
	 * 收信
	 */
	@Override
	public String recieve(Model model, HttpSession session) {
		List<Map<String, Object>> lm = emailDao.recieve(MyUtil.getUserId(session));
		model.addAttribute("emailList", lm);
		return "before/receive";
	}
	/***
	 * 详情
	 */
	@Override
	public String detail(Model model, Integer id) {
		//更新打开标记
		emailDao.updateOpen(id);
		//查看邮件详情
		List<Map<String, Object>>  le = emailDao.detail(id);
		model.addAttribute("detail", le.get(0));
		return "before/detail";
	}
	/**
	 * 删除一个
	 */
	@Override
	public String deleteOne(Integer id) {
		emailDao.deleteOne(id);
		//删除后到收件箱
		return "forward:/email/recieve";
	}
	/***
	 * 删除多个
	 */
	@Override
	public String deleteMore(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			emailDao.deleteOne(ids[i]);
		}
		return "forward:/email/recieve";
	}
	/**
	 * 回复初始化
	 */
	@Override
	public String reEmailInit(Integer id, Model model) {
		List<Map<String, Object>>  le = emailDao.detail(id);
		Email em = new Email();
		Map<String, Object> mp = le.get(0);
		Integer id_s = (Integer)mp.get("id_s");
		em.setBemailsid(id_s+"");
		String email_s = (String)mp.get("email_s");
		em.setBemails(email_s);
		String title = (String)mp.get("title");
		em.setTitle("Re:" + title);
		String content = (String)mp.get("content");
		content = "At " + mp.get("sendtime") + " " + email_s + " write:"
				+ "                                      "
				+ "                                         " + content;
		em.setContent(content);
		//邮件备选项
        List<Buser> bemailsoption = emailDao.selectBuserEmail();
		model.addAttribute("bemailsoption", bemailsoption);
		model.addAttribute("myemail", em);
		return "before/write";
	}
	/**
	 * 已发送
	 */
	@Override
	public String send(Model model, HttpSession session) {
		List<Map<String, Object>> lm = emailDao.send(MyUtil.getUserId(session));
		model.addAttribute("emailList", lm);
		return "before/send";
	}
}
