<%@page import="com.sos.fleet.common.util.UrlPathUtil"%><%@page
 import="com.sos.fleet.common.util.SecurityUtil"%><%@ page 
 language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib 
 prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="form" 
 uri="http://www.springframework.org/tags/form" %><%@ taglib 
 prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib 
 prefix="c" uri="http://java.sun.com/jstl/core_rt" %><%@ taglib 
 prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ taglib 
 prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%
String path = UrlPathUtil.getContextPath(request);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><c:set var="contextPath" value="<%=UrlPathUtil.getContextPath(request) %>"/><c:set 
var="cssPath" value="${contextPath}/common/css"/><c:set 
var="jsPath" value="${contextPath}/common/js"/><c:set 
var="imgPath" value="${contextPath}/common/images"/><c:set 
var="currentUser" value="<%=SecurityUtil.getUser() %>"/><c:set 
var="currentUserName" value="<%=SecurityUtil.getUserName() %>"/><c:set 
var="currentUserId" value="<%=SecurityUtil.getUserId() %>"/><c:set 
var="currentFleet" value="${currentUser.fleetDomain}"/><c:set 
var="servletPathKey" value="<%=UrlPathUtil.getServletPathKey(request) %>"></c:set><spring:message 
var="pageName" code="page.title.${servletPathKey}"></spring:message><c:set 
var="servletName" value="<%=UrlPathUtil.getServletName(request) %>"></c:set><c:set 
var="logoutUrl" value="${contextPath}/logout"></c:set><c:set 
var="fullServletName" value="<%=UrlPathUtil.getFullServletName(request) %>"></c:set><c:set 
var="fuPath" value="${contextPath}/fu"></c:set><c:set 
var="vehiclePath" value="${contextPath}/vehicle"></c:set>