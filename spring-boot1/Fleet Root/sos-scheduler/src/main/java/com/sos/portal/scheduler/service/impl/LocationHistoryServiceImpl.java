package com.sos.portal.scheduler.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sos.portal.scheduler.annotation.DataSource;
import com.sos.portal.scheduler.data.dao.LocationHistoryDao;
import com.sos.portal.scheduler.domain.LocationHistory;
import com.sos.portal.scheduler.domain.LocationHistory_;
import com.sos.portal.scheduler.service.LocationHistoryService;

@Service
@Transactional(readOnly=true,rollbackFor = RuntimeException.class)
@DataSource("fleetDataSource")
public class LocationHistoryServiceImpl extends BaseServiceImpl<LocationHistory, Long>
		implements LocationHistoryService {
	private static final Log log = LogFactory.getLog(LocationHistoryServiceImpl.class);
	@Autowired
	private LocationHistoryDao locationHistoryDao;

	@Override
	@Transactional
	public void batchProcess(List<LocationHistory> list, Date innerDate) {
		try {
			log.debug("start this batch. list's size: "+list.size() ); 
			for (int i = 0; i < list.size(); i++) {
				final LocationHistory lh = list.get(i);
				if (lh == null
						|| !StringUtils.hasText(lh.getVin())
						){
					log.debug("the "+(i+1)+"th object of list is null or vin is null: "); 
					continue;
				}
				log.debug("Current VIN: "+lh.getVin() ); 
				final String vin =lh.getVin();
				Page<LocationHistory> page = locationHistoryDao.findPage(new Specification<LocationHistory>() {
					
					@Override
					public Predicate toPredicate(Root<LocationHistory> root,
							CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.and(cb.equal(cb.upper(root.get(LocationHistory_.vin)), org.apache.commons.lang3.StringUtils.upperCase(vin)),
								cb.equal(root.get(LocationHistory_.locatedTime), lh.getLocatedTime()));
						
					}
				}, new PageRequest(0, 1, Direction.DESC, "locatedTime"));
				List<LocationHistory> listLgh = page==null?null:page.getContent();
				LocationHistory latest =CollectionUtils.isEmpty(listLgh)?null:listLgh.get(0);
				log.debug("latest: "  + (latest==null?null:(latest.getLocatedTime()+"-"+latest.getVin())));
				if(latest!=null){
					log.debug(" VIN: "+lh.getVin() +", date compare[source,latest]: "+lh.getLocatedTime().getTime()+","+latest.getLocatedTime().getTime()); 
					continue;
				}
				lh.setCreateDate(innerDate);
				locationHistoryDao.save(lh);
			}
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e.getMessage());
		}
	}

}
