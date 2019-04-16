<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" type="text/css" rel="stylesheet">
<title>Insert title here</title>
<script type="text/javascript">
	function goback(){
		history.back();
	}
	function confirmDelete(id){
		if(window.confirm("真的删除吗？really?")){
				window.location.href = "/emailSys01/email/deleteOne?id="+id;
		}
	}
</script>
</head>
<body>
<a href="javascript:goback();" >返回</a>&nbsp;&nbsp;<a href="javascript:confirmDelete(${detail.id});">删除</a>
&nbsp;&nbsp;<a href="email/reEmailInit?id=${detail.id}" >回复</a><br><br>
主题：<strong>${detail.title }</strong><br><br>
发件人：${detail.email_s }<br><br>
收件人：${detail.email_r }<br><br>
时间：${detail.sendtime }<br><br>
附件：<c:forTokens var="filename" items="${detail.attachfilename }" delims="|">
		${filename}&nbsp;&nbsp;
	</c:forTokens><br><br>
附件下载：
	<c:forTokens var="fileid" items="${detail.attachfile }" varStatus="status"  delims="|">
		<a href="email/down?fileid=${fileid}">附件${status.index+1 }下载</a>&nbsp;&nbsp;
	</c:forTokens>
<br><br>
<pre>${detail.content }</pre>
</body>
</html>