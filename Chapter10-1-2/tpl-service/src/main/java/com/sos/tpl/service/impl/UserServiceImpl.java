package com.sos.tpl.service.impl;
//package com.sos.fleet.service.impl;
//
//import java.util.List;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import com.sos.fleet.common.constants.StatusKeys;
//import com.sos.fleet.common.settings.SecuritySettings;
//import com.sos.fleet.common.util.JsonUtil;
//import com.sos.fleet.common.web.mvc.domain.FleetDomain;
//import com.sos.fleet.common.web.mvc.domain.FleetDomain_;
//import com.sos.fleet.common.web.mvc.domain.UserDomain;
//import com.sos.fleet.common.web.mvc.domain.UserDomain_;
//import com.sos.fleet.common.web.mvc.domain.ldap.UserLdapDomain;
//import com.sos.fleet.common.web.mvc.service.SubNotificationService;
//import com.sos.fleet.common.web.security.UserDetailsMapper;
//import com.sos.fleet.data.dao.FLeetDao;
//import com.sos.fleet.data.dao.UserDao;
//import com.sos.fleet.data.ldap.UserLdapDao;
//import com.sos.fleet.data.util.LdapUtil;
//import com.sos.fleet.service.UserService;
//
//@Service
//@Transactional(readOnly=true,rollbackFor = RuntimeException.class)
//public class UserServiceImpl extends BaseServiceImpl<UserDomain, Long> implements
//		UserService,UserDetailsMapper<UserDomain>,SubNotificationService {
//	
//	protected UserDao userDao;
//	
//	@Autowired
//	private FLeetDao fleetDao;
//	
//	@Autowired
//	private UserLdapDao userLdapDao;
//	
//	@Autowired
//	public void setSampleDao(UserDao userDao){
//		this.jpaRepository = this.userDao = userDao;
//	}
//	
//	@Override
//	public UserDomain mapping(String username) {
//		List<UserDomain> list =  userDao.findByProperty("userName", username);
//		if(CollectionUtils.isEmpty(list)){
//			return null;
//		}
//		UserDomain userDomain = list.get(0);
//		if(userDomain.getFleetDomain()!=null){
//			 userDomain.getFleetDomain().getName();
//		}
//		return userDomain;
//	}
//	@Override
//	public void save(UserDomain entity) {
//		entity.setUserName(StringUtils.lowerCase(entity.getUserName()));
//		super.save(entity);
//	}
//	@Override
//	public boolean hasUser(final String userName) {
//		return !CollectionUtils.isEmpty(userDao.findAll(new IgnoreUserNameCase(userName), null));
//	}
//
//	public static class IgnoreUserNameCase implements  Specification<UserDomain>{
//		private String userName;
//		public IgnoreUserNameCase(String userName) {
//			super();
//			this.userName = userName;
//		}
//		@Override
//		public Predicate toPredicate(Root<UserDomain> root,
//				CriteriaQuery<?> query, CriteriaBuilder cb) {
//			return cb.equal(cb.upper(root.get(UserDomain_.userName)),StringUtils.upperCase(userName));
//		}
//		
//	}
//	public static class IgnoreFleetNameCase implements  Specification<FleetDomain>{
//		private String fleetName;
//		public IgnoreFleetNameCase(String fleetName) {
//			super();
//			this.fleetName = fleetName;
//		}
//		@Override
//		public Predicate toPredicate(Root<FleetDomain> root,
//				CriteriaQuery<?> query, CriteriaBuilder cb) {
//			return cb.equal(root.get(FleetDomain_.name), fleetName);
//		}
//		
//	}
//	@Override
//	@Transactional
//	public void saveFetch(UserDomain user) {
//		// TODO Auto-generated method stub
//		List<FleetDomain> fleets = this.fleetDao.findAll(new IgnoreFleetNameCase(user.getFleetDomain().getName()), null);
//		
//		FleetDomain fleet;
//		
//		if(!CollectionUtils.isEmpty(fleets)) {
//			fleet = fleets.get(0);
//		}else {
//			fleet = new FleetDomain();
//			fleet.setName(user.getFleetDomain().getName());
//			this.fleetDao.save(fleet);
//		}
//		//创建ldap用户
//		UserLdapDomain ldapuser = LdapUtil.biuldLdapUser(user);
//		List<UserLdapDomain> domains =  userLdapDao.findByProperty("uid", user.getUserName());
//		if(CollectionUtils.isEmpty(domains)) {
//			userLdapDao.save(ldapuser);
//		}else {
//			BeanUtils.copyProperties(ldapuser, domains.get(0));
//			userLdapDao.update(domains.get(0));
//		}
//		System.out.println("ldap user: "+JsonUtil.getJson(ldapuser));
//		
//		user.setUserName(user.getUserName().toLowerCase());
//		user.setFleetDomain(null);
//		user.setFleetId(fleet.getId());
//		this.userDao.save(user);
//	}
//
//	@Override
//	@Transactional
//	public void updateFetch(UserDomain userdomain) throws Exception {
//		// TODO Auto-generated method stub
//		UserDomain user = this.get(userdomain.getId());
//		user.setOrgId(userdomain.getOrgId());
//		user.setEmail(userdomain.getEmail());
//		user.setMobile(userdomain.getMobile());
//		user.setAddress(userdomain.getAddress());
//		
////		FleetDomain fleet = this.fleetDao.getOne(user.getFleetId());
////		fleet.setName(user.getFleetDomain().getName());
////		this.fleetDao.save(fleet);
//		
//		//修改ldap信息
//		
//		List<UserLdapDomain> domains =  userLdapDao.findByProperty("uid", user.getUserName());
//		if(CollectionUtils.isEmpty(domains)){
//			throw new Exception("ldap user is not exists");
//		}
//		
//		UserLdapDomain ldapuser = domains.get(0);
//		
//		ldapuser.setCompanyName(user.getFleetDomain().getName());
//		ldapuser.setMail(user.getEmail());
//		ldapuser.setMobile(user.getMobile());
//		ldapuser.setSodwType(SecuritySettings.SODW_TYPE_USER);
//		ldapuser.setIsEnabled(StatusKeys.LDAP_ENABLED);
//		userLdapDao.update(ldapuser);
//		
//		FleetDomain fleet = user.getFleetDomain();
//		fleet.setName(userdomain.getFleetDomain().getName());
//		this.fleetDao.save(fleet);
//		
//		user.setFleetDomain(null);
//		this.userDao.save(user);
//		
//	}
//
//	@Override
//	public String getMobileByUserName(String username) {
//		// TODO Auto-generated method stub
//		List<UserDomain> users = userDao.findByProperty(UserDomain_.userName.getName(), username.toLowerCase());
//		
//		if(CollectionUtils.isEmpty(users)) {
//			return null;
//		}
//		
//		return users.get(0).getMobile();
//	}
//	
//}
