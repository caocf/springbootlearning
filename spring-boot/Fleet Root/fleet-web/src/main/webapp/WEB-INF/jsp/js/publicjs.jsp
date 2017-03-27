<%@page import="com.sos.fleet.common.util.UrlPathUtil"%>
<%@page import="com.cus.common.settings.CommonJsSettings"%>
<%@page import="com.cus.common.settings.EnvironmentSettings"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/javascript; charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ include file="../common/init.jsp"%>
(function(w,$){
	function _FSP(){
		this.contextPath = "${contextPath}",
		this.baseUrl= location.protocol+"//"+location.host+this.contextPath,
		this.loginUrl=this.baseUrl+"/login",
		this.logoutUrl=this.baseUrl+"${logoutUrl}",
		this.logout  = function(){
			location=this.logoutUrl;
		},
		this.currentUserName="${currentUserName}",
		this.isSignIn=function(){
			return <%=SecurityUtil.getUser()!=null %>;
		};
	}
	w.FSP=new _FSP();
	console?console.log("Signed: "+w.FSP.isSignIn()):!0;
})(window);
