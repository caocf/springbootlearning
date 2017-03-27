package com.sos.fleet.data.dao;

import org.springframework.stereotype.Repository;

import com.sos.fleet.common.domain.LoginLogDomain;
import com.sos.fleet.data.BaseRepository;
@Repository
public interface LoginLogDao extends BaseRepository<LoginLogDomain, Long> {
}
