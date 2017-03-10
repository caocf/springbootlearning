package com.sos.fleet.data.dao;

import org.springframework.stereotype.Repository;

import com.sos.fleet.common.domain.LocationHistory;
import com.sos.fleet.data.BaseRepository;
@Repository
public interface LocationHistoryDao extends BaseRepository<LocationHistory, Long>{

}
