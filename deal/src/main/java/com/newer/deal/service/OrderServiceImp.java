package com.newer.deal.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.deal.entiry.Order;
import com.newer.deal.repository.OrderMapper;

@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	OrderMapper orderMapper;

	// 新建订单
	/*
	 * {
		"user": {
			"user_id": 1
		},
		"saletype": "购买",
		"totalprice": 210,----------
		"unitprice": 123,
		"dealnumber": 0.12,
		"currency": {
			"id": 1
		},
		"servicecharge": 0.002,---------
		"trading": {
			"user_id": 2
		},
		"entrust_id": 1
	}
	 */
	@Override
	public boolean createOrder(Order order) {
		// 获取订单货币的数量
		BigDecimal dealnumber = order.getDealnumber();
		// 获取订单的单价
		BigDecimal unitprice = order.getUnitprice();
		// 生成总价
		order.setTotalprice(dealnumber.multiply(unitprice));
		// 计算服务费
		order.setServicecharge(dealnumber.multiply(new BigDecimal("0.02")));
		
		
		return orderMapper.createOrder(order);
	}

	// 获取所有订单
	@Override
	public List<Order> findAll() {
		return orderMapper.findAll();
//		return null;
	}

	// 根据ID获取订单
	@Override
	public Order findById(int order_id) {
		return orderMapper.findById(order_id);
//		return null;
	}

	// 获取订单创建的用户所有订单
	@Override
	public List<Order> findByUser_id(int user_id) {
		return orderMapper.findByUser_id(user_id);
//		return null;
	}

	// 委托者的所有订单
	@Override
	public List<Order> findByTrading_id(int trading_id) {
		return orderMapper.findByTrading_id(trading_id);
//		return null;
	}

	

}
