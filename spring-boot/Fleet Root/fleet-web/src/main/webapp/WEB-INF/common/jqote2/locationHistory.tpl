<%if(this.pageResult&&this.pageResult.content&&this.pageResult.content.length>0){
			for(var i=0;i<this.pageResult.content.length;i++){
			var pojo = this.pageResult.content[i];
%>
	<tr>
		<td width="118"><%=i+this.offset+1%></td>
		<td width="196"><%=new Date(pojo.locatedTime).format("yyyy-MM-dd HH:mm")%></td>
	</tr>
<%}}%>