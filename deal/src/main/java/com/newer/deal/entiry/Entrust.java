package com.newer.deal.entiry;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 委托（广告）
 * @author ASUS
 *
 */
public class Entrust {

	/**
	 * 编号
	 */
	int entrust_id;
	
	/**
	 * 主题
	 */
	String theme;
	
	/**
	 * 用户
	 */
	User user;
	
	/**
	 * 销售类型
	 * （出售或购买）
	 */
	String saletype;
	
	/**
	 * 货币类型
	 */
	Currency currency;
	
	/**
	 * 最大交易数量
	 * （出售：上架后，冻结用户的一部分资产，下架，则将资产返回；购买不设限制）
	 */
	BigDecimal deal_max;
	
	/**
	 * 固定单价
	 */
	BigDecimal price;
	
	/**
	 * 最小单笔限额
	 */
	BigDecimal price_min;
	
	/**
	 * 最大单笔限额
	 */
	BigDecimal price_max;
	
	/**
	 * 冻结编号
	 */
	int freeze_id;
	
	/**
	 * 委托创建时间
	 */
	Date time;
	
	/**
	 * 状态（1上架/0下架)
	 */
	String status;
	
	public Entrust() {

	}

	public int getEntrust_id() {
		return entrust_id;
	}

	public void setEntrust_id(int entrust_id) {
		this.entrust_id = entrust_id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
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

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getDeal_max() {
		return deal_max;
	}

	public void setDeal_max(BigDecimal deal_max) {
		this.deal_max = deal_max;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice_min() {
		return price_min;
	}

	public void setPrice_min(BigDecimal price_min) {
		this.price_min = price_min;
	}

	public BigDecimal getPrice_max() {
		return price_max;
	}

	public void setPrice_max(BigDecimal price_max) {
		this.price_max = price_max;
	}

	public int getFreeze_id() {
		return freeze_id;
	}

	public void setFreeze_id(int freeze_id) {
		this.freeze_id = freeze_id;
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
		return "Entrust [entrust_id=" + entrust_id + ", theme=" + theme + ", user=" + user + ", saletype=" + saletype
				+ ", currency=" + currency + ", deal_max=" + deal_max + ", price=" + price + ", price_min=" + price_min
				+ ", price_max=" + price_max + ", freeze_id=" + freeze_id + ", time=" + time + ", status=" + status
				+ "]";
	}

	

	
}
