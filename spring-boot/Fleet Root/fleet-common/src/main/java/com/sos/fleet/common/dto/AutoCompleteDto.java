package com.sos.fleet.common.dto;

import java.io.Serializable;

public class AutoCompleteDto implements Serializable {
	private String label;
	private Object value;
	public AutoCompleteDto(String label, Object value) {
		super();
		this.label = label;
		this.value = value;
	}
	public AutoCompleteDto() {
		super();
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
