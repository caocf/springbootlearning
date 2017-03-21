package com.sos.tpl.common.web.mvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;
@Entity
@Table(name="T_SAMPLE")
public class SampleNoneBaseDomain implements Persistable<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8449934218634121466L;
	private Long id;
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
	@SequenceGenerator(name = "S_FLEET_SAMPLE__ID", sequenceName = "S_FLEET_SAMPLE__ID",allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_FLEET_SAMPLE__ID")
	public Long getId() {
		return id;
	}
	@Override
	@Transient
	public boolean isNew() {
		return null==getId();
	}
	public void setId(Long id) {
		this.id = id;
	}
}
