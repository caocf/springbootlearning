package org.spboot.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sos.fleet.ApplicationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ApplicationConfiguration.class)
//@Transactional
@SpringApplicationConfiguration(classes = ApplicationConfiguration.class)
//@SpringApplicationConfiguration(classes = ApplicationConfiguration.class)
public class DBInitTests {
	
//	@Autowired
//	CityRepository cityRepository;
	
	@Test
	public void testHome()  {
		System.out.print("2222");
//		CityDomain city = this.cityRepository
//				.findAll(new PageRequest(0, 1, Direction.ASC, "name")).getContent()
//				.get(0);
//		assertThat(city.getName()).isEqualTo("Atlanta");  
	}
	
	
	
}