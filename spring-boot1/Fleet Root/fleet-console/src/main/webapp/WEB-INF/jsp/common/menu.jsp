<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col1">
	<a href="${contextPath }/fu/showUsers" class="ico ${fn:startsWith(fullServletName,'user.') ?'current':''}">账户管理</a>
	<a href="${contextPath }/vehicle/showVehicles" class="ico ${fn:startsWith(fullServletName,'vehicle.') ?'current':''}">查询所有车辆</a>
</div>