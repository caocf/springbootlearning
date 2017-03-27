Scheduler is running now.
This task information as below:
<table border=1>
	<tr><th align=left width=250>Task Name</th><td>${root.taskName}</td></tr>
	<tr><th align=left>Is Manual</th><td>${root.isManual?string("yes","no")}</td></tr>
	<tr><th align=left>Method</th><td>${root.methodName}</td></tr>
	<tr><th align=left>Arguments</th><td>${root.arguments!"&nbsp;"}</td></tr>
</table> 