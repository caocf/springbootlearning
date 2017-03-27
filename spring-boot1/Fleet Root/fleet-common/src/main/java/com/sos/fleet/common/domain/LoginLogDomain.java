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
@Table(name="T_LOGIN_LOG")
@EntityListeners(AuditingEntityListener.class)
public class LoginLogDomain  implements java.io.Serializable {
    // Fields    

     private Long id,userId;
     private UserDomain userDomain;
     private Date loginTime;
     private String ipAddress;
     private String roleType;


    // Constructors

    public LoginLogDomain(Long id, Long userId, Date loginTime,
			String ipAddress, String roleType, UserDomain userDomain) {
		super();
		this.id = id;
		this.userId = userId;
		this.loginTime = loginTime;
		this.ipAddress = ipAddress;
		this.roleType = roleType;
		this.userDomain = userDomain;
	}

	/** default constructor */
    public LoginLogDomain() {
    }

	/** minimal constructor */
    public LoginLogDomain(Long id) {
        this.id = id;
    }
    
    // Property accessors
    @Id 
    @SequenceGenerator(name = "S_LOGIN_LOG__ID", sequenceName = "S_LOGIN_LOG__ID",allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_LOGIN_LOG__ID")
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID",insertable=false,updatable=false)
	@JsonIgnore
    public UserDomain getUserDomain() {
        return this.userDomain;
    }
    
    public void setUserDomain(UserDomain userDomain) {
        this.userDomain = userDomain;
    }
    
    @Column(name="LOGIN_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    
    @Column(name="IP_ADDRESS")

    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    @Column(name="ROLE_TYPE")

    public String getRoleType() {
        return this.roleType;
    }
    
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Column(name="USER_ID")
    @CreatedBy
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}