package com.newer.deal.repository;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.deal.entiry.User;

/**
 * 用户数据操作
 * @author ASUS
 *
 */
@Mapper
public interface UserMapper {

	@Select("select * from user")
	List<User> findAll();
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	@Select("select * from user where user_id=#{id}")
	User findById(int id);
	
	/**
	 * 用户登录验证
	 * @param username
	 * @param password
	 * @return
	 */
	@Select("select * from user where username=#{username} and password=#{password}")
	User register(User user);
	
	/**
	 * 创建用户
	 * @param user
	 * @return
	 */
	@Insert("insert into user(username,password) values(#{username},#{password})")
	@Options(useGeneratedKeys = true, keyProperty = "user_id")
	boolean create(User user);
	
	/**
	 * 完善个人信息（修改个人信息）
	 * @param user
	 */
	@Update("update user set e_mail=#{e_mail},realname=#{realname},idcardno=#{idcardno},moneypwd=#{moneypwd} where user_id=#{user_id}")
	boolean save(User user);
	
	/**
	 * 钱包充值
	 * @param id
	 * @param rmb
	 * @return
	 */
	@Update("update user set RMB=RMB+#{rmb} where user_id=#{id}")
	boolean topup(int id, BigDecimal rmb);
	


}
