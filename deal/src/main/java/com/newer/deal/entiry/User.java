package com.newer.deal.entiry;

import java.math.BigDecimal;

/**
 * 用户
 * @author ASUS
 *
 */
public class User {

	/**
	 * 用户编号
	 */
	int user_id;
	
	/**
	 * 登录账号（模拟手机号）
	 */
	String username;
	
	/**
	 * 登录密码
	 */
	String password;
	
	/**
	 * 邮箱
	 */
	String e_mail;
	
	/**
	 * 国家（默认CH）
	 */
	String nationality;
	
	/**
	 * 真实姓名
	 */
	String realname;
	
	/**
	 * 身份证号码
	 */
	String idcardno;
	
	/**
	 * RMB 钱包
	 */
	BigDecimal RMB;
	
	/**
	 * 资金密码
	 */
	String moneypwd;
	
	public User() {

	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public BigDecimal getRMB() {
		if (RMB!=null && RMB.compareTo(BigDecimal.valueOf(0)) ==0 ) {
			return new BigDecimal(RMB.stripTrailingZeros().toPlainString());
		}
		return RMB;
	}

	public void setRMB(BigDecimal rMB) {
		RMB = rMB;
	}

	public String getMoneypwd() {
		return moneypwd;
	}

	public void setMoneypwd(String moneypwd) {
		this.moneypwd = moneypwd;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", e_mail=" + e_mail
				+ ", nationality=" + nationality + ", realname=" + realname + ", idcardno=" + idcardno + ", RMB=" + RMB
				+ ", moneypwd=" + moneypwd + "]";
	}

	
}
