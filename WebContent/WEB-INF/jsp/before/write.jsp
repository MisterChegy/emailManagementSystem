<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>write.jsp</title>
<link href="css/common.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
	function gogo() {
		document.forms[0].act.value = "gogo";
		document.forms[0].submit();
	}
	
	function selectEmail(){
		var bemails = document.forms[0].bemails.value;
		var bemailsid =  document.forms[0].bemailsid.value;
		var htmlinner = document.getElementById("bemails").innerHTML;
		var bemail = document.forms[0].bemail;
		var bemailop = "";
		var bemailidop = "";
		for ( var i = 0; i < bemail.options.length; i++) { 
			if(bemail.options[i].selected){
					bemailop = bemail.options[i].text;
					bemailidop = bemail.options[i].value;
			}
		}
		if(htmlinner != ""){
			var emailarray = htmlinner.split(";");
			if(!validelement(emailarray, bemailop)){
				htmlinner = htmlinner + ";" + bemailop;
				bemails = bemails + ";" +  bemailop;
				bemailsid = bemailsid + ";" + bemailidop;
			}
		}else{
			htmlinner = bemailop;
			bemails = bemailop;
			bemailsid = bemailidop;
		}
		document.getElementById("bemails").innerHTML = htmlinner;
		document.forms[0].bemails.value = bemails;
		document.forms[0].bemailsid.value = bemailsid;
	}
	//验证数组中是否有该元素
	function validelement(emailarray, bemailop){
		for(var i = 0; i < emailarray.length; i++){
			if(emailarray[i] == bemailop)
				return true;
		}
		return false;
	}
</script>
</head>    
<body>
	<form:form action="email/write" method="post" modelAttribute="myemail" enctype="multipart/form-data">
		<table border=1 style="border-collapse: collapse">
			<tr>
				<td>收件人<font color="red">*</font></td>
				<td>
					<span id="bemails">${myemail.bemails}</span>
					<form:hidden path="bemails"/>
					<form:hidden path="bemailsid"/>
					<form:select path="bemail" onchange="selectEmail()">
						<form:options items="${bemailsoption }" itemLabel="bemail" itemValue="id"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>主题<font color="red">*</font></td>
				<td>
					<form:input path="title" cssStyle="width:500px"/>
				</td>
			</tr>
			<tr>
				<td>附件</td>
				<td>
					<span id="attachmentsid">${myemail.attachfilename}</span>
					<form:hidden path="attachfilename"/>
					<form:hidden path="attachfile"/>
					<input type="hidden" name="act" value="submit"/>
					<input type="file" name="attachments" onChange="gogo()"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<form:textarea path="content" rows="20"  cols="90"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit"  value="发送"/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
