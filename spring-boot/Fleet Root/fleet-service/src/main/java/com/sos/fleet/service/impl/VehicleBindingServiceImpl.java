package com.sos.fleet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sos.fleet.common.constants.ApplicationKeys;
import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.domain.BindingHistoryDomain;
import com.sos.fleet.common.domain.BindingHistoryDomain_;
import com.sos.fleet.common.domain.FleetDomain;
import com.sos.fleet.common.domain.FleetDomain_;
import com.sos.fleet.common.domain.UserDomain;
import com.sos.fleet.common.domain.UserDomain_;
import com.sos.fleet.common.domain.VehicleBindingDomain;
import com.sos.fleet.common.domain.VehicleBindingDomain_;
import com.sos.fleet.common.domain.VehicleDomain;
import com.sos.fleet.common.domain.VehicleDomain_;
import com.sos.fleet.common.dto.VehicleBindResultDto;
import com.sos.fleet.common.util.JsonUtil;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.data.dao.BindingHistoryDao;
import com.sos.fleet.data.dao.FLeetDao;
import com.sos.fleet.data.dao.UserDao;
import com.sos.fleet.data.dao.VehicleBindingDao;
import com.sos.fleet.data.dao.VehicleDao;
import com.sos.fleet.service.VehicleBindingService;

@Service
@Transactional(readOnly=true,rollbackFor = RuntimeException.class)
public class VehicleBindingServiceImpl extends BaseServiceImpl<VehicleBindingDomain, Long> implements VehicleBindingService{
	static Logger logger = LoggerFactory.getLogger(VehicleBindingServiceImpl.class);
	
	protected VehicleBindingDao vehicleBindingDao;
	
	@Autowired
	private FLeetDao fleetDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private BindingHistoryDao bindingHistoryDao;
	
	@Autowired
	public void setSampleDao(VehicleBindingDao vehicleBindingDao){
		this.jpaRepository = this.vehicleBindingDao = vehicleBindingDao;
	}

//	@Override
//	public VehicleBindingDomain getBindVehicle(Long id) {
//		// TODO Auto-generated method stub
//		Specification<VehicleBindingDomain> spec = new Specification<VehicleBindingDomain>() {
//			
//			ArrayList<Predicate> predicates = new ArrayList<Predicate> (0);
//			
//			@Override
//			public Predicate toPredicate(Root<VehicleBindingDomain> root,
//					CriteriaQuery<?> query, CriteriaBuilder cb) {
//				// TODO Auto-generated method stub
//				predicates.add(cb.and(cb.equal(root.get(VehicleBindingDomain_.fleetId), fleetId)));
//				predicates.add(cb.and(cb.equal(root.get(VehicleBindingDomain_.vin), vin)));
//				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//			}
//		};
//		
//		List<VehicleBindingDomain> list = this.vehicleBindingDao.findAll(spec, null);
//		
//		return CollectionUtils.isEmpty(list) ? null : list.get(0);
//	}

	@Override
	@Transactional
	public void releaseBind(Long id) {
		// TODO Auto-generated method stub
		final VehicleBindingDomain vehiclebind = this.vehicleBindingDao.getOne(id);
//		List<VehicleBindingDomain> binds = this.vehicleBindingDao.findAll(new Specification<VehicleBindingDomain>() {
//			
//			@Override
//			public Predicate toPredicate(Root<VehicleBindingDomain> root,
//					CriteriaQuery<?> query, CriteriaBuilder cb) {
//				// TODO Auto-generated method stub
//				return cb.and(cb.equal(root.get(VehicleBindingDomain_.vin), vehiclebind.getVin()), cb.notEqual(root.get(VehicleBindingDomain_.id), vehiclebind.getId()));
//			}
//			
//		}, null);
		
		if(vehiclebind.getStatus() == StatusKeys.SUCCESS) {
			this.vehicleDao.delete(vehiclebind.getVehicleDomain().getId());
		}
		this.vehicleBindingDao.delete(vehiclebind);
		List<BindingHistoryDomain> bindingHistoryDomains = this.bindingHistoryDao.findByProperty(BindingHistoryDomain_.bindingId.getName(), vehiclebind.getId());
		if(!CollectionUtils.isEmpty(bindingHistoryDomains)){
			bindingHistoryDomains.get(0).setBindingEndDate(new Date());
		}
		this.bindingHistoryDao.save(bindingHistoryDomains);
//		OperationUtil.log(vehiclebind.getUserId(), OperationKeys.RELEASE_BIND, String.valueOf(id), String.valueOf(id), OperationTypeKeys.VEHICLE_MGMT, null);
		OperationUtil.log(vehiclebind.getUserId(),  OperationKeys.RELEASE_BIND, String.valueOf(id), StatusKeys.SUCCESS, vehiclebind.getVin(), OperationTypeKeys.VEHICLE_MGMT, null);
	}

	@Override
	@Transactional
	public List<VehicleBindResultDto> bindVehicle(List<VehicleBindingDomain> vehiclebinds, Long userId) {
		// TODO Auto-generated method stub
		List<VehicleDomain> vehicles = null;
//		List<UserDomain> users = null;
		List<VehicleBindingDomain> binds = null;
		List<VehicleBindResultDto> results = new ArrayList<VehicleBindResultDto>(vehiclebinds.size());
		VehicleBindResultDto resultDto = null;
		UserDomain user = this.userDao.getOne(userId);
		String username = "";
		
		for (final VehicleBindingDomain vb : vehiclebinds) {
			username = vb.getUserDomain().getUserName();
			StringBuilder sb = new StringBuilder();
			if(StringUtils.isBlank(vb.getUserDomain().getUserName()) || StringUtils.isBlank(vb.getVin())) {
				sb.append("cv_not_found");
			}
			
			if(StringUtils.isNotBlank(vb.getVin()) && vb.getVin().length() != 17) {
				if(sb.length() > 0) {
					sb.append(",");
				}
				sb.append("vin_length_error");
			}
			
			if(StringUtils.isNotBlank(vb.getModel()) && vb.getModel().length() > 50) {
				if(sb.length() > 0) {
					sb.append(",");
				}
				sb.append("model_length_error");
			}
			
			if(StringUtils.isNotBlank(vb.getPlateId()) && vb.getPlateId().length() > 50) {
				if(sb.length() > 0) {
					sb.append(",");
				}
				sb.append("plateId_length_error");
			}
			
			if(StringUtils.isNotBlank(vb.getDriver()) && vb.getDriver().length() > 100) {
				if(sb.length() > 0) {
					sb.append(",");
				}
				sb.append("driver_length_error");
			}
			
			if(StringUtils.isNotBlank(vb.getTelephone()) && vb.getTelephone().length() > 25) {
				if(sb.length() > 0) {
					sb.append(",");
				}
				sb.append("telephone_length_error");
			}
			
			if(StringUtils.isNotBlank(vb.getUserDomain().getUserName())) {
//				users = this.userDao.findByProperty(UserDomain_.userName.getName(), vb.getUserDomain().getUserName());
//				if(CollectionUtils.isEmpty(users)) {
//					vb.setFleetId(null);
//					if(sb.length() > 0) {
//						sb.append(",");
//					}
//					sb.append("user_not_exists.").append(vb.getUserDomain().getUserName());
//				}else{
//					vb.setUserId(users.get(0).getId());
//					vb.setFleetId(users.get(0).getFleetId());
//				}
				if(sb.length() > 0) {
					sb.append(",");
				}
				if(!user.getUserName().equals(vb.getUserDomain().getUserName())) {
					sb.append("user_not_match.").append(user.getUserName());
				}
			}
			
			if(StringUtils.isNotBlank(vb.getVin())) {
				binds = this.vehicleBindingDao.findAll(new Specification<VehicleBindingDomain>() {
					
					@Override
					public Predicate toPredicate(Root<VehicleBindingDomain> root,
							CriteriaQuery<?> query, CriteriaBuilder cb) {
						// TODO Auto-generated method stub
						return cb.and(cb.equal(root.get(VehicleBindingDomain_.vin), vb.getVin()), 
								cb.equal(root.get(VehicleBindingDomain_.status), StatusKeys.SUCCESS));
					}
					
				}, null);
				logger.info(binds.size()+"--------------");
				if(!CollectionUtils.isEmpty(binds)) { 
					if(sb.length() > 0) {
						sb.append(",");
					}
					sb.append("vin_is_bind.").append(this.userDao.getOne(binds.get(0).getUserId()).getUserName());
				}
			}
			
			VehicleDomain vd = new VehicleDomain();
			BeanUtils.copyProperties(vb, vd, "id");
			boolean isUpdateVehicle =false;
			if(sb.length() == 0) {
				vehicles = this.vehicleDao.findByProperty(VehicleDomain_.vin.getName(), vb.getVin());
				if(!CollectionUtils.isEmpty(vehicles)) {
					BeanUtils.copyProperties(vb, vehicles.get(0), "id");
					vd = this.vehicleDao.save(vehicles.get(0));
					isUpdateVehicle=true;
				}else {
					vd = this.vehicleDao.save(vd);
				}
				OperationUtil.log(user.getId(), isUpdateVehicle?OperationKeys.UPDATE_VEHICLE:OperationKeys.ADD_VEHICLE, null, StatusKeys.SUCCESS, vb.getVin(), OperationTypeKeys.VEHICLE_MGMT, null);
				vb.setStatus(StatusKeys.SUCCESS);
				
				vb.setUserId(user.getId());
				vb.setUserDomain(null);
				vb.setFleetId(user.getFleetId());
				vb.setFleetDomain(null);
				vb.setVehicleDomain(null);
				this.vehicleBindingDao.save(vb);
				
				BindingHistoryDomain bh = new BindingHistoryDomain();
				BeanUtils.copyProperties(vb, bh);
				BeanUtils.copyProperties(vd, bh);
				bh.setVehicleId(vd.getId()==null?null:vd.getId());
				bh.setBindingId(vb.getId());
				bh.setVehicleBindingDomain(null);
				bh.setFleetDomain(null);
				bh.setId(null);
				bh.setBindingStartDate(new Date());
				this.bindingHistoryDao.save(bh);
			}
			
			resultDto = new VehicleBindResultDto();
			BeanUtils.copyProperties(vb, resultDto);
			resultDto.setUserName(username);
			resultDto.setStatus(sb.length() > 0 ? "Failure" : "Success");
			resultDto.setComments(sb.toString());
			results.add(resultDto);
			OperationUtil.log(user.getId(), OperationKeys.BINDING_VEHICLE, null, sb.length() > 0 ? StatusKeys.FAILURE:StatusKeys.SUCCESS, vb.getVin().length() <= 17 ? vb.getVin() : null, OperationTypeKeys.VEHICLE_MGMT, vb.getVin().length() > 17 ? vb.getVin() : null);
		}
		
		return results;
	}

	@Override
	@Transactional
	public void updateVehicleBind(VehicleBindingDomain vehiclebind) {
		// TODO Auto-generated method stub
		VehicleBindingDomain vb = this.vehicleBindingDao.getOne(vehiclebind.getId());
		
		vb.setPlateId(vehiclebind.getPlateId());
		vb.setModel(vehiclebind.getModel());
		vb.setDriver(vehiclebind.getDriver());
		vb.setTelephone(vehiclebind.getTelephone());
		if(vb.getStatus() == StatusKeys.SUCCESS) {
			vb.getVehicleDomain().setPlateId(vb.getPlateId());
			vb.getVehicleDomain().setModel(vb.getModel());
			vb.getVehicleDomain().setDriver(vb.getDriver());
			vb.getVehicleDomain().setTelephone(vb.getTelephone());
		}
		
		this.vehicleBindingDao.save(vb);
	}
}
