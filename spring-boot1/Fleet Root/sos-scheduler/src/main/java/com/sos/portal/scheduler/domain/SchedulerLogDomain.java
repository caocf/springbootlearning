package com.sos.portal.scheduler.domain;
// default package

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Entity
@Table(name = "T_SCHEDULER_LOG")
@EntityListeners(AuditingEntityListener.class)
public class SchedulerLogDomain implements Serializable{

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8790134019067081001L;
	private String name;
	private Date executeStartDate;
	private Date executeEndDate;
	private Integer status;
	private String appName;
	private Date sourceFromDate;
	private Date sourceToDate;
	private String sourceItemsRange;
	private Integer sourceCount;
	private Integer sourceFailure;
	private Boolean isSpecifyDate;
	private Long id;
	private Date createDate;
	private Date lastUpdateDate;

	// Constructors

	/** default constructor */
	public SchedulerLogDomain() {
	}

	/** minimal constructor */
	public SchedulerLogDomain(Long id) {
		this.id = id;
	}

	/** full constructor */
	public SchedulerLogDomain(Long id, String name,
			Date executeStartDate, Date executeEndDate, Integer status,
			String appName, Date sourceFromDate, Date sourceToDate,
			String sourceItemsRange, Integer sourceCount,
			Integer sourceFailure, Boolean isSpecifyDate, Date createDate,
			Date lastUpdateDate) {
		this.id = id;
		this.name = name;
		this.executeStartDate = executeStartDate;
		this.executeEndDate = executeEndDate;
		this.status = status;
		this.appName = appName;
		this.sourceFromDate = sourceFromDate;
		this.sourceToDate = sourceToDate;
		this.sourceItemsRange = sourceItemsRange;
		this.sourceCount = sourceCount;
		this.sourceFailure = sourceFailure;
		this.isSpecifyDate = isSpecifyDate;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_SCHEDULER_LOG__ID")
	@SequenceGenerator(name = "S_SCHEDULER_LOG__ID", sequenceName = "S_SCHEDULER_LOG__ID",allocationSize=0)
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	public Long getId() {
		return this.id;
	}


	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EXECUTE_START_DATE")
	public Date getExecuteStartDate() {
		return this.executeStartDate;
	}

	public void setExecuteStartDate(Date executeStartDate) {
		this.executeStartDate = executeStartDate;
	}

	@Column(name = "EXECUTE_END_DATE")
	public Date getExecuteEndDate() {
		return this.executeEndDate;
	}

	public void setExecuteEndDate(Date executeEndDate) {
		this.executeEndDate = executeEndDate;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "APP_NAME")
	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Column(name = "SOURCE_FROM_DATE")
	public Date getSourceFromDate() {
		return this.sourceFromDate;
	}

	public void setSourceFromDate(Date sourceFromDate) {
		this.sourceFromDate = sourceFromDate;
	}

	@Column(name = "SOURCE_TO_DATE")
	public Date getSourceToDate() {
		return this.sourceToDate;
	}

	public void setSourceToDate(Date sourceToDate) {
		this.sourceToDate = sourceToDate;
	}

	@Column(name = "SOURCE_ITEMS_RANGE")
	public String getSourceItemsRange() {
		return this.sourceItemsRange;
	}

	public void setSourceItemsRange(String sourceItemsRange) {
		this.sourceItemsRange = sourceItemsRange;
	}

	@Column(name = "SOURCE_COUNT")
	public Integer getSourceCount() {
		return this.sourceCount;
	}

	public void setSourceCount(Integer sourceCount) {
		this.sourceCount = sourceCount;
	}

	@Column(name = "SOURCE_FAILURE")
	public Integer getSourceFailure() {
		return this.sourceFailure;
	}

	public void setSourceFailure(Integer sourceFailure) {
		this.sourceFailure = sourceFailure;
	}

	@Column(name = "IS_SPECIFY_DATE")
	public Boolean getIsSpecifyDate() {
		return this.isSpecifyDate;
	}

	public void setIsSpecifyDate(Boolean isSpecifyDate) {
		this.isSpecifyDate = isSpecifyDate;
	}

	@Column(name = "CREATE_DATE")
	@CreatedDate
	public Date getCreateDate() {
		return this.createDate;
	}


	@Column(name = "LAST_UPDATE_DATE")
	@LastModifiedDate
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


}