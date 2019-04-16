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
	 * д��
	 */
	@Override
	public String write(Email myemail, Model model, HttpServletRequest request, HttpSession session) {
		if("submit".equals(myemail.getAct())) {//�ύ��ť
			emailDao.writeEmail(myemail);//����ʼ�����ӳɹ���Email��ID�Զ����myemail������
			String ids = myemail.getBemailsid();//������id�Էֺ�;�ָ�
			System.out.println(ids + "ids");
			String rid[] = ids.split(";");
			String emails = myemail.getBemails();//������email�Էֺ�;�ָ�
			String remail[] = emails.split(";");
			int s_id = MyUtil.getUserId(session);
			String s_email = MyUtil.getUserEmail(session);
			//����ʼ���¼
			for (int i = 0; i < rid.length; i++) {
				Rsrecordtable rt = new Rsrecordtable();
				rt.setId_s(s_id);//������id
				rt.setEmail_s(s_email);
				rt.setId_r(Integer.parseInt(rid[i]));//������id
				rt.setEmail_r(remail[i]);//������email
				rt.setId_email(myemail.getId());//�ʼ�id
				emailDao.writeEmailRecord(rt);
			}
			return "before/writeSuccess";
		}else {//�ϴ�����
			String attachfile = myemail.getAttachfile();
			String attachfilename = myemail.getAttachfilename();
			/*�ļ��ϴ�����������λ��"/uploadfiles"����λ����ָ
			workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps��
			������ʹ��*/
			String realpath = request.getServletContext().getRealPath("uploadfiles");
			String fileName = myemail.getAttachments().getOriginalFilename(); 
			//ԭ�ļ���
			if(attachfilename.length() > 0) {
				attachfilename = attachfilename + "|" + fileName;
			}else {
				attachfilename = fileName;
			}
			//���ļ���
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
			//�ϴ� 
	        try {   
	        	myemail.getAttachments().transferTo(targetFile);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
	        myemail.setAttachfilename(attachfilename);
	        myemail.setAttachfile(attachfile);
	        //�ʼ���ѡ��
	        List<Buser> bemailsoption = emailDao.selectBuserEmail();
			model.addAttribute("bemailsoption", bemailsoption);
			model.addAttribute("myemail", myemail);
			return "before/write";
		}
	}
	/**
	 * ����
	 */
	@Override
	public String recieve(Model model, HttpSession session) {
		List<Map<String, Object>> lm = emailDao.recieve(MyUtil.getUserId(session));
		model.addAttribute("emailList", lm);
		return "before/receive";
	}
	/***
	 * ����
	 */
	@Override
	public String detail(Model model, Integer id) {
		//���´򿪱��
		emailDao.updateOpen(id);
		//�鿴�ʼ�����
		List<Map<String, Object>>  le = emailDao.detail(id);
		model.addAttribute("detail", le.get(0));
		return "before/detail";
	}
	/**
	 * ɾ��һ��
	 */
	@Override
	public String deleteOne(Integer id) {
		emailDao.deleteOne(id);
		//ɾ�����ռ���
		return "forward:/email/recieve";
	}
	/***
	 * ɾ�����
	 */
	@Override
	public String deleteMore(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			emailDao.deleteOne(ids[i]);
		}
		return "forward:/email/recieve";
	}
	/**
	 * �ظ���ʼ��
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
		//�ʼ���ѡ��
        List<Buser> bemailsoption = emailDao.selectBuserEmail();
		model.addAttribute("bemailsoption", bemailsoption);
		model.addAttribute("myemail", em);
		return "before/write";
	}
	/**
	 * �ѷ���
	 */
	@Override
	public String send(Model model, HttpSession session) {
		List<Map<String, Object>> lm = emailDao.send(MyUtil.getUserId(session));
		model.addAttribute("emailList", lm);
		return "before/send";
	}
}
