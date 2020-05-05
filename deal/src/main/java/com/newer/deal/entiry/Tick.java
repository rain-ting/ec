package com.newer.deal.entiry;

import java.util.Arrays;

public class Tick {

	Long id;
	Double amount;
	Integer count;
	Double open;
	Double close;
	Double low;
	Double high;
	Double vol;
	Long version;
	Double[] bid;
	Double[] ask;
	
	public Tick() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getVol() {
		return vol;
	}

	public void setVol(Double vol) {
		this.vol = vol;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Double[] getBid() {
		return bid;
	}

	public void setBid(Double[] bid) {
		this.bid = bid;
	}

	public Double[] getAsk() {
		return ask;
	}

	public void setAsk(Double[] ask) {
		this.ask = ask;
	}

	@Override
	public String toString() {
		return "Tick [id=" + id + ", amount=" + amount + ", count=" + count + ", open=" + open + ", close=" + close
				+ ", low=" + low + ", high=" + high + ", vol=" + vol + ", version=" + version + ", bid="
				+ Arrays.toString(bid) + ", ask=" + Arrays.toString(ask) + "]";
	}
	
}
