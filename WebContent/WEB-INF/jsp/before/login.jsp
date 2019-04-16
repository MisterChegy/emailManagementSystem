<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录页面</title>
	<style type="text/css">
	table{
		text-align: center;
	}
	.textSize{
		width: 200px;
		height: 20px;
	}
	.ared a:link,.ared a:visited{color:#333; text-decoration:none;}
	.ared a:hover,.ared a:active{color:#ff6600; text-decoration:none;}
	.my_ann1{ width:80px; height:34px; cursor:pointer; line-height:34px;font-size:14px; color:#fff; border:none; background:url(images/my_bj5.gif) no-repeat 0 0; font-family:"微软雅黑"}
	</style>
	<script type="text/javascript">
	//确定按钮
	function gogo(){
		document.loginform.submit();
	}
	//取消按钮
	function cancel(){
		document.loginform.action="";
	}
	function refreshCode(){
		document.getElementById("code").src = "validateCode?" + Math.random();
    }
	function register(){
		window.location.href="user/toRegister";
	}
	</script>
  </head>
  <body>
  	<center>
  	<form:form action="user/login" method="post" modelAttribute="buser"  name = "loginform">
	<table>
		<tr>
			<td colspan="2"><img src="images/login.gif"></td>
		</tr>
		<tr>
			<td>E-Mail：</td>
			<td><input type="text" name="bemail" value="${buser.bemail }"  class="textSize"/></td>
			<td>@126.com</td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" name="bpwd" class="textSize"/></td>
			<td>&nbsp;</td>
		</tr>		
		<tr>
			<td>验证码：</td>
			<td><input type="text" name="code" class="textSize"/></td>
			<td>&nbsp;</td>
		</tr>		
		<tr>
			<td>
				<img id="code" src="validateCode"/>
			</td>
			<td class="ared">
				<a href="javascript:refreshCode();"><font color="blue">看不清，换一个！</font></a>
			</td>
			<td>&nbsp;</td>
		</tr>		
		<tr>
			<td height="50" colspan="3"><input type="button" name="button" onclick="register()" value="注册账号" class="my_ann1" />
			<input type="button" name="button" onclick="gogo()" value="登录" class="my_ann1" />
			<input type="button" name="button" onclick="cancel()" value="重置" class="my_ann1" /></td>	
		</tr>
	</table>
	</form:form>
	${msg }
	</center>
  </body>
</html>
