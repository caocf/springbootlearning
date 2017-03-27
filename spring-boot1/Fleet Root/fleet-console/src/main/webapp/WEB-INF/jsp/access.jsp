<%@page import="com.sos.fleet.common.settings.CaptchaSettings"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./common/top.jsp" %>
<style type="text/css">
	ol,li{list-style-type:decimal;}
	#message{text-align:center;}
	#message a:hover{
		text-decoration:underline;
	}
</style>
</head>
<body>
<div class="login">
	<div class="form" style="text-align:left;">
		<h2>您访问的页面可能存在如下几个问题</h2>
		<div id="message"><a href="${contextPath }"><span></span>秒后返回首页，或者点击返回首页</a></div>
		<ol>
			<li>会话超时</li>
			<li>没有权限访问</li>
			<li>请求的资源不存在</li>
			<li>不合法操作</li>
			<li>系统繁忙</li>
		</ol>
	</div>
</div>
<%@include file="./common/bottom.jsp" %>
<script>
	(function(){
		var time = 1000;
		var maxTime = 3000;
		var times = maxTime/time;
		$("#message span").html(times);
		var timer = window.setInterval(function(){
			if(--times==0){
				location='${contextPath }';
				return;
			}
			$("#message span").html(times);
		}, time);
	})();
</script>
</body>
</html>