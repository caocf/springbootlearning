<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib 
 prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="col1">
	<a href="${vehiclePath}/showVehicle" class="ico ${fn:startsWith(fullServletName,'vehicle.')?'current':''  }">车辆信息查询</a>
	<a href="${locationPath}/monitorLocation" class="ico">车辆位置监控</a>
	<a href="${userPath}/modifyAuthPage" class="ico ${fn:startsWith(fullServletName,'user.')?'current':''  }">修改密码</a>
</div>