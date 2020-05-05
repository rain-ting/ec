package com.newer.deal.entiry;

import java.math.BigDecimal;

/**
 * 币币库
 * @author ASUS
 *
 */
public class Coin {

	/**
	 * 用户ID
	 */
	int user_id;
	
	/**
	 * 货币ID
	 */
	int currency_id;
	
	/**
	 * 数量
	 */
	BigDecimal count;
	
	public Coin() {
		
	}

	@Override
	public String toString() {
		return "Coin [user_id=" + user_id + ", currency_id=" + currency_id + ", count=" + count + "]";
	}
	
	
}
