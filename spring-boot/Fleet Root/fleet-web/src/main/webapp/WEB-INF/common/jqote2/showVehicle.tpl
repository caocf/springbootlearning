<table cellpadding="0" cellspacing="0" class="table1">
	<thead class="ico">
		<tr>
			<th width="49"><span class="ico blank">编号</span></th>
			<th width="91" order="vin"><span class="ico blank">车架号</span></th>
			<th width="102" order="plateId"><span class="ico blank">车牌</span></th>
			<th width="110">历史停驻点</th>
		</tr>
	</thead>
	<tbody>
		<%if(this.pageResult&&this.pageResult.content&&this.pageResult.content.length>0){
			for(var i=0;i<this.pageResult.content.length;i++){
			var pojo = this.pageResult.content[i];
			%>
			<tr>
				<td width="49"><%=i+this.offset+1%></td>
				<td width="91">
					<a vin="<%=pojo.vin%>" class="vin alen" href="javascript:;"><%=pojo.vin||""%></a>
				</td>
				<td width="102"><%=pojo.plateId||""%></td>
				<td width="110">
					<a href="javascript:;" vin="<%=pojo.vin%>" class="signOff" target="_blank">停驻点</a>
				</td>
			</tr>
		<%}}%>
	</tbody>
</table>