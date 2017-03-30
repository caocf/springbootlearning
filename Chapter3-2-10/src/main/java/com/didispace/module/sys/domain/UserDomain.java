package com.didispace.module.sys.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.didispace.common.mvc.domain.base.BaseDomain;


@Entity
@Table(name="T_USER")
@EntityListeners(AuditingEntityListener.class)
public class UserDomain extends BaseDomain<Long>{


    // Fields    
     private String userName;
     private String orgId;
     private String email;
     private String mobile;
     private String address; 
    // Constructors

    /** default constructor */
    public UserDomain() {
    }

	/** minimal constructor */
    public UserDomain(Long id) {
        this.id = id;
    }
    
	public UserDomain(String userName, String email) {
		super();
		this.userName = userName;
		this.email = email;
	}

	public UserDomain(String userName, String orgId, String email,
			String mobile, String address) {
		super();
		this.userName = userName;
		this.orgId = orgId;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}

	// Property accessors
    @Id 
    @GeneratedValue
    //    @SequenceGenerator(name = "S_USER__ID", sequenceName = "S_USER__ID",allocationSize=0)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_USER__ID")
    public Long getId() {
        return this.id;
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

}