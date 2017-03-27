package com.sos.fleet.common.condition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.domain.SearchPageImpl.SimpleOrder;
import com.sos.fleet.common.domain.VehicleBindingDomain;
import com.sos.fleet.common.domain.VehicleBindingDomain_;
import com.sos.fleet.common.domain.VehicleDomain;
import com.sos.fleet.common.domain.VehicleDomain_;
import com.sos.fleet.common.settings.EscapeSettings;

public class VehicleCondition extends SortCondition<VehicleDomain> {
	private String keyword;
	private Long userId;
	private Integer status;
	private String vin;
	private static Set<String> pinyinOrder = new HashSet<String>(0);
	static {
		pinyinOrder.add("plateId");
		pinyinOrder.add("driver");
		pinyinOrder.add("model");
	}

	@Override
	public Predicate parsePredicate(Root<VehicleDomain> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		ArrayList<Predicate> predicates = new ArrayList<Predicate>(0);
		if (StringUtils.hasText(keyword)) {
			predicates.add(cb.or(cb.like(
					cb.upper(root.get(VehicleDomain_.vin)),
					EscapeSettings.likeForAnyWhere(keyword.toUpperCase()),
					EscapeSettings.getEscapeChar()), cb.like(
					cb.upper(root.get(VehicleDomain_.plateId)),
					EscapeSettings.likeForAnyWhere(keyword.toUpperCase()),
					EscapeSettings.getEscapeChar())));
		}
		Join<VehicleDomain, VehicleBindingDomain> setJoin = null;
		if (status != null&&userId!=null) {
			// If use inner join , when order by vehicleBindingDomainsForVin, jpa factory will auto add a left outter join to complete order.
			setJoin = root.join(VehicleDomain_.vehicleBindingDomainsForVin,JoinType.LEFT);
		}
		if (status != null) {
			predicates.add(cb.equal(setJoin.get(VehicleBindingDomain_.status),
					status));
		}
		if(userId!=null){
			predicates.add(cb.equal(setJoin.get(VehicleBindingDomain_.userId),
					userId));
		}
		if (StringUtils.hasText(vin)) {
			predicates.add(cb.equal(root.get(VehicleDomain_.vin),
					vin));
		}
		predicates.add(cb.equal(root.get(VehicleDomain_.deleteFlag),
				StatusKeys.PERSISTENT));
		predicates.trimToSize();
		return predicates.size() == 0 ? null : cb.and(predicates
				.toArray(new Predicate[predicates.size()]));
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public List<Order> invertOrder(Root<VehicleDomain> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
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

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
