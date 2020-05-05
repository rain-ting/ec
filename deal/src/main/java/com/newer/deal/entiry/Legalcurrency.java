package com.newer.deal.entiry;

import java.math.BigDecimal;

/**
 * 法币库
 * 
 * @author ASUS
 *
 */
public class Legalcurrency {

	/**
	 * 用户ID
	 */
	int user_id;
	
	/**
	 * 货币ID
	 */
	Currency currency;
	
	/**
	 * 数量
	 */
	BigDecimal count;
	
	public Legalcurrency() {
		
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Legalcurrency [user_id=" + user_id + ", currency=" + currency + ", count=" + count + "]";
	}

	
	
	
}
