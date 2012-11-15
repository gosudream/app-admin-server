package com.ztgame.admin.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ztgame.admin.domain.Role;
import com.ztgame.admin.repository.UserRepository;

@Controller
public class TestController {
	
	@Resource
	private UserRepository userRepository;

	@RequestMapping("/test.aspx")
	public void test(HttpServletResponse response) throws IOException {
		
		Role role = new Role();
		role.setName("小流氓");
		role.setExp(1000);
		role.setGender(true);
		userRepository.save(role);
		
		
		Role find = userRepository.findOne(role.getId());
		response.getWriter().write("Hi, Kitty." + find.getName());
		
		
		System.out.println(userRepository.count());
		
		// 页数 是 0开始的
		Pageable pageable = new PageRequest(0, 10);
		Page<Role> roles = userRepository.findByName("小流氓", pageable);
		for(Role r: roles){
			System.out.println(r.getId());
		}
	}
}
