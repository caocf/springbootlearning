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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.Modifying;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_BINDING_HISTORY")
@EntityListeners(AuditingEntityListener.class)
public class BindingHistoryDomain implements java.io.Serializable {

	// Fields
	private Long id, operatorId,userId;
	private UserDomain operator;
	private VehicleBindingDomain vehicleBindingDomain;
	private FleetDomain fleetDomain;
	private UserDomain userDomain;
	private Integer status;
	private String comments;
	private Date bindingStartDate;
	private Long fleetId;
	private Long vehicleId;
	private Long bindingId;
	private Date bindingEndDate;
	
	private String vin;
    private String model;
    private String plateId;
    private String driver;
    private String telephone;

	// Constructors

	/** default constructor */
	public BindingHistoryDomain() {
	}

	public BindingHistoryDomain(Long id) {
		this.id = id;
	}

	public BindingHistoryDomain(Long id, Long operatorId, UserDomain operator,
			VehicleBindingDomain vehicleBindingDomain, FleetDomain fleetDomain,
			Integer status, String comments, Date bindingStartDate,
			Long fleetId, Long vehicleId, Long bindingId, Date bindingEndDate,
			String vin, String model, String plateId, String driver,
			String telephone) {
		super();
		this.id = id;
		this.operatorId = operatorId;
		this.operator = operator;
		this.vehicleBindingDomain = vehicleBindingDomain;
		this.fleetDomain = fleetDomain;
		this.status = status;
		this.comments = comments;
		this.bindingStartDate = bindingStartDate;
		this.fleetId = fleetId;
		this.vehicleId = vehicleId;
		this.bindingId = bindingId;
		this.bindingEndDate = bindingEndDate;
		this.vin = vin;
		this.model = model;
		this.plateId = plateId;
		this.driver = driver;
		this.telephone = telephone;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "S_BINDING_HISTORY__ID", sequenceName = "S_BINDING_HISTORY__ID", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_BINDING_HISTORY__ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "OPERATOR_ID", insertable = false, updatable = false)
	@JsonIgnore
	public UserDomain getOperator() {
		return this.operator;
	}

	public void setOperator(UserDomain operator) {
		this.operator = operator;
	}

	@Column(name = "BINDING_ID")
	public Long getBindingId() {
		return bindingId;
	}

	public void setBindingId(Long bindingId) {
		this.bindingId = bindingId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "BINDING_ID", insertable = false, updatable = false)
	@JsonIgnore
	public VehicleBindingDomain getVehicleBindingDomain() {
		return this.vehicleBindingDomain;
	}

	public void setVehicleBindingDomain(
			VehicleBindingDomain vehicleBindingDomain) {
		this.vehicleBindingDomain = vehicleBindingDomain;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "BINDING_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
//	@CreatedDate
	public Date getBindingStartDate() {
		return this.bindingStartDate;
	}

	public void setBindingStartDate(Date bindingStartDate) {
		this.bindingStartDate = bindingStartDate;
	}

	@Column(name = "FLEET_ID")
	public Long getFleetId() {
		return this.fleetId;
	}

	public void setFleetId(Long fleetId) {
		this.fleetId = fleetId;
	}

	@Column(name = "VEHICLE_ID")
	public Long getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Column(name = "BINDING_END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
//	@LastModifiedDate
	public Date getBindingEndDate() {
		return this.bindingEndDate;
	}

	public void setBindingEndDate(Date bindingEndDate) {
		this.bindingEndDate = bindingEndDate;
	}

	@Column(name = "OPERATOR_ID")
	@CreatedBy
	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "FLEET_ID", insertable = false, updatable = false)
	@JsonIgnore
	public FleetDomain getFleetDomain() {
		return fleetDomain;
	}

	public void setFleetDomain(FleetDomain fleetDomain) {
		this.fleetDomain = fleetDomain;
	}

	public String getVin() {
        return this.vin;
    }
    
    public void setVin(String vin) {
        this.vin = vin;
    }
    

    public String getModel() {
        return this.model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    @Column(name="PLATE_ID")
    public String getPlateId() {
        return this.plateId;
    }
    
    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }
    

    public String getDriver() {
        return this.driver;
    }
    
    public void setDriver(String driver) {
        this.driver = driver;
    }
    

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    @Column(name="USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID",insertable=false,updatable=false)
	@JsonIgnore
	public UserDomain getUserDomain() {
		return userDomain;
	}

	public void setUserDomain(UserDomain userDomain) {
		this.userDomain = userDomain;
	}
}