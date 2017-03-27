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
				<h2>查询所有车辆</h2>
			</div>
			<form name="fm" action="${vehiclePath }/showVehicles" method="post">
			<input type="hidden" name="page" value="${page.page }"/>
			<input type="hidden" name="orders[0].property" value="${page.orders[0].property }" />
			<input type="hidden" name="orders[0].direction" value="${page.orders[0].direction }" />
			<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="search">
				<input type="text" name="key" value="${condition.key }" class="inputbox" placeholder="请输入车架号/车牌" />
				<a href="javascript:;" class="ico btn searchbtn">搜索</a>
			</div>
			<div class="tableform">
				<table cellpadding="0" cellspacing="0">
					<thead class="ico">
						<tr>
							<th class="ico first" width="51" sort="false"><i class="ico blank">编号</i></th>
							<th width="95"><i class="ico blank">车架号</i></th>
							<th width="70"><i class="ico blank">车型</i></th>
							<th width="78"><i class="ico blank">车牌</i></th>
							<th width="75"><i class="ico blank">使用人</i></th>
							<th width="110"><i class="ico blank">联系电话</i></th>
							<th width="85"><i class="ico blank">公司名称</i></th>
							<!-- <th width="82"><i class="ico blank">绑定状态</i></th> -->
							<th class="ico last" width="130">绑定时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.pageResult.content }" var="pojo" varStatus="i">
							<tr>
								<td class="t11">${i.index+1 }</td>
								<td width="95">${pojo.vin }</td>
								<td class="t13">${pojo.model }</td>
								<td class="t14">${pojo.plateId }</td>
								<td class="t15">${pojo.driver }</td>
								<td width="110">${pojo.telephone }</td>
								<td class="t17">${pojo.fleetDomain.name }</td>
								<%-- <c:set var="comments" value=""></c:set>
								<c:forTokens items="${pojo.comments }" delims="," varStatus="status" var="msg">
									<c:set var="indexmsg" value="${fn:indexOf(msg,'.') }"></c:set>
									<spring:message var="comm" code='fu.message.binding.${fn:substring(msg, 0, indexmsg < 0 ? fn:length(msg) : indexmsg) }' arguments='${indexmsg < 0 ? "" : fn:substring(msg, indexmsg+1, fn:length(msg)) }'></spring:message>
									<c:if test="${fn:length(comments) > 0 }">
										<c:set var="comments" value="${comments }&#10;"></c:set>
									</c:if>
									<c:set var="comments" value="${comments }${status.count}. ${comm }"></c:set>
								</c:forTokens> --%>
								<%-- <td class="t18" title="${comments }">
									<font color="${pojo.status eq '1' ? 'none' : '#e51f1f' }">${pojo.status eq "1" ? "Success" : "Failure" }</font>
								</td> --%>
								<td width="130"><fmt:formatDate  pattern="yyyy-MM-dd HH:mm" value="${pojo.bindingDate }"/></td>
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
<script type="text/javascript" src="${jsPath}/vehicles.js"></script>
</body>
</html>