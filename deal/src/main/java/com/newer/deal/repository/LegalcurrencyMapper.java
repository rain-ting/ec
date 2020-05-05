package com.newer.deal.repository;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.newer.deal.entiry.Currency;
import com.newer.deal.entiry.Legalcurrency;

@Mapper
public interface LegalcurrencyMapper {
	
	/**
	 * 插入数据--初始化用
	 * @param legalcurrency
	 * @return
	 */
	@Insert("insert into legalcurrency(user_id,currency_id,count) values(#{user_id},#{currency.id},10)")
	boolean create(Legalcurrency legalcurrency);

	/**
	 * 根据指定用户，获取所有库存货币
	 * @param user_id
	 * @return
	 */
	@Select("select * from legalcurrency where user_id=#{user_id}")
	@Results(
			@Result(column = "currency_id", jdbcType = JdbcType.INTEGER,
					property = "currency", javaType = Currency.class,
					one = @One(select = "com.newer.deal.repository.CurrencyMapper.load")
					)
			)
	List<Legalcurrency> loadByUserId(int user_id);
	
	/**
	 * 根据指定用户和货币，查看资产
	 * @param user_id
	 * @param currency_id
	 * @return
	 */
	@Select("select * from legalcurrency where user_id=#{user_id} and currency_id=#{currency_id}")
	Legalcurrency findByUserAndCurrencyId(int user_id, int currency_id);
	
	/**
	 * 修改指定用户货币的数量
	 * @param user_id
	 * @param currency_id
	 * @param decimal
	 * @return
	 */
	@Update("update legalcurrency set count=count+#{decimal} where user_id=#{user_id} and currency_id=#{currency_id}")
	boolean updateCount(int user_id, int currency_id, BigDecimal decimal);
	
}
