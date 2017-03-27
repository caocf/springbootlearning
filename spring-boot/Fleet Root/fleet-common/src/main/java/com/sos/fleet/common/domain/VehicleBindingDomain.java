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
@Table(name="T_VEHICLE_BINDING")
@EntityListeners(AuditingEntityListener.class)
public class VehicleBindingDomain  implements java.io.Serializable {


    // Fields    

     private Long id,operatorId,fleetId,userId;
     private UserDomain operator;
     private FleetDomain fleetDomain;
     private Integer status;
     private String comments;
     private Date bindingDate;
     private String vin;
     private VehicleDomain vehicleDomain;
     private UserDomain userDomain;
     private String model;
     private String plateId;
     private String driver;
     private String telephone;

    // Constructors

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="vin",referencedColumnName="vin",insertable=false,updatable=false)
 	@JsonIgnore
    public VehicleDomain getVehicleDomain() {
		return vehicleDomain;
	}

	public void setVehicleDomain(VehicleDomain vehicleDomain) {
		this.vehicleDomain = vehicleDomain;
	}

	/** default constructor */
    public VehicleBindingDomain() {
    }

	/** minimal constructor */
    public VehicleBindingDomain(Long id) {
        this.id = id;
    }
    
	public VehicleBindingDomain(Long id, Long operatorId, Long fleetId,
			UserDomain operator, FleetDomain fleetDomain, Integer status,
			String comments, Date bindingDate, String vin,
			VehicleDomain vehicleDomain, String model, String plateId,
			String driver, String telephone) {
		super();
		this.id = id;
		this.operatorId = operatorId;
		this.fleetId = fleetId;
		this.operator = operator;
		this.fleetDomain = fleetDomain;
		this.status = status;
		this.comments = comments;
		this.bindingDate = bindingDate;
		this.vin = vin;
		this.vehicleDomain = vehicleDomain;
		this.model = model;
		this.plateId = plateId;
		this.driver = driver;
		this.telephone = telephone;
	}

	// Property accessors
    @Id 
    @SequenceGenerator(name = "S_VEHICLE_BINDING__ID", sequenceName = "S_VEHICLE_BINDING__ID",allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_VEHICLE_BINDING__ID")
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OPERATOR_ID",insertable=false,updatable=false)
	@JsonIgnore
    public UserDomain getOperator() {
        return this.operator;
    }
    
    public void setOperator(UserDomain operator) {
        this.operator = operator;
    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FLEET_ID",insertable=false,updatable=false)
	@JsonIgnore
    public FleetDomain getFleetDomain() {
        return this.fleetDomain;
    }
    
    public void setFleetDomain(FleetDomain fleetDomain) {
        this.fleetDomain = fleetDomain;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="COMMENTS")
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    @Column(name="BINDING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date getBindingDate() {
        return this.bindingDate;
    }
    
    public void setBindingDate(Date bindingDate) {
        this.bindingDate = bindingDate;
    }
//    @Column(unique=true)
    public String getVin() {
        return this.vin;
    }
    
    public void setVin(String vin) {
        this.vin = vin;
    }

    @Column(name="OPERATOR_ID")
    @CreatedBy
	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	
	 @Column(name="FLEET_ID")
	public Long getFleetId() {
		return fleetId;
	}

	public void setFleetId(Long fleetId) {
		this.fleetId = fleetId;
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