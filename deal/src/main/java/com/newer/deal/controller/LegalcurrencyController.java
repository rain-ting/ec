package com.newer.deal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.deal.entiry.Freeze;
import com.newer.deal.entiry.Legalcurrency;
import com.newer.deal.repository.FreezeMapper;
import com.newer.deal.repository.LegalcurrencyMapper;

@RestController
@RequestMapping("/legal")
public class LegalcurrencyController {

	@Autowired
	LegalcurrencyMapper mapper;
	
	@Autowired
	FreezeMapper map;
	
	
	/**
	 * 根据登录用户ID获取该用户的所有法币库存
	 * @param id
	 * @return
	 */
	
	@GetMapping("/{id}")
	public List<Legalcurrency> a(@PathVariable int id) {
		return mapper.loadByUserId(id);
	}
	
//	@DeleteMapping("/freeze")
//	public boolean c(@RequestBody Freeze freeze) {
//		boolean b = false;
//		b = map.removeFreeze(freeze);
//		return b;
//		
//	}
	
//	@PostMapping
//	public Legalcurrency b(@RequestBody Legalcurrency legalcurrency) {
//		mapper.create(legalcurrency);
//		return legalcurrency;
//	}
}
