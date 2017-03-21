package com.sos.tpl.common.web.mvc.domain;
// default package


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sos.tpl.common.web.mvc.domain.base.BaseDomain;


@Entity
@Table(name="T_FLEET")
public class FleetDomain  extends BaseDomain<Long> {
    // Fields    ASTQueryTranslatorFactory
     private String name;
     private String fleetType;


	public FleetDomain(Long id, Long createBy, Long lastUpdateBy,
			Date createDate, Date lastUpdateDate, boolean deleteFlag,
			String name, String fleetType,
			UserDomain userDomainByCreateBy, UserDomain userDomainLastUpdateBy) {
		super(id, createBy, lastUpdateBy, createDate, lastUpdateDate,
				deleteFlag, userDomainByCreateBy, userDomainLastUpdateBy);
		this.name = name;
		this.fleetType = fleetType;
	}

    public FleetDomain(Long id) {
    	this.id  = id;
    }

	/** default constructor */
    public FleetDomain() {
    }


   
    // Property accessors
    @Id 
	@SequenceGenerator(name = "S_FlEET__ID", sequenceName = "S_FlEET__ID",allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_FlEET__ID")
    @Override
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="FLEET_TYPE")
    public String getFleetType() {
        return this.fleetType;
    }
    
    public void setFleetType(String fleetType) {
        this.fleetType = fleetType;
    }
}
