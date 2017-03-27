package com.sos.fleet.data.dao;

import com.sos.fleet.common.domain.VehicleBindingDomain;
import com.sos.fleet.data.BaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VehicleBindingDao extends BaseRepository<VehicleBindingDomain, Long>{

}
