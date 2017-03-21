package com.sos.tpl.common.web.mvc.domain;
// default package


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="T_LOCATION_HISTORY")
@EntityListeners(AuditingEntityListener.class)
public class LocationHistory  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String latitude;
     private String longitude;
     private Date locatedTime;
     private Date createDate;
     private String vin;


    // Constructors

    /** default constructor */
    public LocationHistory() {
    }

	/** minimal constructor */
    public LocationHistory(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public LocationHistory(Long id, String latitude, String longitude, Date locatedTime, Date createDate, String vin) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locatedTime = locatedTime;
        this.createDate = createDate;
        this.vin = vin;
    }

   
    // Property accessors
    @Id 
    @SequenceGenerator(name = "S_LOCATION_HISTORY__ID", sequenceName = "S_LOCATION_HISTORY__ID",allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_LOCATION_HISTORY__ID")
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    public String getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    @Column(name="LOCATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLocatedTime() {
        return this.locatedTime;
    }
    
    public void setLocatedTime(Date locatedTime) {
        this.locatedTime = locatedTime;
    }
    
    @Column(name="CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public String getVin() {
        return this.vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
   








}