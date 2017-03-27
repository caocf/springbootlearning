package com.sos.fleet.data.dao;

import org.springframework.stereotype.Repository;

import com.sos.fleet.common.domain.OperationLogDomain;
import com.sos.fleet.data.BaseRepository;
@Repository
public interface OperationLogDao extends BaseRepository<OperationLogDomain, Long> {

}
