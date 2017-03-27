package com.sos.fleet.common.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "pagination")
@Configuration
public class PageSettings {
	public static final String ATTRIBUTE_KEY="page";
	private static PageSettings PAGE_SETTINGS;
	private int size, visiblePages,more;
	@PostConstruct
	private void init(){
		PAGE_SETTINGS = this;
	}
	
	public static PageSettings instance(){
		return PAGE_SETTINGS;
	}
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getVisiblePages() {
		return visiblePages;
	}

	public void setVisiblePages(int visiblePages) {
		this.visiblePages = visiblePages;
	}

	public int getMore() {
		return more;
	}

	public void setMore(int more) {
		this.more = more;
	}
}
