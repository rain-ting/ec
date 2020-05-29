package com.newer.deal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.deal.entiry.Entrust;
import com.newer.deal.exception.NoCurrencyException;
import com.newer.deal.repository.EntrustMapper;
import com.newer.deal.service.EntrustService;

@RestController
@RequestMapping("/contract")
public class ContractController {

	@Autowired
	EntrustService entrustService;
	
	@Autowired
	EntrustMapper mapper;
	
	@GetMapping("/{id}")
	public Entrust load(@PathVariable int id) {
		return mapper.loadByEntrustId(id);
	}
	
	/**
	 * 查看所有已上架的委托
	 * @return
	 */
	@GetMapping
	public List<Entrust> findAllEntrust() {
		return entrustService.findAll();
	}
	
	/**
	 * 用户查看自己所有委托
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/user")
	public List<Entrust> findAllUser(@PathVariable int id) {
		return entrustService.lookAll(id);
	}
	
	/**
	 * 用户创建委托
	 * @param entrust
	 * @return
	 */
	@PostMapping
	public boolean createEntrust(@RequestBody Entrust entrust) {
		
		return entrustService.found(entrust);
	}
	
	/**
	 * 快速修改 限额、单价、数量
	 * @param entrust_id
	 * @param entrust
	 * @return
	 */
	@PutMapping("/fast/{entrust_id}")
	public boolean updateEntrust(@PathVariable int entrust_id, @RequestBody Entrust entrust) {
		entrust.setEntrust_id(entrust_id);
		return entrustService.updateFast(entrust);
	}
	
	/**
	 * 删除委托 -- 上架中的委托无法删除 - 返回false
	 * @param entrust_id
	 * @return
	 */
	@DeleteMapping("/{entrust_id}")
	public boolean removeEntrust(@PathVariable int entrust_id) {
		
		return entrustService.removeEntrust(entrust_id);
	}
	
	/**
	 * 委托上架
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}/issue")
	public boolean putaway(@PathVariable int id) {
		boolean b = false;
		try {
			b = entrustService.issue(id);
		} catch (NoCurrencyException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 委托下架
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}/sold")
	public boolean soldOut(@PathVariable int id) {
		boolean b = false;
		try {
			b =  entrustService.soldOut(id);
		} catch (NoCurrencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
}
