package com.sos.tpl.common.web.mvc.condition;

import com.sos.tpl.common.constants.StatusKeys;
import com.sos.tpl.common.settings.EscapeSettings;
import com.sos.tpl.common.web.mvc.domain.FleetDomain_;
import com.sos.tpl.common.web.mvc.domain.UserDomain;
import com.sos.tpl.common.web.mvc.domain.UserDomain_;
import com.sos.tpl.common.web.mvc.domain.SearchPageImpl.SimpleOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.util.StringUtils;

public class UserCondition extends SortCondition<UserDomain>{
	
	private String key;
	
	private static Set<String> pinyinOrder = new HashSet<String>(0);
	static {
		pinyinOrder.add("fleetDomain.name");
		pinyinOrder.add("orgId");
		pinyinOrder.add("address");
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public Predicate parsePredicate(Root<UserDomain> root,
			CriteriaQuery<?> query, CriteriaBuilder builder) {
		// TODO Auto-generated method stub
		
		ArrayList<Predicate> predicates = new ArrayList<Predicate> (0);
		predicates.add(builder.and(builder.notEqual(root.get(UserDomain_.deleteFlag), StatusKeys.DELETED)));
//		query.from(UserDomain.class);
		if(StringUtils.hasText(key)){
			predicates.add(builder.or(
					builder.like(builder.upper(root.get(UserDomain_.userName)), EscapeSettings.likeForAnyWhere(key.toUpperCase()), EscapeSettings.getEscapeChar()),
					builder.like(builder.upper(root.get(UserDomain_.orgId)), EscapeSettings.likeForAnyWhere(key.toUpperCase()), EscapeSettings.getEscapeChar()),
					builder.like(builder.upper(root.get(UserDomain_.fleetDomain).get(FleetDomain_.name)), EscapeSettings.likeForAnyWhere(key.toUpperCase()), EscapeSettings.getEscapeChar())
					));
		}
		predicates.trimToSize();
		return predicates.size()==0?null:builder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	@Override
	public List<Order> invertOrder(Root<UserDomain> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		List<Order> arrayList = new ArrayList<Order>(sorders.size());
		for (int i = 0; i < sorders.size(); i++) {
			SimpleOrder so = sorders.get(i);
			String property = so.getProperty();
			if (pinyinOrder.contains(property)) {
				/*Order order = new OrderImpl(cb.function("NLSSORT",
						String.class, root.get(property),
						new LiteralExpression<String>(
								(CriteriaBuilderImpl) cb,
								"NLS_SORT=SCHINESE_PINYIN_M")), so
						.getDirection().equals(Direction.ASC));*/
				arrayList.add(pinyin(so, root, cb, JoinType.LEFT));
			}else{
				arrayList.add(so.getCriteriaOrder(root, cb,JoinType.LEFT));
			}
		}
		return arrayList;
	}

}
