package com.sos.fleet.common.filter.properties;


public abstract class FilterProperties {
	
	protected boolean enable;
	protected int order;
	protected String[] includePattern;
	protected String[] excludePattern;
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String[] getIncludePattern() {
		return includePattern;
	}
	public void setIncludePattern(String[] includePattern) {
		this.includePattern = includePattern;
	}
	public String[] getExcludePattern() {
		return excludePattern;
	}
	public void setExcludePattern(String[] excludePattern) {
		this.excludePattern = excludePattern;
	}
}
