package com.sos.tpl.common.web.mvc.condition;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.hibernate.jpa.criteria.OrderImpl;
import org.hibernate.jpa.criteria.expression.LiteralExpression;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import com.sos.tpl.common.util.JpaUtil;
import com.sos.tpl.common.web.mvc.domain.SearchPageImpl.SimpleOrder;

public abstract class SortCondition<T> implements Specification<T>,Serializable {

	@Override
	public final Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Predicate rs =	parsePredicate(root,query,cb);
		if(!CollectionUtils.isEmpty(sorders)){
			query.orderBy(invertOrder(root,query,cb));
		}
		return rs;
	}
	public abstract Predicate parsePredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder cb);
	
	public List<SimpleOrder> getSorders() {
		return sorders;
	}


	public void setSorders(List<SimpleOrder> sorders) {
		this.sorders = sorders;
	}


	protected List<SimpleOrder> sorders;
	
	
	public abstract List<Order> invertOrder(Root<T> root,
			CriteriaQuery<?> query, CriteriaBuilder cb);
	
	protected Order pinyin(SimpleOrder so,Root<T> root,CriteriaBuilder cb,JoinType join) {
		return new OrderImpl(cb.function("NLSSORT",
				String.class, JpaUtil.toExpressionRecursively(root, so.getProperty(), join),
				new LiteralExpression<String>(
						(CriteriaBuilderImpl) cb,
						"NLS_SORT=SCHINESE_PINYIN_M")), so
				.getDirection().equals(Direction.ASC));
	}
}
