package com.newer.deal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.deal.entiry.Order;
import com.newer.deal.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	// 获取所有订单
	@GetMapping
	public List<Order> findAll() {
		return orderService.findAll();
	}
	
	// 订单id获取
	@GetMapping("/{order_id}")
	public Order loadOrder(@PathVariable int order_id) {
		return orderService.findById(order_id);
	}
	
	// 创建者订单
	@GetMapping("/users/{user_id}")
	public List<Order> listUser(@PathVariable int user_id) {
		
		return orderService.findByUser_id(user_id);
		
	}
	
	
	// 委托者订单
	@GetMapping("/trading/{trading_id}")
	public List<Order> listTrading(@PathVariable int trading_id) {
		return orderService.findByTrading_id(trading_id);
	}
	
	// 新建订单
	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody Order order) {
		
		boolean b = orderService.createOrder(order);
		
		if (b) {
			return new ResponseEntity<>(order, HttpStatus.OK);
		}
		
		return new ResponseEntity<>("创建订单失败", HttpStatus.OK);
		
	}
	
}
