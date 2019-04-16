<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<style type="text/css">
.blank20{height:20px; line-height:8px; clear:both; visibility:hidden;}
.box4{width:700px; height:auto;}
.clearfix:after{
content:"."; display:block; height:0; clear:both;
visibility:hidden;
}
*html .clearfix{
 height:1%;
}
*+html .clearfix{
 height:1%;
}
.case_ll{background: #FFFFFF;border: 1px solid #D9D9D9;box-shadow: 0 0 4px #CCCCCC; -webkit-box-shadow:0px 0px 4px #CCCCCC; -moz-box-shadow:0px 0px 4px #CCCCCC;}
.regist{ padding:19px; font-size:14px;}
.ttbb2 td {padding:2px;}
.cl_f30{ color:#ff3300;}
.my_txt_350{ width:300px; font-size:14px; color:#666; border:1px solid #dcdcdc; padding:5px 3px; background:url(images/input.gif) repeat-x 0 0;}
.my_txt_120{ width:120px; font-size:14px; color:#666; border:1px solid #dcdcdc; padding:5px 3px; background:url(images/input.gif) repeat-x 0 0;}
.my_ann1{ width:147px; height:34px; cursor:pointer; line-height:34px;font-size:14px; color:#fff; border:none; background:url(images/my_bj5.gif) no-repeat 0 0; font-family:"微软雅黑"}
</style>
<script type="text/javascript">
	//刷新验证码
	function refreshCode(){
		document.getElementById("code").src = "validateCode?" + Math.random();
    }
	//表单验证
	function checkForm(){
		var bpwd = document.registerForm.bpwd.value;
		var rebpwd = document.registerForm.rebpwd.value;
		if(bpwd != rebpwd){
			alert("两次密码不一致！");
			return false;
		}
		document.registerForm.submit();
		return true;
	}
</script>
</head>
<body>
	<div class="clearfix">
		<div class="blank20"></div>
		<div class="box4">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100%" valign="bottom" align="center">
						<h2>普通会员注册</h2>
					</td>
				</tr>
			</table>
		</div>
		<div class="blank10"></div>
		<div class="box4">
			<div class="case_ll clearfix">
				<div class="regist">
					<form:form action="user/register" method="post" modelAttribute="buser"  name="registerForm">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="ttbb2">
							<tr>
								<td width="16%" align="right"><span class="cl_f30">*</span>
									E-mail：</td>
								<td width="59%"><input type="text" name="bemail"
									id="textfield" class="my_txt_350" value="${buser.bemail}" />@126.com</td>
								<td>${msg }</td>
							</tr>
							<tr>
								<td align="right"><span class="cl_f30">*</span> 密码：</td>
								<td><input type="password" name="bpwd" id="textfield2"
									class="my_txt_350" /></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><span class="cl_f30">*</span> 重复密码：</td>
								<td><input type="password" name="rebpwd" id="textfield3"
									class="my_txt_350" /></td>
								<td>&nbsp;</td>
							</tr>		
							<tr>
								<td align="right"><span class="cl_f30">*</span> 验证码：</td>
								<td class="ared"><input type="text" name="code"
									id="textfield5" class="my_txt_120" />
									<img id="code" src="validateCode" /> 
									<a href="javascript:refreshCode();"><font color="blue">看不清，换一个！</font></a>
								</td>
								<td>${codeError}</td>
							</tr>
							<tr>
								<td align="right">&nbsp;</td>
								<td height="50"><input type="button" name="button" id="button" onclick="checkForm()" value="注册账号" class="my_ann1" /></td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</form:form>
					<div class="blank10"></div>
				</div>
			</div>
		</div>
		<div class="blank20"></div>
	</div>
</body>
</html>
