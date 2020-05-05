package com.newer.deal.entiry;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易记录
 * @author ASUS
 *
 */
public class Record {

	/**
	 * 流水号
	 */
	int deal_id;
	
	/**
	 * 转出用户
	 */
	int user_from;
	
	/**
	 * 转入用户
	 */
	int user_to;
	
	/**
	 * 货币类型
	 */
	int currency_id;
	
	/**
	 * 总交易金额
	 */
	BigDecimal amount;
	
	/**
	 * 货币数量
	 */
	BigDecimal quantity;
	
	/**
	 * 订单ID
	 */
	int order_id;
	
	/**
	 * 委托ID
	 */
	int entrust_id;
	
	
	/**
	 * 交易时间
	 */
	Date time;
	
	/**
	 * 最终状态
	 */
	String status;
	
	public Record() {

	}

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}

	public int getUser_from() {
		return user_from;
	}

	public void setUser_from(int user_from) {
		this.user_from = user_from;
	}

	public int getUser_to() {
		return user_to;
	}

	public void setUser_to(int user_to) {
		this.user_to = user_to;
	}

	public int getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(int currency_id) {
		this.currency_id = currency_id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getEntrust_id() {
		return entrust_id;
	}

	public void setEntrust_id(int entrust_id) {
		this.entrust_id = entrust_id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Record [deal_id=" + deal_id + ", user_from=" + user_from + ", user_to=" + user_to + ", currency_id="
				+ currency_id + ", amount=" + amount + ", quantity=" + quantity + ", order_id=" + order_id
				+ ", entrust_id=" + entrust_id + ", time=" + time + ", status=" + status + "]";
	}

	
	
	
}
