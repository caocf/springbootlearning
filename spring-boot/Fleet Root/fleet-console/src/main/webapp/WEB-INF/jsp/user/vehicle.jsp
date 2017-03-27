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
				<h2>修改车辆信息</h2>
			</div>
			<div class="form2">
				<form name="fm" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" defaultValue="${_csrf.token}" />
				<input type="hidden" name="companyName" value="${pojo.fleetDomain.name }" />
				<input type="hidden" name="id" value="${pojo.id }" />
				<input type="hidden" name="userId" value="${pojo.userId }" />
				<ul>
					<li>
						<label>公司名称：</label>
						<span class="txt">${pojo.fleetDomain.name }</span>
						<p class="err"></p>
					</li>
					<li>
						<label>车架号：</label>
						<span class="txt">${pojo.vin }</span>
						<p class="err"></p>
					</li>
					<li>
						<label>绑定状态：</label>
						<span class="txt">${pojo.status eq "1" ? "Success" : "Failure" }</span>
						<p class="err"></p>
					</li>
					<li>
						<label>绑定时间：</label>
						<span class="txt"><fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${pojo.bindingDate }"/></span>
						<p class="err"></p>
					</li>
					<li>
						<label>使用人：</label>
						<input type="text" name="driver" value="${pojo.driver }" maxlength="100" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>车型：</label>
						<input type="text" name="model" value="${pojo.model }" maxlength="50" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>车牌：</label>
						<input type="text" name="plateId" value="${pojo.plateId }" maxlength="100" class="inputbox" />
						<p class="err"></p>
					</li>
					<li>
						<label>联系方式：</label>
						<input type="text" name="telephone" value="${pojo.telephone }" maxlength="25" class="inputbox" />
						<p class="err"></p>
					</li>
				</ul>
				<div class="submit_info">
					<p></p>
				</div>
				<a href="javascript:;" class="ico btn submitbtn env_read_only">提交</a>
				<a href="javascript:;" class="ico btn cancel">返回</a>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../common/footer.jsp" %>
</div>
<%@include file="../common/bottom.jsp" %>
<script type="text/javascript" src="${jsPath}/vehicle.js"></script>
</body>
</html>