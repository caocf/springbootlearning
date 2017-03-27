<%@page import="com.cus.common.util.UrlPathUtil"%><%@page
 import="com.cus.common.util.SecurityUtil"%><%@ page 
 language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib 
 prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="form" 
 uri="http://www.springframework.org/tags/form" %><%@ taglib 
 prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib 
 prefix="c" uri="http://java.sun.com/jstl/core_rt" %><%@ taglib 
 prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ taglib 
 prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%
String path = UrlPathUtil.getContextPath(request);
String scheme = request.getScheme();
String basePath =  request.getScheme()+"://"+request.getServerName();
if("http".equals(scheme)&&request.getServerPort()!=80||"https".equals(scheme)&&request.getServerPort()!=443){
	basePath = basePath+":"+request.getServerPort();
}
basePath = basePath+path;
%><c:set var="contextPath" value="<%=path%>"/><c:set 
var="basePath" value="<%=basePath %>"/><c:set 
var="commonPath" value="${contextPath}/common"/><c:set 
var="cssPath" value="${commonPath}/css"/><c:set 
var="jsPath" value="${commonPath}/js"/><c:set 
var="imgPath" value="${commonPath}/images"/><c:set 
var="jqote2Path" value="${commonPath}/jqote2"/><c:set 
var="currentUser" value="<%=SecurityUtil.getUser() %>"/><c:set 
var="currentUserName" value="<%=SecurityUtil.getUserName() %>"/><c:set 
var="currentUserId" value="<%=SecurityUtil.getUserId() %>"/><c:set 
var="currentFleet" value="${currentUser.fleetDomain}"/><c:set 
var="servletPathKey" value="<%=UrlPathUtil.getServletPathKey(request) %>"></c:set><c:set 
var="servletName" value="<%=UrlPathUtil.getServletName(request) %>"></c:set><c:set 
var="pageName" value="page.title.${servletPathKey}"/><c:set 
var="fullServletName" value="<%=UrlPathUtil.getFullServletName(request)%>"></c:set><c:set 
var="logoutUrl" value="${contextPath}/logout"></c:set><c:set 
var="vehiclePath" value="${contextPath}"></c:set><c:set 
var="locationPath" value="${contextPath}"></c:set><c:set 
var="userPath" value="${contextPath}"></c:set><c:set 
var="logoUrl" value="${vehiclePath}/showVehicle"></c:set>
