Scheduler run success!
This task information as below:
<table border=1>
	<tr><th align=left width=250>Task Name</th><td>${root.name!"&nbsp;"}</td></tr>
	<tr><th align=left>Source Count</th><td>${root.sourceCount!"&nbsp;"}</td></tr>
	<tr><th align=left>Execution Start Time</th><td>
		<#if root.executeStartDate??>
				${root.executeStartDate?string("yyyy-MM-dd HH:mm:ss.SSS")}
				<#else>
					&nbsp;
		</#if>
	</td></tr>
	<tr><th align=left>Execution End Time</th><td>
		<#if root.executeEndDate??>
				${root.executeEndDate?string("yyyy-MM-dd HH:mm:ss.SSS")}
				<#else>
					&nbsp;
		</#if>
	</td></tr>
	<tr><th align=left>Source First Date</th><td>
	<#if root.sourceFromDate??>
				${root.sourceFromDate?string("yyyy-MM-dd HH:mm:ss.SSS")}
				<#else>
					&nbsp;
		</#if>
	</td></tr>
	<tr><th align=left>Source Last Date</th><td>
	<#if root.sourceToDate??>
				${root.sourceToDate?string("yyyy-MM-dd HH:mm:ss.SSS")}
				<#else>
					&nbsp;
		</#if>
	</td></tr>
	<tr><th align=left>Source Items Range</th><td>${root.sourceItemsRange!"&nbsp;"}</td></tr>
</table> 