package com.sos.fleet.common.condition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.util.StringUtils;

import com.sos.fleet.common.domain.SampleAllDomain;
import com.sos.fleet.common.domain.SampleAllDomain_;
import com.sos.fleet.common.settings.EscapeSettings;

public class SampleCondition implements Specification<SampleAllDomain>,Serializable {

	private String name;
	private Integer age;
	private Date createDate;
	public SampleCondition() {
		super();
	}
	public SampleCondition(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public Predicate toPredicate(Root<SampleAllDomain> root,
			CriteriaQuery<?> query, CriteriaBuilder builder) {
		ArrayList<Predicate> predicates = new ArrayList<Predicate> (0);
		if(StringUtils.hasText(name)){
			predicates.add(builder.like(builder.upper(root.get(SampleAllDomain_.name)), EscapeSettings.likeForAnyWhere(name.toUpperCase()), EscapeSettings.getEscapeChar()));
		}
		if(age!=null){
			predicates.add(builder.equal(root.get("age"), age));
		}
		if(createDate!=null){
			predicates.add(builder.lessThan(root.get(SampleAllDomain_.createDate), createDate));
		}
		predicates.trimToSize();
		return predicates.size()==0?null:builder.and(predicates.toArray(new Predicate[predicates.size()]));
	}
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
