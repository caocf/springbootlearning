package com.didispace.module.sys.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.didispace.common.mvc.domain.base.BaseDomain;
import com.didispace.module.sys._enum.SexType;
import com.google.common.collect.Lists;


@Entity
@Table(name="T_SYS_USER")
@EntityListeners(AuditingEntityListener.class)
public class UserDomain extends BaseDomain<Long>{

	/**
     * 登录用户
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 性别 女(0) 男(1) 保密(2) 默认：保密
     */
    private Integer sex = SexType.secrecy.getValue();
    /**
     * 用户出生日期
     */
    private Date birthday;

    /**
     * 头像
     */
    private String photo;

    /**
     * 邮件 以 ","分割
     */
    private String email;
    /**
     * 住址
     */
    private String address;
    /**
     * 住宅电话 以 ","分割
     */
    private String tel;
    /**
     * 手机号 以 ","分割
     */
    private String mobilephone;

    /**
     * 排序
     */
    private Integer orderNo;
    /**
     * 备注
     */
    private String remark;
    // Constructors

    /** default constructor */
    public UserDomain() {
    }

	/** minimal constructor */
    public UserDomain(Long id) {
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

    @Column(name="USER_NAME", updatable=false)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name = "LOGIN_NAME",length = 36, nullable = false)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "PASSWORD",length = 64, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "SEX")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 性别描述.
     */
    @Transient
    public String getSexView() {
    	SexType ss = SexType.getSexType(sex);
    	String str = "";
    	if(ss != null){
    		str = ss.getDescription();
    	}
        return str;
    }

    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "PHOTO",length = 1000)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Column(name = "EMAIL",length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "ADDRESS",length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "TEL",length = 36)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    @Column(name = "MOBILEPHONE",length = 36)
    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    @Column(name = "ORDER_NO")
    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "REMARK", length = 1000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}