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
	 * 写信
	 */
	@RequestMapping("/write")
	public String write(Email myemail, Model model, HttpServletRequest request, HttpSession session) {
		return emailService.write(myemail,model,request,session);
	}
	/**
	 * 回复初始化
	 */
	@RequestMapping("/reEmailInit")
	public String reEmailInit(Integer id, Model model, HttpSession session) {
		return emailService.reEmailInit(id, model);
	}
	/**
	 * 收信
	 */
	@RequestMapping("/recieve") 
	public String recieve(Model model, HttpSession session) {
		return emailService.recieve(model, session);
	}
	/**
	 * 已发送
	 */
	@RequestMapping("/send")
	public String send(Model model, HttpSession session) {
		return emailService.send(model, session);
	}
	/**
	 * 打开信件看详情
	 */
	@RequestMapping("/detail") 
	public String detail(Model model, Integer id) {//id接收参数id
		return emailService.detail(model, id);
	}
	/**
	 * 删除一个邮件
	 */
	@RequestMapping("/deleteOne") 
	public String deleteOne(Integer id) {//id接收参数id
		return emailService.deleteOne(id);
	}
	/***
	 * 删除多个邮件
	 */
	@RequestMapping("/deleteMore")
	public String deleteMore(Integer ids[]) {//id接收参数id
		return emailService.deleteMore(ids);
	}
	/**
	 * 下载附件，该方法不访问数据库，故只写控制层
	 */
	@RequestMapping("/down") 
	public String down(String  fileid, HttpServletRequest request, HttpServletResponse response) {//fileid接收参数fileid
		String aFilePath = null; //要下载的文件路径
		FileInputStream in = null; //输入流
		ServletOutputStream out = null; //输出流
		try {
			//从workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps下载
			aFilePath = request.getServletContext().getRealPath("uploadfiles");
			//设置下载文件使用的报头
			response.setHeader("Content-Type", "application/x-msdownload" );
			response.setHeader("Content-Disposition", "attachment; filename="
					+ MyUtil.toUTF8String(fileid));
			// 读入文件
			in = new FileInputStream(aFilePath + "\\"+ fileid); 
			//得到响应对象的输出流，用于向客户端输出二进制数据
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
