package com.newer.deal.service;

import com.newer.deal.entiry.Record;
import com.newer.deal.exception.NoCurrencyException;

/**
 * 交易
 * @author ASUS
 *
 */
public interface TransactionService {

	/**
	 * RBM交易   // 这里不添加进记录  RMB一般是脱离平台来交易的
	 * @return
	 * @throws NoCurrencyException 
	 */
	boolean exchangeRMB(int order_id) throws NoCurrencyException;
	
	/**
	 * 法币交易
	 * @param order_id
	 * @return
	 * @throws NoCurrencyException 
	 */
	boolean exchangeDeal(int order_id) throws NoCurrencyException;
	
	
	/**
	 * 创建交易记录
	 * @param record
	 * @return
	 */
	boolean createExchange(Record record);
	
}
