package com.didispace.module.sys.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didispace.common.exception.DaoException;
import com.didispace.common.exception.ServiceException;
import com.didispace.common.exception.SystemException;
import com.didispace.common.mvc.dao.orm.Page;
import com.didispace.common.mvc.dao.orm.hibernate.HibernateDao;
import com.didispace.module.sys.domain.RoleDomain;
import com.didispace.module.sys.service.RoleService;

@Service
public class RoleServiceImpl   implements RoleService 
{
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private RoleRepository roleRepository;
	
		
	@PersistenceContext
	private EntityManager em;
	
	private HibernateDao<RoleDomain, Long> roleEntityDao = new HibernateDao<RoleDomain, Long>(RoleDomain.class,this.em); ;
	
//	@Autowired
//	public void setEntityManager(final EntityManager entityManager) {
//		roleEntityDao = new HibernateDao<RoleDomain, Long>(RoleDomain.class,em);
//		this.entityManager = entityManager;
//	}
//	public HibernateDao(Class<T> domainClass, EntityManager em) {
//		super(domainClass, em);
//	}
	
//	
//	public List<UserDomain> getUserbyName(String userName){
//		List<UserDomain> users = new ArrayList<UserDomain>();
//		String sqlStr="select * from UserDomain where userName='"+userName+"'";
//		users = em.createNativeQuery(sqlStr,UserDomain.class).getResultList();
//		if(users!=null && !users.isEmpty()){
//			for(UserDomain user:users){
//				System.out.println("username:"+user.getUserName()+";useremail:"+user.getEmail());
//			}
//		}
//		return users;
//	}
	
//	public List<UserDomain> getUsers(){
//		List<UserDomain> users = userRepository.findAll();
//		return users;
//	}

	@Override
	public Page findPage(Page p, List filters)
			throws DaoException, SystemException, ServiceException {
		return roleEntityDao.findPage(p, filters);
	}


	



}
