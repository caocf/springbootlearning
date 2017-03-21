package com.sos.tpl.modules.sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sos.tpl.common.web.mvc.domain.base.BaseDomain;

@Entity
@Table(name="T_SAMPLE")
/*@AttributeOverrides({
	@AttributeOverride(name="createDate", column=@Column(name="CREATE_DATE", nullable = false)),
	@AttributeOverride(name="createBy", column=@Column(name="CREATE_BY")),
	@AttributeOverride(name="lastUpdateBy", column=@Column(name="LAST_UPDATE_BY")),
	@AttributeOverride(name="lastUpdateDate", column=@Column(name="LAST_UPDATE_DATE")),
	@AttributeOverride(name="deleteFlag", column=@Column(name="DELETE_FLAG"))
})*/
@EntityListeners(AuditingEntityListener.class)
public class SampleAllDomain extends BaseDomain<Long>{
	@Column
	private String name;
	private Integer age;
	private String comments;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	@Id
	@SequenceGenerator(name = "S_FLEET__ID", sequenceName = "S_FLEET__ID",allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_FLEET__ID")
	public Long getId() {
		return id;
	}
}