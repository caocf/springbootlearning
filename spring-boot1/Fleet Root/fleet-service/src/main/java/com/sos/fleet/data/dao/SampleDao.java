package com.sos.fleet.data.dao;

import org.springframework.stereotype.Repository;

import com.sos.fleet.common.domain.SampleAllDomain;
import com.sos.fleet.data.BaseRepository;
@Repository
public interface SampleDao extends BaseRepository<SampleAllDomain, Long> {
	
}
