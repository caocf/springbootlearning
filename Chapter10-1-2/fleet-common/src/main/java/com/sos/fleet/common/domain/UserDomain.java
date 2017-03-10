package com.sos.fleet.common.domain;
// default package

import java.util.Date;

import javax.persistence.CascadeType;
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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sos.fleet.common.domain.base.BaseDomain;


@Entity
@Table(name="T_USER")
@EntityListeners(AuditingEntityListener.class)
public class UserDomain extends BaseDomain<Long>{


    // Fields    

     private Long fleetId;
     private FleetDomain fleetDomain;
     private String userName;
     private String orgId;
     private String email;
     private String mobile;
     private String address;
/*     private Set<OperationLogDomain> operationLogDomainsForOperatedUserId = new HashSet<OperationLogDomain>(0);
     private Set<UserDomain> userDomainsForCreateBy = new HashSet<UserDomain>(0);
     private Set<BindingHistoryDomain> bindingHistoryDomains = new HashSet<BindingHistoryDomain>(0);
     private Set<VehicleDomain> vehicleDomainsForLastUpdateBy = new HashSet<VehicleDomain>(0);
     private Set<UserDomain> userDomainsForLastUpdateBy = new HashSet<UserDomain>(0);
     private Set<VehicleDomain> vehicleDomainsForCreateBy = new HashSet<VehicleDomain>(0);
     private Set<VehicleBindingDomain> vehicleBindingDomains = new HashSet<VehicleBindingDomain>(0);
     private Set<FleetDomain> fleetDomainsForLastUpdateBy = new HashSet<FleetDomain>(0);
     private Set<OperationLogDomain> operationLogDomainsForOperatorId = new HashSet<OperationLogDomain>(0);
     private Set<LoginLogDomain> loginLogDomains = new HashSet<LoginLogDomain>(0);
     private Set<FleetDomain> fleetDomainsForCreateBy = new HashSet<FleetDomain>(0);
*/

    // Constructors

    /** default constructor */
    public UserDomain() {
    }

	/** minimal constructor */
    public UserDomain(Long id) {
        this.id = id;
    }
    
   

	public UserDomain(Long id, Long createBy, Long lastUpdateBy,
			Date createDate, Date lastUpdateDate, boolean deleteFlag,
			UserDomain userDomainByCreateBy,
			Long fleetId, String userName,
			String orgId, String email, String mobile, String address, UserDomain userDomainLastUpdateBy, FleetDomain fleetDomain) {
		super(id, createBy, lastUpdateBy, createDate, lastUpdateDate,
				deleteFlag, userDomainByCreateBy, userDomainLastUpdateBy);
		this.fleetId = fleetId;
		this.fleetDomain = fleetDomain;
		this.userName = userName;
		this.orgId = orgId;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}

	// Property accessors
    @Id 
    @Override
    @SequenceGenerator(name = "S_USER__ID", sequenceName = "S_USER__ID",allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_USER__ID")
    public Long getId() {
        return this.id;
    }
    /**
     * 这里的fetch关系不要改掉，否则SecurityUtil获得的Fleet对象将只有id。
     * 一般情况下，获得用户对象就要获得Fleet对象。
     * @return {@link FleetDomain}
     */
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FLEET_ID",insertable=false,updatable=false)
	@JsonIgnore
    public FleetDomain getFleetDomain() {
        return this.fleetDomain;
    }
    
    public void setFleetDomain(FleetDomain fleetDomain) {
        this.fleetDomain = fleetDomain;
    }
    
    @Column(name="USER_NAME", updatable=false)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="ORG_ID")
    public String getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="FLEET_ID")
	public Long getFleetId() {
		return fleetId;
	}

	public void setFleetId(Long fleetId) {
		this.fleetId = fleetId;
	}
    
    
}