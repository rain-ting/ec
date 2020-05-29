package com.newer.deal.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.newer.deal.entiry.Currency;
import com.newer.deal.entiry.Order;
import com.newer.deal.entiry.User;

@Mapper
public interface OrderMapper {

	
	/**
	 * 新增一条订单记录
	 * @param order
	 * @return
	 */
	@Insert("insert into `order`(user_id,saletype,totalprice,unitprice,dealnumber,currency_id,servicecharge,trading_id,entrust_id) values(#{user.user_id},#{saletype},#{totalprice},#{unitprice},#{dealnumber},#{currency.id},#{servicecharge},#{trading.user_id},#{entrust_id})")
	@Options(useGeneratedKeys = true, keyProperty = "order_id")
	boolean createOrder(Order order);
	
	
	/**
	 * 修改订单状态
	 * @param status
	 * @param order_id
	 * @return
	 */
	@Update("update `order` set status=#{status} where order_id=#{order_id}")
	boolean updateStatus(String status, int order_id);
	
	/**
	 * 修改付款状态
	 * @param status
	 * @param order_id
	 * @return
	 */
	@Update("update `order` set payment_status=#{status} where order_id=#{order_id}")
	boolean updatePayment_status(String status, int order_id);
	
	
	/**
	 * 货币是否放行
	 * @param status
	 * @param order_id
	 * @return
	 */
	@Update("update `order` set currency_status=#{status} where order_id=#{order_id}")
	boolean updateCurrency_status(String status, int order_id);
	
	/**
	 * 查询所有订单
	 * @return
	 */
	@Select("select * from `order`")
	@Results(id = "order_mapp", value = {
			@Result(
					column = "user_id", jdbcType = JdbcType.INTEGER,
					property = "user", javaType = User.class,
					one = @One(select = "com.newer.deal.repository.UserMapper.findById")
			),
			@Result(
					column = "currency_id", jdbcType = JdbcType.INTEGER,
					property = "currency", javaType = Currency.class,
					one = @One(select = "com.newer.deal.repository.CurrencyMapper.load")
			),
			@Result(
					column = "trading_id", jdbcType = JdbcType.INTEGER,
					property = "trading", javaType = User.class,
					one = @One(select = "com.newer.deal.repository.UserMapper.findById")
			)
	})
	List<Order> findAll();
	
	
	/**
	 * 根据订单id查询订单
	 * @param order_id
	 * @return
	 */
	@Select("select * from `order` where order_id=#{order_id}")
	@ResultMap(value = "order_mapp")
	Order findById(int order_id);
	
	
	/**
	 * 创建者所属订单
	 * @param user_id
	 * @return
	 */
	@Select("select * from `order` where user_id=#{user_id}")
	@ResultMap(value = "order_mapp")
	List<Order> findByUser_id(int user_id);
	
//	/**
//	 * 根据订单创建所属用户，获取订单 正在进行的
//	 * @param user_id
//	 * @return
//	 */
//	@Select("select * from `order` where user_id=#{user_id} and status=0")
//	List<Order> findUserUnderway(int user_id);
//	
//	/**
//	 * 根据订单创建所属用户，获取订单成功的
//	 * @param user_id
//	 * @return
//	 */
//	@Select("select * from `order` where user_id=#{user_id} and status=1")
//	List<Order> findUserSuccess(int user_id);
//	
//	/**
//	 * 根据订单创建所属用户，获取订单 失败的
//	 * @param user_id
//	 * @return
//	 */
//	@Select("select * from `order` where user_id=#{user_id} and status=2")
//	List<Order> findUserCancelling(int user_id);
	
	/**
	 * 委托者的订单查询
	 * @param trading_id
	 * @return
	 */
	@Select("select * from `order` where trading_id=#{trading_id}")
	@ResultMap(value = "order_mapp")
	List<Order> findByTrading_id(int trading_id);
	
	
//	/**
//	 * 委托用户的订单 正在 进行的
//	 * @param user_id
//	 * @return
//	 */
//	@Select("select * from `order` where trading_id=#{trading_id} and status=0")
//	List<Order> findTradingUnderway(int trading_id);
//	
//	/**
//	 * 委托用户的订单 成功的
//	 * @param user_id
//	 * @return
//	 */
//	@Select("select * from `order` where trading_id=#{trading_id} and status=1")
//	List<Order> findTradingSuccess(int trading_id);
//	
//	/**
//	 * 委托用户的订单 失败的
//	 * @param user_id
//	 * @return
//	 */
//	@Select("select * from `order` where trading_id=#{trading_id} and status=2")
//	List<Order> findTradingCancelling(int trading_id);
	
}
