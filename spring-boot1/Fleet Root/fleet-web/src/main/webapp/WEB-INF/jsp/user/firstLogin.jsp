<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../common/top.jsp" %>
</head>
<body>
<div class="login">
	<%@include file="../common/navigation.jsp" %>
	<div class="form" style="margin-left: -223px;width:600px;">
		<div style="text-align:left;"><h2 class="nobg">为了您的账户安全，请修改您的密码</h2></div>
		<form name="fm" method="post">
		<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}" defaultValue="${_csrf.token}" />
		<ul>
			<li>
				<label>旧密码：</label>
				<input type="text" readonly="readonly" value="●●●●●●" class="inputbox" />
				<p class="err"></p>
			</li>
			<li>
				<label>新密码：</label>
				<input type="password" id="password" name="password" autocomplete="off" class="inputbox" maxlength="20" />
				<p class="err"></p>
			</li>
			<li>
				<label>确认新密码：</label>
				<input type="password" name="confirmPassword" autocomplete="off" class="inputbox" maxlength="20" />
				<p class="err"></p>
			</li>
		</ul>
		<div class="submit_info">
			<p></p>
		</div>
		<a href="javascript:;" class="ico btn submitbtn firstloginbtn">提交</a>
		</form>
	</div>
</div>
<%@include file="../common/bottom.jsp" %>
<script type="text/javascript">
	(function(f){
			f.messages={
					oldPassword:{
						required:'<spring:message code="oldPassword.required"></spring:message>'
					},
					password:{
						required:'<spring:message code="newPassword.required"></spring:message>',
						regex:'<spring:message code="signword.regex"></spring:message>'
					},
					confirmPassword:{
						required:'<spring:message code="confirmPassword.required"></spring:message>',
						equalTo:'<spring:message code="confirmPassword.equalTo"></spring:message>'
					},
					modifyAuth:{
						success:'<spring:message code="modifyAuth.success"></spring:message>',
						failed:'<spring:message code="modifyAuth.failed"></spring:message>'
					}
			}
	})(window.Fsp);
</script>
<script id="commonScript_login" type="text/javascript" src="${jsPath}/user.js"></script>
</body>
</html>