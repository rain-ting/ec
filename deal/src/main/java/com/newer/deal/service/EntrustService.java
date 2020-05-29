package com.newer.deal.service;

import java.util.List;

import com.newer.deal.entiry.Entrust;
import com.newer.deal.exception.NoCurrencyException;

/**
 * 委托（广告）模块
 * @author ASUS
 *
 */
public interface EntrustService {
	
	/**
	 * 委托上架
	 * @param entrust_id
	 * @return
	 * @throws NoCurrencyException 
	 */
	boolean issue(int entrust_id) throws NoCurrencyException;
	
	/**
	 * 委托下架
	 * @param entrust_id
	 * @return
	 * @throws NoCurrencyException 
	 */
	boolean soldOut(int entrust_id) throws NoCurrencyException;
	
	/**
	 * 查看所有上架的委托
	 * @return
	 */
	List<Entrust> findAll();
	
	/**
	 * 用户查看自己所有的委托
	 * @param user_id
	 * @return
	 */
	List<Entrust> lookAll(int user_id);

	/**
	 * 用户创建广告
	 * @param entrust
	 * @return
	 */
	boolean found(Entrust entrust);
	
	/**
	 * 快速修改 限额、单价、数量
	 * @param entrust
	 * @return
	 */
	boolean updateFast(Entrust entrust);
	
	/**
	 * 删除委托
	 * @param entrust_id
	 * @return
	 */
	boolean removeEntrust(int entrust_id);
	
	
}
