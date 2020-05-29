package com.newer.deal.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;

import com.newer.deal.entiry.Currency;
import com.newer.deal.entiry.Entrust;
import com.newer.deal.entiry.User;

@Mapper
public interface EntrustMapper {
	
	
	/**
	 * 根据委托编号查找委托
	 * @param entrust_id
	 * @return
	 */
	@Select("select * from entrust where entrust_id=#{entrust_id}")
	@Results(id = "uc", value = {
			@Result(
					column = "user_id", jdbcType = JdbcType.INTEGER,
					property = "user", javaType = User.class,
					one = @One(select = "com.newer.deal.repository.UserMapper.findById")
					),
			@Result(column = "currency_id", jdbcType = JdbcType.INTEGER,
					property = "currency", javaType = Currency.class,
					one = @One(select = "com.newer.deal.repository.CurrencyMapper.load")
					)
			})
	Entrust loadByEntrustId(int entrust_id);

	/**
	 * 根据用户编号查看委托
	 * @param user_id
	 * @return
	 */
	@Select("select * from entrust where user_id=#{user_id}")
	@ResultMap(value = {"uc"})
	List<Entrust> loadByUserId(int user_id);
	
	/**
	 * 查看所有已经上架的委托（广告）
	 * @return
	 */
	@Select("select * from entrust where status='1'")
	@ResultMap(value = {"uc"})
	List<Entrust> findByStatusOnIssue();
	
	/**
	 * 创建委托
	 * @param entrust
	 * @return
	 */
	@Insert("insert into entrust(theme,user_id,saletype,currency_id,deal_max,price,price_min,price_max) values(#{theme},#{user.user_id},#{saletype},#{currency.id},#{deal_max},#{price},#{price_min},#{price_max})")
	@Options(useGeneratedKeys = true, keyProperty = "entrust_id")
	boolean create(Entrust entrust);
	
	/**
	 * 根据委托 id ,快速修改 限额 、 单价、 数量
	 * @param entrust
	 * @return
	 */
//	@Update("update entrust set price_min=#{price_min},price_max=#{price_max},price=#{price},deal_max=#{deal_max} where entrust_id=#{entrust_id}")
	@UpdateProvider(type = SQLProvider.class, method = "updateDSQL")
	boolean updateFast(Entrust entrust);
	
	/**
	 * 根据委托 id 删除委托
	 * @param entrust_id
	 * @return
	 */
	@Delete("delete from entrust where entrust_id=#{entrust_id}")
	boolean remove(int entrust_id);
	
	/**
	 * 用户将委托上架
	 * @param entrust_id
	 * @return
	 */
	@Update("update entrust set status='1' where entrust_id=#{entrust_id}")
	boolean updateIssue(int entrust_id);
	
	/**
	 * 用户将委托下架
	 * @param entrust_id
	 * @return
	 */
	@Update("update entrust set status='0' where entrust_id=#{entrust_id}")
	boolean updateSoldOut(int entrust_id);
	
	/**
	 * 修改冻结关联的记录
	 * @param id
	 * @param entrust_id
	 * @return
	 */
	@Update("update entrust set freeze_id=#{freeze_id} where entrust_id=#{entrust_id}")
	boolean updateFreeze(int freeze_id, int entrust_id);
	
	// 动态sql语句生产类
	static class SQLProvider {
		
		// 动态修改  -- 根据委托 id ,快速修改 限额 、 单价、 数量
		public String updateDSQL(Entrust entrust) {
			return new SQL() {{
				
				if (entrust!=null) {
					UPDATE("entrust");
					System.out.println(entrust);
					if (entrust.getPrice_min()!=null&&entrust.getPrice_min().doubleValue()>0) {
			// @Update("update entrust set price_min=#{price_min},price_max=#{price_max},price=#{price},deal_max=#{deal_max} where entrust_id=#{entrust_id}")
						SET("price_min=#{price_min}");
					}
					if (entrust.getPrice_max()!=null&&entrust.getPrice_max().doubleValue()>0) {
						SET("price_max=#{price_max}");
					}
					if (entrust.getPrice()!=null&&entrust.getPrice().doubleValue()>0) {
						SET("price=#{price}");
					}
					if (entrust.getDeal_max()!=null&&entrust.getDeal_max().doubleValue()>0) {
						SET("deal_max=#{deal_max}");
					}
					
					WHERE("entrust_id=#{entrust_id}");
					
				}
				
				
			}} .toString();
		}
	}
}
