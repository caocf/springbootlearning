<ul>
	<%this.lon=this.lon||515;this.lat=this.lat||515;%>
	<li><label>车架号：</label><%=this.vehicleDomain.vin||""%></li>
	<li><label>车牌：</label><%=this.vehicleDomain.plateId||""%></li>
	<li><label>使用人：</label><%=this.vehicleDomain.driver||""%></li>
	<li><label>电话号码：</label><%=this.vehicleDomain.telephone||""%></li>
	<li><label>速度：</label><%=parseInt(this.lon)==515&&parseInt(this.lat)==515?0:(this.speed||0)%> km/h</li>
	<li><label>状态：</label><%=parseInt(this.lon)==515&&parseInt(this.lat)==515?'获取当前位置失败':this.signOff==0?'熄火':'行驶'%></li>
	<li><label>定位时间：</label><%=this.locatedTimeText||""%></li>
</ul>
<a href="javascript:;" vin="<%=this.vehicleDomain.vin%>" class="ico btn2">历史停驻点</a>