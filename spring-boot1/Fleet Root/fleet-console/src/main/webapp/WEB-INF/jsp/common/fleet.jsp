<%@page import="com.sos.fleet.common.util.UrlPathUtil"%>
<%@page import="com.sos.fleet.common.settings.CommonJsSettings"%>
<%@page import="com.sos.fleet.common.settings.EnvironmentSettings"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/javascript; charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@include file="../../common/js/jquery-2.1.4.min.js" %>
<%
String path = UrlPathUtil.getContextPath(request);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
(function(w,$){
	var Fsp_ =function(){
		var f = this;
		var IS_READ_ONLY_ENV = <%=EnvironmentSettings.ENABLE_DREAD_ONLY %>;
		this.contextPath = "<%=path %>";
		var Env_  =  function(name){
			var modifierCss = ".env_read_only";
			var containerCss = ".env_read_only_mes";
			var containerText = "<spring:message code="env.read.only.message"></spring:message>";
			var e_ = this;
			this.name = name;
			this.isReadOnlyEnv = function(){
				return IS_READ_ONLY_ENV;
			}
			this.activeReadOnly = function(){
				if(IS_READ_ONLY_ENV){
					$(modifierCss).remove();
					if($(containerCss).length<=0){
						_createContainer();
					}
					$(containerCss).text(containerText);
				}
			}
			var _createContainer = function(){
				$(".main").append("<div class='"+ containerCss.substring(1)+"'></div>");
			}
			var _init = function(){
				e_.activeReadOnly();
			}
			
			$(document).ready(function(){
				_init();
			});
		}
		this.Env = new Env_("<%=EnvironmentSettings.instance().getName()%>");
	}
	w.Fsp = $.Fsp = new Fsp_();
})(window,jQuery);
