package com.newer.deal.entiry;

/**
 * 货币
 * 
 * @author ASUS
 *
 */
public class Currency {

	/**
	 * 编号
	 */
	int id;
	
	/**
	 * 货币名称
	 */
	String name;

	public Currency() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Currency [id=" + id + ", name=" + name + "]";
	}

}
