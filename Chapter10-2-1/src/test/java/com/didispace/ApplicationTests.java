package com.didispace;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didispace.module.sys.dao.UserRepository;
import com.didispace.module.sys.domain.UserDomain;
import com.didispace.module.sys.service.UserService;
import com.didispace.utils.PageableTools;
import com.didispace.utils.SortDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ApplicationConfiguration.class)
public class ApplicationTests {

	@Autowired
	private UserRepository UserRepository;
	

	@Autowired
	private UserService UserService;
	
	@Before
	public void beforTest() throws Exception {
		// 创建12条记录
//		UserRepository.save(new UserDomain("AAA","arenzhj@163.com"));
//		UserRepository.save(new UserDomain("BBB", "arenzhj111@163.com"));
//		UserRepository.save(new UserDomain("CCC", "arenzhj1121@163.com"));
//		UserRepository.save(new UserDomain("CCC","arenzhj11123@163.com"));
//		UserRepository.save(new UserDomain("CCC", "arenzhj11124@163.com"));
//		UserRepository.save(new UserDomain("DDD", "arenzhj11125@163.com"));
//		UserRepository.save(new UserDomain("EEE", "arenzhj11126@163.com"));
//		UserRepository.save(new UserDomain("FFF", "arenzhj11127@163.com"));
//		UserRepository.save(new UserDomain("GGG", "arenzhj111272@163.com"));
//		UserRepository.save(new UserDomain("HHH", "arenzhj111234@163.com"));
//		UserRepository.save(new UserDomain("III", "arenzhj111253@163.com"));
//		UserRepository.save(new UserDomain("JJJ", "arenzhj111334@163.com"));
	}
	
	@Test
	public void test() throws Exception {

		

		// 测试findAll, 查询所有记录
//		Assert.assertEquals(10, UserRepository.findAll().size());

		// 测试findByUserName, 查询姓名为FFF的UserDomain
//		Assert.assertEquals(60, UserRepository.findByUserName("FFF").getEmail());

		// 测试findUserDomain, 查询姓名为FFF的UserDomain
//		Assert.assertEquals(60, UserRepository.findByUserName("FFF").getEmail());

		// 测试findByUserNameAndEmail, 查询姓名为FFF并且年龄为60的UserDomain
//		Assert.assertEquals("FFF", UserRepository.findByUserNameAndEmail("FFF", "arenzhj").getUserName());

		// 测试删除姓名为AAA的UserDomain
//		UserRepository.delete(UserRepository.findByUserName("AAA"));
//		
//		UserService.getUserbyName("CCC");
		// 测试findAll, 查询所有记录, 验证上面的删除是否成功
//		Assert.assertEquals(9, UserRepository.findAll().size());

	}
	
	/**
	 * 测试只传页码参数
	 */
	@Test
	public void test2() {
		Page<UserDomain> datas = UserRepository.findAll(PageableTools.basicPage(0));
		print(datas);
	}
	
	/**
	 * 测试传页码和条数
	 */
	@Test
	public void test3() {
	    Page<UserDomain> datas = UserRepository.findAll(PageableTools.basicPage(1, 5));
	    print(datas);
	}
	
	/**
	 * 测试传页码、条数和排序
	 */
	@Test
	public void test4() {
	    Page<UserDomain> datas = UserRepository.findAll(PageableTools.basicPage(1, 5, new SortDto("id")));
	    print(datas);

	    Page<UserDomain> datas2 = UserRepository.findAll(PageableTools.basicPage(1, 5, new SortDto("ASC", "id")));
	    print(datas2);
	}
	
	private void print(Page<UserDomain> datas) {
		System.out.println("总条数："+datas.getTotalElements());
		System.out.println("总页数："+datas.getTotalPages());
		for(UserDomain u : datas) {
			System.out.println(u.getId()+"===="+u.getUserName());
		}
	}
}
