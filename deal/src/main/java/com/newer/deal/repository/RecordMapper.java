package com.newer.deal.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.deal.entiry.Record;

@Mapper
public interface RecordMapper {

	// 查询所有记录
	@Select("select * from record")
	List<Record> findAll();
	
	
	// 根据id修改交易记录的最终状态
	@Update("update record set status='1' where deal_id=#{deal_id}")
	boolean updateStatus(int deal_id);
	
	
	// 新建一条交易记录
	@Insert("insert into record(user_from,user_to,currency_id,amount,quantity,order_id,entrust_id) values(#{user_from},#{user_to},#{currency_id},#{amount},#{quantity},#{order_id},#{entrust_id})")
	@Options(useGeneratedKeys = true, keyProperty = "deal_id")
	boolean createRecord(Record record);
	
	
}
