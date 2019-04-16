<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>receive.jsp</title>
<link href="css/common.css" type="text/css" rel="stylesheet">
<style type="text/css">
		table{
			text-align: center;
			border-collapse: collapse;
		}
		.bgcolor{
		  	background-color: #F08080;
		}
	</style>
	<script type="text/javascript">
		function changeColor(obj){
			obj.className = "bgcolor";
		}
		function changeColor1(obj){
			obj.className = "";
		}
  		function confirmDelete(id){
  			if(window.confirm("真的删除吗？really?")){
  				window.location.href = "/emailSys01/admin/delete?id=" + id;
  			}
  		}
	</script>
</head>
<body>
	<c:if test="${allEmails.size() == 0 }">
		您还没有邮件可管理。
	</c:if>
	<c:if test="${allEmails.size() != 0 }">
		<table border="1" bordercolor="PaleGreen">
			<tr>
				<td width="100px">收件人</td>
				<td width="200px">发件人</td>
				<td width="300px">主题</td>
				<td width="100px">时间</td>
				<td width="100px">操作</td>
			</tr>
			
			<c:forEach items="${allEmails }" var="email">
				<tr onmousemove="changeColor(this)" onmouseout="changeColor1(this)">
					<td>${email.email_r }</td>
					<td>${email.email_s }</td>
					<td>${email.title }</td>
					<td>${email.sendtime }</td>
					<td><a href="javaScript:confirmDelete('${email.id_email }')">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>	
</body>
</html>