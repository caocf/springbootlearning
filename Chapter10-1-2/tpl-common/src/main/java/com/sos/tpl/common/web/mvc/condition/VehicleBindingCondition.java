package com.sos.tpl.common.web.mvc.condition;

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

import org.springframework.util.StringUtils;

import com.sos.tpl.common.settings.EscapeSettings;
import com.sos.tpl.common.web.mvc.domain.VehicleBindingDomain;
import com.sos.tpl.common.web.mvc.domain.VehicleBindingDomain_;
import com.sos.tpl.common.web.mvc.domain.SearchPageImpl.SimpleOrder;

public class VehicleBindingCondition extends SortCondition<VehicleBindingDomain>{
	
	private String key;
	
	private Long userId;
	
	private static Set<String> pinyinOrder = new HashSet<String>(0);
	static {
		pinyinOrder.add("fleetDomain.name");
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public Predicate parsePredicate(Root<VehicleBindingDomain> root,
			CriteriaQuery<?> query, CriteriaBuilder builder) {
		// TODO Auto-generated method stub
		ArrayList<Predicate> predicates = new ArrayList<Predicate> (0);
		if(StringUtils.hasText(key)){
			predicates.add( builder.or(
							builder.like(builder.upper(root.get(VehicleBindingDomain_.vin)), EscapeSettings.likeForAnyWhere(key.toUpperCase()), EscapeSettings.getEscapeChar()), 
							builder.like(builder.upper(root.get(VehicleBindingDomain_.plateId)), EscapeSettings.likeForAnyWhere(key.toUpperCase()), EscapeSettings.getEscapeChar())
							));
		}
		
		if(userId != null) {
			predicates.add(builder.equal(root.get(VehicleBindingDomain_.userId), userId));
		}
		
		predicates.trimToSize();
		return predicates.size()==0?null:builder.and(predicates.toArray(new Predicate[predicates.size()]));

	}
	@Override
	public List<Order> invertOrder(Root<VehicleBindingDomain> root,
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
				arrayList.add(pinyin(so, root, cb,JoinType.LEFT));
			}else{
				arrayList.add(so.getCriteriaOrder(root, cb,JoinType.LEFT));
			}
		}
		return arrayList;
	}

}
