package com.sos.portal.scheduler.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.jpa.internal.QueryImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sos.portal.scheduler.annotation.DataSource;
import com.sos.portal.scheduler.data.dao.TelemetryDao;
import com.sos.portal.scheduler.domain.LocationHistory;
import com.sos.portal.scheduler.service.TelemetryService;

@SuppressWarnings("rawtypes")
@Service
@Transactional(readOnly=true)
@DataSource("fleetDataSource")
public class TelemetryServiceImpl extends BaseServiceImpl implements TelemetryService {
	private TelemetryDao telemetryDao;
	
	@Value("${scheduler.telemetry.loadSourceSql:}")
	private String findSource;

	private Log log = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocationHistory> findInDaysRange(Date start, Date end) {
			return  ((SQLQuery) ( (QueryImpl)(this.telemetryDao.getEntityManager().createNativeQuery(findSource))).getHibernateQuery())
			.addScalar("vin",StringType.INSTANCE)
			.addScalar("latitude",StringType.INSTANCE)
			.addScalar("longitude",StringType.INSTANCE)
			.addScalar("locatedTime", TimestampType.INSTANCE)
			.addScalar("createDate",TimestampType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(LocationHistory.class))
			.setParameter("startDate", start)
			.setParameter("endDate", end).list();
	}

	@SuppressWarnings("unchecked")
	@Autowired
	public void setTelemetryDao(TelemetryDao telemetryDao) {
		this.jpaRepository = this.telemetryDao = telemetryDao;
	}

}
