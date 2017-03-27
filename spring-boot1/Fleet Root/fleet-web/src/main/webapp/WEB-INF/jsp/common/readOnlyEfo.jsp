<%@page import="com.cus.common.settings.EnvironmentSettings"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:if test="<%=EnvironmentSettings.ENABLE_DREAD_ONLY %>">
	<div class="env_read_only_mes"></div>
</c:if>