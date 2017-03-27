package com.sos.fleet.common.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.sos.fleet.common.settings.PageSettings;


public class SearchPageRequest<T> extends PageRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2702172174664152708L;
	private Specification<T> condition;
	private int visiblePages,more;
	public SearchPageRequest(int page, int size, Direction direction,
			String... properties) {
		super(page, size, direction, properties);
	}

	public SearchPageRequest(int page, int size, Sort sort,Specification<T> condition) {
		super(page, size, sort);
		this.condition = condition;
	}

	public SearchPageRequest(int page, int size,Specification<T> condition) {
		super(page, size);
		this.condition = condition;
	}

	public Specification<T> getCondition() {
		return condition;
	}

	public void setCondition(Specification<T> condition) {
		this.condition = condition;
	}

	public int getVisiblePages() {
		return visiblePages==0?visiblePages=PageSettings.instance().getVisiblePages():visiblePages;
	}

	public void setVisiblePages(int visiblePages) {
		this.visiblePages = visiblePages;
	}

	public int getMore() {
		return more==0?more=PageSettings.instance().getMore():more;
	}

	public void setMore(int more) {
		this.more = more;
	}

}
