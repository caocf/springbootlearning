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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "T_SCHEDULER_FAILURE_LOG")
@EntityListeners(AuditingEntityListener.class)
public class SchedulerFailureDomain implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3035222838293427729L;
	private Date executeStartDate;
	private Date executeEndDate;
	private Long schedulerId;
	private String name;
	private String appName;
	private Date sourceFirstDate;
	private Date sourceLastDate;
	private String sourceItemsRange;
	private Integer sourceCount;
	private Integer rerunStatus;
	private Date lastRerunStartDate;
	private Date lastRerunEndDate;
	private Integer rerunCount;
	private String lastFailureComments;
	private Boolean activeAutoRerun;
	private Boolean isManualRerun;
	private Integer sourceFirstIndex;
	private Integer sourceLastIndex;
	private Long id;
	private Date createDate;
	private Date lastUpdateDate;

	// Constructors

	/** default constructor */
	public SchedulerFailureDomain() {
	}

	/** minimal constructor */
	public SchedulerFailureDomain(Long id) {
		this.id = id;
	}

	/** full constructor */
	public SchedulerFailureDomain(Long id, Date executeStartDate,
			Date executeEndDate, Long schedulerId, String name,
			String appName, Date sourceFirstDate, Date sourceLastDate,
			String sourceItemsRange, Integer sourceCount, Integer rerunStatus,
			Date lastRerunStartDate, Date lastRerunEndDate, Integer rerunCount,
			String lastFailureComments, Boolean activeAutoRerun,
			Boolean isManualRerun, Date createDate, Date lastUpdateDate,Integer sourceFirstIndex,Integer sourceLastIndex) {
		this.id = id;
		this.executeStartDate = executeStartDate;
		this.executeEndDate = executeEndDate;
		this.schedulerId = schedulerId;
		this.name = name;
		this.appName = appName;
		this.sourceFirstDate = sourceFirstDate;
		this.sourceLastDate = sourceLastDate;
		this.sourceItemsRange = sourceItemsRange;
		this.sourceCount = sourceCount;
		this.rerunStatus = rerunStatus;
		this.lastRerunStartDate = lastRerunStartDate;
		this.lastRerunEndDate = lastRerunEndDate;
		this.rerunCount = rerunCount;
		this.lastFailureComments = lastFailureComments;
		this.activeAutoRerun = activeAutoRerun;
		this.isManualRerun = isManualRerun;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
		this.sourceFirstIndex = sourceFirstIndex;
		this.sourceLastIndex = sourceLastIndex;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_SCHEDULER_FAIL__ID")
	@SequenceGenerator(name = "S_SCHEDULER_FAIL__ID", sequenceName = "S_SCHEDULER_FAIL__ID",allocationSize=0)
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	public Long getId() {
		return this.id;
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

	@Column(name = "SCHEDULER_ID")
	public Long getSchedulerId() {
		return this.schedulerId;
	}

	public void setSchedulerId(Long schedulerId) {
		this.schedulerId = schedulerId;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "APP_NAME")
	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Column(name = "SOURCE_FIRST_DATE")
	public Date getSourceFirstDate() {
		return this.sourceFirstDate;
	}

	public void setSourceFirstDate(Date sourceFirstDate) {
		this.sourceFirstDate = sourceFirstDate;
	}

	@Column(name = "SOURCE_LAST_DATE")
	public Date getSourceLastDate() {
		return this.sourceLastDate;
	}

	public void setSourceLastDate(Date sourceLastDate) {
		this.sourceLastDate = sourceLastDate;
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

	@Column(name = "RERUN_STATUS")
	public Integer getRerunStatus() {
		return this.rerunStatus;
	}

	public void setRerunStatus(Integer rerunStatus) {
		this.rerunStatus = rerunStatus;
	}

	@Column(name = "LAST_RERUN_START_DATE")
	public Date getLastRerunStartDate() {
		return this.lastRerunStartDate;
	}

	public void setLastRerunStartDate(Date lastRerunStartDate) {
		this.lastRerunStartDate = lastRerunStartDate;
	}

	@Column(name = "LAST_RERUN_END_DATE")
	public Date getLastRerunEndDate() {
		return this.lastRerunEndDate;
	}

	public void setLastRerunEndDate(Date lastRerunEndDate) {
		this.lastRerunEndDate = lastRerunEndDate;
	}

	@Column(name = "RERUN_COUNT")
	public Integer getRerunCount() {
		return this.rerunCount;
	}

	public void setRerunCount(Integer rerunCount) {
		this.rerunCount = rerunCount;
	}

	@Column(name = "LAST_FAILURE_COMMENTS")
	public String getLastFailureComments() {
		return this.lastFailureComments;
	}

	public void setLastFailureComments(String lastFailureComments) {
		this.lastFailureComments = lastFailureComments;
	}

	@Column(name = "ACTIVE_AUTO_RERUN")
	public Boolean getActiveAutoRerun() {
		return this.activeAutoRerun;
	}

	public void setActiveAutoRerun(Boolean activeAutoRerun) {
		this.activeAutoRerun = activeAutoRerun;
	}

	@Column(name = "IS_MANUAL_RERUN")
	public Boolean getIsManualRerun() {
		return this.isManualRerun;
	}

	public void setIsManualRerun(Boolean isManualRerun) {
		this.isManualRerun = isManualRerun;
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

	/**
	 * @Author      :      junjunzhu
	 *
	 * @Date        :      2015年5月6日
	 *
	 * @return the sourceFirstIndex
	 */
	@Column(name = "SOURCE_FIRST_INDEX")
	public Integer getSourceFirstIndex() {
		return sourceFirstIndex;
	}

	/**
	 * @Author      :      junjunzhu
	 *
	 * @Date        :      2015年5月6日
	 *
	 * @param sourceFirstIndex the sourceFirstIndex to set
	 */
	public void setSourceFirstIndex(Integer sourceFirstIndex) {
		this.sourceFirstIndex = sourceFirstIndex;
	}

	/**
	 * @Author      :      junjunzhu
	 *
	 * @Date        :      2015年5月6日
	 *
	 * @return the sourceLastIndex
	 */
	@Column(name = "SOURCE_LAST_INDEX")
	public Integer getSourceLastIndex() {
		return sourceLastIndex;
	}

	/**
	 * @Author      :      junjunzhu
	 *
	 * @Date        :      2015年5月6日
	 *
	 * @param sourceLastIndex the sourceLastIndex to set
	 */
	public void setSourceLastIndex(Integer sourceLastIndex) {
		this.sourceLastIndex = sourceLastIndex;
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