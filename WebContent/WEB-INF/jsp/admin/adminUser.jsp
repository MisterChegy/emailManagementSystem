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
		function confirmLock(id){
  			if(window.confirm("真的锁定吗？really?")){
  				window.location.href = "/emailSys01/admin/lock?id=" + id;
  			}
  		}
		function confirmUnlock(id){
  			if(window.confirm("真的解锁吗？really?")){
  				window.location.href = "/emailSys01/admin/unLock?id=" + id;
  			}
  		}
	</script>
</head>
<body>
	<c:if test="${allUsers.size() == 0 }">
		您还没有用户可管理。
	</c:if>
	<c:if test="${allUsers.size() != 0 }">
		<table border="1" bordercolor="PaleGreen">
			<tr>
				<td width="100px">用户ID</td>
				<td width="200px">用户Email</td>
				<td width="100px">操作</td>
			</tr>
			
			<c:forEach items="${allUsers }" var="u">
				<tr onmousemove="changeColor(this)" onmouseout="changeColor1(this)">
					<td>${u.id }</td>
					<td>${u.bemail }</td>
					<td>
					<c:if test="${u.islock == 0 }">
						<a href="javaScript:confirmLock('${u.id }')">锁定</a>
					</c:if>
					<c:if test="${u.islock == 1 }">
						<a href="javaScript:confirmUnlock('${u.id }')">解锁</a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>	
</body>
</html>