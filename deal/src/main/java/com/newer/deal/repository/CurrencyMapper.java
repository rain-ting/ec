package com.newer.deal.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.newer.deal.entiry.Currency;

@Mapper
public interface CurrencyMapper {

	/**
	 * 根据ID获取指定货币
	 * @param id
	 * @return
	 */
	@Select("select * from currency where id=#{id}")
	Currency load(int id);
}
