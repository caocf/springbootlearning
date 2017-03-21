package com.sos.tpl.common.web.mvc.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sos.tpl.common.settings.PageSettings;
import com.sos.tpl.common.util.JpaUtil;
import com.sos.tpl.common.web.mvc.condition.SortCondition;

public class SearchPageImpl<T> implements Pageable,Serializable {
	public static final String TYPE_SIMPLE="simple";
	/**
	 * 
	 */
	private static final long serialVersionUID = -8814725629410908533L;
	private SearchPageRequest<T> searchPageRequest;
	private int page,size;
	private Page<T> pageResult;
	private Specification<T> condition;
	private List<SimpleOrder> orders;
	private boolean inactive;
	private String type;
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Specification<T> getCondition() {
		return condition;
	}
	public void setCondition(Specification<T> condition) {
		this.condition = condition;
	}
	public int getPageSize() {
		return getSearchPageRequest().getPageSize();
	}
	public int getPageNumber() {
		return getSearchPageRequest().getPageNumber();
	}
	public int getOffset() {
		return getSearchPageRequest().getOffset();
	}
	public boolean hasPrevious() {
		return getSearchPageRequest().hasPrevious();
	}
	public Sort getSort() {
		return getSearchPageRequest().getSort();
	}
	public Pageable previousOrFirst() {
		return getSearchPageRequest().previousOrFirst();
	}
	public Pageable next() {
		return getSearchPageRequest().next();
	}
	public PageRequest previous() {
		return getSearchPageRequest().previous();
	}
	public Pageable first() {
		return getSearchPageRequest().first();
	}
	public int getVisiblePages() {
		return getSearchPageRequest().getVisiblePages();
	}
	public void setVisiblePages(int visiblePages) {
		getSearchPageRequest().setVisiblePages(visiblePages);
	}
	public int getMore() {
		return getSearchPageRequest().getMore();
	}
	public void setMore(int more) {
		getSearchPageRequest().setMore(more);
	}
	protected SearchPageRequest<T> getSearchPageRequest() {
		return searchPageRequest==null?newPageRequest():searchPageRequest;
	}
	
	protected final SearchPageRequest<T> newPageRequest(){
		return searchPageRequest = new SearchPageRequest<T>(page, size<1?size=PageSettings.instance().getSize():size, getSortBySimpleOrder(),condition);
	}
	public Page<T> getPageResult() {
		return pageResult;
	}
	public void setPageResult(Page<T> pageResult) {
		this.pageResult = pageResult;
	}
	public List<SimpleOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<SimpleOrder> orders) {
		this.orders = orders;
	}
	protected Sort getSortBySimpleOrder(){
		if(!validateSort()){
			return null;
		}
		if(condition instanceof SortCondition&&!CollectionUtils.isEmpty(orders)){
			((SortCondition<T>)condition).setSorders(orders);
			return null;
		}
		return SimpleOrder.getSortBySimpleOrder(orders);
	}
	
	protected  boolean validateSort() {
		if(!CollectionUtils.isEmpty(orders)){
			for (int i = 0; i < orders.size(); i++) {
				if(!orders.get(i).validate()){
					return false;
				}
			}
		}
		return true;
	}
	
	public static class SimpleOrder{
		
		public static Sort getSortBySimpleOrder(List<SimpleOrder> orders){
			if(!CollectionUtils.isEmpty(orders)){
				List<Order> list = new ArrayList<Sort.Order>(orders.size());
				for (int i = 0; i < orders.size(); i++) {
					if(orders.get(i).validate()){
						list.add(orders.get(i).getOrder());
					}
				}
				if(CollectionUtils.isEmpty(list)){
					return null;
				}
				return new Sort(list);
			}
			return null;
		}
		public boolean validate(){
			return StringUtils.hasText(property);
		}
		public static Sort getSortBySimpleOrder(SimpleOrder order){
			Order ord = order.getOrder();
			if(ord==null){
				return null;
			}
			List<Order> list = new ArrayList<Sort.Order>(1);
			list.add(ord);
			return new Sort(list);
		}
		public SimpleOrder() {
			super();
		}
		public SimpleOrder(Direction direction, String property) {
			super();
			this.direction = direction;
			this.property = property;
		}
		public SimpleOrder(Direction direction, String property,
				boolean ignoreCase, NullHandling nullHandling) {
			super();
			this.direction = direction;
			this.property = property;
			this.ignoreCase = ignoreCase;
			this.nullHandling = nullHandling;
		}
		private  Direction direction;
		private  String property;
		private  boolean ignoreCase;
		private  NullHandling nullHandling;
		public Direction getDirection() {
			return direction;
		}
		public void setDirection(Direction direction) {
			this.direction = direction;
		}
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public boolean isIgnoreCase() {
			return ignoreCase;
		}
		public void setIgnoreCase(boolean ignoreCase) {
			this.ignoreCase = ignoreCase;
		}
		public NullHandling getNullHandling() {
			return nullHandling;
		}
		public void setNullHandling(NullHandling nullHandling) {
			this.nullHandling = nullHandling;
		}
		public Order getOrder() {
			if(validate()){
				return new Order(direction, property, nullHandling);
			}
			return null;
		}
		public <T> javax.persistence.criteria.Order getCriteriaOrder(Root<T> root,CriteriaBuilder cb,JoinType joinType){
			List<javax.persistence.criteria.Order> list = JpaUtil.toOrders(getSortBySimpleOrder(this), root, cb,joinType);
			return CollectionUtils.isEmpty(list)?null:list.get(0);
		}
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
