package com.didispace.module.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.didispace.module.sys.domain.UserDomain;

//@Repository
//public interface UserRepository extends BaseRepository<UserDomain, Long> {
@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long> {

	UserDomain findByUserName(String userName);

	UserDomain findByUserNameAndEmail(String userName, String email);

    @Query("from UserDomain u where u.userName=:userName")
    UserDomain findUser(@Param("userName") String userName);
    

}
