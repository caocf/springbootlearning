package com.sos.fleet.common.util;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.domain.OperationLogDomain;

@Component
public class OperationUtil {
	static Log log = LogFactory.getLog(OperationUtil.class);
	public static interface OperationService{
		public void log(OperationLogDomain operationLogDomain);
	}
	
	private static OperationUtil OPERATION_LOG_UTIL;
	@PostConstruct
	public void init(){
		OPERATION_LOG_UTIL = this;
	}
	@Autowired
	private OperationService operationService;
	
	public static void log(final OperationLogDomain operationLogDomain){
		try{
			OPERATION_LOG_UTIL.operationService.log(operationLogDomain);
		}catch(Exception e){
			if(log.isDebugEnabled()){
				log.debug(e,e);
			}else{
				log.error(e.getMessage());
			}
			
		}
	}
	
	public static void log( Long operatedUserId,
			String operation, String operatedObject, int status,
			String vin, String operationType,
			String comments){
		log(new OperationLogDomain(null, null, operatedUserId, operation, operatedObject, status, null, vin, operationType, comments, null, null));
	}
	
	public static void log( Long operatedUserId,
			String operation, String operatedObject, int status,
			String operationType,
			String comments){
		log(operatedUserId,operation,operatedObject,status,null,operationType,comments);
	}
	public static void log( Long operatedUserId,
			String operation, String operatedObject,
			String operationType,
			String comments){
		log(operatedUserId,operation,operatedObject,StatusKeys.SUCCESS,null,operationType,comments);
	}
	
	
	public static void log(String operation,String operationType,String vin,int status){
		log(null,operation,null,status,vin,operationType,null);
	}
	
	public static void log(String operation,String operationType,String vin){
		log(operation,operationType,vin,StatusKeys.SUCCESS);
	}
	
	
	public static void log(String operation,String operationType,int status){
		log(operation,operationType,null,status);
	}
	public static void log(String operation,String operationType){
		log(operation,operationType,StatusKeys.SUCCESS);
	}
	
}
