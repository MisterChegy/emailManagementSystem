package com.po;

import org.springframework.web.multipart.MultipartFile;

public class Email {
	private int id;
	private String bemails;//����һ���Է��������ϵ��
	private String bemailsid;//�û�ID
	private String bemail;//��ѡ���email
	private String title;
	private String attachfilename;//�ϴ����ļ�ԭ��
	private String attachfile;//�ϴ�������ļ���
	private MultipartFile attachments;
	private String content;
	private String act;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBemails() {
		return bemails;
	}
	public void setBemails(String bemails) {
		this.bemails = bemails;
	}
	public String getAttachfilename() {
		return attachfilename;
	}
	public void setAttachfilename(String attachfilename) {
		this.attachfilename = attachfilename;
	}
	public String getAttachfile() {
		return attachfile;
	}
	public void setAttachfile(String attachfile) {
		this.attachfile = attachfile;
	}
	public MultipartFile getAttachments() {
		return attachments;
	}
	public void setAttachments(MultipartFile attachments) {
		this.attachments = attachments;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getBemailsid() {
		return bemailsid;
	}
	public void setBemailsid(String bemailsid) {
		this.bemailsid = bemailsid;
	}
	public String getBemail() {
		return bemail;
	}
	public void setBemail(String bemail) {
		this.bemail = bemail;
	}
	
}
