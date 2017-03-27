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
				<c:choose>
					<c:when test="${user.id == null }">
						<h2>创建企业用户</h2>
					</c:when>
					<c:otherwise>
						<h2>修改企业用户信息</h2>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form2">
				<form name="fm" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" defaultValue="${_csrf.token}" />
				<input name="id" value="${user.id }" defaultValue="" type="hidden" class="inputbox" />
				<input name="fleetId" value="${user.fleetId }" defaultValue="" type="hidden" class="inputbox" />
				<ul>
					<c:if test="${user.id != null }">
						<li>
							<label>*用户名：</label>
							<span class="txt">${user.userName }</span>
							<input name="userName" value="${user.userName }" defaultValue="" maxlength="25" type="hidden" class="inputbox" />
							<p class="err"></p>
						</li>
					</c:if>
					<li>
						<label>*公司名称：</label>
						<input name="fleetDomain.name" value="${user.fleetDomain.name }" maxlength="100" defaultValue="" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>*组织机构代码：</label>
						<input name="orgId" value="${user.orgId }" defaultValue="" maxlength="100" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
					<c:if test="${user.id == null }">
						<li>
							<label>*用户名：</label>
							<input name="hasUser" value="false" type="hidden" />
							<input name="userName" value="${user.userName }" maxlength="25" defaultValue="" ${user.id != null ? "readonly" : "" } type="text" class="inputbox" />
							<p class="err"></p>
						</li>
					</c:if>
					<li>
						<label>*邮箱：</label>
						<input name="email" value="${user.email }" maxlength="50" defaultValue="" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>*联系方式：</label>
						<input name="mobile" value="${user.mobile }" maxlength="25" defaultValue="" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>地址：</label>
						<input name="address" value="${user.address }" maxlength="200" defaultValue="" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
				</ul>
				<div class="submit_info">
					<p></p>
				</div>
				<a href="javascript:;" class="ico btn savebtn env_read_only">保存</a>
				<a href="javascript:;" class="ico btn backbtn">返回</a>
				<c:if test="${user.id != null }">
					<a class="restpwdbtn env_read_only" style="font-size:14px; color:#1462ac; text-decoration:underline; margin:14px 0px 0px 30px; float:left;" href="javascript:;">重置密码</a>
					<!-- <a href="javascript:;" class="ico btn restpwdbtn">重置密码</a> -->
				</c:if>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../common/footer.jsp" %>
</div>
<%@include file="../common/bottom.jsp" %>

<div class="mask" id="resetpwd">
	<div class="modal">
		<h2 class="title">&nbsp;</h2>
		<a href="javascript:;" class="ico close"></a>
		<div class="content">
			<div class="container">
				<p>此次操作会重试用户密码为初始密码，请确认是否要重置用户密码？</p>
				<a href="javascript:;" class="ico btn1 confirm">确定</a>
				<a href="javascript:;" class="ico btn1 cancel">取消</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	(function(f){
			f._csrfName = "${_csrf.parameterName}";
			f._csrfToken = "${_csrf.token}";
			f.messages={
					"fleetDomain.name":{
						required:'<spring:message code="fu.star.required"></spring:message>'
					},
					orgId:{
						required:'<spring:message code="fu.star.required"></spring:message>'
					},
					userName:{
						required:'<spring:message code="fu.star.required"></spring:message>',
						regex:'<spring:message code="fu.username.regex"></spring:message>',
						remote:'<spring:message code="fu.username.hasUser"></spring:message>'
					},
					email:{
						required:'<spring:message code="fu.star.required"></spring:message>',
						regex:'<spring:message code="fu.email.right"></spring:message>'
					},
					mobile:{
						required:'<spring:message code="fu.star.required"></spring:message>',
						regex:'<spring:message code="fu.mobile.regex"></spring:message>'
					},
					createUser:{
						success:'<spring:message code="fu.user.create.success"></spring:message>'
					},
					updateUser:{
						success:'<spring:message code="fu.user.update.success"></spring:message>'
					},
					resetPwd:{
						success:'<spring:message code="fu.user.resetPwd.success"></spring:message>',
						fail:'<spring:message code="fu.user.resetPwd.fail"></spring:message>'
					}
			}
	})(window.Fsp);
</script>
<script type="text/javascript" src="${jsPath}/user.js"></script>
</body>
</html>