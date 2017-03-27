/************************************************************************************
 * @File name   :      DynamicDataSourceAop.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年4月23日
 *
 * @Copyright Notice: 
 * Copyright (c) 2012 Shanghai OnStar, Inc. All  Rights Reserved.
 * This software is published under the terms of the Shanghai OnStar Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2015年4月23日 下午2:57:04			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.aop;



import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.sos.portal.scheduler.annotation.DataSource;
import com.sos.portal.scheduler.util.DbContextHolder;


/**
 *
 */
@Component
@Aspect
public class DynamicDataSourceAop implements Ordered {
	protected static final Log log = LogFactory.getLog(DynamicDataSourceAop.class);
  /*
   * 用@Pointcut不能拦截父类方法 
   * @Pointcut("(execution(* com.sos.portal.scheduler.service.*.*(..))) or (execution(* com.sos.portal.common.service.*.*(..)))")
	public void serviceMethod(){	}*/
	
	/*
	 * 不建议监听DAO，同时service层事务开启时，是不能切换DataSource的。同时，监听dao会频繁切换DataSource。
	 * 虽然目前所有的dao层实现类都加入了@DataSource的注解，但是是无用的，标注上@DataSource的DAO是为了测试用的，同时也为了标注该DAO是操作的哪个datasource。
	 * 只有Service层的@DataSource才会被该AOP解析
	 * 只要设计时，确保所有的service层的调用只用统一的datasource，且在事务开启时，是不能切换
	 * @Pointcut("(execution(* com.sos.portal.scheduler.dao..*.*(..))) or (execution(* com.sos.portal.common.dao..*.*(..)))")
	public void daoMethod(){	}*/
	
	
	@Before("execution(* com.sos.portal.scheduler.service.*.*(..))")
	public void before(JoinPoint jp){
		DataSource ds=null ;
		try{
			MethodSignature sign = (MethodSignature)jp.getSignature();
			ds=	sign.getMethod().getAnnotation(DataSource.class);
		}catch(Exception ex){
			log.error(ex.getMessage());
		}
		if(ds==null){
			Class<?> clazz = jp.getTarget().getClass();
			log.info("target class: "+clazz);
			ds =  clazz.getAnnotation(DataSource.class);
		}
		if(ds==null){
			throw new RuntimeException("Not set  @DataSource annotation.");
		}
		String dbType = ds.value();
		if(StringUtils.isBlank(dbType)){
			throw new RuntimeException("Please set a dbType value by @DataSource annotation");
		}
		String rs = DbContextHolder.getDbName(dbType);
		if(rs==null){
			throw new RuntimeException("The datasource name["+dbType+"] is not exists in datasource configuration file. Please reset the value");
		}
		DbContextHolder.setDbType(rs);
		log.info("Set db type : " + rs);
	}


	/** 
	 * 排序的顺序一定要在事务aop的前面
	 * overridden:
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月11日
	 * @see org.springframework.core.Ordered#getOrder()
	**/
	@Override
	public int getOrder() {
		return 1;
	}
}
