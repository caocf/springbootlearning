package com.didispace.module.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.didispace.module.sys.domain.RoleDomain;

@Repository
public interface RoleRepository extends JpaRepository<RoleDomain, Long>  {

    
    
    
    

}
