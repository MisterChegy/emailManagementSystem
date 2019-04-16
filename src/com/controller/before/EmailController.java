package com.controller.before;

import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.Email;
import com.service.before.EmailService;
import com.util.MyUtil;

@Controller
@RequestMapping("/email") 
public class EmailController extends BaseBeforeController{
	@Autowired
	private EmailService emailService;
	/**
	 * д��
	 */
	@RequestMapping("/write")
	public String write(Email myemail, Model model, HttpServletRequest request, HttpSession session) {
		return emailService.write(myemail,model,request,session);
	}
	/**
	 * �ظ���ʼ��
	 */
	@RequestMapping("/reEmailInit")
	public String reEmailInit(Integer id, Model model, HttpSession session) {
		return emailService.reEmailInit(id, model);
	}
	/**
	 * ����
	 */
	@RequestMapping("/recieve") 
	public String recieve(Model model, HttpSession session) {
		return emailService.recieve(model, session);
	}
	/**
	 * �ѷ���
	 */
	@RequestMapping("/send")
	public String send(Model model, HttpSession session) {
		return emailService.send(model, session);
	}
	/**
	 * ���ż�������
	 */
	@RequestMapping("/detail") 
	public String detail(Model model, Integer id) {//id���ղ���id
		return emailService.detail(model, id);
	}
	/**
	 * ɾ��һ���ʼ�
	 */
	@RequestMapping("/deleteOne") 
	public String deleteOne(Integer id) {//id���ղ���id
		return emailService.deleteOne(id);
	}
	/***
	 * ɾ������ʼ�
	 */
	@RequestMapping("/deleteMore")
	public String deleteMore(Integer ids[]) {//id���ղ���id
		return emailService.deleteMore(ids);
	}
	/**
	 * ���ظ������÷������������ݿ⣬��ֻд���Ʋ�
	 */
	@RequestMapping("/down") 
	public String down(String  fileid, HttpServletRequest request, HttpServletResponse response) {//fileid���ղ���fileid
		String aFilePath = null; //Ҫ���ص��ļ�·��
		FileInputStream in = null; //������
		ServletOutputStream out = null; //�����
		try {
			//��workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps����
			aFilePath = request.getServletContext().getRealPath("uploadfiles");
			//���������ļ�ʹ�õı�ͷ
			response.setHeader("Content-Type", "application/x-msdownload" );
			response.setHeader("Content-Disposition", "attachment; filename="
					+ MyUtil.toUTF8String(fileid));
			// �����ļ�
			in = new FileInputStream(aFilePath + "\\"+ fileid); 
			//�õ���Ӧ������������������ͻ����������������
			out = response.getOutputStream();
			out.flush();
			int aRead = 0;
			byte b[] = new byte[1024];
			while ((aRead = in.read(b)) != -1 & in != null) {
				out.write(b,0,aRead);
			}
			out.flush();
			in.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		return null;
	}
}
