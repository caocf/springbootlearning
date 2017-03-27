<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../common/top.jsp" %>
</head>
<body>
<div class="searchform">
	<%@include file="../common/navigation.jsp" %>
	<div class="content">
		<%@include file="../common/menu.jsp" %>
		<div class="col2">
			<div class="env_read_only_mes"></div>
			<div class="title">
				<h2>修改密码</h2>
			</div>
			<div class="form2">
			<form name="fm" method="post">
				<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}" defaultValue="${_csrf.token}" />
				<ul>
					<li>
						<label>旧密码：</label>
						<input type="password" name="oldPassword" autocomplete="off" defaultValue="" class="inputbox" maxlength="20" />
						<p class="err"></p>
					</li>
					<li>
						<label>新密码：</label>
						<input type="password" id="password" name="password" autocomplete="off" defaultValue="" class="inputbox" maxlength="20" />
						<p class="err"><!-- 密码长度位数必须在6-20位之间，必须含有大小写字母和数字，例如：Abc123 --></p>
					</li>
					<li>
						<label>确认新密码：</label>
						<input type="password" name="confirmPassword" autocomplete="off" defaultValue="" class="inputbox" maxlength="20" />
						<p class="err"><!-- 新密码必须与确认新密码相同 --></p>
					</li>
				</ul>
				<div class="submit_info">
					<p></p>
				</div>
				<a href="javascript:;" class="ico btn submitbtn modifypwdbtn env_read_only">提交</a>
				<a href="javascript:;" class="ico btn cancel">重置</a>
			</form>
			</div>
		</div>
	</div>
	<%@include file="../common/footer.jsp" %>
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
						validate_failed:'<spring:message code="modifyAuth.validate.failed"></spring:message>',
						failed:'<spring:message code="modifyAuth.failed"></spring:message>'
					}
			}
	})(window.Fsp);
</script>
<script id="commonScript_login" type="text/javascript" src="${jsPath}/user.js"></script>
</body>
</html>