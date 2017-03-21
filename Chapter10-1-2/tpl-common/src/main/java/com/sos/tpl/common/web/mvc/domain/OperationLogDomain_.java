package com.sos.tpl.common.web.mvc.domain;

import java.sql.Timestamp;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OperationLogDomain.class)
public abstract class OperationLogDomain_ {

	public static volatile SingularAttribute<OperationLogDomain, Long> id;
	public static volatile SingularAttribute<OperationLogDomain, String> operation;
	public static volatile SingularAttribute<OperationLogDomain, Long> operatorId;
	public static volatile SingularAttribute<OperationLogDomain, String> operationType;
	public static volatile SingularAttribute<OperationLogDomain, Integer> status;
	public static volatile SingularAttribute<OperationLogDomain, String> operatedObject;
	public static volatile SingularAttribute<OperationLogDomain, String> vin;
	public static volatile SingularAttribute<OperationLogDomain, Timestamp> operationTime;
	public static volatile SingularAttribute<OperationLogDomain, UserDomain> userDomainByOperatedUserId;
	public static volatile SingularAttribute<OperationLogDomain, UserDomain> userDomainByOperatorId;
	public static volatile SingularAttribute<OperationLogDomain, Long> operatedUserId;
	public static volatile SingularAttribute<OperationLogDomain, String> comments;

}

