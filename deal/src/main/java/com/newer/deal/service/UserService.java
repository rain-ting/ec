package com.newer.deal.service;

import com.newer.deal.entiry.User;

/**
 * 用户模块
 * @author ASUS
 *
 */
public interface UserService {
	
	/**
	 * 查找指定用户
	 * @return
	 */
	User loadById(int id);
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	User login(User user);
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	boolean add(User user);
	
	/**
	 * 验证资金密码
	 * @param user
	 * @return
	 */
	Integer checkMoneyPwd(User user);
	
	/**
	 * 钱包充值
	 * @param id
	 * @param user
	 * @return
	 */
	boolean topup(int id, User user);

	/**
	 * 完善用户信息
	 * @return
	 */
	User perfect(int id, User user);
	
	
	
}
