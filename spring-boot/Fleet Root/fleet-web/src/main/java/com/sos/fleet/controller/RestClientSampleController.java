package com.sos.fleet.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sos.fleet.common.condition.SampleCondition;
import com.sos.fleet.common.controller.RestClientController;
import com.sos.fleet.common.domain.FleetDomain;
import com.sos.fleet.common.domain.SampleAllDomain;
import com.sos.fleet.common.domain.SearchPageRequest;
import com.sos.fleet.common.domain.ldap.UserLdapDomain;
import com.sos.fleet.common.rest.RestClient;
import com.sos.fleet.common.settings.WebServiceURLSettings;
import com.sos.fleet.common.util.JsonUtil;
import com.sos.fleet.common.util.SecurityUtil;
import com.sos.fleet.service.OperationLogService;
import com.sos.fleet.service.SampleService;
import com.sos.fleet.service.UserLdapService;
import com.sos.fleet.service.UserService;

@Controller
@Secured("ROLE_SUBSCRIBER")
public class RestClientSampleController extends RestClientController {
	static Log log = LogFactory.getLog(RestClientSampleController.class);
	@Value("${ws.getSubscriberByEmail}")
	String getSubscriberByEmail;
	@Autowired
	WebServiceURLSettings urlSettings;
	@Autowired
	private SampleService sampleService;
	@Autowired 
	UserService userService;
	@Autowired
	private UserLdapService userLdapService;
//	@Secured(SecuritySettings.DENY_ON_READ_ONLY_ENV)
	@RequestMapping("/test")
	public String home(@RequestParam(value="testXss",defaultValue="default") String testXss,HttpServletRequest request){
		log.debug("----------------------------------------------------------------------------------------------");
		log.debug("----------------------------------------Login Information------------------------------------------");
		log.debug("----------------------------------------------------------------------------------------------");
//		log.debug("Fleet: "+SecurityUtil.getFleet());
//		log.debug("Fleet: "+SecurityUtil.getFleet());
		FleetDomain fd = SecurityUtil.getFleet();
		log.debug("Fleet id: "+fd.getId());
		log.debug("get Fleet name by user service: "+userService.get(SecurityUtil.getUserId()).getFleetDomain().getName());
		log.debug("get Fleet name by security util: "+fd.getName()+", "+fd.getFleetType());
		log.debug("----------------------------------------------------------------------------------------------");
		log.debug("----------------------------------------XSS Sample------------------------------------------");
		log.debug("----------------------------------------------------------------------------------------------");
		log.debug("@RequestParam: "+testXss);
		log.debug("request.getParameter(): "+request.getParameter("testXss"));
		log.debug("----------------------------------------------------------------------------------------------");
		log.debug("----------------------------------------Ldap Sample------------------------------------------");
		log.debug("----------------------------------------------------------------------------------------------");
		UserLdapDomain userLdapDomain = new UserLdapDomain();
		userLdapDomain.setFirstLogin("true");
		userLdapDomain.setGender("Male");
		userLdapDomain.setIsEnabled("true");
		userLdapDomain.setCompanyName("SOS");
		userLdapDomain.setName("Ceshi1");
		userLdapDomain.setUid("fleet"+new Date().getTime());
		userLdapDomain.setSodwType("SUBSCRIBER");
		userLdapService.save(userLdapDomain);
		userLdapDomain.setNickname("nick1");
		userLdapService.update(userLdapDomain);
		
		userLdapService.logicDelete("fleetUser1");
		
		UserLdapDomain dbUser = userLdapService.get("fleetUser1");
		log.debug(JsonUtil.getJson(dbUser));
		log.debug("----------------------------------------------------------------------------------------------");
		log.debug("----------------------------------------DB Sample------------------------------------------");
		log.debug("----------------------------------------------------------------------------------------------");
		sampleService.logicDelete(0l);
		SampleAllDomain domain = new SampleAllDomain();
		domain.setName(String.valueOf(new Date().getTime()));
//		Random rand = new Random();
//		rand.setSeed(new Date().getTime());
		domain.setAge(11);
		sampleService.save(domain );
//		AutowiredAnnotationBeanPostProcessor
//		ConfigurationPropertiesBindingPostProcessor
//		ValidatorImpl
//		JPATraversableResolver
		domain.setComments(String.valueOf(new Date().getTime()));
		sampleService.update(domain );
		SampleCondition sc = new SampleCondition("6",null);
//		sc.setCreateDate(new Date());
//		SampleNonMetaCondition sc = new SampleNonMetaCondition("6",null);
		SearchPageRequest<SampleAllDomain> pageRequest 
		= new SearchPageRequest<SampleAllDomain>(0, 5,new Sort(Direction.ASC, "id"), sc);
		Page<SampleAllDomain> pager = sampleService.findAll(pageRequest);
		if(pager!=null){
			log.debug("pageSize:"+pager.getSize());
			log.debug("count row:"+pager.getTotalElements());
			log.debug("total page:"+pager.getTotalPages());
			log.debug("current Page:"+pager.getNumber());
			log.debug("current Page size:"+pager.getNumberOfElements());
			List<SampleAllDomain> list = pager.getContent();
			for (SampleAllDomain sampleAllDomain : list) {
				log.debug(JsonUtil.getJson(sampleAllDomain));
			}
		}
		log.debug("----------------------------------------------------------------------------------------------");
		log.debug("----------------------------------------Restful Client Sample------------------------------------------");
		log.debug("----------------------------------------------------------------------------------------------");
		/*
		 * The WS URL is Https. If want to run success that will import cer provided WS site to JRE's cacerts by keytool of java.
		 * url: ${ws.baseUrl}/GlobalIG/getSubscriberBySubId?subscriberId={0}&locale={1}
		 *  ResponseType: Class<T>
		 */
		/*log.debug(WebServiceURLSettings.instance().getGetSubscriberBySubId());
		HashMap<?,?> hashMap = restClient.getForObject(WebServiceURLSettings.instance().getGetSubscriberBySubId(), HashMap.class, "00000000000108040604_00000001427682832649_00013","zh-CN");
		log.debug(JsonUtil.getJson(hashMap));*/
		
		/*
		 * The url is a non-host URL. The ParameterizedTypeReference<T> is a Generic Type constructor that is a interface.
		 * url: /GlobalIG/getSubscriberByEmail?email={0}&locale={1}
		 * ResponseType: 
		 */
		/*log.debug(WebServiceURLSettings.getWSUrl(WebServiceURLSettings.instance().getGetSubscriberByEmail()));
		ParameterizedTypeReference<HashMap<String,Object>> responseType = new ParameterizedTypeReference<HashMap<String,Object>>() {};
		// WebServiceURLSettings.getFullUrl(getSubscriberByEmail) = WebServiceURLSettings.getFullUrl(WebServiceURLSettings.instance().getGetSubscriberByEmail())
		HashMap<String,Object> rs = restClient.getForObject(WebServiceURLSettings.getWSUrl(getSubscriberByEmail), responseType, "ZHENNI@126.COM","zh-CN");
		log.debug(JsonUtil.getJson(rs));*/
		
		/*
		 * Call rest service via injected WebServiceURLSettings instance.
		 * RestClient static.
		 */
		/*log.debug(urlSettings.getGetSubscriberByVID());
		hashMap = RestClient.instance().getForObject(urlSettings.getGetSubscriberByVID(), HashMap.class, "LSGAR55L2F0700526");
		log.debug(JsonUtil.getJson(hashMap));*/
		
		return "index";
	}
	
}
