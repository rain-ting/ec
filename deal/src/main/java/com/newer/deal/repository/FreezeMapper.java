package com.newer.deal.repository;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.deal.entiry.Freeze;

@Mapper
public interface FreezeMapper {
	
	/**
	 * 获取指定货币数据
	 * @param user_id
	 * @param currency_id
	 * @return
	 */
	@Select("select * from freeze where freeze_id=#{freeze_id}")
	Freeze load(int freeze_id);
	
	/**
	 * 修改冻结资产
	 * @param user_id
	 * @param currency_id
	 * @param decimal
	 * @return
	 */
	@Update("update freeze set count=count+#{decimal} where freeze_id=#{id}")
	boolean updateAmount(int id, BigDecimal decimal);

	/**
	 * 插入一条冻结信息
	 * @param freeze
	 */
	@Insert("insert into freeze(user_id,currency_id,count) values(#{user_id},#{currency_id},#{count})")
	@Options(useGeneratedKeys = true, keyProperty = "freeze_id")
	void create(Freeze freeze);
	
	/**
	 * 删除指定冻结记录
	 * @param freeze
	 * @return
	 */
	@Delete("delete from freeze where freeze_id=#{id}")
	boolean removeFreeze(int id);


}
