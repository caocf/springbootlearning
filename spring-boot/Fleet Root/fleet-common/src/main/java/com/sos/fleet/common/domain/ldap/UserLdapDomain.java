package com.sos.fleet.common.domain.ldap;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entry(objectClasses={"top","person","organizationalPerson","inetOrgPerson","fsp"},base="ou=user,ou=FSP,ou=app,o=shanghaionstar.com")
public class UserLdapDomain implements BaseLdapDomain<String> {
	@Id
	@JsonIgnore
	protected Name rdn; 
	@DnAttribute(value="uid")
	protected String uid;
	@Attribute(name="fsp-isenable")
	protected String isEnabled;
	@Attribute(name="fsp-usertype")
	private String sodwType;
	@Attribute(name="fsp-firstlogin")
	protected String firstLogin;
	@Attribute(name="cn")
	protected String companyName;
	@Attribute(name="sn")
	protected String name;
	private String mail;
	private String gender;
	private String mobile;
	@Attribute(name="fsp-nickname")
	private String nickname;
	private String userPassword;
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String getUid() {
		return this.uid;
	}
	
	public Name getRdn() {
		return rdn;
	}
	@Override
	public void setRdn(Name rdn) {
		this.rdn = rdn;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getSodwType() {
		return sodwType;
	}
	public void setSodwType(String sodwType) {
		this.sodwType = sodwType;
	}
	public String getFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
