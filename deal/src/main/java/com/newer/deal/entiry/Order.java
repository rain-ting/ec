package com.newer.deal.entiry;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 * @author ASUS
 *
 */
public class Order {

	/**
	 * 订单号
	 */
	int order_id;
	
	/**
	 * 定单用户ID
	 */
	User user;
	
	/**
	 * 销售类型（出售或购买）
	 */
	String saletype;
	
	/**
	 * 订单状态
	 * （0 进行中，1 取消，2 完成）
	 */
	String status;
	
	/**
	 * 付款状态
	 */
	String payment_status;
	
	/**
	 * 货币状态（是否放行）
	 */
	String currency_status;
	
	/**
	 * RMB 总价
	 */
	BigDecimal totalprice;
	
	/**
	 * 单价
	 */
	BigDecimal unitprice;
	
	/**
	 * 交易数量
	 */
	BigDecimal dealnumber;
	
	/**
	 * 货币类型
	 */
	Currency currency;
	
	/**
	 * 手续费
	 */
	BigDecimal servicecharge;
	
	/**
	 * 交易的用户对象
	 */
	User trading;
	
	/**
	 * 委托编号
	 */
	int entrust_id;
	
	/**
	 * 创建时间
	 */
	Date time;
	
	public Order() {

	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSaletype() {
		return saletype;
	}

	public void setSaletype(String saletype) {
		this.saletype = saletype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getCurrency_status() {
		return currency_status;
	}

	public void setCurrency_status(String currency_status) {
		this.currency_status = currency_status;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public BigDecimal getDealnumber() {
		return dealnumber;
	}

	public void setDealnumber(BigDecimal dealnumber) {
		this.dealnumber = dealnumber;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getServicecharge() {
		return servicecharge;
	}

	public void setServicecharge(BigDecimal servicecharge) {
		this.servicecharge = servicecharge;
	}

	public User getTrading() {
		return trading;
	}

	public void setTrading(User trading) {
		this.trading = trading;
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

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", user=" + user + ", saletype=" + saletype + ", status=" + status
				+ ", payment_status=" + payment_status + ", currency_status=" + currency_status + ", totalprice="
				+ totalprice + ", unitprice=" + unitprice + ", dealnumber=" + dealnumber + ", currency=" + currency
				+ ", servicecharge=" + servicecharge + ", trading=" + trading + ", entrust_id=" + entrust_id + ", time="
				+ time + "]";
	}

	
	
}
