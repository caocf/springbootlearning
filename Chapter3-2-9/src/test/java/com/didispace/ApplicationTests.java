package com.didispace;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didispace.dao.UserRepository;
import com.didispace.domain.User;
import com.didispace.service.UserService;
import com.didispace.util.PageableTools;
import com.didispace.util.SortDto;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private UserService userService;
	
	@Before
	public void beforTest() throws Exception {
		// 创建12条记录
		userRepository.save(new User("AAA", 10));
		userRepository.save(new User("BBB", 20));
		userRepository.save(new User("CCC", 30));
		userRepository.save(new User("CCC", 40));
		userRepository.save(new User("CCC", 50));
		userRepository.save(new User("DDD", 40));
		userRepository.save(new User("EEE", 50));
		userRepository.save(new User("FFF", 60));
		userRepository.save(new User("GGG", 70));
		userRepository.save(new User("HHH", 80));
		userRepository.save(new User("III", 90));
		userRepository.save(new User("JJJ", 100));
	}
	
	@Test
	public void test() throws Exception {

		

		// 测试findAll, 查询所有记录
//		Assert.assertEquals(10, userRepository.findAll().size());

		// 测试findByName, 查询姓名为FFF的User
		Assert.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());

		// 测试findUser, 查询姓名为FFF的User
		Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());

		// 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
		Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());

		// 测试删除姓名为AAA的User
		userRepository.delete(userRepository.findByName("AAA"));
		
		userService.getUserbyName("CCC");
		// 测试findAll, 查询所有记录, 验证上面的删除是否成功
//		Assert.assertEquals(9, userRepository.findAll().size());

	}
	
	/**
	 * 测试只传页码参数
	 */
	@Test
	public void test2() {
		Page<User> datas = userRepository.findAll(PageableTools.basicPage(0));
		print(datas);
	}
	
	/**
	 * 测试传页码和条数
	 */
	@Test
	public void test3() {
	    Page<User> datas = userRepository.findAll(PageableTools.basicPage(1, 5));
	    print(datas);
	}
	
	/**
	 * 测试传页码、条数和排序
	 */
	@Test
	public void test4() {
	    Page<User> datas = userRepository.findAll(PageableTools.basicPage(1, 5, new SortDto("id")));
	    print(datas);

	    Page<User> datas2 = userRepository.findAll(PageableTools.basicPage(1, 5, new SortDto("ASC", "id")));
	    print(datas2);
	}
	
	private void print(Page<User> datas) {
		System.out.println("总条数："+datas.getTotalElements());
		System.out.println("总页数："+datas.getTotalPages());
		for(User u : datas) {
			System.out.println(u.getId()+"===="+u.getName());
		}
	}
}
