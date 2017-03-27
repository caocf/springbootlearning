package com.sos.fleet.common.domain;

// default package


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_OPERATION_LOG")
@EntityListeners(AuditingEntityListener.class)
public class OperationLogDomain implements java.io.Serializable {

	// Fields

	private Long id, operatorId, operatedUserId;
	private UserDomain userDomainByOperatorId;
	private UserDomain userDomainByOperatedUserId;
	private String operation;
	private String operatedObject;
	private Integer status;
	private Date operationTime;
	private String vin;
	private String operationType;
	private String comments;

	// Constructors

	/** default constructor */
	public OperationLogDomain() {
	}

	/** minimal constructor */
	public OperationLogDomain(Long id) {
		this.id = id;
	}

	public OperationLogDomain(Long id, Long operatorId, Long operatedUserId,
			String operation, String operatedObject, Integer status,
			Date operationTime, String vin, String operationType,
			String comments, UserDomain userDomainByOperatedUserId,
			UserDomain userDomainByOperatorId) {
		super();
		this.id = id;
		this.operatorId = operatorId;
		this.operatedUserId = operatedUserId;
		this.operation = operation;
		this.operatedObject = operatedObject;
		this.status = status;
		this.operationTime = operationTime;
		this.vin = vin;
		this.operationType = operationType;
		this.comments = comments;
		this.userDomainByOperatedUserId = userDomainByOperatedUserId;
		this.userDomainByOperatorId = userDomainByOperatorId;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "S_OPERATION_LOG__ID", sequenceName = "S_OPERATION_LOG__ID",allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_OPERATION_LOG__ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATOR_ID", insertable = false, updatable = false)
	@JsonIgnore
	public UserDomain getUserDomainByOperatorId() {
		return this.userDomainByOperatorId;
	}

	public void setUserDomainByOperatorId(UserDomain userDomainByOperatorId) {
		this.userDomainByOperatorId = userDomainByOperatorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATED_USER_ID", insertable = false, updatable = false)
	@JsonIgnore
	public UserDomain getUserDomainByOperatedUserId() {
		return this.userDomainByOperatedUserId;
	}

	public void setUserDomainByOperatedUserId(
			UserDomain userDomainByOperatedUserId) {
		this.userDomainByOperatedUserId = userDomainByOperatedUserId;
	}

	@Column(name = "OPERATION")
	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name = "OPERATED_OBJECT")
	public String getOperatedObject() {
		return this.operatedObject;
	}

	public void setOperatedObject(String operatedObject) {
		this.operatedObject = operatedObject;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "OPERATION_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	public Date getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Column(name = "OPERATION_TYPE")
	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "OPERATOR_ID")
	@CreatedBy
	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "OPERATED_USER_ID")
	public Long getOperatedUserId() {
		return operatedUserId;
	}

	public void setOperatedUserId(Long operatedUserId) {
		this.operatedUserId = operatedUserId;
	}

}