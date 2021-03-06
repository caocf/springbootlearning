<%@page import="com.cus.common.settings.CaptchaSettings"%>
<%@ page language="java" import="java.util.*,org.springframework.security.core.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./common/top.jsp" %>
</head>
<body>
<div class="login">
	<div class="form">
		<form action="${contextPath}/login" method="post" name="fm">
			<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<h2 class="ico">企业用户登录</h2>
			<ul>
				<li>
					<label>用户名：</label>
					<span class="ico clear"></span>
					<input class="inputbox" name="username"/>
					<p class="err"></p>
				</li>
				<li>
					<label>密码：</label>
					<span class="ico clear"></span>
					<input type="password" autocomplete="off" name="password"  class="inputbox" />
					<p class="err"></p>
				</li>
				<li>
					<label>验证码：</label>
					<span class="ico clear"></span>
					<input type="text" maxlength="6" size="7" name="securityCode" id="securityCode" class="g-ipt">
					<a id="sendSecurityCode" class="bigBlue">获取验证码</a>
					<!-- <span style="width: auto; margin: 0px; padding: 0px; background: none repeat scroll 0% 0% transparent;" id="securityCodeTip" class="ico-light"></span> -->
					<p class="err"></p>
				</li>
				
				<%-- <c:if test="<%=CaptchaSettings.showCapcha(request) %>">
					<li>
						<label>验证码：</label>
						<span class="ico clear"></span>
						<input type="text" maxlength="5" autocomplete="off" name="captcha" class="inputbox" />
						<p class="err"></p>
					</li>
					<li  style="height:55px">
						<img style="cursor: pointer;" src="${contextPath}/captcha?t=<%=new Date().getTime() %>" width="129" height="46" class="vcode" />
						<a href="javascript:;" class="refresh">看不清？换一张</a>
					</li>
				</c:if> --%>
				<li>
					<span class="left sl">
						<input type="checkbox" name="remember-me" value="true"/> 记住我
					</span>
					<span class="right sr">
						<i class="tips">请联系管理员重置您的密码</i>
						<a href="javascript:;" class="underline" id="forgetPwd">忘记密码？</a>
					</span>
				</li>
			</ul>
			<a href="javascript:;" class="ico btn loginbtn">登录</a>
			<p class="err" style="text-indent: 118px;">
				<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
					<%
						boolean isAuthenticationException = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") instanceof AuthenticationException ;
					 %>
					<c:choose>
						<c:when test="<%= !isAuthenticationException%>">
							本次验证出错，请稍后再试。
						</c:when>
						<c:otherwise>
							${SPRING_SECURITY_LAST_EXCEPTION.message}
						</c:otherwise>
					</c:choose>
					<%
						session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
					%>	
				</c:if>
			</p>
		</form>
		<div class="secode"></div>
	</div>
</div>
<%@include file="./common/bottom.jsp" %>
<script type="text/javascript">
	(function(f){
			f.messages={
					username:{
						required:'<spring:message code="username.required"></spring:message>'
					},
					password:{
						required:'<spring:message code="signword.required"></spring:message>'
					},
					securityCode:{
						required:'<spring:message code="securitycode.required"></spring:message>',
						getFailed:'<spring:message code="securitycode.get.failed"></spring:message>',
						get:'<spring:message code="securitycode.get"></spring:message>',
						getting:'<spring:message code="securitycode.getting"></spring:message>'
					}
			}
	})(window.Fsp);
</script>
<script id="commonScript_login" type="text/javascript" src="${jsPath}/login.js"></script>
</body>
</html>