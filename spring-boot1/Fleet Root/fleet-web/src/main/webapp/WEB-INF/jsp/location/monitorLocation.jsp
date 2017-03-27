<%@page import="com.cus.common.settings.AmapSettings"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/top.jsp" %>
</head>
<body>
<div class="header">
	<h1 class="ico onstarlogo"></h1><!--  onclick="javascript:location.href='${logoUrl }'" -->
	<a href="javascript:;" onclick="return false;" class="txt">车辆位置监控</a>
	<a href="${vehiclePath}/showVehicle" class="link">车辆信息查询</a>
	<a href="${userPath}/modifyAuthPage" class="link">修改密码</a>
	<a href="${logoutUrl }" class="logout">退出</a>
	<span class="user">${currentUserName}</span>
	<div class="env_read_only_mes link"></div>
</div>
<div id="mapContainer"></div>
<div class="map">
	<div class="content w390">
		<div class="searchbar">
			<span class="ico clear"></span>
			<form action="${vehiclePath}/showVehicleJson" method="post" name="fm">
				<input type="hidden"  name="page" value="0"/>
				<input type="hidden" name="orders[0].property" value="vehicleBindingDomainsForVin.bindingDate"/>
				<input type="hidden" name="orders[0].direction" value="DESC"/>
				<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input name="keyword" value="${vin}" class="inputbox" id="searchbox" placeholder="请输入车架号/车牌号" />
				<a href="javascript:;" class="ico btn3">搜索</a>
			</form>
		</div>
		<div class="cont">
			<div class="company">企业名称：${currentFleet.name}</div>
		</div>
		<a href="javascript:;"  class="ico arrow current"></a>
	</div>
</div>
<div class="tingzhu" id="tingzhu">
</div>
<link rel="stylesheet" href="<%=AmapSettings.instance().getCssUrl()%>" />
<script id="amapScript" type="text/javascript" src="<%=AmapSettings.instance().getJavascriptUrl()%>"></script>
<%@include file="../common/bottom.jsp" %>
<c:if test="${not empty vin and fn:length(vin) gt 0}">
	<script type="text/javascript">
		(function(f,$,w){
			f.currentVin="${vin}";
		})(window.Fsp,jQuery,window);
	</script>
</c:if>
<link rel="stylesheet" href="${cssPath}/jquery-ui.min.css" />
<script id="jqueryScript_ui" type="text/javascript" src="${jsPath}/jquery-ui.min.js"></script>
<script id="jqueryScript_jqote2" type="text/javascript" src="${jsPath}/jquery-jqote2.min.js"></script>
<script id="commonScript_monitorLocation" type="text/javascript" src="${jsPath}/monitorLocation.js"></script>
</body>
</html>