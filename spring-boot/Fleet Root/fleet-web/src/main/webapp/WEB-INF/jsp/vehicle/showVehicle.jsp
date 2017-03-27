<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
				<h2>车辆信息查询</h2>
			</div>
			<form action="${vehiclePath}/showVehicle" method="post" name="fm">
				<c:forEach var="order" items="${page.orders}" varStatus="status">
					<input type="hidden"  name="orders[${status.index}].property" value="${order.property}"/>
					<input type="hidden"  name="orders[${status.index}].direction" value="${order.direction}"/>
				</c:forEach>
				<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden"  name="page" value="${empty page.page?0:page.page}"/>
				<div class="search">
					<span class="ico clear" ></span>
					<input value="${page.condition.keyword}" class="inputbox" name="keyword" placeholder="请输入VIN码/车牌号" />
					<a href="javascript:;" class="ico btn searchbtn">搜索</a>
				</div>
			</form>
			<form style="display:none" action="${locationPath}/monitorLocation" name="mlForm" method="post">
				<input name="vin" value=""/>
				<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
				<div class="tableform">
					<table cellpadding="0" cellspacing="0">
						<thead class="ico">
							<tr>
								<th class="ico first" width="60"><i class="ico blank">编号</i></th>
								<th width="124" order="vin"><i class="ico blank">车架号</i></th>
								<th width="101" order="model"><i class="ico blank">车型</i></th>
								<th width="101" order="plateId"><i class="ico blank">车牌</i></th>
								<th width="99" order="driver"><i class="ico blank">使用人</i></th>
								<th width="104" order="telephone"><i class="ico blank">联系电话</i></th>
								<th class="ico last" width="105">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.pageResult.content }" var="pojo" varStatus="status">
								<tr>
									<td class="t27">${status.count+	page.offset}</td>
									<td class="t28"><span>${pojo.vin}</span></td>
									<td class="t29">${pojo.model}</td>
									<td class="t29">${pojo.plateId}</td>
									<td class="t30">${pojo.driver}</td>
									<td class="t31">${pojo.telephone}</td>
									<td class="t32">
										<a href="javascript:;" vin="${pojo.vin}" class="underline">查看位置</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<%@ include file="../common/page.jsp" %>
		</div>
	</div>
	<%@include file="../common/footer.jsp" %>
</div>
<%@include file="../common/bottom.jsp" %>
<script type="text/javascript">
	(function(f,$,w){
		f.currentOrder = "${fn:length(page.orders) gt 0}"=='true'?{
				property:'${page.orders[0].property}',
				direction:'${page.orders[0].direction}'
		}:!1;
	})(window.Fsp,jQuery,window);
</script>
<script id="commonScript_vehicle" type="text/javascript" src="${jsPath}/showVehicle.js"></script>
</body>
</html>