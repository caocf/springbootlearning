package com.sos.fleet.common.condition;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.sos.fleet.common.domain.SampleAllDomain_;



@SuppressWarnings("rawtypes")
public class SampleNonMetaCondition implements Specification,Serializable {

	private String name;
	private Integer age;
	@SuppressWarnings("unchecked")
	@Override
	public Predicate toPredicate(Root root,
			CriteriaQuery query, CriteriaBuilder builder) {
		ArrayList<Predicate> predicates = new ArrayList<Predicate> (0);
		if(StringUtils.hasText(name)){
			predicates.add(builder.like(builder.upper(root.get(SampleAllDomain_.name)), name.toUpperCase(), '/'));
		}
		if(age!=null){
			predicates.add(builder.equal(root.get("age"), age));
		}
		predicates.trimToSize();
		return predicates.size()==0?null:builder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
