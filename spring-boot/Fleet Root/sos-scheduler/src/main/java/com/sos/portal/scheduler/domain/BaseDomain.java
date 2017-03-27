package com.sos.portal.scheduler.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;


@MappedSuperclass
public class BaseDomain<PK extends Serializable> implements Persistable<PK> {
	protected PK id, createBy, lastUpdateBy;
	protected Date createDate, lastUpdateDate;
	protected boolean deleteFlag;

	public BaseDomain(PK id, PK createBy, PK lastUpdateBy,
			Date createDate, Date lastUpdateDate, boolean deleteFlag) {
		super();
		this.id = id;
		this.createBy = createBy;
		this.lastUpdateBy = lastUpdateBy;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
		this.deleteFlag = deleteFlag;
	}

	public BaseDomain() {
		super();
	}

	@Transient
	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	@Column(name = "CREATE_BY")
	@CreatedBy
	public PK getCreateBy() {
		return createBy;
	}

	public void setCreateBy(PK createBy) {
		this.createBy = createBy;
	}

	@Column(name = "LAST_UPDATE_BY")
	@LastModifiedBy
	public PK getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(PK lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	@Column(name = "CREATE_DATE")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "LAST_UPDATE_DATE")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "DELETE_FLAG")
	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Transient
	public boolean isNew() {
		return null == getId();
	}

}
