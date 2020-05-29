package com.newer.deal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.deal.entiry.Order;
import com.newer.deal.exception.NoCurrencyException;
import com.newer.deal.service.TransactionService;

/**
 * 交易
 * @author ASUS
 *
 */
@RestController
@RequestMapping("/trade")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	/**
	 * 订单付款
	 * @param order
	 * @return
	 */
	@PostMapping("/payment")
	public ResponseEntity<?> payment(@RequestBody Order order) {
		
		boolean exchangeRMB = false;
		try {
			exchangeRMB = transactionService.exchangeRMB(order.getOrder_id());
		} catch (NoCurrencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(exchangeRMB, HttpStatus.OK);
	}
	
	/**
	 * 放币
	 * @param order
	 * @return
	 */
	@PostMapping("/deal")
	public ResponseEntity<?> dealLetgo(@RequestBody Order order) {
		
		boolean exchangedeal = false;
		try {
			exchangedeal = transactionService.exchangeDeal(order.getOrder_id());
		} catch (NoCurrencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(exchangedeal, HttpStatus.OK);
	}
	

}
