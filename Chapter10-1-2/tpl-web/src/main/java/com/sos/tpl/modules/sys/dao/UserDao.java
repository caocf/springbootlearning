package com.sos.tpl.modules.sys.dao;
import org.springframework.stereotype.Repository;

import com.sos.tpl.common.web.mvc.dao.BaseRepository;
import com.sos.tpl.common.web.mvc.domain.UserDomain;

@Repository
public interface UserDao extends BaseRepository<UserDomain, Long> {
}
