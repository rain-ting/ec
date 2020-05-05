package com.newer.deal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.deal.entiry.User;
import com.newer.deal.service.UserService;

@RestController
@RequestMapping("/user-center")
public class UserController {

	@Autowired
	UserService userService;
	
	//查找指定用户
	@GetMapping("/{id}")
	public User load(@PathVariable int id) {
		return userService.loadById(id);
	}
	
	// 用户登录
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		return userService.login(user);
	}
	
	// 用户注册
	@PostMapping
	public boolean create(@RequestBody User user) {
		return userService.add(user);
	}
	
	// 完善个人信息--业务逻辑
	@PutMapping("/{id}")
	public User update(@PathVariable int id, @RequestBody User user) {
		return userService.perfect(id, user);
	}
	
	/**
	 * 钱包充值
	 * 发送字段："idcardno":"金额"
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping("/{id}/topup")
	public boolean up(@PathVariable int id, @RequestBody User user) {
		
		return userService.topup(id, user);
	}
}
