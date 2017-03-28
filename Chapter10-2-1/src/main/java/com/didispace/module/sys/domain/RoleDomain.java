package com.didispace.module.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.didispace.common.mvc.domain.base.BaseDomain;


@Entity
@Table(name="T_SYS_ROLE")
@EntityListeners(AuditingEntityListener.class)
public class RoleDomain extends BaseDomain<Long>{

	/**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 描述
     */
    private String remark;
    // Constructors

    /** default constructor */
    public RoleDomain() {
    }

	/** minimal constructor */
    public RoleDomain(Long id) {
        this.id = id;
    }
    
	// Property accessors
    @Id 
    @GeneratedValue
    //    @SequenceGenerator(name = "S_USER__ID", sequenceName = "S_USER__ID",allocationSize=0)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_USER__ID")
    public Long getId() {
        return this.id;
    }

    @Column(name = "NAME",length = 100,nullable = false,unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE",length = 36)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "REMARK",length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}