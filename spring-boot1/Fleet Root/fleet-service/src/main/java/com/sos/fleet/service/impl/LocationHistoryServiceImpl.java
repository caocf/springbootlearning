package com.sos.fleet.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sos.fleet.common.condition.LocationHistoryCondition;
import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.domain.LocationHistory;
import com.sos.fleet.common.domain.LocationHistory_;
import com.sos.fleet.common.domain.SearchPageImpl;
import com.sos.fleet.common.domain.VehicleBindingDomain;
import com.sos.fleet.common.domain.VehicleBindingDomain_;
import com.sos.fleet.common.domain.VehicleDomain;
import com.sos.fleet.common.domain.SearchPageImpl.SimpleOrder;
import com.sos.fleet.common.util.SecurityUtil;
import com.sos.fleet.data.dao.LocationHistoryDao;
import com.sos.fleet.data.dao.VehicleBindingDao;
import com.sos.fleet.service.LocationHistoryService;

@Service
@Transactional(readOnly=true,rollbackFor = RuntimeException.class)
public class LocationHistoryServiceImpl extends BaseServiceImpl<LocationHistory, Long>
		implements LocationHistoryService {
	@Autowired
	private VehicleBindingDao vehicleBindingDao;
	@Autowired
	private LocationHistoryDao locationHistoryDao;
	public SearchPageImpl<LocationHistory> findAllByVinAndCurrentUserId(final SearchPageImpl<LocationHistory> searchPage){
		final Long userId = SecurityUtil.getUserId();
		final String vin =  ((LocationHistoryCondition)searchPage.getCondition()).getVin();
		if(userId==null){
			throw new AccessDeniedException("Current userId of login user is null");
		}
		List<VehicleBindingDomain> list = vehicleBindingDao.findAll(new Specification<VehicleBindingDomain>() {
			@Override
			public Predicate toPredicate(Root<VehicleBindingDomain> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				 return cb.and(cb.equal(root.get(VehicleBindingDomain_.vin),vin),
						cb.equal(root.get(VehicleBindingDomain_.userId), userId),
						cb.equal(root.get(VehicleBindingDomain_.status), StatusKeys.SUCCESS));
			}
		}, null);
		if(CollectionUtils.isEmpty(list)){
			throw new AccessDeniedException("The vin["+vin+"] is invalid.");
		}
		return locationHistoryDao.findPage(searchPage);
	}
	@Override
	public LocationHistory getLatestLocationHistoryByVin(final String vin) {
		final Long userId = SecurityUtil.getUserId();
		if(userId==null){
			throw new AccessDeniedException("Current userId of login user is null");
		}
		List<VehicleBindingDomain> list = vehicleBindingDao.findAll(new Specification<VehicleBindingDomain>() {
			@Override
			public Predicate toPredicate(Root<VehicleBindingDomain> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				 return cb.and(cb.equal(root.get(VehicleBindingDomain_.vin),vin),
						cb.equal(root.get(VehicleBindingDomain_.userId), userId),
						cb.equal(root.get(VehicleBindingDomain_.status), StatusKeys.SUCCESS));
			}
		}, null);
		if(CollectionUtils.isEmpty(list)){
			throw new AccessDeniedException("The vin["+vin+"] is invalid.");
		}
		SearchPageImpl<LocationHistory> pager = new SearchPageImpl<LocationHistory>();
		pager.setPage(0);
		pager.setSize(1);
		
		ArrayList<SimpleOrder> orderList = new ArrayList<SearchPageImpl.SimpleOrder>(1);
		SimpleOrder so = new SimpleOrder(Direction.DESC, "locatedTime");
		orderList.add(so);
		pager.setOrders(orderList);
		pager.setCondition(new Specification<LocationHistory>() {
			
			@Override
			public Predicate toPredicate(Root<LocationHistory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get(LocationHistory_.vin), vin));
			}
		});
		locationHistoryDao.findPage(pager);
		if(pager.getPageResult()!=null&&pager.getPageResult().getTotalElements()>0){
			return pager.getPageResult().getContent().get(0);
		}else{
			return null;
		}
	}

}
