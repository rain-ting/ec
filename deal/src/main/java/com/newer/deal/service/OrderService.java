package com.newer.deal.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.newer.deal.entiry.Order;

/**
 * 订单
 * @author ASUS
 *
 */
public interface OrderService {
	
	/**
	 * 新增一条订单记录
	 * @param order
	 * @return
	 */
	boolean createOrder(Order order);
	
	
	/**
	 * 查询所有订单
	 * @return
	 */
	List<Order> findAll();
	
	
	/**
	 * 根据订单id查询订单
	 * @param order_id
	 * @return
	 */
	Order findById(int order_id);
	
	
	/**
	 * 根据订单的创建所属用户查询
	 * @param user_id
	 * @return
	 */
	List<Order> findByUser_id(int user_id);
	
	
	/**
	 * 委托用户的订单查询
	 * @param trading_id
	 * @return
	 */
	List<Order> findByTrading_id(int trading_id);
	
	
	

}
