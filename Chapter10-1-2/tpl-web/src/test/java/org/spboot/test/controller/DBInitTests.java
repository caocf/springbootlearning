package org.spboot.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sos.tpl.ApplicationConfiguration;
import com.sos.tpl.common.web.mvc.dao.dao.UserDao;
import com.sos.tpl.common.web.mvc.domain.UserDomain;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ApplicationConfiguration.class)
//@Transactional
//@SpringApplicationConfiguration(classes = ApplicationConfiguration.class)
@SpringApplicationConfiguration(classes =ApplicationConfiguration.class)
//@SpringApplicationConfiguration(classes = ApplicationConfiguration.class)
public class DBInitTests {
	
//	@Autowired
//	CityRepository cityRepository;
	@Autowired
	protected UserDao userDao;
	

	@Test
	public void testHome()  {
		System.out.print("2222");
		UserDomain user1 = new UserDomain();
		user1.setUserName("测试人员");
		user1.setMobile("13764026448");
		userDao.save(user1);
		System.out.print("2222--end");
//		CityDomain city = this.cityRepository
//				.findAll(new PageRequest(0, 1, Direction.ASC, "name")).getContent()
//				.get(0);
//		assertThat(city.getName()).isEqualTo("Atlanta");  
	}
	
	
	
}