<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上商城管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet" type="text/css" />

<script>
	function checkForm() {
		// 校验用户名:
		// 获得用户名文本框的值:
		var username = document.getElementById("username").value;
		if (username == null || username == '') {
			alert("用户名不能为空!");
			return false;
		}
		// 校验密码:
		// 获得密码框的值:
		var password = document.getElementById("password").value;
		if (password == null || password == '') {
			alert("密码不能为空!");
			return false;
		}
	}
</script>

<style type="text/css">
body {
	color: white;
}
</style>

<body style="background: #278296">
	<center><s:actionerror /></center>
	<form method="post"
		action="${pageContext.request.contextPath }/adminUser_login.action"
		target="_parent" name='theForm' onsubmit="return checkForm();">
		<table cellspacing="0" cellpadding="0" style="margin-top: 100px"
			align="center">
				<td style="padding-left: 50px">
					<table>
						<tr>
							<td colspan="2" align="center"><h2>商城后台管理登录</h2></td>
						</tr>
						<tr>
							<td>管理员姓名：</td>
							<td><input type="text" name="username" /></td>
						</tr>
						<tr>
							<td>管理员密码：</td>
							<td><input type="password" name="password" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="进入管理中心" class="button" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" name="act" value="signin" />
	</form>

</body>