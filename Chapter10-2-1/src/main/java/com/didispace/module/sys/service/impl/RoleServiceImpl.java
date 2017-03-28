package com.didispace.module.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didispace.common.mvc.services.base.impl.BaseServiceImpl;
import com.didispace.module.sys.dao.RoleRepository;
import com.didispace.module.sys.domain.RoleDomain;
import com.didispace.module.sys.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDomain,Long> implements RoleService 
{
//	@Autowired
//	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
		
//	@PersistenceContext
//	private EntityManager em;
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

//	@Override
//	public Page findPage(Page p, List filters, boolean isFilterDelete)
//			throws DaoException, SystemException, ServiceException {
//		return super.findPage(p, filters, isFilterDelete);
//		
//	}


	



}
