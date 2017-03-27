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
				<h2>账户管理</h2>
			</div>
			<form name="fm" method="post">
			<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="search">
				<input type="hidden" name="userId" />
				<input type="hidden" name="page" value="${page.page }"/>
				<input type="hidden" name="companyName" />
				<input type="hidden" name="orders[0].property" value="${page.orders[0].property }" />
				<input type="hidden" name="orders[0].direction" value="${page.orders[0].direction }" />
				<input type="text" name="key" value="${condition.key }" class="inputbox" placeholder="请输入公司名称/用户名/组织机构代码" />
				<a href="javascript:;" class="ico btn searchbtn">搜索</a>
				<a href="javascript:;" class="ico btn bangding">创建新用户</a>
			</div>
			<div class="tableform" id="choose">
				<table cellpadding="0" cellspacing="0">
					<thead class="ico">
						<tr>
							<th width="53" class="ico first" sort="false"><i class="ico blank">编号</i></th>
							<th width="98"><i class="ico blank">公司名称</i></th>
							<th width="89"><i class="ico blank">组织机构</i></th>
							<th width="82"><i class="ico blank">用户名</i></th>
							<th width="94"><i class="ico blank">邮件</i></th>
							<th width="88"><i class="ico blank">联系电话</i></th>
							<th width="95"><i class="ico blank">地址</i></th>
							<th width="95" class="ico last" sort="false">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.pageResult.content }" var="pojo" varStatus="i">
							<tr>
								<td class="t20">${i.index+1 }</td>
								<td class="t21">${pojo.fleetDomain.name }</td>
								<td class="t22" title="${pojo.orgId }">
									<nobr>
									${fn:length(pojo.orgId) gt 9 ? 
									fn:substring(pojo.orgId,0,6):pojo.orgId}${fn:length(pojo.orgId) gt 9?'...':''}
									</nobr>
								</td>
								<td class="t23" title="${pojo.userName }">
									<nobr>
									${fn:length(pojo.userName) gt 9 ? 
									fn:substring(pojo.userName,0,6):pojo.userName}${fn:length(pojo.userName) gt 9?'...':''}
									</nobr>
								</td>
								<td class="t24">${pojo.email }</td>
								<td class="t25">${pojo.mobile }</td>
								<td class="t26">${pojo.address }</td>
								<td class="t26">
									<a href="javascript:;" onclick="toUpdateUser(${pojo.id });">编辑</a>
									<a href="javascript:;" onclick="toUserVehicles(${pojo.id },'${pojo.fleetDomain.name }');">查看车辆</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<%@include file="../common/page.jsp" %>
				</form>
			</div>
		</div>
	<%@include file="../common/footer.jsp" %>
	</div>
<%@include file="../common/bottom.jsp" %>
<script type="text/javascript" src="${jsPath}/accounts.js"></script>
</body>
</html>