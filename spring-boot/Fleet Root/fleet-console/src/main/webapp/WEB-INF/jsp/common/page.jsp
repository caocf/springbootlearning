<%@page import="com.sos.fleet.common.settings.PageSettings"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib 
 prefix="c" uri="http://java.sun.com/jstl/core_rt" %><%@ taglib 
 prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ taglib 
 prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${page.pageResult.totalElements gt 0}">
<c:set var="pageId" value="${empty pageId?'pages':pageId}"></c:set>
<div class="pages" id="${pageId}">
	<c:set var="visiblePages" value="${page.visiblePages}"></c:set>
	<c:set var="currentPage" value="${page.pageResult.number+1}"></c:set>
	<c:set var="totalPages" value="${page.pageResult.totalPages}"></c:set>
	<c:set var="beginPage" value="${totalPages gt visiblePages?currentPage-visiblePages:1}"/>
	<c:set var="beginPage" value="${beginPage lt 1?1:beginPage}"/>
	<c:set var="endPage" value="${totalPages gt visiblePages?currentPage+visiblePages:totalPages}"/>
	<c:set var="endPage" value="${endPage gt totalPages?totalPages:endPage}"/>
	<c:set var="morePage" value="${currentPage + page.more lt totalPages? currentPage + page.more:totalPages}"/>
	<script type="application/json" class="scriptJson" id="script_${pageId}">
		{"visible":${visiblePages},"number":${currentPage-1},"totalPages":${totalPages},"beginPage":${beginPage},"endPage":${endPage},"more":${morePage-1},"totalElements":${page.pageResult.totalElements}}
	</script>
	<span class="total">共有${page.pageResult.totalElements}条</span>
	<span class="right">
		<c:if test="${currentPage le 1}">
			<span class="first">首页</span>
			<span class="previous">上一页</span>
		</c:if>
		<c:if test="${currentPage gt 1}">
			<a href="javascript:;" class="cur first">首页</a>
			<a href="javascript:;" class="cur previous">上一页</a>
		</c:if>
		<c:forEach var="curPage" begin="${beginPage}" end="${endPage }" step="1">
			<c:if test="${curPage eq currentPage }">
				<a class="current nums">${curPage}</a>
			</c:if>
			<c:if test="${curPage ne currentPage }">
				<a href="javascript:;" class="nums" pageNum="${curPage-1}">${curPage}</a>
			</c:if>
		</c:forEach>
		<c:if test="${endPage lt  totalPages}">
			<a href="javascript:;" class="more cur" >...</a>
			<a href="javascript:;" class="endNum cur" >${totalPages}</a>
		</c:if>
		<c:if test="${currentPage lt totalPages}">
			<a href="javascript:;" class="cur next">下一页</a>
			<a href="javascript:;" class="cur end">尾页</a>
		</c:if>
		<c:if test="${currentPage ge totalPages}">
			<span class="next">下一页</span>
			<span class="end">尾页</span>
		</c:if>
		<span class="goto">跳转：<input class="boxes input" value="${currentPage}"/>页</span>
	</span>
</div>
</c:if>