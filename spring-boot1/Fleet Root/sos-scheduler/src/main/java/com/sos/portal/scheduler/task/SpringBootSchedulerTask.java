package com.sos.portal.scheduler.task;

import java.util.List;

import com.sos.portal.scheduler.util.SchedulerUtil;
import com.sos.portal.scheduler.work.TaskWork;
import com.sos.portal.scheduler.work.impl.SchedulerTaskWork;

public abstract class SpringBootSchedulerTask<T> implements SchedulerTask<T> {
	private TaskWork taskWork;

	@SuppressWarnings("unchecked")
	@Override
	public TaskWork registerTaskWork() {
		taskWork = new SchedulerTaskWork();
		((SchedulerTaskWork) taskWork).setSchedulerTask((SchedulerTask<Object>) this);
		return taskWork;
	}

	public TaskWork getTaskWork() {
		return taskWork;
	}
	@Override
	public String getItemRangeJson(List<T> list) {
		return SchedulerUtil.getItemRangeJson(list);
	}
}
