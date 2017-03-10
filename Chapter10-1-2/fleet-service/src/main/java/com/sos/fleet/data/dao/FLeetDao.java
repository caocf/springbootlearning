package com.sos.fleet.data.dao;

import org.springframework.stereotype.Repository;

import com.sos.fleet.common.domain.FleetDomain;
import com.sos.fleet.data.BaseRepository;
@Repository
public interface FLeetDao extends BaseRepository<FleetDomain, Long> {
}
