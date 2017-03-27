package com.sos.fleet.common.domain;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sos.fleet.common.domain.base.BaseDomain;


@Entity
@Table(name="T_VEHICLE")
public class VehicleDomain  extends BaseDomain<Long> {


    // Fields    

     private String vin;
     private String model;
     private String plateId;
     private String driver;
     private String telephone;
     private Set<VehicleBindingDomain> vehicleBindingDomainsForVin = new HashSet<VehicleBindingDomain>(0);

    // Constructors

    /** default constructor */
    public VehicleDomain() {
    }

	/** minimal constructor */
    public VehicleDomain(Long id) {
        this.id = id;
    }

   
    public VehicleDomain(Long id, Long createBy, Long lastUpdateBy,
			Date createDate, Date lastUpdateDate, boolean deleteFlag,
			String vin, String model, String plateId, String driver,
			String telephone, UserDomain userDomainByCreateBy, UserDomain userDomainLastUpdateBy) {
		super(id, createBy, lastUpdateBy, createDate, lastUpdateDate,
				deleteFlag, userDomainByCreateBy, userDomainLastUpdateBy);
		this.vin = vin;
		this.model = model;
		this.plateId = plateId;
		this.driver = driver;
		this.telephone = telephone;
	}

	// Property accessors
    @Id 
    @Override
    @SequenceGenerator(name = "S_VEHICLE__ID", sequenceName = "S_VEHICLE__ID",allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_VEHICLE__ID")
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //修改vehicle,避免vin没传值的时候被修改
    @Column(name = "vin", updatable = false)
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

    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.DETACH,mappedBy="vehicleDomain")
    @JsonIgnore
	public Set<VehicleBindingDomain> getVehicleBindingDomainsForVin() {
		return vehicleBindingDomainsForVin;
	}

	public void setVehicleBindingDomainsForVin(
			Set<VehicleBindingDomain> vehicleBindingDomainsForVin) {
		this.vehicleBindingDomainsForVin = vehicleBindingDomainsForVin;
	}
}    
