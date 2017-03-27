package com.sos.fleet.data.dao;

import org.springframework.stereotype.Repository;

import com.sos.fleet.common.domain.UserDomain;
import com.sos.fleet.data.BaseRepository;
@Repository
public interface UserDao extends BaseRepository<UserDomain, Long> {
}
