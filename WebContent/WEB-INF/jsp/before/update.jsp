<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		document.updateform.submit();
	}
	//取消按钮
	function cancel(){
		document.updateform.action="";
	}
	</script>
</head>
<body>
	 	<center>
  	<form:form action="user/update" method="post" modelAttribute="buser"  name = "updateform">
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
			<td height="50" colspan="3">
			<input type="button" name="button" onclick="gogo()" value="修改" class="my_ann1" />
			<input type="button" name="button" onclick="cancel()" value="重置" class="my_ann1" /></td>	
		</tr>
	</table>
	</form:form>
	</center>
</body>
</html>