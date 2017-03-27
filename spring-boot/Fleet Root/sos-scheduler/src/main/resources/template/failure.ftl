<h2>Scheduler run failure! The error report following below: </h2>
  <table border=1>
  		<tr>
  			<th align='center'>NO.</th>
  			<th align='center'>Application</th>
  			<th align='center'>Scheduler Id</th>
  			<th align='center'>Scheduler Name</th>
			<th align='center'>Source Time Range</th>
			<th align='center'>Run Time Range</th>
			<th align='center'>Source Index Range</th>
			<th align='center'>Source Item Range JSON</th>
			<th align='center'>Failure Message</th>
		</tr>
		<#list root as failure>
			<tr>
				<td align='center'>${failure_index+1}</td>
				<td align='center'>${failure.appName!"&nbsp;"}</td>
				<td align='center'>${failure.schedulerId!"&nbsp;"}</td>
				<td align='center'>${failure.name!"&nbsp;"}</td>
				<td align='center'>
						<#if failure.sourceFirstDate??>
							${failure.sourceFirstDate?string("yyyy-MM-dd HH:mm:ss.SSS")}
						</#if>
						-
						<#if failure.sourceLastDate??>
							${failure.sourceLastDate?string("yyyy-MM-dd HH:mm:ss.SSS")}
						</#if>
				</td>
				<td align='center'>
					<#if failure.executeStartDate??>
							${failure.executeStartDate?string("yyyy-MM-dd HH:mm:ss.SSS")}
						</#if>
						-
						<#if failure.executeEndDate??>
							${failure.executeEndDate?string("yyyy-MM-dd HH:mm:ss.SSS")}
						</#if>
				</td>
				<td align='center'>${failure.sourceFirstIndex!} - ${failure.sourceLastIndex!}</td>
				<td align='center'>${failure.sourceItemsRange!"&nbsp;"} </td>
				<th align='center'>${failure.lastFailureComments!"&nbsp;"}</th>
			</tr>
		</#list>
</table>
 