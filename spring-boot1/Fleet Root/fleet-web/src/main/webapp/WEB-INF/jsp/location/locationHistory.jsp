<%@page import="org.apache.commons.lang3.time.DateUtils"%>
<%@page import="com.cus.common.settings.AmapSettings"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/top.jsp" %>
</head>
<body>
<%-- <div class="header">
	<h1 class="ico onstarlogo" onclick="javascript:location.href='${logoUrl }'"></h1>
	<a href="javascript:;" onclick="return false;" class="txt">查看历史停驻点</a>
	<a href="${locationPath}/monitorLocation" class="link">车辆位置监控</a>
	<a href="${vehiclePath}/showVehicle" class="link">车辆信息查询</a>
	<a href="${userPath}/modifyAuthPage" class="link">修改密码</a>
	<a href="${logoutUrl }" class="logout">退出</a>
	<span class="user">${currentUserName}</span>
</div> --%>
<div id="mapContainer"></div>
<jsp:useBean id="dateNow" class="java.util.Date" ></jsp:useBean>
<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="map">
	<div class="content">
		<div class="step">
			<div class="range" id="range"></div>
			<div class="date">
			<c:forEach var="i" step="1" begin="1" end="7">
				<dl>
					<dt></dt>
					<%
						int i = (Integer)pageContext.getAttribute("i"); 
						Date currentDate = DateUtils.addDays(dateNow, i*-1+1);
						pageContext.setAttribute("currentDate", currentDate);
					%>
					<dd><fmt:formatDate value="${currentDate }" pattern="dd"/>号</dd>
					<c:if test="${i eq 7 }">
						<dd class="month"><fmt:formatDate value="${dateNow }" pattern="MM"/>月</dd>
					</c:if>
				</dl>
			</c:forEach>
			</div>
			<div class="timeTip"></div>
		</div>
		<div class="cont">
			<p>车牌：${vehicle.plateId }</p>
			<p>车架号：${vehicle.vin }</p>
			<table cellpadding="0" cellspacing="0" class="table2">
				<thead class="ico">
					<tr>
						<th width="118"><span class="ico blank">编号</span></th>
						<th width="196">时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.pageResult.content }" var="pojo" varStatus="status">
						<tr>
							<td width="118">${status.count+page.offset}</td>
							<td width="196" locationMetaData="{index:${status.index},lat:${ pojo.latitude},lon:${ pojo.longitude},locatedTime:${pojo.locatedTime.time}}"><fmt:formatDate value="${pojo.locatedTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="pageDiv"><%@ include file="../common/page.jsp" %></div>
			<a vin="${vin}" href="javascript:;"  class="ico btn2">查看当前位置</a>
		</div>
		<a href="javascript:;" class="ico arrow current"></a>
	</div>
</div>
<link rel="stylesheet" href="<%=AmapSettings.instance().getCssUrl()%>" />
<script id="amapScript" type="text/javascript" src="<%=AmapSettings.instance().getJavascriptUrl()%>"></script>
<%@include file="../common/bottom.jsp" %>
<script id="jqueryScript_nouislider" type="text/javascript" src="${jsPath}/jquery.nouislider.min.js"></script>
<c:if test="${not empty vin and fn:length(vin) gt 0}">
	<script type="text/javascript">
		(function(f,$,w){
			f.currentVin="${vin}";
			f.nowTime=<%=dateNow.getTime()%>;
			f.truncateTime=<%=DateUtils.truncate(dateNow, Calendar.DAY_OF_MONTH).getTime()%>;
		})(window.Fsp,jQuery,window);
	</script>
</c:if>
<script id="jqueryScript_jqote2" type="text/javascript" src="${jsPath}/jquery-jqote2.min.js"></script>
<script id="commonScript_loactionHistory" type="text/javascript" src="${jsPath}/locationHistory.js"></script>
</body>
</html>