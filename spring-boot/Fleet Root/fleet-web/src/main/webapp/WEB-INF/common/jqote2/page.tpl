<%if(this.totalElements > 0){%>
<div class="pages" id="<%=this.pageId%>">
	<%if(this.type!="simple"){%>
		<span class="total">共有<%=this.totalElements%>条</span>
	<%}%>
	<span class="right">
		<%if(this.currentPage <= 1){%>
			<span class="first">首页</span>
			<span class="previous">上一页</span>
		<%}else{%>
			<a href="javascript:;" class="cur first">首页</a>
			<a href="javascript:;" class="cur previous">上一页</a>
		<%}
		for(var i =this.beginPage;i<=this.endPage;i++){
			if(i == this.currentPage){%>
				<a class="current nums"><%=i%></a>
			<%}else{%>
				<a href="javascript:;" class="nums" pageNum="<%=i-1%>"><%=i%></a>
		<%}
		}
		if(this.endPage < this.totalPages){%>
			<a href="javascript:;" class="more cur" >...</a>
			<a href="javascript:;" class="endNum cur" ><%=this.totalPages%></a>
		<%}
		if(this.currentPage < this.totalPages){%>
			<a href="javascript:;" class="cur next">下一页</a>
			<a href="javascript:;" class="cur end">尾页</a>
		<%}else{%>
			<span class="next">下一页</span>
			<span class="end">尾页</span>
		<%}%>
		<%if(this.type!="simple"){%>
			<span class="goto">跳转：<input class="boxes input" value="<%=this.currentPage%>"/>页</span>
		<%}%>
	</span>
</div>
<%}else{%>
<div class="noresult" style="height:auto;line-height:normal">数据库中查不到数据信息</div>
<%}%>