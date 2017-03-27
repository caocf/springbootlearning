<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="./common/top.jsp" %>
</head>
<body>

<div class="searchform">
	<%@include file="common/navigation.jsp" %>
	<div class="content">
		<%@include file="common/menu.jsp" %>
		<div class="col2">
			<div class="title">
				<h2>创建企业用户</h2>
			</div>
			<div class="form2">
				<form id="userform" method="post">
				<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input name="id" value="${user.id }" type="hidden" class="inputbox" />
				<ul>
					<li>
						<label>*公司名称：</label>
						<input name="fleetDomain.name" value="${user.fleetDomain.name }" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>*组织机构代码：</label>
						<input name="orgId" value="${user.orgId }" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>*用户名：</label>
						<input name="userName" value="${user.userName }" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>*邮箱：</label>
						<input name="email" value="${user.email }" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>*联系方式：</label>
						<input name="mobile" value="${user.mobile }" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>地址：</label>
						<input name="address" value="${user.address }" type="text" class="inputbox" />
						<p class="err"></p>
					</li>
				</ul>
				<a href="javascript:;" onclick="addUser();" class="ico btn savebtn">保存</a>
				<a href="javascript:;" onclick="searchUsers();" class="ico btn backbtn">返回</a>
				</form>
			</div>
		</div>
	</div>
	<div class="footer">
		<p class="left">&copy;2013上海安吉星信息服务有限公司 版权所有 | 沪ICP备11047918号</p>
		<p class="right"><a href="">网站地图</a> | <a href="">联系我们</a> | <a href="">网站声明</a></p>
	</div>
</div>
<%@include file="./common/bottom.jsp" %>
<script type="text/javascript" src="${request.contextPath}/common/js/user.js"></script>
</body>
</html>