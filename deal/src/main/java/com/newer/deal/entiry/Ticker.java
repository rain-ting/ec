package com.newer.deal.entiry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Ticker {

	@Id
	String id;
	String status;
	String ch;
	Long ts;
	Tick tick;
	
	public Ticker() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	public Tick getTick() {
		return tick;
	}

	public void setTick(Tick tick) {
		this.tick = tick;
	}

	@Override
	public String toString() {
		return "Ticker [id=" + id + ", status=" + status + ", ch=" + ch + ", ts=" + ts + ", tick=" + tick + "]";
	}
	
}
