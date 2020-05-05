package com.newer.deal.entiry;

import java.math.BigDecimal;

/**
 * 冻结的法币资产
 * @author ASUS
 *
 */
public class Freeze {
	
	/**
	 * 编号
	 */
	int freeze_id;

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

	public int getFreeze_id() {
		return freeze_id;
	}

	public void setFreeze_id(int freeze_id) {
		this.freeze_id = freeze_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(int currency_id) {
		this.currency_id = currency_id;
	}

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Freeze [freeze_id=" + freeze_id + ", user_id=" + user_id + ", currency_id=" + currency_id + ", count="
				+ count + "]";
	}

	
	
}
