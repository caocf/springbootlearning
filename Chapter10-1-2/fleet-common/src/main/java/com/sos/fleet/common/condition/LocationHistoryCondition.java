package com.sos.fleet.common.condition;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;

import com.sos.fleet.common.domain.LocationHistory;
import com.sos.fleet.common.domain.LocationHistory_;

public class LocationHistoryCondition implements Specification<LocationHistory> {

	private String vin;
	private Integer recent;
	private Integer far;
	private Date newDate = new Date();
	@Override
	public Predicate toPredicate(Root<LocationHistory> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		ArrayList<Predicate> list = new ArrayList<Predicate>(3);
		if(StringUtils.isNotBlank(vin)){
			list.add(cb.equal(root.get(LocationHistory_.vin), vin));
		}
//		Date ceilingDate = DateUtils.ceiling(newDate, Calendar.DAY_OF_MONTH);
		Date truncateDate = DateUtils.truncate(newDate, Calendar.DAY_OF_MONTH);
		Date recentDate;
		if(recent==null||recent<=0||recent>=7){
			recentDate = newDate;
		}else{
			recentDate = DateUtils.addDays(truncateDate,(recent-1)*-1);
		}
		Date farDate;
		if(far==null||far<=0||far>7){
			farDate = truncateDate;
		}else{
			farDate = DateUtils.addDays(truncateDate,(far-1)*-1);
		}
		int com = recentDate.compareTo(farDate);
		if(com<0){
			Date tempDate = recentDate;
			recentDate = farDate;
			farDate = tempDate;
		}
		list.add(cb.greaterThanOrEqualTo(root.get(LocationHistory_.locatedTime), farDate));
		if(com!=0){
			list.add(cb.lessThan(root.get(LocationHistory_.locatedTime), recentDate));
		}else{
			list.add(cb.lessThanOrEqualTo(root.get(LocationHistory_.locatedTime), recentDate));
		}
		if(StringUtils.isNotBlank(vin)){
			list.add(cb.equal(root.get(LocationHistory_.vin), vin));
		}
		list.trimToSize();
		return cb.and(list.toArray(new Predicate[list.size()]));
	}
/*public static void main(String[] args) throws ParseException {
	Date nowDate = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
//	Date nowDate = DateUtils.ceiling(DateFormatUtils.ISO_DATETIME_FORMAT.parse("2015-10-14T00:00:00"), Calendar.DAY_OF_MONTH);	
	System.out.println(DateFormatUtils.ISO_DATETIME_FORMAT.format(nowDate));
	Date before7DayDate = DateUtils.addDays(nowDate, -6);
	System.out.println(DateFormatUtils.ISO_DATETIME_FORMAT.format(before7DayDate));
}*/
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	public Integer getRecent() {
		return recent;
	}
	public void setRecent(Integer recent) {
		this.recent = recent;
	}
	public Integer getFar() {
		return far;
	}
	public void setFar(Integer far) {
		this.far = far;
	}


}
