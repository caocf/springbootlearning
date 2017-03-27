package com.sos.portal.scheduler.data.dao;

import org.springframework.stereotype.Repository;

import com.sos.portal.scheduler.data.BaseRepository;
import com.sos.portal.scheduler.domain.LocationHistory;
@Repository
public interface LocationHistoryDao extends BaseRepository<LocationHistory, Long>{

}
